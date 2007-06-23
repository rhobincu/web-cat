package ognl;

import com.webobjects.eocontrol.EOAndQualifier;
import com.webobjects.eocontrol.EOKeyComparisonQualifier;
import com.webobjects.eocontrol.EOKeyValueQualifier;
import com.webobjects.eocontrol.EONotQualifier;
import com.webobjects.eocontrol.EOOrQualifier;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.eocontrol.EOQualifierVariable;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSKeyValueCoding;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSMutableDictionary;
import com.webobjects.foundation.NSSelector;

/**
 * The fact that this class is in this package is a major hack. We need access
 * to the ognl.AST* classes, but they only have package visibility. Dumping it
 * in the ognl package is easier than using reflection everywhere.
 * 
 * @author aallowat
 *
 */
public class OgnlQualifierUtils
{
	public static EOQualifier qualifierFromOgnlExpression(String expression)
	throws OgnlException
	{
		Node node = (Node)Ognl.parseExpression(expression);
		return qualifierFromOgnlAST(node);
	}
	
	public static void printOgnlAST(Node node, int depth)
	{
		for(int j = 0; j < depth; j++)
			System.out.print("   ");
		System.out.print(node.getClass().getSimpleName());
		System.out.println(":" + node.toString());

		for(int i = 0; i < node.jjtGetNumChildren(); i++)
		{
			Node child = node.jjtGetChild(i);
			printOgnlAST(child, depth + 1);
		}
	}

	private static EOQualifier qualifierFromOgnlAST(Node node)
	{
		NodeHandler handler =
			(NodeHandler)astHandlers.objectForKey(node.getClass());
		
		if(handler != null)
		{
			return handler.handle(node);
		}
		else
		{
			generateException("Expression is not a valid qualifier");
			return null;
		}
	}

	private static EOQualifier processRelational(NSSelector selector,
			Node lhs, Node rhs)
	{
		String lhsString = null, rhsString = null;
		Object rhsObject = null;
		
		if(lhs instanceof ASTProperty)
		{
			lhsString = nameOfPropertyOrVariable(lhs);
		}
		else if(lhs instanceof ASTChain)
		{
			lhsString = stringForChain((ASTChain)lhs, true,
					lhs.jjtGetNumChildren());
		}
		else
		{
			generateException("Only properties are allowed on the " +
					"left-hand side of an expression in a qualifier");
		}
		
		if(rhs instanceof ASTProperty || rhs instanceof ASTVarRef)
		{
			rhsString = nameOfPropertyOrVariable(rhs);
			
			if(rhsString.equals("nil"))
			{
				return new EOKeyValueQualifier(lhsString, selector,
						NSKeyValueCoding.NullValue);
			}
			else if(rhsString.startsWith("#"))
			{
				String varName = rhsString.substring(1);
				
				return new EOKeyValueQualifier(lhsString, selector,
						new EOQualifierVariable(varName));
			}
			else
			{
				return new EOKeyComparisonQualifier(lhsString, selector,
						rhsString);
			}
		}
		else if(rhs instanceof ASTChain)
		{
			rhsString = stringForChain((ASTChain)rhs, false,
					rhs.jjtGetNumChildren());
			
			if(rhsString.startsWith("$"))
			{
				String varName = rhsString.substring(1);
				
				return new EOKeyValueQualifier(lhsString, selector,
						new EOQualifierVariable(varName));				
			}
			else
			{
				return new EOKeyComparisonQualifier(lhsString, selector,
						rhsString);
			}
		}
		else if(rhs instanceof ASTConst)
		{
			rhsObject = ((ASTConst)rhs).getValue();
			return new EOKeyValueQualifier(lhsString, selector, rhsObject);
		}
		else
		{
			generateException("Only properties, variable references, and " +
					"constant values are allowed on the right-hand side of " +
					"an expression in a qualifier");
			return null;
		}
	}

