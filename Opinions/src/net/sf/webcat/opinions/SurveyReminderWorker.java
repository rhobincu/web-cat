/*==========================================================================*\
 |  $Id: SurveyReminderWorker.java,v 1.3 2009/11/23 00:46:43 stedwar2 Exp $
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

package net.sf.webcat.opinions;

import net.sf.webcat.jobqueue.WorkerThread;

//-------------------------------------------------------------------------
/**
 * A {@link WorkerThread} subclass for processing survey reminders.
 *
 * @author Stephen Edwards
 * @author Last changed by $Author: stedwar2 $
 * @version $Revision: 1.3 $, $Date: 2009/11/23 00:46:43 $
 */
public class SurveyReminderWorker
    extends WorkerThread<SurveyReminderJob>
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new object.
     * @param descriptor the descriptor for this worker thread
     */
    public SurveyReminderWorker()
    {
        super(SurveyReminderJob.ENTITY_NAME);
    }


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Processes one survey reminder job by sending out e-mail to everyone
     * who submitted to the given assignment offering, as well as all
     * instructors assigned to teach the corresponding course offering.
     */
    protected void processJob()
    {
        // TODO: implement
    }
}
