/*==========================================================================*\
 |  $Id: PMDForPlugins.java,v 1.2 2008/04/02 01:13:29 stedwar2 Exp $
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

package net.sf.webcat.pmdforplugins;

import com.webobjects.foundation.*;
import net.sf.webcat.core.Application;
import net.sf.webcat.core.Subsystem;

//-------------------------------------------------------------------------
/**
 *  This subsystem provides <a href="http://pmd.sf.net/">PMD</a> for
 *  grading plug-ins.
 *
 *  @author  stedwar2
 *  @version $Id: PMDForPlugins.java,v 1.2 2008/04/02 01:13:29 stedwar2 Exp $
 */
public class PMDForPlugins
    extends Subsystem
{
   //~ Constructors ..........................................................

   // ----------------------------------------------------------
   /**
    * Creates a new PMDForPlugins subsystem object.
    */
   public PMDForPlugins()
   {
       super();
   }


   //~ Methods ...............................................................

   // ----------------------------------------------------------
   /**
    * Add any subsystem-specific plug-in property bindings
    * to the given dictionary.
    * @param properties the dictionary to add new properties to;
    * individual plug-in information may override these later.
    */
   public void addPluginPropertyBindings( NSMutableDictionary properties )
   {
       // pmd.lib
       addFileBinding(
           properties,
           PMD_LIB_KEY,
           SUBSYSTEM_PREFIX + PMD_LIB_KEY,
           "pmd/lib" );
   }


   //~ Instance/static variables .............................................

   private static final String SUBSYSTEM_PREFIX   = "PMDForPlugins.";
   private static final String PMD_LIB_KEY        = "pmd.lib";
}
