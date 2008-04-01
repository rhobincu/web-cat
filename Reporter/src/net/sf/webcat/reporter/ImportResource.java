/*==========================================================================*\
 |  $Id: ImportResource.java,v 1.2 2008/04/01 02:58:39 stedwar2 Exp $
 |*-------------------------------------------------------------------------*|
 |  Copyright (C) 2006 Virginia Tech
 |
 |  This file is part of Web-CAT.
 |
 |  Web-CAT is free software; you can redistribute it and/or modify
 |  it under the terms of the GNU General Public License as published by
 |  the Free Software Foundation; either version 2 of the License, or
 |  (at your option) any later version.
 |
 |  Web-CAT is distributed in the hope that it will be useful,
 |  but WITHOUT ANY WARRANTY; without even the implied warranty of
 |  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 |  GNU General Public License for more details.
 |
 |  You should have received a copy of the GNU General Public License
 |  along with Web-CAT; if not, write to the Free Software
 |  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA
 |
 |  Project manager: Stephen Edwards <edwards@cs.vt.edu>
 |  Virginia Tech CS Dept, 660 McBryde Hall (0106), Blacksburg, VA 24061 USA
\*==========================================================================*/

package net.sf.webcat.reporter;

import com.webobjects.appserver.WOAssociation;
import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WODynamicElement;
import com.webobjects.appserver.WOElement;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSBundle;
import com.webobjects.foundation.NSDictionary;
import er.ajax.AjaxUtils;
import net.sf.webcat.core.WCResourceManager;

//-------------------------------------------------------------------------
/**
 * A dynamic element ...
 *
 * @author  Anthony Allevato
 * @version $Id: ImportResource.java,v 1.2 2008/04/01 02:58:39 stedwar2 Exp $
 */
public class ImportResource
    extends WODynamicElement
{
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Create an element.
     * @param aName        the component's name
     * @param associations the bindings for this instance of the component
     * @param template
     */
	public ImportResource(
        String aName, NSDictionary associations, WOElement template)
	{
		super(aName, associations, template);

		aType = (WOAssociation)associations.objectForKey("type");
		aFramework = (WOAssociation)associations.objectForKey("framework");
		aFilename = (WOAssociation)associations.objectForKey("filename");
	}


    //~ Methods ...............................................................

    // ----------------------------------------------------------
	public void appendToResponse(WOResponse response, WOContext context)
	{
		WOComponent component = context.component();

		String type = aType != null
            ? (String)aType.valueInComponent(component)
            : null;
		String framework = aFramework != null
            ? (String)aFramework.valueInComponent(component)
            : null;
		String filename = aFilename != null
            ? (String)aFilename.valueInComponent(component)
            : null;

		// If no framework is specified, get the one from the calling
		// component.
		if (framework == null)
		{
			NSBundle bundle = NSBundle.bundleForClass(
				context.component().getClass());
			framework = bundle.name();
		}

		if (type.equalsIgnoreCase("script"))
		{
			AjaxUtils.addScriptResourceInHead(
                context, response, framework, filename);
		}
		else if (type.equalsIgnoreCase("stylesheet"))
		{
			AjaxUtils.addStylesheetResourceInHead(
                context, response, framework, filename);
		}
	}


    //~ Instance/static variables .............................................

    private WOAssociation aType;
    private WOAssociation aFramework;
    private WOAssociation aFilename;
}
