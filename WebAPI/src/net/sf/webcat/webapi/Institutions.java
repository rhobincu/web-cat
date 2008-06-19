/*==========================================================================*\
 |  $Id: Institutions.java,v 1.1 2008/06/19 01:22:17 stedwar2 Exp $
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

package net.sf.webcat.webapi;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSArray;

import net.sf.webcat.core.AuthenticationDomain;

//-------------------------------------------------------------------------
/**
 * XML Response page for webapi/institutions requests.
 *
 * @author Stephen Edwards
 * @version $Id: Institutions.java,v 1.1 2008/06/19 01:22:17 stedwar2 Exp $
 */
public class Institutions
    extends XmlResponsePage
{
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new page.
     *
     * @param context The page's context
     */
    public Institutions(WOContext context)
    {
        super(context);
    }


    //~ KVC Properties ........................................................

    public AuthenticationDomain          anInstitution;
    public NSArray<AuthenticationDomain> institutions;


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    public void appendToResponse(WOResponse response, WOContext context)
    {
        institutions = AuthenticationDomain.authDomains();
        super.appendToResponse(response, context);
    }
}
