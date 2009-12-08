/*==========================================================================*\
 |  _EnqueuedReportGenerationJob.java
 |*-------------------------------------------------------------------------*|
 |  Created by eogenerator
 |  DO NOT EDIT.  Make changes to EnqueuedReportGenerationJob.java instead.
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

package net.sf.webcat.reporter;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import er.extensions.eof.ERXKey;
import java.util.Enumeration;
import org.apache.log4j.Logger;

// -------------------------------------------------------------------------
/**
 * An automatically generated EOGenericRecord subclass.  DO NOT EDIT.
 * To change, use EOModeler, or make additions in
 * EnqueuedReportGenerationJob.java.
 *
 * @author Generated by eogenerator
 * @version version suppressed to control auto-generation
 */
public abstract class _EnqueuedReportGenerationJob
    extends er.extensions.eof.ERXGenericRecord
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new _EnqueuedReportGenerationJob object.
     */
    public _EnqueuedReportGenerationJob()
    {
        super();
    }


    // ----------------------------------------------------------
    /**
     * A static factory method for creating a new
     * EnqueuedReportGenerationJob object given required
     * attributes and relationships.
     * @param editingContext The context in which the new object will be
     * inserted
     * @return The newly created object
     */
    public static EnqueuedReportGenerationJob create(
        EOEditingContext editingContext
        )
    {
        EnqueuedReportGenerationJob eoObject = (EnqueuedReportGenerationJob)
            EOUtilities.createAndInsertInstance(
                editingContext,
                _EnqueuedReportGenerationJob.ENTITY_NAME);
        return eoObject;
    }


    // ----------------------------------------------------------
    /**
     * Get a local instance of the given object in another editing context.
     * @param editingContext The target editing context
     * @param eo The object to import
     * @return An instance of the given object in the target editing context
     */
    public static EnqueuedReportGenerationJob localInstance(
        EOEditingContext editingContext, EnqueuedReportGenerationJob eo)
    {
        return (eo == null)
            ? null
            : (EnqueuedReportGenerationJob)EOUtilities.localInstanceOfObject(
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
    public static EnqueuedReportGenerationJob forId(
        EOEditingContext ec, int id )
    {
        EnqueuedReportGenerationJob obj = null;
        if (id > 0)
        {
            NSArray<EnqueuedReportGenerationJob> results =
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
    public static EnqueuedReportGenerationJob forId(
        EOEditingContext ec, String id )
    {
        return forId( ec, er.extensions.foundation.ERXValueUtilities.intValue( id ) );
    }


    //~ Constants (for key names) .............................................

    // Attributes ---
    public static final String DESCRIPTION_KEY = "description";
    public static final ERXKey<String> description =
        new ERXKey<String>(DESCRIPTION_KEY);
    public static final String QUEUE_TIME_KEY = "queueTime";
    public static final ERXKey<NSTimestamp> queueTime =
        new ERXKey<NSTimestamp>(QUEUE_TIME_KEY);
    // To-one relationships ---
    public static final String REPORT_TEMPLATE_KEY = "reportTemplate";
    public static final ERXKey<net.sf.webcat.reporter.ReportTemplate> reportTemplate =
        new ERXKey<net.sf.webcat.reporter.ReportTemplate>(REPORT_TEMPLATE_KEY);
    public static final String USER_KEY = "user";
    public static final ERXKey<net.sf.webcat.core.User> user =
        new ERXKey<net.sf.webcat.core.User>(USER_KEY);
    // To-many relationships ---
    public static final String DATA_SET_QUERIES_KEY = "dataSetQueries";
    public static final ERXKey<net.sf.webcat.reporter.ReportDataSetQuery> dataSetQueries =
        new ERXKey<net.sf.webcat.reporter.ReportDataSetQuery>(DATA_SET_QUERIES_KEY);
    // Fetch specifications ---
    public static final String USER_FSPEC = "user";
    public static final String ENTITY_NAME = "EnqueuedReportGenerationJob";


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Get a local instance of this object in another editing context.
     * @param editingContext The target editing context
     * @return An instance of this object in the target editing context
     */
    public EnqueuedReportGenerationJob localInstance(EOEditingContext editingContext)
    {
        return (EnqueuedReportGenerationJob)EOUtilities.localInstanceOfObject(
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
     * Retrieve this object's <code>description</code> value.
     * @return the value of the attribute
     */
    public String description()
    {
        return (String)storedValueForKey( "description" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>description</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setDescription( String value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setDescription("
                + value + "): was " + description() );
        }
        takeStoredValueForKey( value, "description" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>queueTime</code> value.
     * @return the value of the attribute
     */
    public NSTimestamp queueTime()
    {
        return (NSTimestamp)storedValueForKey( "queueTime" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>queueTime</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setQueueTime( NSTimestamp value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setQueueTime("
                + value + "): was " + queueTime() );
        }
        takeStoredValueForKey( value, "queueTime" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the entity pointed to by the <code>reportTemplate</code>
     * relationship.
     * @return the entity in the relationship
     */
    public net.sf.webcat.reporter.ReportTemplate reportTemplate()
    {
        return (net.sf.webcat.reporter.ReportTemplate)storedValueForKey( "reportTemplate" );
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>reportTemplate</code>
     * relationship (DO NOT USE--instead, use
     * <code>setReportTemplateRelationship()</code>.
     * This method is provided for WebObjects use.
     *
     * @param value The new entity to relate to
     */
    public void setReportTemplate( net.sf.webcat.reporter.ReportTemplate value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setReportTemplate("
                + value + "): was " + reportTemplate() );
        }
        takeStoredValueForKey( value, "reportTemplate" );
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>reportTemplate</code>
     * relationship.  This method is a type-safe version of
     * <code>addObjectToBothSidesOfRelationshipWithKey()</code>.
     *
     * @param value The new entity to relate to
     */
    public void setReportTemplateRelationship(
        net.sf.webcat.reporter.ReportTemplate value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setReportTemplateRelationship("
                + value + "): was " + reportTemplate() );
        }
        if ( value == null )
        {
            net.sf.webcat.reporter.ReportTemplate object = reportTemplate();
            if ( object != null )
                removeObjectFromBothSidesOfRelationshipWithKey( object, "reportTemplate" );
        }
        else
        {
            addObjectToBothSidesOfRelationshipWithKey( value, "reportTemplate" );
        }
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the entity pointed to by the <code>user</code>
     * relationship.
     * @return the entity in the relationship
     */
    public net.sf.webcat.core.User user()
    {
        return (net.sf.webcat.core.User)storedValueForKey( "user" );
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
    public void setUser( net.sf.webcat.core.User value )
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
        net.sf.webcat.core.User value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setUserRelationship("
                + value + "): was " + user() );
        }
        if ( value == null )
        {
            net.sf.webcat.core.User object = user();
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
     * Retrieve the entities pointed to by the <code>dataSetQueries</code>
     * relationship.
     * @return an NSArray of the entities in the relationship
     */
    @SuppressWarnings("unchecked")
    public NSArray<net.sf.webcat.reporter.ReportDataSetQuery> dataSetQueries()
    {
        return (NSArray)storedValueForKey( "dataSetQueries" );
    }


    // ----------------------------------------------------------
    /**
     * Replace the list of entities pointed to by the
     * <code>dataSetQueries</code> relationship.
     *
     * @param value The new set of entities to relate to
     */
    public void setDataSetQueries( NSMutableArray<net.sf.webcat.reporter.ReportDataSetQuery>  value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setDataSetQueries("
                + value + "): was " + dataSetQueries() );
        }
        takeStoredValueForKey( value, "dataSetQueries" );
    }


    // ----------------------------------------------------------
    /**
     * Add a new entity to the <code>dataSetQueries</code>
     * relationship (DO NOT USE--instead, use
     * <code>addToDataSetQueriesRelationship()</code>.
     * This method is provided for WebObjects use.
     *
     * @param value The new entity to relate to
     */
    public void addToDataSetQueries( net.sf.webcat.reporter.ReportDataSetQuery value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "addToDataSetQueries("
                + value + "): was " + dataSetQueries() );
        }
        NSMutableArray<net.sf.webcat.reporter.ReportDataSetQuery> array =
            (NSMutableArray<net.sf.webcat.reporter.ReportDataSetQuery>)dataSetQueries();
        willChange();
        array.addObject( value );
    }


    // ----------------------------------------------------------
    /**
     * Remove a specific entity from the <code>dataSetQueries</code>
     * relationship (DO NOT USE--instead, use
     * <code>removeFromDataSetQueriesRelationship()</code>.
     * This method is provided for WebObjects use.
     *
     * @param value The entity to remove from the relationship
     */
    public void removeFromDataSetQueries( net.sf.webcat.reporter.ReportDataSetQuery value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "RemoveFromDataSetQueries("
                + value + "): was " + dataSetQueries() );
        }
        NSMutableArray<net.sf.webcat.reporter.ReportDataSetQuery> array =
            (NSMutableArray<net.sf.webcat.reporter.ReportDataSetQuery>)dataSetQueries();
        willChange();
        array.removeObject( value );
    }


    // ----------------------------------------------------------
    /**
     * Add a new entity to the <code>dataSetQueries</code>
     * relationship.
     *
     * @param value The new entity to relate to
     */
    public void addToDataSetQueriesRelationship( net.sf.webcat.reporter.ReportDataSetQuery value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "addToDataSetQueriesRelationship("
                + value + "): was " + dataSetQueries() );
        }
        addObjectToBothSidesOfRelationshipWithKey(
            value, "dataSetQueries" );
    }


    // ----------------------------------------------------------
    /**
     * Remove a specific entity from the <code>dataSetQueries</code>
     * relationship.
     *
     * @param value The entity to remove from the relationship
     */
    public void removeFromDataSetQueriesRelationship( net.sf.webcat.reporter.ReportDataSetQuery value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "removeFromDataSetQueriesRelationship("
                + value + "): was " + dataSetQueries() );
        }
        removeObjectFromBothSidesOfRelationshipWithKey(
            value, "dataSetQueries" );
    }


    // ----------------------------------------------------------
    /**
     * Create a brand new object that is a member of the
     * <code>dataSetQueries</code> relationship.
     *
     * @return The new entity
     */
    public net.sf.webcat.reporter.ReportDataSetQuery createDataSetQueriesRelationship()
    {
        if (log.isDebugEnabled())
        {
            log.debug( "createDataSetQueriesRelationship()" );
        }
        EOClassDescription eoClassDesc = EOClassDescription
            .classDescriptionForEntityName( "ReportDataSetQuery" );
        EOEnterpriseObject eoObject = eoClassDesc
            .createInstanceWithEditingContext( editingContext(), null );
        editingContext().insertObject( eoObject );
        addObjectToBothSidesOfRelationshipWithKey(
            eoObject, "dataSetQueries" );
        return (net.sf.webcat.reporter.ReportDataSetQuery)eoObject;
    }


    // ----------------------------------------------------------
    /**
     * Remove and then delete a specific entity that is a member of the
     * <code>dataSetQueries</code> relationship.
     *
     * @param value The entity to remove from the relationship and then delete
     */
    public void deleteDataSetQueriesRelationship( net.sf.webcat.reporter.ReportDataSetQuery value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "deleteDataSetQueriesRelationship("
                + value + "): was " + dataSetQueries() );
        }
        removeObjectFromBothSidesOfRelationshipWithKey(
            value, "dataSetQueries" );
        editingContext().deleteObject( value );
    }


    // ----------------------------------------------------------
    /**
     * Remove (and then delete, if owned) all entities that are members of the
     * <code>dataSetQueries</code> relationship.
     */
    public void deleteAllDataSetQueriesRelationships()
    {
        if (log.isDebugEnabled())
        {
            log.debug( "deleteAllDataSetQueriesRelationships(): was "
                + dataSetQueries() );
        }
        Enumeration<?> objects = dataSetQueries().objectEnumerator();
        while ( objects.hasMoreElements() )
            deleteDataSetQueriesRelationship(
                (net.sf.webcat.reporter.ReportDataSetQuery)objects.nextElement() );
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
    public static NSArray<EnqueuedReportGenerationJob> objectsWithFetchSpecification(
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
    public static NSArray<EnqueuedReportGenerationJob> allObjects(
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
    public static NSArray<EnqueuedReportGenerationJob> objectsMatchingQualifier(
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
    public static NSArray<EnqueuedReportGenerationJob> objectsMatchingQualifier(
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
     * Retrieve objects using a list of keys and values to match.
     *
     * @param context The editing context to use
     * @param keysAndValues a list of keys and values to match, alternating
     *     "key", "value", "key", "value"...
     *
     * @return an NSArray of the entities retrieved
     */
    public static NSArray<EnqueuedReportGenerationJob> objectsMatchingValues(
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
    public static NSArray<EnqueuedReportGenerationJob> objectsMatchingValues(
        EOEditingContext context,
        NSDictionary<String, Object> keysAndValues)
    {
        return EOUtilities.objectsMatchingValues(context, ENTITY_NAME,
            keysAndValues);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve a single object using a list of keys and values to match.
     *
     * @param context The editing context to use
     * @param keysAndValues a list of keys and values to match, alternating
     *     "key", "value", "key", "value"...
     *
     * @return the single entity that was retrieved
     *
     * @throws EOObjectNotAvailableException
     *     if there is no matching object
     * @throws EOUtilities.MoreThanOneException
     *     if there is more than one matching object
     */
    public static EnqueuedReportGenerationJob objectMatchingValues(
        EOEditingContext context,
        Object... keysAndValues) throws EOObjectNotAvailableException,
                                        EOUtilities.MoreThanOneException
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

        return objectMatchingValues(context, valueDictionary);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve an object using a dictionary of keys and values to match.
     *
     * @param context The editing context to use
     * @param keysAndValues a dictionary of keys and values to match
     *
     * @return the single entity that was retrieved
     *
     * @throws EOObjectNotAvailableException
     *     if there is no matching object
     * @throws EOUtilities.MoreThanOneException
     *     if there is more than one matching object
     */
    public static EnqueuedReportGenerationJob objectMatchingValues(
        EOEditingContext context,
        NSDictionary<String, Object> keysAndValues)
        throws EOObjectNotAvailableException,
               EOUtilities.MoreThanOneException
    {
        return (EnqueuedReportGenerationJob)EOUtilities.objectMatchingValues(
            context, ENTITY_NAME, keysAndValues);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve object according to the <code>User</code>
     * fetch specification.
     *
     * @param context The editing context to use
     * @param userBinding fetch spec parameter
     * @return an NSArray of the entities retrieved
     */
    public static NSArray<EnqueuedReportGenerationJob> objectsForUser(
            EOEditingContext context,
            net.sf.webcat.core.User userBinding
        )
    {
        EOFetchSpecification spec = EOFetchSpecification
            .fetchSpecificationNamed( "user", "EnqueuedReportGenerationJob" );

        NSMutableDictionary<String, Object> bindings =
            new NSMutableDictionary<String, Object>();

        if ( userBinding != null )
        {
            bindings.setObjectForKey( userBinding,
                                      "user" );
        }
        spec = spec.fetchSpecificationWithQualifierBindings( bindings );

        NSArray<EnqueuedReportGenerationJob> result = objectsWithFetchSpecification( context, spec );
        if (log.isDebugEnabled())
        {
            log.debug( "objectsForUser(ec"
                + ", " + userBinding
                + "): " + result );
        }
        return result;
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

    static Logger log = Logger.getLogger( EnqueuedReportGenerationJob.class );
}
