/*==========================================================================*\
 |  _GraderPrefs.java
 |*-------------------------------------------------------------------------*|
 |  Created by eogenerator
 |  DO NOT EDIT.  Make changes to GraderPrefs.java instead.
 |*-------------------------------------------------------------------------*|
 |  Copyright (C) 2006-2010 Virginia Tech
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

package org.webcat.grader;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import er.extensions.eof.ERXEOControlUtilities;
import er.extensions.eof.ERXKey;
import org.apache.log4j.Logger;
import org.webcat.core.EOBasedKeyGenerator;

// -------------------------------------------------------------------------
/**
 * An automatically generated EOGenericRecord subclass.  DO NOT EDIT.
 * To change, use EOModeler, or make additions in
 * GraderPrefs.java.
 *
 * @author Generated by eogenerator
 * @version version suppressed to control auto-generation
 */
public abstract class _GraderPrefs
    extends er.extensions.eof.ERXGenericRecord
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new _GraderPrefs object.
     */
    public _GraderPrefs()
    {
        super();
    }


    // ----------------------------------------------------------
    /**
     * A static factory method for creating a new
     * GraderPrefs object given required
     * attributes and relationships.
     * @param editingContext The context in which the new object will be
     * inserted
     * @return The newly created object
     */
    public static GraderPrefs create(
        EOEditingContext editingContext
        )
    {
        GraderPrefs eoObject = (GraderPrefs)
            EOUtilities.createAndInsertInstance(
                editingContext,
                _GraderPrefs.ENTITY_NAME);
        return eoObject;
    }


    // ----------------------------------------------------------
    /**
     * Get a local instance of the given object in another editing context.
     * @param editingContext The target editing context
     * @param eo The object to import
     * @return An instance of the given object in the target editing context
     */
    public static GraderPrefs localInstance(
        EOEditingContext editingContext, GraderPrefs eo)
    {
        return (eo == null)
            ? null
            : (GraderPrefs)EOUtilities.localInstanceOfObject(
                editingContext, eo);
    }


    // ----------------------------------------------------------
    /**
     * Look up an object by id number.  Assumes the editing
     * context is appropriately locked.
     * @param ec The editing context to use
     * @param id The id to look up
     * @return The object, or null if no such id exists
     */
    public static GraderPrefs forId(
        EOEditingContext ec, int id )
    {
        GraderPrefs obj = null;
        if (id > 0)
        {
            NSArray<GraderPrefs> objects =
                objectsMatchingValues(ec, "id", new Integer(id));
            if (objects != null && objects.count() > 0)
            {
                obj = objects.objectAtIndex(0);
            }
        }
        return obj;
    }


    // ----------------------------------------------------------
    /**
     * Look up an object by id number.  Assumes the editing
     * context is appropriately locked.
     * @param ec The editing context to use
     * @param id The id to look up
     * @return The object, or null if no such id exists
     */
    public static GraderPrefs forId(
        EOEditingContext ec, String id )
    {
        return forId( ec, er.extensions.foundation.ERXValueUtilities.intValue( id ) );
    }


    //~ Constants (for key names) .............................................

    // Attributes ---
    public static final String COMMENT_HISTORY_KEY = "commentHistory";
    public static final ERXKey<String> commentHistory =
        new ERXKey<String>(COMMENT_HISTORY_KEY);
    // To-one relationships ---
    public static final String ASSIGNMENT_KEY = "assignment";
    public static final ERXKey<org.webcat.grader.Assignment> assignment =
        new ERXKey<org.webcat.grader.Assignment>(ASSIGNMENT_KEY);
    public static final String ASSIGNMENT_OFFERING_KEY = "assignmentOffering";
    public static final ERXKey<org.webcat.grader.AssignmentOffering> assignmentOffering =
        new ERXKey<org.webcat.grader.AssignmentOffering>(ASSIGNMENT_OFFERING_KEY);
    public static final String STEP_KEY = "step";
    public static final ERXKey<org.webcat.grader.Step> step =
        new ERXKey<org.webcat.grader.Step>(STEP_KEY);
    public static final String SUBMISSION_KEY = "submission";
    public static final ERXKey<org.webcat.grader.Submission> submission =
        new ERXKey<org.webcat.grader.Submission>(SUBMISSION_KEY);
    public static final String SUBMISSION_FILE_STATS_KEY = "submissionFileStats";
    public static final ERXKey<org.webcat.grader.SubmissionFileStats> submissionFileStats =
        new ERXKey<org.webcat.grader.SubmissionFileStats>(SUBMISSION_FILE_STATS_KEY);
    public static final String USER_KEY = "user";
    public static final ERXKey<org.webcat.core.User> user =
        new ERXKey<org.webcat.core.User>(USER_KEY);
    // To-many relationships ---
    // Fetch specifications ---
    public static final String OBJECTS_FOR_USER_FSPEC = "objectsForUser";
    public static final String ENTITY_NAME = "GraderPrefs";

    public final EOBasedKeyGenerator generateKey = new EOBasedKeyGenerator(this);


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Get a local instance of this object in another editing context.
     * @param editingContext The target editing context
     * @return An instance of this object in the target editing context
     */
    public GraderPrefs localInstance(EOEditingContext editingContext)
    {
        return (GraderPrefs)EOUtilities.localInstanceOfObject(
            editingContext, this);
    }


    // ----------------------------------------------------------
    /**
     * Get a list of changes between this object's current state and the
     * last committed version.
     * @return a dictionary of the changes that have not yet been committed
     */
    @SuppressWarnings("unchecked")
    public NSDictionary<String, Object> changedProperties()
    {
        return changesFromSnapshot(
            editingContext().committedSnapshotForObject(this) );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>id</code> value.
     * @return the value of the attribute
     */
    public Number id()
    {
        try
        {
            return (Number)EOUtilities.primaryKeyForObject(
                editingContext() , this ).objectForKey( "id" );
        }
        catch (Exception e)
        {
            return er.extensions.eof.ERXConstant.ZeroInteger;
        }
    }

    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>commentHistory</code> value.
     * @return the value of the attribute
     */
    public String commentHistory()
    {
        return (String)storedValueForKey( "commentHistory" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>commentHistory</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setCommentHistory( String value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setCommentHistory("
                + value + "): was " + commentHistory() );
        }
        takeStoredValueForKey( value, "commentHistory" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the entity pointed to by the <code>assignment</code>
     * relationship.
     * @return the entity in the relationship
     */
    public org.webcat.grader.Assignment assignment()
    {
        return (org.webcat.grader.Assignment)storedValueForKey( "assignment" );
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>assignment</code>
     * relationship (DO NOT USE--instead, use
     * <code>setAssignmentRelationship()</code>.
     * This method is provided for WebObjects use.
     *
     * @param value The new entity to relate to
     */
    public void setAssignment( org.webcat.grader.Assignment value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setAssignment("
                + value + "): was " + assignment() );
        }
        takeStoredValueForKey( value, "assignment" );
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>assignment</code>
     * relationship.  This method is a type-safe version of
     * <code>addObjectToBothSidesOfRelationshipWithKey()</code>.
     *
     * @param value The new entity to relate to
     */
    public void setAssignmentRelationship(
        org.webcat.grader.Assignment value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setAssignmentRelationship("
                + value + "): was " + assignment() );
        }
        if ( value == null )
        {
            org.webcat.grader.Assignment object = assignment();
            if ( object != null )
                removeObjectFromBothSidesOfRelationshipWithKey( object, "assignment" );
        }
        else
        {
            addObjectToBothSidesOfRelationshipWithKey( value, "assignment" );
        }
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the entity pointed to by the <code>assignmentOffering</code>
     * relationship.
     * @return the entity in the relationship
     */
    public org.webcat.grader.AssignmentOffering assignmentOffering()
    {
        return (org.webcat.grader.AssignmentOffering)storedValueForKey( "assignmentOffering" );
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>assignmentOffering</code>
     * relationship (DO NOT USE--instead, use
     * <code>setAssignmentOfferingRelationship()</code>.
     * This method is provided for WebObjects use.
     *
     * @param value The new entity to relate to
     */
    public void setAssignmentOffering( org.webcat.grader.AssignmentOffering value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setAssignmentOffering("
                + value + "): was " + assignmentOffering() );
        }
        takeStoredValueForKey( value, "assignmentOffering" );
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>assignmentOffering</code>
     * relationship.  This method is a type-safe version of
     * <code>addObjectToBothSidesOfRelationshipWithKey()</code>.
     *
     * @param value The new entity to relate to
     */
    public void setAssignmentOfferingRelationship(
        org.webcat.grader.AssignmentOffering value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setAssignmentOfferingRelationship("
                + value + "): was " + assignmentOffering() );
        }
        if ( value == null )
        {
            org.webcat.grader.AssignmentOffering object = assignmentOffering();
            if ( object != null )
                removeObjectFromBothSidesOfRelationshipWithKey( object, "assignmentOffering" );
        }
        else
        {
            addObjectToBothSidesOfRelationshipWithKey( value, "assignmentOffering" );
        }
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the entity pointed to by the <code>step</code>
     * relationship.
     * @return the entity in the relationship
     */
    public org.webcat.grader.Step step()
    {
        return (org.webcat.grader.Step)storedValueForKey( "step" );
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>step</code>
     * relationship (DO NOT USE--instead, use
     * <code>setStepRelationship()</code>.
     * This method is provided for WebObjects use.
     *
     * @param value The new entity to relate to
     */
    public void setStep( org.webcat.grader.Step value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setStep("
                + value + "): was " + step() );
        }
        takeStoredValueForKey( value, "step" );
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>step</code>
     * relationship.  This method is a type-safe version of
     * <code>addObjectToBothSidesOfRelationshipWithKey()</code>.
     *
     * @param value The new entity to relate to
     */
    public void setStepRelationship(
        org.webcat.grader.Step value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setStepRelationship("
                + value + "): was " + step() );
        }
        if ( value == null )
        {
            org.webcat.grader.Step object = step();
            if ( object != null )
                removeObjectFromBothSidesOfRelationshipWithKey( object, "step" );
        }
        else
        {
            addObjectToBothSidesOfRelationshipWithKey( value, "step" );
        }
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the entity pointed to by the <code>submission</code>
     * relationship.
     * @return the entity in the relationship
     */
    public org.webcat.grader.Submission submission()
    {
        return (org.webcat.grader.Submission)storedValueForKey( "submission" );
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>submission</code>
     * relationship (DO NOT USE--instead, use
     * <code>setSubmissionRelationship()</code>.
     * This method is provided for WebObjects use.
     *
     * @param value The new entity to relate to
     */
    public void setSubmission( org.webcat.grader.Submission value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setSubmission("
                + value + "): was " + submission() );
        }
        takeStoredValueForKey( value, "submission" );
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>submission</code>
     * relationship.  This method is a type-safe version of
     * <code>addObjectToBothSidesOfRelationshipWithKey()</code>.
     *
     * @param value The new entity to relate to
     */
    public void setSubmissionRelationship(
        org.webcat.grader.Submission value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setSubmissionRelationship("
                + value + "): was " + submission() );
        }
        if ( value == null )
        {
            org.webcat.grader.Submission object = submission();
            if ( object != null )
                removeObjectFromBothSidesOfRelationshipWithKey( object, "submission" );
        }
        else
        {
            addObjectToBothSidesOfRelationshipWithKey( value, "submission" );
        }
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the entity pointed to by the <code>submissionFileStats</code>
     * relationship.
     * @return the entity in the relationship
     */
    public org.webcat.grader.SubmissionFileStats submissionFileStats()
    {
        return (org.webcat.grader.SubmissionFileStats)storedValueForKey( "submissionFileStats" );
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>submissionFileStats</code>
     * relationship (DO NOT USE--instead, use
     * <code>setSubmissionFileStatsRelationship()</code>.
     * This method is provided for WebObjects use.
     *
     * @param value The new entity to relate to
     */
    public void setSubmissionFileStats( org.webcat.grader.SubmissionFileStats value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setSubmissionFileStats("
                + value + "): was " + submissionFileStats() );
        }
        takeStoredValueForKey( value, "submissionFileStats" );
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>submissionFileStats</code>
     * relationship.  This method is a type-safe version of
     * <code>addObjectToBothSidesOfRelationshipWithKey()</code>.
     *
     * @param value The new entity to relate to
     */
    public void setSubmissionFileStatsRelationship(
        org.webcat.grader.SubmissionFileStats value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setSubmissionFileStatsRelationship("
                + value + "): was " + submissionFileStats() );
        }
        if ( value == null )
        {
            org.webcat.grader.SubmissionFileStats object = submissionFileStats();
            if ( object != null )
                removeObjectFromBothSidesOfRelationshipWithKey( object, "submissionFileStats" );
        }
        else
        {
            addObjectToBothSidesOfRelationshipWithKey( value, "submissionFileStats" );
        }
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the entity pointed to by the <code>user</code>
     * relationship.
     * @return the entity in the relationship
     */
    public org.webcat.core.User user()
    {
        return (org.webcat.core.User)storedValueForKey( "user" );
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>user</code>
     * relationship (DO NOT USE--instead, use
     * <code>setUserRelationship()</code>.
     * This method is provided for WebObjects use.
     *
     * @param value The new entity to relate to
     */
    public void setUser( org.webcat.core.User value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setUser("
                + value + "): was " + user() );
        }
        takeStoredValueForKey( value, "user" );
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>user</code>
     * relationship.  This method is a type-safe version of
     * <code>addObjectToBothSidesOfRelationshipWithKey()</code>.
     *
     * @param value The new entity to relate to
     */
    public void setUserRelationship(
        org.webcat.core.User value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setUserRelationship("
                + value + "): was " + user() );
        }
        if ( value == null )
        {
            org.webcat.core.User object = user();
            if ( object != null )
                removeObjectFromBothSidesOfRelationshipWithKey( object, "user" );
        }
        else
        {
            addObjectToBothSidesOfRelationshipWithKey( value, "user" );
        }
    }


    // ----------------------------------------------------------
    /**
     * Retrieve objects using a fetch specification.
     *
     * @param context The editing context to use
     * @param fspec The fetch specification to use
     *
     * @return an NSArray of the entities retrieved
     */
    @SuppressWarnings("unchecked")
    public static NSArray<GraderPrefs> objectsWithFetchSpecification(
        EOEditingContext context,
        EOFetchSpecification fspec)
    {
        return context.objectsWithFetchSpecification(fspec);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve all objects of this type.
     *
     * @param context The editing context to use
     *
     * @return an NSArray of the entities retrieved
     */
    public static NSArray<GraderPrefs> allObjects(
        EOEditingContext context)
    {
        return objectsMatchingQualifier(context, null, null);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve objects using a qualifier.
     *
     * @param context The editing context to use
     * @param qualifier The qualifier to use
     *
     * @return an NSArray of the entities retrieved
     */
    public static NSArray<GraderPrefs> objectsMatchingQualifier(
        EOEditingContext context,
        EOQualifier qualifier)
    {
        return objectsMatchingQualifier(context, qualifier, null);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve objects using a qualifier and sort orderings.
     *
     * @param context The editing context to use
     * @param qualifier The qualifier to use
     * @param sortOrderings The sort orderings to use
     *
     * @return an NSArray of the entities retrieved
     */
    public static NSArray<GraderPrefs> objectsMatchingQualifier(
        EOEditingContext context,
        EOQualifier qualifier,
        NSArray<EOSortOrdering> sortOrderings)
    {
        EOFetchSpecification fspec = new EOFetchSpecification(
            ENTITY_NAME, qualifier, sortOrderings);
        fspec.setUsesDistinct(true);
        return objectsWithFetchSpecification(context, fspec);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the first object that matches a qualifier, when
     * sorted with the specified sort orderings.
     *
     * @param context The editing context to use
     * @param qualifier The qualifier to use
     * @param sortOrderings the sort orderings
     *
     * @return the first entity that was retrieved, or null if there was none
     */
    public static GraderPrefs firstObjectMatchingQualifier(
        EOEditingContext context,
        EOQualifier qualifier,
        NSArray<EOSortOrdering> sortOrderings)
    {
        NSArray<GraderPrefs> objects =
            objectsMatchingQualifier(context, qualifier, sortOrderings);
        return (objects.size() > 0)
            ? objects.get(0)
            : null;
    }


    // ----------------------------------------------------------
    /**
     * Retrieve a single object using a list of keys and values to match.
     *
     * @param context The editing context to use
     * @param qualifier The qualifier to use
     *
     * @return the single entity that was retrieved
     *
     * @throws EOUtilities.MoreThanOneException
     *     if there is more than one matching object
     */
    public static GraderPrefs uniqueObjectMatchingQualifier(
        EOEditingContext context,
        EOQualifier qualifier) throws EOUtilities.MoreThanOneException
    {
        NSArray<GraderPrefs> objects =
            objectsMatchingQualifier(context, qualifier);
        if (objects.size() > 1)
        {
            throw new EOUtilities.MoreThanOneException(null);
        }
        return (objects.size() > 0)
            ? objects.get(0)
            : null;
    }


    // ----------------------------------------------------------
    /**
     * Retrieve objects using a list of keys and values to match.
     *
     * @param context The editing context to use
     * @param keysAndValues a list of keys and values to match, alternating
     *     "key", "value", "key", "value"...
     *
     * @return an NSArray of the entities retrieved
     */
    public static NSArray<GraderPrefs> objectsMatchingValues(
        EOEditingContext context,
        Object... keysAndValues)
    {
        if (keysAndValues.length % 2 != 0)
        {
            throw new IllegalArgumentException("There should a value " +
                "corresponding to every key that was passed.");
        }

        NSMutableDictionary<String, Object> valueDictionary =
            new NSMutableDictionary<String, Object>();

        for (int i = 0; i < keysAndValues.length; i += 2)
        {
            Object key = keysAndValues[i];
            Object value = keysAndValues[i + 1];

            if (!(key instanceof String))
            {
                throw new IllegalArgumentException("Keys should be strings.");
            }

            valueDictionary.setObjectForKey(value, key);
        }

        return objectsMatchingValues(context, valueDictionary);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve objects using a dictionary of keys and values to match.
     *
     * @param context The editing context to use
     * @param keysAndValues a dictionary of keys and values to match
     *
     * @return an NSArray of the entities retrieved
     */
    @SuppressWarnings("unchecked")
    public static NSArray<GraderPrefs> objectsMatchingValues(
        EOEditingContext context,
        NSDictionary<String, Object> keysAndValues)
    {
        return EOUtilities.objectsMatchingValues(context, ENTITY_NAME,
            keysAndValues);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the first object that matches a set of keys and values, when
     * sorted with the specified sort orderings.
     *
     * @param context The editing context to use
     * @param sortOrderings the sort orderings
     * @param keysAndValues a list of keys and values to match, alternating
     *     "key", "value", "key", "value"...
     *
     * @return the first entity that was retrieved, or null if there was none
     */
    public static GraderPrefs firstObjectMatchingValues(
        EOEditingContext context,
        NSArray<EOSortOrdering> sortOrderings,
        Object... keysAndValues)
    {
        if (keysAndValues.length % 2 != 0)
        {
            throw new IllegalArgumentException("There should a value " +
                "corresponding to every key that was passed.");
        }

        NSMutableDictionary<String, Object> valueDictionary =
            new NSMutableDictionary<String, Object>();

        for (int i = 0; i < keysAndValues.length; i += 2)
        {
            Object key = keysAndValues[i];
            Object value = keysAndValues[i + 1];

            if (!(key instanceof String))
            {
                throw new IllegalArgumentException("Keys should be strings.");
            }

            valueDictionary.setObjectForKey(value, key);
        }

        return firstObjectMatchingValues(
            context, sortOrderings, valueDictionary);
    }


    // ----------------------------------------------------------
    /**
     * Retrieves the first object that matches a set of keys and values, when
     * sorted with the specified sort orderings.
     *
     * @param context The editing context to use
     * @param sortOrderings the sort orderings
     * @param keysAndValues a dictionary of keys and values to match
     *
     * @return the first entity that was retrieved, or null if there was none
     */
    public static GraderPrefs firstObjectMatchingValues(
        EOEditingContext context,
        NSArray<EOSortOrdering> sortOrderings,
        NSDictionary<String, Object> keysAndValues)
    {
        EOFetchSpecification fspec = new EOFetchSpecification(
            ENTITY_NAME,
            EOQualifier.qualifierToMatchAllValues(keysAndValues),
            sortOrderings);
        fspec.setFetchLimit(1);

        NSArray<GraderPrefs> objects =
            objectsWithFetchSpecification( context, fspec );

        if ( objects.count() == 0 )
        {
            return null;
        }
        else
        {
            return objects.objectAtIndex(0);
        }
    }


    // ----------------------------------------------------------
    /**
     * Retrieve a single object using a list of keys and values to match.
     *
     * @param context The editing context to use
     * @param keysAndValues a list of keys and values to match, alternating
     *     "key", "value", "key", "value"...
     *
     * @return the single entity that was retrieved, or null if there was none
     *
     * @throws EOUtilities.MoreThanOneException
     *     if there is more than one matching object
     */
    public static GraderPrefs uniqueObjectMatchingValues(
        EOEditingContext context,
        Object... keysAndValues) throws EOUtilities.MoreThanOneException
    {
        if (keysAndValues.length % 2 != 0)
        {
            throw new IllegalArgumentException("There should a value " +
                "corresponding to every key that was passed.");
        }

        NSMutableDictionary<String, Object> valueDictionary =
            new NSMutableDictionary<String, Object>();

        for (int i = 0; i < keysAndValues.length; i += 2)
        {
            Object key = keysAndValues[i];
            Object value = keysAndValues[i + 1];

            if (!(key instanceof String))
            {
                throw new IllegalArgumentException("Keys should be strings.");
            }

            valueDictionary.setObjectForKey(value, key);
        }

        return uniqueObjectMatchingValues(context, valueDictionary);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve an object using a dictionary of keys and values to match.
     *
     * @param context The editing context to use
     * @param keysAndValues a dictionary of keys and values to match
     *
     * @return the single entity that was retrieved, or null if there was none
     *
     * @throws EOUtilities.MoreThanOneException
     *     if there is more than one matching object
     */
    public static GraderPrefs uniqueObjectMatchingValues(
        EOEditingContext context,
        NSDictionary<String, Object> keysAndValues)
        throws EOUtilities.MoreThanOneException
    {
        try
        {
            return (GraderPrefs)EOUtilities.objectMatchingValues(
                context, ENTITY_NAME, keysAndValues);
        }
        catch (EOObjectNotAvailableException e)
        {
            return null;
        }
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the count of all objects of this type.
     *
     * @param context The editing context to use
     *
     * @return the count of all objects
     */
    public static int countOfAllObjects(EOEditingContext context)
    {
        return countOfObjectsMatchingQualifier(context, null);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the count of objects that match a qualifier.
     *
     * @param context The editing context to use
     * @param qualifier The qualifier to use
     *
     * @return the count of objects matching the qualifier
     */
    public static int countOfObjectsMatchingQualifier(
        EOEditingContext context, EOQualifier qualifier)
    {
        return ERXEOControlUtilities.objectCountWithQualifier(
                context, ENTITY_NAME, qualifier);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the count of objects using a list of keys and values to match.
     *
     * @param context The editing context to use
     * @param keysAndValues a list of keys and values to match, alternating
     *     "key", "value", "key", "value"...
     *
     * @return the count of objects that match the specified values
     */
    public static int countOfObjectsMatchingValues(
        EOEditingContext context,
        Object... keysAndValues)
    {
        if (keysAndValues.length % 2 != 0)
        {
            throw new IllegalArgumentException("There should a value " +
                "corresponding to every key that was passed.");
        }

        NSMutableDictionary<String, Object> valueDictionary =
            new NSMutableDictionary<String, Object>();

        for (int i = 0; i < keysAndValues.length; i += 2)
        {
            Object key = keysAndValues[i];
            Object value = keysAndValues[i + 1];

            if (!(key instanceof String))
            {
                throw new IllegalArgumentException("Keys should be strings.");
            }

            valueDictionary.setObjectForKey(value, key);
        }

        return countOfObjectsMatchingValues(context, valueDictionary);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the count of objects using a dictionary of keys and values to
     * match.
     *
     * @param context The editing context to use
     * @param keysAndValues a dictionary of keys and values to match
     *
     * @return the count of objects that matched the specified values
     */
    public static int countOfObjectsMatchingValues(
        EOEditingContext context,
        NSDictionary<String, Object> keysAndValues)
    {
        return countOfObjectsMatchingQualifier(context,
                EOQualifier.qualifierToMatchAllValues(keysAndValues));
    }


    // ----------------------------------------------------------
    /**
     * Retrieve objects according to the <code>objectsForUser</code>
     * fetch specification.
     *
     * @param context The editing context to use
     * @param userBinding fetch spec parameter
     * @return an NSArray of the entities retrieved
     */
    public static NSArray<GraderPrefs> objectsForUser(
            EOEditingContext context,
            org.webcat.core.User userBinding
        )
    {
        EOFetchSpecification spec = EOFetchSpecification
            .fetchSpecificationNamed( "objectsForUser", "GraderPrefs" );

        NSMutableDictionary<String, Object> bindings =
            new NSMutableDictionary<String, Object>();

        if ( userBinding != null )
        {
            bindings.setObjectForKey( userBinding,
                                      "user" );
        }
        spec = spec.fetchSpecificationWithQualifierBindings( bindings );

        NSArray<GraderPrefs> objects =
            objectsWithFetchSpecification( context, spec );
        if (log.isDebugEnabled())
        {
            log.debug( "objectsForUser(ec"
                + ", " + userBinding
                + "): " + objects );
        }
        return objects;
    }


    // ----------------------------------------------------------
    /**
     * Produce a string representation of this object.  This implementation
     * calls UserPresentableDescription(), which uses WebObjects' internal
     * mechanism to print out the visible fields of this object.  Normally,
     * subclasses would override userPresentableDescription() to change
     * the way the object is printed.
     *
     * @return A string representation of the object's value
     */
    public String toString()
    {
        return userPresentableDescription();
    }


    //~ Instance/static variables .............................................

    static Logger log = Logger.getLogger( GraderPrefs.class );
}
