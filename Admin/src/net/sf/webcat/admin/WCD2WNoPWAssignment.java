/*==========================================================================*\
 |  $Id: WCD2WNoPWAssignment.java,v 1.2 2008/04/02 00:56:34 stedwar2 Exp $
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

import com.webobjects.eocontrol.*;
import com.webobjects.directtoweb.*;
import com.webobjects.foundation.*;
import org.apache.log4j.Logger;

// -------------------------------------------------------------------------
/**
 *  A basic class that adds on Web-CAT specific behavior to the
 *  <code>DefaultAssignment</code> DirectToWeb class.  It also
 *  sets up the cache-significant keys for DirectToWeb use in its
 *  static initializer.
 *
 *  @author edwards
 *  @version $Id: WCD2WNoPWAssignment.java,v 1.2 2008/04/02 00:56:34 stedwar2 Exp $
 */
public class WCD2WNoPWAssignment
    extends WCD2WAssignment
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new WCD2WAssignment object.
     * @param unarchiver
     */
    public WCD2WNoPWAssignment( EOKeyValueUnarchiver unarchiver )
    {
        super( unarchiver );
        log.debug( "constructor( " + unarchiver + " )" );
    }


    // ----------------------------------------------------------
    /**
     * Creates a new WCD2WAssignment object.
     * @param keyPath
     * @param value
     */
    public WCD2WNoPWAssignment( String keyPath, String value )
    {
        super( keyPath, value );
        log.debug( "constructor( " + keyPath + ", " + value + " )" );
    }


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Required for new 'feature' of key-value unarchiving.
     * @param eokeyvalueunarchiver
     * @return a new WCD2WAssignment object
     */
    public static Object decodeWithKeyValueUnarchiver(
        EOKeyValueUnarchiver eokeyvalueunarchiver
    )
    {
        return new WCD2WNoPWAssignment( eokeyvalueunarchiver );
    }


    /*
    // ----------------------------------------------------------
    public Object fire( D2WContext context )
    {
        log.debug( "fire()" );
        Object result = super.fire( context );
        log.debug( "fire() = " + result );
        return result;
    }
    */


    // ----------------------------------------------------------
    /**
     * Returns the full list of property keys, with any properties
     * that start with "password" removed.
     * @return the property key list
     */
    public NSArray defaultPropertyKeysFromEntity()
    {
        log.debug( "defaultPropertyKeysFromEntity()" );
        NSArray superResult = super.defaultPropertyKeysFromEntity();
        NSMutableArray result =
            ( superResult instanceof NSMutableArray )
            ? (NSMutableArray)superResult
            : new NSMutableArray( superResult );
        log.debug( "super = " + result );
        for ( int i = 0; i < result.count(); i++ )
        {
            String key = (String)result.objectAtIndex( i );
            if ( key != null && ( key.startsWith( "password" ) ) )
            {
                result.removeObjectAtIndex( i );
                i--;
            }
        }
        log.debug( "result = " + result );
        return result;
    }


    //~ Instance/static variables .............................................

    static Logger log = Logger.getLogger( WCD2WNoPWAssignment.class );

    // Sets up the cache-significant keys for DirectToWeb use.
    static
    {
        D2W.factory().newSignificantKey( "session.user.accessLevel" );
    }
}
