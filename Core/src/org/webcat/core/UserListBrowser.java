/*==========================================================================*\
 |  $Id: UserListBrowser.java,v 1.1 2010/05/11 14:51:55 aallowat Exp $
 |*-------------------------------------------------------------------------*|
 |  Copyright (C) 2006-2009 Virginia Tech
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

import com.webobjects.appserver.*;
import com.webobjects.eoaccess.EODatabaseDataSource;
import com.webobjects.eoaccess.EOEntity;
import com.webobjects.eocontrol.EODetailDataSource;
import com.webobjects.eocontrol.EOGenericRecord;
import org.webcat.core.User;
import org.webcat.core.UserListBrowser;
import org.webcat.core.WCComponent;
import org.apache.log4j.Logger;

// -------------------------------------------------------------------------
/**
 * This class presents a list of users associated with some object, as
 * determined by some key path from the object.
 *
 * @binding master The object with which users are associated.  If this
 *          binding is omitted, then the user list will include all
 *          users.
 * @binding keyPath The key path (originating from the master object)
 *          that identifies the relationship of users to display.
 * @binding allowUserUploads a boolean that indicates whether controls
 *          to upload/create a set of user accounts from a CSV file
 *          (to add to the relationship) should be provided (default: false).
 * @binding allowEdit a boolean (default: true) that indicates whether
 *          the browser allows one to add/remove users.
 * @binding roleLabel An optional text string (default: "Target") that
 *          is used to describe the user relationship in labels.
 * @binding minAccessLevel The minimum access level that users must
 *          have to be presented for adding to the relationship (default: 0).
 * @binding promoteToAccessLevel The minimum access level that users will
 *          gain when being added to the relationship (default: 0).
 *
 * @author Stephen Edwards
 * @author Last changed by $Author: aallowat $
 * @version $Revision: 1.1 $, $Date: 2010/05/11 14:51:55 $
 */
public class UserListBrowser
    extends WCComponent
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * This is the default constructor
     *
     * @param context The page's context
     */
    public UserListBrowser(WOContext context)
    {
        super(context);
    }


    //~ KVC Attributes (must be public) .......................................



    //~ Methods ...............................................................

    // ----------------------------------------------------------
    public boolean synchronizesVariablesWithBindings()
    {
        return false;
    }


    // ----------------------------------------------------------
    public WODisplayGroup users()
    {
        if (initializeUserList)
        {
            if (canGetValueForBinding("master"))
            {
                EOGenericRecord master =
                    (EOGenericRecord)valueForBinding("master");
                users.setDataSource(new EODetailDataSource(
                    master.classDescription(),
                    (String)valueForBinding("keyPath")));
            }
            else
            {
                users.setDataSource(
                    new EODatabaseDataSource(localContext(), User.ENTITY_NAME));
            }
            users.fetch();
            initializeUserList = false;
        }
        return users;
    }


    // ----------------------------------------------------------
    public void setUsers(WODisplayGroup group)
    {
        users = group;
    }


    //~ Instance/static variables .............................................

    private WODisplayGroup users;
    private boolean initializeUserList = true;

    static Logger log = Logger.getLogger(UserListBrowser.class);
}