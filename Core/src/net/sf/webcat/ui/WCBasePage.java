/*==========================================================================*\
 |  $Id: WCBasePage.java,v 1.10 2010/02/07 01:31:21 stedwar2 Exp $
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

package net.sf.webcat.ui;

import com.webobjects.appserver.WOApplication;
import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResourceManager;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSBundle;
import com.webobjects.foundation.NSMutableArray;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.sf.webcat.core.Application;
import net.sf.webcat.core.Session;
import net.sf.webcat.core.Theme;
import net.sf.webcat.core.WCResourceManager;
import org.apache.log4j.Logger;

// ------------------------------------------------------------------------
/**
 * <p>
 * The base class for any page that uses the Dojo toolkit. This component
 * defines the base HTML content and also manages the stylesheets and scripts
 * used by Dojo.
 * </p><p>
 * WCBasePage also provides the ability to automatically import CSS and
 * JavaScript resources for the particular component page that contains this
 * WCBasePage component. That is, if you have a component named FooPage defined
 * as follows:
 * <pre>
 * &lt;wo:WCBasePage&gt;
 * your content...
 * &lt;/wo:WCBasePage&gt;
 * </pre>
 * then this component will automatically search in the WebServerResources
 * directory of the framework that contains FooPage to find
 * "stylesheets/FooPage.css" and "javascript/FooPage.js". If they are found,
 * they will be imported.
 * </p><p>
 * The logic described above only applies to the page component itself. If
 * nested components need to import their own scripts, they should make use of
 * {@link WCScriptFragment}.
 * </p>
 *
 * <h2>Bindings</h2>
 * <table>
 * <tr>
 * <td>{@code extraRequires}</td>
 * <td>A semicolon-separated list of additional module names that should be
 * <tt>dojo.require</tt>d in the page header.</td>
 * </tr>
 * </table>
 *
 * @author Tony Allevato
 * @author Last changed by $Author: stedwar2 $
 * @version $Revision: 1.10 $, $Date: 2010/02/07 01:31:21 $
 */
