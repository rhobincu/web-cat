/*==========================================================================*\
 |  $Id: EnqueuedReportGenerationJob.java,v 1.2 2008/10/24 20:52:57 aallowat Exp $
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

package net.sf.webcat.reporter;

import com.webobjects.foundation.*;
import com.webobjects.eocontrol.*;
import net.sf.webcat.core.Application;

// -------------------------------------------------------------------------
/**
 * Represents the generation phase of a report in Web-CAT. The rendering phase
 * is handled by the {@link EnqueuedReportRenderJob} entity.
 *
 * @author Tony Allevato
 * @version $Id: EnqueuedReportGenerationJob.java,v 1.2 2008/10/24 20:52:57 aallowat Exp $
 */
public class EnqueuedReportGenerationJob
    extends _EnqueuedReportGenerationJob
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new EnqueuedReportGenerationJob object.
     */
    public EnqueuedReportGenerationJob()
    {
        super();
    }


    //~ Methods ...............................................................

}
