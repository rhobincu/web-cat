/*==========================================================================*\
 |  $Id: PluralString.java,v 1.1 2011/04/19 16:47:15 aallowat Exp $
 |*-------------------------------------------------------------------------*|
 |  Copyright (C) 2011 Virginia Tech
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

package org.webcat.core;

import com.webobjects.appserver.WOAssociation;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOElement;
import com.webobjects.appserver.WOResponse;
import com.webobjects.appserver._private.WODynamicElementCreationException;
import com.webobjects.appserver._private.WOHTMLDynamicElement;
import com.webobjects.foundation.NSDictionary;

//-------------------------------------------------------------------------
/**
 * <p>
 * Simplifies the process of writing descriptions of countable objects in a
 * component. For example, instead of using conditional code to write
 * "1 thing" vs. "2 things", you can use this element:
 * <pre>
 *     &lt;wo:PluralString value="$someValue" object="thing"/&gt;
 * </pre>
 * </p><p>
 * The default behavior of the element is to simply add "s" to make the plural.
 * If the plural needs to be constructed differently, use the {@code plural}
 * binding:
 * <pre>
 *     &lt;wo:PluralString value="$someValue" object="child" plural="children"/&gt;
 * </pre>
 * </p><p>
 * FIXME This element is pretty English-centric in the way it constructs its
 * strings. When it comes time to localize Web-CAT, we should probably move
 * toward {@code ERXPluralString} and its use of {@code ERXLocalizer} instead.
 * </p>
 *
 * <h2>Bindings</h2>
 * <dl>
 * <dt>value</dt>
 * <dd>The value to use to determine whether the singular or plural form should
 * be used. This value must be an {@code int}.</dd>
 * <dt>object</dt>
 * <dd>The singular form of the name of the object being counted.</dd>
 * <dt>plural (optional)</dt>
 * <dd>The plural form of the name of the object being counted. If omitted, the
 * plural form is generated by adding "s" to the string bound to
 * {@code object}.</dd>
 * </dl>
 *
 * @author  Tony Allevato
 * @author  Last changed by $Author: aallowat $
 * @version $Revision: 1.1 $, $Date: 2011/04/19 16:47:15 $
 */
public class PluralString extends WOHTMLDynamicElement
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    public PluralString(String aName,
            NSDictionary<String, WOAssociation> someAssociations,
            WOElement template)
    {
        super(aName, someAssociations, template);

        _value = _associations.removeObjectForKey("value");
        _object = _associations.removeObjectForKey("object");
        _plural = _associations.removeObjectForKey("plural");

        if (_value == null || _object == null)
        {
            throw new WODynamicElementCreationException("<"
                    + getClass().getName()
                    + "> Missing required attribute: 'value' or 'object'");
        }
    }


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    protected int _valueInContext(WOContext context)
    {
        Object valueObject = _value.valueInComponent(context.component());

        if (valueObject != null)
        {
            return (Integer) valueObject;
        }
        else
        {
            return 0;
        }
    }


    // ----------------------------------------------------------
    protected String _objectInContext(WOContext context)
    {
        return _object.valueInComponent(context.component()).toString();
    }


    // ----------------------------------------------------------
    protected String _pluralInContext(WOContext context)
    {
        if (_plural == null)
        {
            return _objectInContext(context) + "s";
        }
        else
        {
            Object plural = _plural.valueInComponent(context.component());
            return plural.toString();
        }
    }


    // ----------------------------------------------------------
    public void appendToResponse(WOResponse response, WOContext context)
    {
        int value = _valueInContext(context);

        String objectToPrint = (value == 1) ?
                _objectInContext(context) : _pluralInContext(context);

        response.appendContentString(Integer.toString(value));
        response.appendContentCharacter(' ');
        response.appendContentString(objectToPrint);
    }


    //~ Static/instance variables .............................................

    private WOAssociation _value;
    private WOAssociation _object;
    private WOAssociation _plural;
}
