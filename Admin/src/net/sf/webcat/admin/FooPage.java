/*==========================================================================*\
 |  $Id: FooPage.java,v 1.2 2008/04/02 00:56:34 stedwar2 Exp $
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

package net.sf.webcat.admin;

import com.webobjects.appserver.*;
import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import net.sf.webcat.core.*;
import org.apache.log4j.Logger;

// -------------------------------------------------------------------------
/**
 * A property listing page.
 *
 *  @author edwards
 *  @version $Id: FooPage.java,v 1.2 2008/04/02 00:56:34 stedwar2 Exp $
 */
public class FooPage
    extends WOComponent
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new PropertyList object.
     *
     * @param context The context to use
     */
    public FooPage( WOContext context )
    {
        super( context );
    }


    // ----------------------------------------------------------
    /* (non-Javadoc)
     * @see com.webobjects.appserver.WOComponent#appendToResponse(com.webobjects.appserver.WOResponse, com.webobjects.appserver.WOContext)
     */
    public void appendToResponse( WOResponse arg0, WOContext arg1 )
    {
        log.debug( "appendToResponse()" );
        // TODO Auto-generated method stub
        super.appendToResponse( arg0, arg1 );
    }

    //~ Instance/static variables .............................................
    static Logger log = Logger.getLogger( PropertyListPage.class );
}
