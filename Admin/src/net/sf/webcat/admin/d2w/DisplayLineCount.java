/*==========================================================================*\
 |  $Id: DisplayLineCount.java,v 1.1 2007/07/08 01:52:22 stedwar2 Exp $
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

package net.sf.webcat.admin.d2w;

import com.webobjects.appserver.*;

import java.sql.*;
import java.util.regex.*;

//-------------------------------------------------------------------------
/**
 * A customized version of {@link er.directtoweb.ERD2WDisplayString}
 * for displaying long strings.  It simply shows a count of the number
 * of lines and characters.
 *
 *  @author edwards
 *  @version $Id: DisplayLineCount.java,v 1.1 2007/07/08 01:52:22 stedwar2 Exp $
 */
public class DisplayLineCount
    extends er.directtoweb.ERD2WDisplayString
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new object.
     *
     * @param context The context to use
     */
    public DisplayLineCount( WOContext context )
    {
        super( context );
    }


    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Creates all tables in their baseline configuration, as needed.
     * @throws SQLException on error
     */
    public Object objectPropertyValue()
    {
        Object value = super.objectPropertyValue();
        if ( value != null )
        {
            String str = value.toString();
            int lineCount = 0;
            Matcher newLineMatcher =
                Pattern.compile( "$", Pattern.MULTILINE ).matcher( str );
            while ( newLineMatcher.find() )
            {
                lineCount++;
            }
            value = "" + lineCount + " lines, " + str.length() + " chars";
        }
        return value;
    }
}