	private static EOQualifier processChain(ASTChain chain)
	{
		// A chain is a potential standalone method call, so it could be
		// isLike or isLikeNoCase.
		
		int methodIndex = 0;
		while(methodIndex < chain.jjtGetNumChildren() &&
				!(chain.jjtGetChild(methodIndex) instanceof ASTMethod))
		{
			methodIndex++;
		}
		
		if(methodIndex == chain.jjtGetNumChildren())
		{
			generateException("A standalone keypath cannot be used in a " +
					"qualifier expression");
			return null;
		}

		ASTMethod method = (ASTMethod)chain.jjtGetChild(methodIndex);
		String methodName = method.getMethodName();

		if(!methodName.equals("isLike") && !methodName.equals("isLikeNoCase"))
		{
			generateException("The only methods supported in a qualifier " +
					"expression are 'isLike' and 'isLikeNoCase'");
			return null;
		}
		
		NSSelector selector = methodName.equals("isLike") ?
				EOQualifier.QualifierOperatorLike :
				EOQualifier.QualifierOperatorCaseInsensitiveLike;

		if(methodIndex == 0)
		{
			generateException("'" + methodName + "' needs a property " +
					"reference preceding it");
			return null;
		}
		
		String lhsString = stringForChain(chain, true, methodIndex);

		if(method.jjtGetNumChildren() != 1)
		{
			generateException("'" + methodName + "' takes 1 argument");
			return null;
		}

		Node rhs = method.jjtGetChild(0);

		if(rhs instanceof ASTProperty || rhs instanceof ASTVarRef)
		{
			String rhsString = nameOfPropertyOrVariable(rhs);
			
			if(rhsString.equals("nil"))
			{
				return new EOKeyValueQualifier(lhsString, selector,
						NSKeyValueCoding.NullValue);
			}
			else if(rhsString.startsWith("$"))
			{
				String varName = rhsString.substring(1);
				
				return new EOKeyValueQualifier(lhsString, selector,
						new EOQualifierVariable(varName));
			}
			else
			{
				return new EOKeyComparisonQualifier(lhsString, selector,
						rhsString);
			}
		}
		else if(rhs instanceof ASTChain)
		{
			String rhsString = stringForChain((ASTChain)rhs, false,
					rhs.jjtGetNumChildren());
			return new EOKeyComparisonQualifier(lhsString, selector, rhsString);
		}
		else if(rhs instanceof ASTConst)
		{
			Object rhsObject = ((ASTConst)rhs).getValue().toString();
			return new EOKeyValueQualifier(lhsString, selector, rhsObject);
		}
		else
		{
			generateException("Only properties, variable references, and " +
					"constant values are allowed on the right-hand side of " +
					"an expression in a qualifier");
			return null;
		}
	}

	private static String stringForChain(ASTChain chain,
			boolean allowPropertiesOnly, int end)
	{
		StringBuilder str = new StringBuilder();

		for(int i = 0; i < end; i++)
		{
			Node node = chain.jjtGetChild(i);
			
			if(i > 0)
				str.append('.');

			if(allowPropertiesOnly && !(node instanceof ASTProperty))
				generateException("Only properties are allowed on the " +
						"left-hand side of an expression in a qualifier");

			str.append(nameOfPropertyOrVariable(node));
		}

		return str.toString();
	}
	
	private static String nameOfPropertyOrVariable(Node node)
	{
		if(node instanceof ASTProperty)
		{
			ASTProperty property = (ASTProperty)node;
			
			if(!property.isIndexedAccess())
			{
				return property.toString();
			}
			else
			{
				generateException("Indexed properties cannot be used in an " +
						"entity qualifier");
				return null;
			}
		}
		else // if(node instanceof ASTVarRef)
		{
			// First character of the ASTVarRef is always '#'
			return "$" + node.toString().substring(1);
		}
	}
	
    public static String computeDependenciesFromOgnlAST(Node node,
    		NSMutableArray dependentBindings)
    {
    	int startChild = 0;

    	if(node instanceof ASTChain)
    	{
    		Node firstChild = node.jjtGetChild(0);
    		Node secondChild = node.jjtGetChild(1);

    		String firstName = null;
    		if(firstChild instanceof ASTProperty)
    		{
    			ASTProperty property = (ASTProperty)firstChild;
    			if(!property.isIndexedAccess())
    			{
    				firstName = property.toString();
    			}
    		}
    		else if(firstChild instanceof ASTVarRef)
    		{
    			firstName = firstChild.toString().substring(1);
    		}
    		
    		if("selected".equals(firstName))
    		{
    			if(secondChild instanceof ASTProperty)
    			{
    				ASTProperty property = (ASTProperty)secondChild;
    				
    				if(!property.isIndexedAccess())
    				{
    					dependentBindings.addObject(property.toString());
    					startChild = 2;
    				}
    				else
    				{
    	    			return "The 'selected' property/variable may only be " +
	    				"followed by a non-indexed property name: ";
    				}
    			}
    			else
    			{
	    			return "The 'selected' property/variable may only be " +
	    				"followed by a non-indexed property name: ";
    			}
    		}
    	}
    	
    	for(int i = startChild; i < node.jjtGetNumChildren(); i++)
    	{
    		String msg = computeDependenciesFromOgnlAST(
    				node.jjtGetChild(i), dependentBindings);
    		
    		if(msg != null)
    			return msg;
    	}
    	
    	return null;
    }

