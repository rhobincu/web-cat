/*==========================================================================*\
 |  $Id: JobBase.java,v 1.1 2008/10/27 01:53:16 stedwar2 Exp $
 |*-------------------------------------------------------------------------*|
 |  Copyright (C) 2006 Virginia Tech
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

package net.sf.webcat.jobqueue;

import com.webobjects.eoaccess.EOGeneralAdaptorException;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;

// -------------------------------------------------------------------------
/**
 * This is the abstract base class for all "job" entities across all
 * Web-CAT subsystems.  It is designed to be used with EO-style horizontal
 * inheritance.  The corresponding EOModel definition provides the common
 * fields that all concrete subclasses will contain, although subclasses
 * can certainly add more as necessary.  To create a job subclass, create
 * an entity the normal way, then set its parent entity to JobBase.  To
 * generate SQL for the corresponding table, build off of the
 * {@link JobQueueDatabaseUpdates#createJobBaseTable(net.sf.webcat.dbupdate.Database,String)}
 * method, which will generate the inherited field definitions for you.
 *
 * @author
 * @version $Id: JobBase.java,v 1.1 2008/10/27 01:53:16 stedwar2 Exp $
 */
public abstract class JobBase
    extends _JobBase
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new JobBase object.
     */
    public JobBase()
    {
        super();
    }


    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Checks to see if this job is available for running, and if so,
     * allocates it to the given worker.  This sets the {@link #worker()}
     * relation to point to the worker thread on success, and returns true.
     * If the job is not available, it returns false.  Note that if the
     * method succeeds, the EC containing this job will have its changes
     * saved as part of the process, in order to commit the new value of
     * worker() to the database.
     *
     * @param worker The worker thread that wishes to take on this job
     * @return True if the worker has been allocated this job, or false
     *     if this worker cannot be given the job (because it has been
     *     cancelled or paused, or because it has already been allocated
     *     to another worker).
     */
    public boolean volunteerToRun(WorkerDescriptor worker)
    {
        if (isCancelled() || !isPaused())
        {
            return false;
        }
        if (worker() == null)
        {
            try
            {
                setWorkerRelationship(worker);
                editingContext().saveChanges();
            }
            catch (EOGeneralAdaptorException e)
            {
                // assume optimistic locking failure
            }
        }
        return worker() == worker;
    }


    //~ Protected Methods .....................................................

}