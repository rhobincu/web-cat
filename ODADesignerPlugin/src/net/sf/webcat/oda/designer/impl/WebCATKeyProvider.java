/*==========================================================================*\
 |  $Id: WebCATKeyProvider.java,v 1.3 2008/04/13 22:04:52 aallowat Exp $
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

package net.sf.webcat.oda.designer.impl;

import org.eclipse.swt.graphics.Image;
import net.sf.webcat.oda.designer.DesignerActivator;
import net.sf.webcat.oda.designer.contentassist.ContentAssistManager;
import net.sf.webcat.oda.designer.util.ImageUtils;
import net.sf.webcat.oda.designer.widgets.IKeyLabelProvider;
import net.sf.webcat.oda.designer.widgets.IKeyProvider;

//------------------------------------------------------------------------
/**
 * A content and label provider for the KeyBrowser widget that provides keys and
 * images based on data retrieved from the Web-CAT server through the content
 * assist manager.
 *
 * @author Tony Allevato (Virginia Tech Computer Science)
 * @version $Id: WebCATKeyProvider.java,v 1.3 2008/04/13 22:04:52 aallowat Exp $
 */
public class WebCATKeyProvider implements IKeyProvider, IKeyLabelProvider
{
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    public WebCATKeyProvider()
    {
        contentAssist = DesignerActivator.getDefault()
                .getContentAssistManager();

        propertyImage = ImageUtils.getImage("icons/keypath/property.gif"); //$NON-NLS-1$
        methodImage = ImageUtils.getImage("icons/keypath/method.gif"); //$NON-NLS-1$
    }


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    public void dispose()
    {
        propertyImage.dispose();
        methodImage.dispose();
    }


    // ----------------------------------------------------------
    public String getKeyType(String className, String key)
    {
        return contentAssist.getAttributeType(className, key);
    }


    // ----------------------------------------------------------
    public boolean hasKeys(String className)
    {
        return contentAssist.isEntity(className);
    }


    // ----------------------------------------------------------
    public String[] getKeys(String className)
    {
        return contentAssist.getAttributeNames(className);
    }


    // ----------------------------------------------------------
    public Image getImage(String className, String key)
    {
        if (contentAssist.isEntity(getKeyType(className, key)))
            return methodImage;
        else
            return propertyImage;
    }


    // ----------------------------------------------------------
    public String getLabel(String className, String key)
    {
        String destType = getKeyType(className, key);
        return key + " (" + destType + ")"; //$NON-NLS-1$ //$NON-NLS-2$
    }


    //~ Static/instance variables .............................................

    private ContentAssistManager contentAssist;
    private Image propertyImage;
    private Image methodImage;
}