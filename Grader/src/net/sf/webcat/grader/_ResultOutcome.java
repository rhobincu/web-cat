/*==========================================================================*\
 |  _ResultOutcome.java
 |*-------------------------------------------------------------------------*|
 |  Created by eogenerator
 |  DO NOT EDIT.  Make changes to ResultOutcome.java instead.
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

package net.sf.webcat.grader;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import er.extensions.eof.ERXKey;
import org.apache.log4j.Logger;

// -------------------------------------------------------------------------
/**
 * An automatically generated EOGenericRecord subclass.  DO NOT EDIT.
 * To change, use EOModeler, or make additions in
 * ResultOutcome.java.
 *
 * @author Generated by eogenerator
 * @version version suppressed to control auto-generation
 */
public abstract class _ResultOutcome
    extends er.extensions.eof.ERXGenericRecord
    implements net.sf.webcat.core.MutableContainer.MutableContainerOwner
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new _ResultOutcome object.
     */
    public _ResultOutcome()
    {
        super();
    }


    // ----------------------------------------------------------
    /**
     * A static factory method for creating a new
     * ResultOutcome object given required
     * attributes and relationships.
     * @param editingContext The context in which the new object will be
     * inserted
     * @param updateMutableFields
     * @return The newly created object
     */
    public static ResultOutcome create(
        EOEditingContext editingContext,
        boolean updateMutableFieldsValue
        )
    {
        ResultOutcome eoObject = (ResultOutcome)
            EOUtilities.createAndInsertInstance(
                editingContext,
                _ResultOutcome.ENTITY_NAME);
        eoObject.setUpdateMutableFields(updateMutableFieldsValue);
        return eoObject;
    }


    // ----------------------------------------------------------
    /**
     * Get a local instance of the given object in another editing context.
     * @param editingContext The target editing context
     * @param eo The object to import
     * @return An instance of the given object in the target editing context
     */
    public static ResultOutcome localInstance(
        EOEditingContext editingContext, ResultOutcome eo)
    {
        return (eo == null)
            ? null
            : (ResultOutcome)EOUtilities.localInstanceOfObject(
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
    public static ResultOutcome forId(
        EOEditingContext ec, int id )
    {
        ResultOutcome obj = null;
        if (id > 0)
        {
            NSArray<ResultOutcome> results =
                objectsMatchingValues(ec, "id", new Integer(id));
            if (results != null && results.count() > 0)
            {
                obj = results.objectAtIndex(0);
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
    public static ResultOutcome forId(
        EOEditingContext ec, String id )
    {
        return forId( ec, er.extensions.foundation.ERXValueUtilities.intValue( id ) );
    }


    //~ Constants (for key names) .............................................

    // Attributes ---
    public static final String CONTENTS_KEY = "contents";
    public static final ERXKey<NSData> contents =
        new ERXKey<NSData>(CONTENTS_KEY);
    public static final String INDEX_KEY = "index";
    public static final ERXKey<Integer> index =
        new ERXKey<Integer>(INDEX_KEY);
    public static final String TAG_KEY = "tag";
    public static final ERXKey<String> tag =
        new ERXKey<String>(TAG_KEY);
    public static final String UPDATE_MUTABLE_FIELDS_KEY = "updateMutableFields";
    public static final ERXKey<Integer> updateMutableFields =
        new ERXKey<Integer>(UPDATE_MUTABLE_FIELDS_KEY);
    // To-one relationships ---
    public static final String SUBMISSION_KEY = "submission";
    public static final ERXKey<net.sf.webcat.grader.Submission> submission =
        new ERXKey<net.sf.webcat.grader.Submission>(SUBMISSION_KEY);
    public static final String SUBMISSION_RESULT_KEY = "submissionResult";
    public static final ERXKey<net.sf.webcat.grader.SubmissionResult> submissionResult =
        new ERXKey<net.sf.webcat.grader.SubmissionResult>(SUBMISSION_RESULT_KEY);
    // To-many relationships ---
    // Fetch specifications ---
    public static final String ENTITY_NAME = "ResultOutcome";


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Get a local instance of this object in another editing context.
     * @param editingContext The target editing context
     * @return An instance of this object in the target editing context
     */
    public ResultOutcome localInstance(EOEditingContext editingContext)
    {
        return (ResultOutcome)EOUtilities.localInstanceOfObject(
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

    //-- Local mutable cache --
    private net.sf.webcat.core.MutableDictionary contentsCache;
    private NSData contentsRawCache;

    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>contents</code> value.
     * @return the value of the attribute
     */
    public net.sf.webcat.core.MutableDictionary contents()
    {
        NSData dbValue =
            (NSData)storedValueForKey( "contents" );
        if ( contentsRawCache != dbValue )
        {
            if ( dbValue != null && dbValue.equals( contentsRawCache ) )
            {
                // They are still equal, so just update the raw cache
                contentsRawCache = dbValue;
            }
            else
            {
                // Underlying attribute may have changed because
                // of a concurrent update through another editing
                // context, so throw away current values.
                contentsRawCache = dbValue;
                net.sf.webcat.core.MutableDictionary newValue =
                    net.sf.webcat.core.MutableDictionary
                    .objectWithArchiveData( dbValue );
                if ( contentsCache != null )
                {
                    contentsCache.copyFrom( newValue );
                }
                else
                {
                    contentsCache = newValue;
                }
                contentsCache.setOwner( this );
                setUpdateMutableFields( true );
            }
        }
        else if ( dbValue == null && contentsCache == null )
        {
            contentsCache =
                net.sf.webcat.core.MutableDictionary
                .objectWithArchiveData( dbValue );
             contentsCache.setOwner( this );
             setUpdateMutableFields( true );
        }
        return contentsCache;
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>contents</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setContents( net.sf.webcat.core.MutableDictionary value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setContents("
                + value + ")" );
        }
        if ( contentsCache == null )
        {
            contentsCache = value;
            value.setHasChanged( false );
            contentsRawCache = value.archiveData();
            takeStoredValueForKey( contentsRawCache, "contents" );
        }
        else if ( contentsCache != value )  // ( contentsCache != null )
        {
            contentsCache.copyFrom( value );
            setUpdateMutableFields( true );
        }
        else  // ( contentsCache == non-null value )
        {
            // no nothing
        }
    }


    // ----------------------------------------------------------
    /**
     * Clear the value of this object's <code>contents</code>
     * property.
     */
    public void clearContents()
    {
        if (log.isDebugEnabled())
        {
            log.debug( "clearContents()" );
        }
        takeStoredValueForKey( null, "contents" );
        contentsRawCache = null;
        contentsCache = null;
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>index</code> value.
     * @return the value of the attribute
     */
    public Integer index()
    {
        return (Integer)storedValueForKey( "index" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>index</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setIndex( Integer value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setIndex("
                + value + "): was " + index() );
        }
        takeStoredValueForKey( value, "index" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>tag</code> value.
     * @return the value of the attribute
     */
    public String tag()
    {
        return (String)storedValueForKey( "tag" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>tag</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setTag( String value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setTag("
                + value + "): was " + tag() );
        }
        takeStoredValueForKey( value, "tag" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>updateMutableFields</code> value.
     * @return the value of the attribute
     */
    public boolean updateMutableFields()
    {
        Integer result =
            (Integer)storedValueForKey( "updateMutableFields" );
        return ( result == null )
            ? false
            : ( result.intValue() > 0 );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>updateMutableFields</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setUpdateMutableFields( boolean value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setUpdateMutableFields("
                + value + "): was " + updateMutableFields() );
        }
        Integer actual =
            er.extensions.eof.ERXConstant.integerForInt( value ? 1 : 0 );
            setUpdateMutableFieldsRaw( actual );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>updateMutableFields</code> value.
     * @return the value of the attribute
     */
    public Integer updateMutableFieldsRaw()
    {
        return (Integer)storedValueForKey( "updateMutableFields" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>updateMutableFields</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setUpdateMutableFieldsRaw( Integer value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setUpdateMutableFieldsRaw("
                + value + "): was " + updateMutableFieldsRaw() );
        }
        takeStoredValueForKey( value, "updateMutableFields" );
    }


    // ----------------------------------------------------------
    /**
     * Called just before this object is saved to the database.
     */
    public void saveMutables()
    {
        log.debug("saveMutables()");
        if ( contentsCache != null
            && contentsCache.hasChanged() )
        {
            contentsRawCache = contentsCache.archiveData();
            takeStoredValueForKey( contentsRawCache, "contents" );
            contentsCache.setHasChanged( false );
        }

        setUpdateMutableFields( false );
    }


    // ----------------------------------------------------------
    /**
     * Called just before this object is saved to the database.
     */
    public void willUpdate()
    {
        log.debug("willUpdate()");
        saveMutables();
        super.willUpdate();
    }


    // ----------------------------------------------------------
    /**
     * Called just before this object is inserted into the database.
     */
    public void willInsert()
    {
        log.debug("willInsert()");
        saveMutables();
        super.willInsert();
    }


    // ----------------------------------------------------------
    /**
     * Called when the object is invalidated.
     */
    public void flushCaches()
    {
        log.debug("flushCaches()");
        contentsCache = null;
        contentsRawCache  = null;
        super.flushCaches();
    }


    // ----------------------------------------------------------
    /**
     * Called when an owned mutable container object is changed.
     */
    public void mutableContainerHasChanged()
    {
        setUpdateMutableFields( true );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the entity pointed to by the <code>submission</code>
     * relationship.
     * @return the entity in the relationship
     */
    public net.sf.webcat.grader.Submission submission()
    {
        return (net.sf.webcat.grader.Submission)storedValueForKey( "submission" );
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
    public void setSubmission( net.sf.webcat.grader.Submission value )
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
        net.sf.webcat.grader.Submission value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setSubmissionRelationship("
                + value + "): was " + submission() );
        }
        if ( value == null )
        {
            net.sf.webcat.grader.Submission object = submission();
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
     * Retrieve the entity pointed to by the <code>submissionResult</code>
     * relationship.
     * @return the entity in the relationship
     */
    public net.sf.webcat.grader.SubmissionResult submissionResult()
    {
        return (net.sf.webcat.grader.SubmissionResult)storedValueForKey( "submissionResult" );
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>submissionResult</code>
     * relationship (DO NOT USE--instead, use
     * <code>setSubmissionResultRelationship()</code>.
     * This method is provided for WebObjects use.
     *
     * @param value The new entity to relate to
     */
    public void setSubmissionResult( net.sf.webcat.grader.SubmissionResult value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setSubmissionResult("
                + value + "): was " + submissionResult() );
        }
        takeStoredValueForKey( value, "submissionResult" );
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>submissionResult</code>
     * relationship.  This method is a type-safe version of
     * <code>addObjectToBothSidesOfRelationshipWithKey()</code>.
     *
     * @param value The new entity to relate to
     */
    public void setSubmissionResultRelationship(
        net.sf.webcat.grader.SubmissionResult value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setSubmissionResultRelationship("
                + value + "): was " + submissionResult() );
        }
        if ( value == null )
        {
            net.sf.webcat.grader.SubmissionResult object = submissionResult();
            if ( object != null )
                removeObjectFromBothSidesOfRelationshipWithKey( object, "submissionResult" );
        }
        else
        {
            addObjectToBothSidesOfRelationshipWithKey( value, "submissionResult" );
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
    public static NSArray<ResultOutcome> objectsWithFetchSpecification(
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
    public static NSArray<ResultOutcome> allObjects(
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
    public static NSArray<ResultOutcome> objectsMatchingQualifier(
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
    public static NSArray<ResultOutcome> objectsMatchingQualifier(
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
    public static ResultOutcome firstObjectMatchingQualifier(
        EOEditingContext context,
        EOQualifier qualifier,
        NSArray<EOSortOrdering> sortOrderings)
    {
        NSArray<ResultOutcome> results =
            objectsMatchingQualifier(context, qualifier, sortOrderings);
        return (results.size() > 0)
            ? results.get(0)
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
    public static ResultOutcome uniqueObjectMatchingQualifier(
        EOEditingContext context,
        EOQualifier qualifier) throws EOUtilities.MoreThanOneException
    {
        NSArray<ResultOutcome> results =
            objectsMatchingQualifier(context, qualifier);
        if (results.size() > 1)
        {
            throw new EOUtilities.MoreThanOneException(null);
        }
        return (results.size() > 0)
            ? results.get(0)
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
    public static NSArray<ResultOutcome> objectsMatchingValues(
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
    public static NSArray<ResultOutcome> objectsMatchingValues(
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
    public static ResultOutcome firstObjectMatchingValues(
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
    public static ResultOutcome firstObjectMatchingValues(
        EOEditingContext context,
        NSArray<EOSortOrdering> sortOrderings,
        NSDictionary<String, Object> keysAndValues)
    {
        EOFetchSpecification fspec = new EOFetchSpecification(
            ENTITY_NAME,
            EOQualifier.qualifierToMatchAllValues(keysAndValues),
            sortOrderings);
        fspec.setFetchLimit(1);

        NSArray<ResultOutcome> result =
            objectsWithFetchSpecification( context, fspec );

        if ( result.count() == 0 )
        {
            return null;
        }
        else
        {
            return result.objectAtIndex(0);
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
    public static ResultOutcome uniqueObjectMatchingValues(
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
    public static ResultOutcome uniqueObjectMatchingValues(
        EOEditingContext context,
        NSDictionary<String, Object> keysAndValues)
        throws EOUtilities.MoreThanOneException
    {
        try
        {
            return (ResultOutcome)EOUtilities.objectMatchingValues(
                context, ENTITY_NAME, keysAndValues);
        }
        catch (EOObjectNotAvailableException e)
        {
            return null;
        }
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

    static Logger log = Logger.getLogger( ResultOutcome.class );
}
