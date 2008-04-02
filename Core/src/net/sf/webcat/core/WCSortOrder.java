/*==========================================================================*\
 |  $Id: WCSortOrder.java,v 1.3 2008/04/02 00:50:30 stedwar2 Exp $
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

package net.sf.webcat.core;


import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSSelector;


// -------------------------------------------------------------------------
/**
 * Places a titlebar and a grey window-looking rectangle around and behind its
 * contents.
 *
 * @author Lally Singh
 * @version $Id: WCSortOrder.java,v 1.3 2008/04/02 00:50:30 stedwar2 Exp $
 */
public class WCSortOrder
    extends com.webobjects.woextensions.WOSortOrder
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new WCSortOrder object.
     *
     * @param context The page's context
     */
    public WCSortOrder( WOContext context )
    {
        super( context );
    }


    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Returns the relative URL for the image file reflecting the
     * current sorting state for the attribute bound to this sorter.
     * This is overridden from <code>WOSortOrder</code> to use
     * split-install web server resources instead of in-framework
     * images (and to override the images built into the framework).
     *
     * @return The page title
     */
    public String imageName()
    {
        String anImageName = "Unsorted.gif";
        if ( _isCurrentKeyPrimary() )
        {
            NSSelector aCurrentState = _primaryKeySortOrderingSelector();
            if ( aCurrentState == EOSortOrdering.CompareAscending
                 || aCurrentState ==
                     EOSortOrdering.CompareCaseInsensitiveAscending )
            {
                anImageName = "Ascending.gif";
            }
            else if ( aCurrentState == EOSortOrdering.CompareDescending
                      || aCurrentState ==
                          EOSortOrdering.CompareCaseInsensitiveDescending )
            {
                anImageName = "Descending.gif";
            }
        }
        return "icons/" + anImageName;
    }
}
