/*==========================================================================*\
|  $Id: CoreNavigatorTestPage.java,v 1.1 2009/04/27 17:10:53 stedwar2 Exp $
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

package net.sf.webcat.core.tests;

import com.webobjects.appserver.*;
import net.sf.webcat.core.*;

//-------------------------------------------------------------------------
/**
 * A test page for testing the core navigator pop-up component.
 *
 *  @author Stephen Edwards
 *  @version $Id: CoreNavigatorTestPage.java,v 1.1 2009/04/27 17:10:53 stedwar2 Exp $
 */
public class CoreNavigatorTestPage
    extends WCCourseComponent
{
   //~ Constructors ..........................................................

   // ----------------------------------------------------------
   /**
    * Creates a new page.
    *
    * @param context The context to use
    */
   public CoreNavigatorTestPage(WOContext context)
   {
       super(context);
   }
}