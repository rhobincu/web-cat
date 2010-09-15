/*==========================================================================*\
 |  $Id: AdminStatusPage.java,v 1.1 2010/05/11 14:51:43 aallowat Exp $
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

package org.webcat.admin;

import org.webcat.core.*;
import com.webobjects.appserver.*;
import com.webobjects.directtoweb.*;
import er.extensions.appserver.ERXApplication;

//-------------------------------------------------------------------------
/**
* Represents a standard Web-CAT page that has not yet been implemented
* (is "to be defined").
*
*  @author Stephen Edwards
*  @version $Id: AdminStatusPage.java,v 1.1 2010/05/11 14:51:43 aallowat Exp $
*/
public class AdminStatusPage
    extends WCComponent
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new AdminStatusPage object.
     *
     * @param context The context to use
     */
    public AdminStatusPage( WOContext context )
    {
        super( context );
    }


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    public WOComponent gracefulShutdown()
    {
        ERXApplication.erxApplication().startRefusingSessions();
        return null;
    }


    // ----------------------------------------------------------
    public WOComponent dieNow()
    {
        ERXApplication.erxApplication().killInstance();
        return null;
    }


    // ----------------------------------------------------------
    public boolean canRestart()
    {
        return org.webcat.WCServletAdaptor.getInstance() == null
            || Application.configurationProperties()
                .stringForKey( "coreKillAction" ) != null;
    }
}