	private static void generateException(String msg)
	{
		throw new IllegalArgumentException(msg);
	}

	private static NSMutableDictionary astHandlers;
	
	private interface NodeHandler
	{
		EOQualifier handle(Node node);
	}

	private static NodeHandler andHandler = new NodeHandler() {
		public EOQualifier handle(Node node)
		{
			NSMutableArray children = new NSMutableArray();
			for(int i = 0; i < node.jjtGetNumChildren(); i++)
				children.addObject(qualifierFromOgnlAST(node.jjtGetChild(i)));
			
			return new EOAndQualifier(children);
		}
	};

	private static NodeHandler orHandler = new NodeHandler() {
		public EOQualifier handle(Node node)
		{
			NSMutableArray children = new NSMutableArray();
			for(int i = 0; i < node.jjtGetNumChildren(); i++)
				children.addObject(qualifierFromOgnlAST(node.jjtGetChild(i)));
			
			return new EOOrQualifier(children);
		}
	};

	private static NodeHandler notHandler = new NodeHandler() {
		public EOQualifier handle(Node node)
		{
			return new EONotQualifier(
					qualifierFromOgnlAST(node.jjtGetChild(0)));
		}
	};

	private static NodeHandler eqHandler = new NodeHandler() {
		public EOQualifier handle(Node node)
		{
			return processRelational(EOQualifier.QualifierOperatorEqual,
					node.jjtGetChild(0), node.jjtGetChild(1));
		}
	};

	private static NodeHandler greaterHandler = new NodeHandler() {
		public EOQualifier handle(Node node)
		{
			return processRelational(EOQualifier.QualifierOperatorGreaterThan,
					node.jjtGetChild(0), node.jjtGetChild(1));
		}
	};

	private static NodeHandler greaterEqHandler = new NodeHandler() {
		public EOQualifier handle(Node node)
		{
			return processRelational(
					EOQualifier.QualifierOperatorGreaterThanOrEqualTo,
					node.jjtGetChild(0), node.jjtGetChild(1));
		}
	};

	private static NodeHandler inHandler = new NodeHandler() {
		public EOQualifier handle(Node node)
		{
			return processRelational(EOQualifier.QualifierOperatorContains,
					node.jjtGetChild(1), node.jjtGetChild(0));
		}
	};
	
	private static NodeHandler lessHandler = new NodeHandler() {
		public EOQualifier handle(Node node)
		{
			return processRelational(EOQualifier.QualifierOperatorLessThan,
					node.jjtGetChild(0), node.jjtGetChild(1));
		}
	};

	private static NodeHandler lessEqHandler = new NodeHandler() {
		public EOQualifier handle(Node node)
		{
			return processRelational(
					EOQualifier.QualifierOperatorLessThanOrEqualTo,
					node.jjtGetChild(0), node.jjtGetChild(1));
		}
	};

	private static NodeHandler notEqHandler = new NodeHandler() {
		public EOQualifier handle(Node node)
		{
			return processRelational(EOQualifier.QualifierOperatorNotEqual,
					node.jjtGetChild(0), node.jjtGetChild(1));
		}
	};
	
	private static NodeHandler notInHandler = new NodeHandler() {
		public EOQualifier handle(Node node)
		{
			return new EONotQualifier(
					processRelational(EOQualifier.QualifierOperatorContains,
					node.jjtGetChild(1), node.jjtGetChild(0)));
		}
	};

	private static NodeHandler chainHandler = new NodeHandler() {
		public EOQualifier handle(Node node)
		{
			return processChain((ASTChain)node);
		}
	};

	static
	{
		astHandlers = new NSMutableDictionary();

		astHandlers.setObjectForKey(andHandler, ASTAnd.class);
		astHandlers.setObjectForKey(orHandler, ASTOr.class);
		astHandlers.setObjectForKey(notHandler, ASTNot.class);
		astHandlers.setObjectForKey(eqHandler, ASTEq.class);
		astHandlers.setObjectForKey(greaterHandler, ASTGreater.class);
		astHandlers.setObjectForKey(greaterEqHandler, ASTGreaterEq.class);
		astHandlers.setObjectForKey(inHandler, ASTIn.class);
		astHandlers.setObjectForKey(lessHandler, ASTLess.class);
		astHandlers.setObjectForKey(lessEqHandler, ASTLessEq.class);
		astHandlers.setObjectForKey(notEqHandler, ASTNotEq.class);
		astHandlers.setObjectForKey(notInHandler, ASTNotIn.class);
		astHandlers.setObjectForKey(chainHandler, ASTChain.class);
	}
}