/*==========================================================================*\
 |  $Id: GraderPrefsManager.java,v 1.3 2008/04/05 17:52:15 stedwar2 Exp $
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

package net.sf.webcat.grader;

import com.webobjects.eocontrol.*;
import net.sf.webcat.core.*;

//-------------------------------------------------------------------------
/**
 *  An {@link CachingEOManager} specialized for managing a
 *  {@link GraderPrefs} object.
 *
 *  @author  Stephen Edwards
 *  @version $Id: GraderPrefsManager.java,v 1.3 2008/04/05 17:52:15 stedwar2 Exp $
 */
public class GraderPrefsManager
    extends CachingEOManager
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new manager for the given GraderPrefs object (which
     * presumably lives in the session's editing context).
     * @param prefs the object to manage
     * @param manager the (probably shared) editing context manager to use
     * for independent saving of the given eo
     */
    public GraderPrefsManager(GraderPrefs prefs, ECManager manager)
    {
        super(prefs, manager);
    }


    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>commentHistory</code> value.
     * @return the value of the attribute
     */
    public String commentHistory()
    {
        return (String)valueForKey(GraderPrefs.COMMENT_HISTORY_KEY);
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>commentHistory</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setCommentHistory(String value)
    {
        takeValueForKey(value, GraderPrefs.COMMENT_HISTORY_KEY);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the entity pointed to by the <code>assignmentOffering</code>
     * relationship.
     * @return the entity in the relationship
     */
    public AssignmentOffering assignmentOffering()
    {
        return (AssignmentOffering)valueForKey(
            GraderPrefs.ASSIGNMENT_OFFERING_KEY);
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>assignmentOffering</code>
     * relationship.
     *
     * @param value The new entity to relate to
     */
    public void setAssignmentOfferingRelationship(AssignmentOffering value)
    {
        addObjectToBothSidesOfRelationshipWithKey(
            value, GraderPrefs.ASSIGNMENT_OFFERING_KEY);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the entity pointed to by the <code>step</code>
     * relationship.
     * @return the entity in the relationship
     */
    public Step step()
    {
        return (Step)valueForKey(GraderPrefs.STEP_KEY);
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>step</code>
     * relationship.
     *
     * @param value The new entity to relate to
     */
    public void setStepRelationship(Step value)
    {
        addObjectToBothSidesOfRelationshipWithKey(value, GraderPrefs.STEP_KEY);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the entity pointed to by the <code>submission</code>
     * relationship.
     * @return the entity in the relationship
     */
    public Submission submission()
    {
        return (Submission)valueForKey(GraderPrefs.SUBMISSION_KEY);
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>submission</code>
     * relationship.
     *
     * @param value The new entity to relate to
     */
    public void setSubmissionRelationship(Submission value)
    {
        addObjectToBothSidesOfRelationshipWithKey(
            value, GraderPrefs.SUBMISSION_KEY);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the entity pointed to by the <code>submissionFileStats</code>
     * relationship.
     * @return the entity in the relationship
     */
    public SubmissionFileStats submissionFileStats()
    {
        return (SubmissionFileStats)valueForKey(
            GraderPrefs.SUBMISSION_FILE_STATS_KEY);
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>submissionFileStats</code>
     * relationship.
     *
     * @param value The new entity to relate to
     */
    public void setSubmissionFileStatsRelationship(SubmissionFileStats value)
    {
        addObjectToBothSidesOfRelationshipWithKey(
            value, GraderPrefs.SUBMISSION_FILE_STATS_KEY);
    }
}
