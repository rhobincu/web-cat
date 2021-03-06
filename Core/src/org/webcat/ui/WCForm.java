/*==========================================================================*\
 |  $Id: WCForm.java,v 1.1 2010/05/11 14:51:58 aallowat Exp $
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

package org.webcat.ui;

import java.util.Stack;
import org.apache.log4j.Logger;
import com.webobjects.appserver.WOAssociation;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOElement;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSMutableDictionary;
import er.extensions.appserver.ERXWOContext;
import er.extensions.components._private.ERXWOForm;

//--------------------------------------------------------------------------
/**
 * <p>
 * Should be used in place of WOForm or ERXWOForm.  Required when using remote
 * validation.
 * </p><p>
 * Provides a static helper method to generate the call to a JavaScript
 * function that will submit this form, and permits nesting of forms. This is
 * necessary when generating a form inside, for example, a dialog box, in which
 * case the form element isn't _really_ nested because the dialog is moved
 * outside of its container, but WebObjects thinks that it is nested due to the
 * way component content is generated by linearly processing the template.
 * </p>
 *
 * @author Tony Allevato
 * @version $Id: WCForm.java,v 1.1 2010/05/11 14:51:58 aallowat Exp $
 */
public class WCForm extends ERXWOForm
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    public WCForm(String name,
            NSDictionary<String, WOAssociation> associations,
            WOElement element)
    {
        super(name, associations, element);
    }


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    public static String scriptToPerformFullSubmit(WOContext context,
            String fieldName)
    {
        String formName = formName(context, null);
        if (formName == null)
        {
            log.warn("An element that uses a faked full form submit must be "
                    + "contained within a form.");
        }

        return "webcat.fullSubmit('" + formName + "', '" + fieldName + "');";
    }


    // ----------------------------------------------------------
    /**
     * Gets a Javascript expression that returns the form element for the form
     * with the specified name.
     *
     * @param formName the name of the form
     * @return a Javascript expression that returns the form element
     */
    public static String formElementByName(String formName)
    {
        return "dojo.query('form[name=" + formName + "]')[0]";
    }


    // ----------------------------------------------------------
    @Override
    public void appendAttributesToResponse(WOResponse response,
            WOContext context)
    {
        super.appendAttributesToResponse(response, context);

        response._appendTagAttributeAndValue("dojoType", "webcat.Form",
                false);
        response._appendTagAttributeAndValue("jsId",
                "form_" + _formName(context), false);
    }


    // ----------------------------------------------------------
    public void appendChildrenToResponse(WOResponse response, WOContext context)
    {
        String varName = "validationResults_" + _formName(context);

        response.appendContentString("<script type=\"text/javascript\">\n");
        response.appendContentString("dojo.addOnLoad(function() {\n");
        response.appendContentString("    " + varName + " = new webcat.ValidationResults();\n");
        response.appendContentString("});\n");
        response.appendContentString("</script>\n");

        super.appendChildrenToResponse(response, context);
    }


    // ----------------------------------------------------------
    protected boolean _shouldAppendFormTags(WOContext context, boolean wasInForm)
    {
        boolean shouldAppendFormTags = !_disabled(context);
        return shouldAppendFormTags;
    }


    // ----------------------------------------------------------
    @SuppressWarnings("unchecked")
    public static NSMutableDictionary<String, WOAssociation> validators()
    {
        return (NSMutableDictionary<String, WOAssociation>) ERXWOContext.
            contextDictionary().objectForKey("formValidators");
    }


    // ----------------------------------------------------------
    protected String _setFormName(WOContext context, boolean wasInForm)
    {
        String previousFormName = super.formName(context, null);

        if (_shouldAppendFormTags(context, wasInForm))
        {
            String formName = _formName(context);

            FormInfo formInfo = new FormInfo();
            formInfo.formName = formName;

            if (formName != null)
            {
                formStack.push(formInfo);
                _updateContextFromStack();
            }
        }

        return previousFormName;
    }


    // ----------------------------------------------------------
    protected void _clearFormName(WOContext context, boolean wasInForm)
    {
        if (_shouldAppendFormTags(context, wasInForm))
        {
            String formName = _formName(context);

            if (formName != null)
            {
                if (!formStack.empty())
                {
                    formStack.pop();
                }

                _updateContextFromStack();
            }
        }
    }


    // ----------------------------------------------------------
    protected void _updateContextFromStack()
    {
        FormInfo info;

        if (formStack.empty())
        {
            info = new FormInfo();
        }
        else
        {
            info = formStack.peek();
        }

        if (info.formName != null)
        {
            ERXWOContext.contextDictionary().setObjectForKey(
                    info.formName, "formName");
        }
        else
        {
            ERXWOContext.contextDictionary().removeObjectForKey("formName");
        }

        if (info.validators != null)
        {
            ERXWOContext.contextDictionary().setObjectForKey(
                    info.validators, "formValidators");
        }
        else
        {
            ERXWOContext.contextDictionary().removeObjectForKey(
                    "formValidators");
        }
    }


    // ----------------------------------------------------------
    public static void addValidatorToCurrentForm(String elementID,
            WOAssociation validator)
    {
        NSMutableDictionary<String, WOAssociation> validators = validators();

        if (validators != null)
        {
            validators.setObjectForKey(validator, elementID);
        }
    }


    // ----------------------------------------------------------
    public static void appendValidatorScriptToResponse(WOResponse response,
            WOContext context)
    {
        String varName = "validationResults_" + formName(context, null);
        String elemID = context.elementID();

        response.appendContentString("<script type=\"dojo/method\" event=\"validator\" args=\"value,constraints\">\n");
        response.appendContentString("return " + varName + ".validateWidget(this, '" + elemID + "', value, constraints);");
        response.appendContentString("</script>\n");
    }


    //~ Private classes .......................................................

    private class FormInfo
    {
        public String formName;
        public NSMutableDictionary<String, WOAssociation> validators =
            new NSMutableDictionary<String, WOAssociation>();
    }


    //~ Static/instance variables .............................................

    private static Stack<FormInfo> formStack = new Stack<FormInfo>();

    private static Logger log = Logger.getLogger(WCForm.class);
}
