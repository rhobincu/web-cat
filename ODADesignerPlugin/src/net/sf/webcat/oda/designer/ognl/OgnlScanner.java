/*==========================================================================*\
 |  $Id: OgnlScanner.java,v 1.2 2008/04/13 22:04:52 aallowat Exp $
 |*-------------------------------------------------------------------------*|
 |  Copyright (C) 2006-2008 Virginia Tech
 |
 |  This file is part of Web-CAT.
 |
 |  Web-CAT is free software; you can redistribute it and/or modify
 |  it under the terms of the GNU Affero General Public License as published
 |  by the Free Software Foundation; either version 3 of the License, or
 |  (at your option) any later version.
 |
 |  Web-CAT is distributed in the hope that it will be useful,
 |  but WITHOUT ANY WARRANTY; without even the implied warranty of
 |  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 |  GNU General Public License for more details.
 |
 |  You should have received a copy of the GNU Affero General Public License
 |  along with Web-CAT; if not, see <http://www.gnu.org/licenses/>.
\*==========================================================================*/

package net.sf.webcat.oda.designer.ognl;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IWhitespaceDetector;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.WhitespaceRule;

//------------------------------------------------------------------------
/**
 * TODO: real description
 *
 * @author Tony Allevato (Virginia Tech Computer Science)
 * @version $Id: OgnlScanner.java,v 1.2 2008/04/13 22:04:52 aallowat Exp $
 */
public class OgnlScanner extends RuleBasedScanner
{
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    public OgnlScanner()
    {
        List<IRule> rules = new ArrayList<IRule>();

        // Add generic whitespace rule.
        rules.add(new WhitespaceRule(new IWhitespaceDetector()
        {
            public boolean isWhitespace(char c)
            {
                return Character.isWhitespace(c);
            }
        }));

        IRule[] result = new IRule[rules.size()];
        rules.toArray(result);
        setRules(result);
    }
}