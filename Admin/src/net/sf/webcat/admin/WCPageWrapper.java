/*==========================================================================*\
 |  $Id: WCPageWrapper.java,v 1.3 2008/04/02 00:56:34 stedwar2 Exp $
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
import com.webobjects.eocontrol.*;
import net.sf.webcat.core.*;
import org.apache.log4j.Logger;

//-------------------------------------------------------------------------
/**
 * The page wrapper for direct-to-web pages.
 *
 *  @author Stephen Edwards
 *  @version $Id: WCPageWrapper.java,v 1.3 2008/04/02 00:56:34 stedwar2 Exp $
 */
public class WCPageWrapper
    extends WOComponent
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new WCPageWrapper object.
     *
     * @param aContext The context to use
     */
    public WCPageWrapper( WOContext aContext )
    {
        super( aContext );
        log.debug( "constructor" );
    }


    //~ KVC Attributes (must be public) .......................................

    public String  stylesheet;
    public String  externalJavascript;
    public String  inlineHeaderContents;


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /* (non-Javadoc)
     * @see com.webobjects.appserver.WOComponent#appendToResponse(com.webobjects.appserver.WOResponse, com.webobjects.appserver.WOContext)
     */
    public void appendToResponse( WOResponse arg0, WOContext arg1 )
    {
        log.debug( "appendToResponse()" );
        // TODO Auto-generated method stub
        super.appendToResponse( arg0, arg1 );
        log.debug( "container = " + arg1.page().getClass().getName() );
    }


    //~ Instance/static variables .............................................

    static Logger log = Logger.getLogger( WCPageWrapper.class );
}