public class WCBasePage
    extends WOComponent
{
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new Dojo page.
     *
     * @param context the context
     */
    public WCBasePage(WOContext context)
    {
        super(context);
    }


    //~ KVC attributes (must be public) .......................................

    public String title;
    public String extraBodyCssClass;
    public String extraRequires;
    public String extraCssFiles;
    public String pageScriptName;
    public String  inlineHeaderContents;
    public boolean includePageWrapping = true;

    /** Used to refer to a single item in a repetition on the page. */
    public String oneExtraRequire;


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    public void appendToResponse( WOResponse response, WOContext context )
    {
        if (Application.isDevelopmentModeSafe())
        {
            pageScriptName = DEVELOPMENT_SCRIPT_NAME;
        }
        else
        {
            pageScriptName = DEPLOYMENT_SCRIPT_NAME;
        }

        log.debug( "nowrap = "
                   + context.request().stringFormValueForKey( "nowrap" ) );
        includePageWrapping =
            ( context.request().stringFormValueForKey( "nowrap" ) == null );
        response.appendHeader("no-cache", "pragma");
        response.appendHeader("no-cache", "cache-control");
        super.appendToResponse( response, context );
    }


    // ----------------------------------------------------------
    /**
     * Returns the HTML page's title string.  This is the title
     * string that will show as the "page title" in the browser.
     * This generic implementation returns "Web-CAT", which is the
     * title that will be used for pages that do not provide one.
     * Ideally, subsystems will override this default.
     *
     * @return The page title
     */
    public String pageTitle()
    {
        return ( title == null )
                ? "Web-CAT"
                : ( "Web-CAT: " + title );
    }


    // ----------------------------------------------------------
    public NSArray<String> extraRequiresArray()
    {
        if (extraRequires == null)
        {
            return null;
        }

        NSMutableArray<String> array = new NSMutableArray<String>();

        String[] requires = extraRequires.split("[,;]\\s*");
        for (String require : requires)
        {
            if (require != null && require.length() > 0)
            {
                array.addObject(require);
            }
        }

        return array;
    }


    // ----------------------------------------------------------
    /**
     * Returns true if a page-specific stylesheet exists for the component
     * containing this WCBasePage instance.
     *
     * @return true if a page-specific stylesheet exists for the component,
     *     otherwise false
     */
    public boolean doesPageSpecificStylesheetExist()
    {
        return doesPageSpecificResourceExist(STYLESHEETS_RESOURCE_DIR,
                STYLESHEETS_RESOURCE_EXT);
    }


    // ----------------------------------------------------------
    /**
     * Returns true if a page-specific JavaScript file exists for the component
     * containing this WCBasePage instance.
     *
     * @return true if a page-specific JavaScript file exists for the component,
     *     otherwise false
     */
    public boolean doesPageSpecificJavascriptExist()
    {
        return doesPageSpecificResourceExist(JAVASCRIPT_RESOURCE_DIR,
                JAVASCRIPT_RESOURCE_EXT);
    }


    // ----------------------------------------------------------
    /**
     * Returns true if a page-specific resource for the component containing
     * this WCBasePage instance.
     *
     * @param directory the WebServerResources subdirectory containing the
     *     resource to look for
     * @param extension the extension of the resource to look for
     *
     * @return true if the page-specific resource exists for the component,
     *     otherwise false
     */
    protected boolean doesPageSpecificResourceExist(String directory,
            String extension)
    {
        String resName = pageSpecificResourcePath(directory, extension);
        WOResourceManager manager =
            WOApplication.application().resourceManager();

        URL url = manager.pathURLForResourceNamed(resName, pageFramework(),
                context()._languages());

        return (url != null);
    }


    // ----------------------------------------------------------
    /**
     * Gets the name of the framework that contains the component containing
     * this WCBasePage instance.
     *
     * @return the name of the framework that contains the component
     */
    public String pageFramework()
    {
        WOComponent root = this;
        while (root.parent() != null) root = root.parent();

        return NSBundle.bundleForClass(root.getClass()).name();
    }


    // ----------------------------------------------------------
    /**
     * Returns the path (relative to WebServerResources) of the page-specific
     * stylesheet for the component containing this WCBasePage instance.
     *
     * @return the WebServerResources-relative path of the page-specific
     *     stylesheet
     */
    public String pageSpecificStylesheetPath()
    {
        return pageSpecificResourcePath(STYLESHEETS_RESOURCE_DIR,
                STYLESHEETS_RESOURCE_EXT);
    }


    // ----------------------------------------------------------
    /**
     * Returns the path (relative to WebServerResources) of the page-specific
     * Javascript file for the component containing this WCBasePage instance.
     *
     * @return the WebServerResources-relative path of the page-specific
     *     JavaScript file
     */
    public String pageSpecificJavascriptPath()
    {
        return pageSpecificResourcePath(JAVASCRIPT_RESOURCE_DIR,
                JAVASCRIPT_RESOURCE_EXT);
    }


    // ----------------------------------------------------------
    public String bodyCssClass()
    {
        String result = null;

        Object dojoTheme =
            theme().valueForKeyPath("inherit.properties.dojoTheme");
        if (dojoTheme != null)
        {
            result = dojoTheme.toString();
        }
        if (extraBodyCssClass != null)
        {
            if (result == null)
            {
                result = "";
            }
            else
            {
                result += " ";
            }
            result += extraBodyCssClass;
        }
        return result;
    }


    // ----------------------------------------------------------
    public String extraCssLinkTags()
    {
        String result = null;
        if (extraCssFiles != null)
        {
            String[] links = extraCssFiles.split("[,;]\\s*");
            StringBuffer buf = new StringBuffer(80 * links.length);
            for (String link : links)
            {
                if (link != null && link.length() > 0)
                {
                    String file = link;
                    String extra = "";
                    Matcher m =
                        Pattern.compile("^(.*)\\[([^\\]]*)\\]$").matcher(link);
                    if (m.matches())
                    {
                        file = m.group(1);
                        extra = m.group(2);
                    }
                    buf.append("<link rel=\"stylesheet\" type=\"text/css\""
                        + "href=\"");
                    buf.append(WCResourceManager.resourceURLFor(file, null));
                    buf.append('\"');
                    if (extra != null)
                    {
                        buf.append(' ');
                        buf.append(extra);
                    }
                    buf.append("/>");
                }
            }
            result = buf.toString();
        }
        return result;
    }


    // ----------------------------------------------------------
    public boolean hasCustomExternalCssLinks()
    {
        return hasSession()
            && ((Session)session()).user().preferences()
                .valueForKey("customCss") != null;
    }


    // ----------------------------------------------------------
    public String customExternalCssLinks()
    {
        String result = null;
        String customCss = (String)((Session)session()).user().preferences()
            .valueForKey("customCss");
        if (customCss != null)
        {
            String[] links = customCss.split("[,;]\\s*");
            StringBuffer buf = new StringBuffer(80 * links.length);
            for (String link : links)
            {
                if (link != null && link.length() > 0)
                {
                    String file = link;
                    String extra = "";
                    Matcher m =
                        Pattern.compile("^(.*)\\[([^\\]]*)\\]$").matcher(link);
                    if (m.matches())
                    {
                        file = m.group(1);
                        extra = m.group(2);
                    }
                    buf.append("<link rel=\"stylesheet\" type=\"text/css\""
                        + "href=\"");
                    buf.append(file);
                    buf.append('\"');
                    if (extra != null)
                    {
                        buf.append(' ');
                        buf.append(extra);
                    }
                    buf.append("/>");
                }
            }
            result = buf.toString();
        }
        return result;
    }


    // ----------------------------------------------------------
    public String customBackground()
    {
        return hasSession()
            ? (String)((Session)session()).user().preferences()
               .valueForKey("customBackgroundUrl")
            : null;
    }


    // ----------------------------------------------------------
    public Theme theme()
    {
        if (hasSession())
        {
            return ((Session)session()).theme();
        }
        else
        {
            Theme lastUsedTheme = Theme.lastUsedThemeInContext(context());

            return (lastUsedTheme != null) ?
                    lastUsedTheme : Theme.defaultTheme();
        }
    }


    // ----------------------------------------------------------
    public String dojoBaseUrl()
    {
        // The resource manager doesn't let us get the path to a directory, so
        // we search for a known file (in this case, dojo/dojo.js inside
        // Core.framework), and then remove the dojo/dojo.js part from the end
        // of the path.

        String basePath = "Core.framework/WebServerResources/";
        String subPath = "dojo/dojo.js";

        String url = WCResourceManager.resourceURLFor(basePath + subPath,
                context().request());

        url = url.substring(0, url.length() - subPath.length());
        return url;
    }


    // ----------------------------------------------------------
    public String resourceUrl(String partialUrl)
    {
        return WCResourceManager.resourceURLFor(
            partialUrl, context().request());
    }


    // ----------------------------------------------------------
    /**
     * Returns true if a page-specific resource for the component containing
     * this WCBasePage instance.
     *
     * @param directory the WebServerResources subdirectory containing the
     *     resource to look for
     * @param extension the extension of the resource to look for
     *
     * @return the WebServerResources-relative path of the page-specific
     *     resource
     */
    protected String pageSpecificResourcePath(String directory,
            String extension)
    {
        WOComponent root = this;
        while (root.parent() != null) root = root.parent();

        return directory + "/" + root.getClass().getSimpleName() + "." +
            extension;
    }


    //~ Static/instance variables .............................................

    private static final long serialVersionUID = 1L;

    private static final String JAVASCRIPT_RESOURCE_DIR = "javascript";
    private static final String JAVASCRIPT_RESOURCE_EXT = "js";

    private static final String STYLESHEETS_RESOURCE_DIR = "stylesheets";
    private static final String STYLESHEETS_RESOURCE_EXT = "css";

    private static final String DEPLOYMENT_SCRIPT_NAME = "BasePage.js";
    private static final String DEVELOPMENT_SCRIPT_NAME =
        "BasePage.js.uncompressed.js";

    static Logger log = Logger.getLogger( WCBasePage.class );
}
