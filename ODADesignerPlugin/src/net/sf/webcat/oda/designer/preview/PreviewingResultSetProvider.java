/*==========================================================================*\
 |  $Id: PreviewingResultSetProvider.java,v 1.2 2008/04/13 22:04:52 aallowat Exp $
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

package net.sf.webcat.oda.designer.preview;

import net.sf.webcat.oda.commons.IWebCATResultSet;
import net.sf.webcat.oda.commons.IWebCATResultSetProvider;
import net.sf.webcat.oda.designer.DesignerActivator;
import net.sf.webcat.oda.designer.preferences.IPreferencesConstants;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.datatools.connectivity.oda.OdaException;

//------------------------------------------------------------------------
/**
 * TODO: real description
 *
 * @author Tony Allevato (Virginia Tech Computer Science)
 * @version $Id: PreviewingResultSetProvider.java,v 1.2 2008/04/13 22:04:52 aallowat Exp $
 */
public class PreviewingResultSetProvider implements IWebCATResultSetProvider
{
    // === Methods ============================================================

    // ------------------------------------------------------------------------
    /**
     * Creates a new instance of the PreviewingResultSetProvider class.
     *
     * @param url
     *            the URL of the Web-CAT server to obtain preview data from
     * @param username
     *            the username used to log in to Web-CAT
     * @param password
     *            the password used to log in to Web-CAT
     * @param maxRecords
     *            the maximum number of records to preview
     */
    public PreviewingResultSetProvider()
    {
        Preferences prefs = DesignerActivator.getDefault().getPluginPreferences();

        // Try to construct a previewing result set provider based on the
        // current preferences settings.
        int maxRecords = 0;

        try
        {
            maxRecords = Integer.parseInt(prefs
                    .getString(IPreferencesConstants.MAX_RECORDS_KEY));
        }
        catch (NumberFormatException e)
        {
            // Ignore exception; retain original value of 0.
        }

        this.maxRecords = maxRecords;
    }


    // ------------------------------------------------------------------------
    /**
     * Gets the result set with the specified name.
     *
     * @param id
     *            the unique identifier of the result set to return
     *
     * @return an IWebCATResultSet object that allows the caller to iterate over
     *         the results for the data set with the given name
     */
    public IWebCATResultSet resultSetWithId(String id)
    {
        return new PreviewingResultSet(id, maxRecords);
    }


    // === Instance Variables =================================================

    /**
     * The maximum number of records to return in a preview.
     */
    private int maxRecords;
}