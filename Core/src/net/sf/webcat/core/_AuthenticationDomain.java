/*==========================================================================*\
 |  _AuthenticationDomain.java
 |*-------------------------------------------------------------------------*|
 |  Created by eogenerator
 |  DO NOT EDIT.  Make changes to AuthenticationDomain.java instead.
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

package net.sf.webcat.core;

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
 * AuthenticationDomain.java.
 *
 * @author Generated by eogenerator
 * @version version suppressed to control auto-generation
 */
public abstract class _AuthenticationDomain
    extends er.extensions.eof.ERXGenericRecord
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new _AuthenticationDomain object.
     */
    public _AuthenticationDomain()
    {
        super();
    }


    // ----------------------------------------------------------
    /**
     * A static factory method for creating a new
     * AuthenticationDomain object given required
     * attributes and relationships.
     * @param editingContext The context in which the new object will be
     * inserted
     * @return The newly created object
     */
    public static AuthenticationDomain create(
        EOEditingContext editingContext
        )
    {
        AuthenticationDomain eoObject = (AuthenticationDomain)
            EOUtilities.createAndInsertInstance(
                editingContext,
                _AuthenticationDomain.ENTITY_NAME);
        return eoObject;
    }


    // ----------------------------------------------------------
    /**
     * Get a local instance of the given object in another editing context.
     * @param editingContext The target editing context
     * @param eo The object to import
     * @return An instance of the given object in the target editing context
     */
    public static AuthenticationDomain localInstance(
        EOEditingContext editingContext, AuthenticationDomain eo)
    {
        return (eo == null)
            ? null
            : (AuthenticationDomain)EOUtilities.localInstanceOfObject(
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
    public static AuthenticationDomain forId(
        EOEditingContext ec, int id )
    {
        AuthenticationDomain obj = null;
        if (id > 0)
        {
            NSArray<AuthenticationDomain> results =
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
    public static AuthenticationDomain forId(
        EOEditingContext ec, String id )
    {
        return forId( ec, er.extensions.foundation.ERXValueUtilities.intValue( id ) );
    }


    //~ Constants (for key names) .............................................

    // Attributes ---
    public static final String DATE_FORMAT_KEY = "dateFormat";
    public static final ERXKey<String> dateFormat =
        new ERXKey<String>(DATE_FORMAT_KEY);
    public static final String DEFAULT_EMAIL_DOMAIN_KEY = "defaultEmailDomain";
    public static final ERXKey<String> defaultEmailDomain =
        new ERXKey<String>(DEFAULT_EMAIL_DOMAIN_KEY);
    public static final String DEFAULT_URL_PATTERN_KEY = "defaultURLPattern";
    public static final ERXKey<String> defaultURLPattern =
        new ERXKey<String>(DEFAULT_URL_PATTERN_KEY);
    public static final String DISPLAYABLE_NAME_KEY = "displayableName";
    public static final ERXKey<String> displayableName =
        new ERXKey<String>(DISPLAYABLE_NAME_KEY);
    public static final String PROPERTY_NAME_KEY = "propertyName";
    public static final ERXKey<String> propertyName =
        new ERXKey<String>(PROPERTY_NAME_KEY);
    public static final String TIME_FORMAT_KEY = "timeFormat";
    public static final ERXKey<String> timeFormat =
        new ERXKey<String>(TIME_FORMAT_KEY);
    public static final String TIME_ZONE_NAME_KEY = "timeZoneName";
    public static final ERXKey<String> timeZoneName =
        new ERXKey<String>(TIME_ZONE_NAME_KEY);
    // To-one relationships ---
    // To-many relationships ---
    // Fetch specifications ---
    public static final String FETCH_ALL_FSPEC = "FetchAll";
    public static final String ENTITY_NAME = "AuthenticationDomain";


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Get a local instance of this object in another editing context.
     * @param editingContext The target editing context
     * @return An instance of this object in the target editing context
     */
    public AuthenticationDomain localInstance(EOEditingContext editingContext)
    {
        return (AuthenticationDomain)EOUtilities.localInstanceOfObject(
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
     * Retrieve this object's <code>dateFormat</code> value.
     * @return the value of the attribute
     */
    public String dateFormat()
    {
        return (String)storedValueForKey( "dateFormat" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>dateFormat</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setDateFormat( String value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setDateFormat("
                + value + "): was " + dateFormat() );
        }
        takeStoredValueForKey( value, "dateFormat" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>defaultEmailDomain</code> value.
     * @return the value of the attribute
     */
    public String defaultEmailDomain()
    {
        return (String)storedValueForKey( "defaultEmailDomain" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>defaultEmailDomain</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setDefaultEmailDomain( String value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setDefaultEmailDomain("
                + value + "): was " + defaultEmailDomain() );
        }
        takeStoredValueForKey( value, "defaultEmailDomain" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>defaultURLPattern</code> value.
     * @return the value of the attribute
     */
    public String defaultURLPattern()
    {
        return (String)storedValueForKey( "defaultURLPattern" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>defaultURLPattern</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setDefaultURLPattern( String value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setDefaultURLPattern("
                + value + "): was " + defaultURLPattern() );
        }
        takeStoredValueForKey( value, "defaultURLPattern" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>displayableName</code> value.
     * @return the value of the attribute
     */
    public String displayableName()
    {
        return (String)storedValueForKey( "displayableName" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>displayableName</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setDisplayableName( String value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setDisplayableName("
                + value + "): was " + displayableName() );
        }
        takeStoredValueForKey( value, "displayableName" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>propertyName</code> value.
     * @return the value of the attribute
     */
    public String propertyName()
    {
        return (String)storedValueForKey( "propertyName" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>propertyName</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setPropertyName( String value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setPropertyName("
                + value + "): was " + propertyName() );
        }
        takeStoredValueForKey( value, "propertyName" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>timeFormat</code> value.
     * @return the value of the attribute
     */
    public String timeFormat()
    {
        return (String)storedValueForKey( "timeFormat" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>timeFormat</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setTimeFormat( String value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setTimeFormat("
                + value + "): was " + timeFormat() );
        }
        takeStoredValueForKey( value, "timeFormat" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>timeZoneName</code> value.
     * @return the value of the attribute
     */
    public String timeZoneName()
    {
        return (String)storedValueForKey( "timeZoneName" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>timeZoneName</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setTimeZoneName( String value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setTimeZoneName("
                + value + "): was " + timeZoneName() );
        }
        takeStoredValueForKey( value, "timeZoneName" );
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
    public static NSArray<AuthenticationDomain> objectsWithFetchSpecification(
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
    public static NSArray<AuthenticationDomain> allObjects(
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
    public static NSArray<AuthenticationDomain> objectsMatchingQualifier(
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
    public static NSArray<AuthenticationDomain> objectsMatchingQualifier(
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
    public static NSArray<AuthenticationDomain> objectsMatchingValues(
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
    public static NSArray<AuthenticationDomain> objectsMatchingValues(
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
    public static AuthenticationDomain objectMatchingValues(
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
    public static AuthenticationDomain objectMatchingValues(
        EOEditingContext context,
        NSDictionary<String, Object> keysAndValues)
        throws EOObjectNotAvailableException,
               EOUtilities.MoreThanOneException
    {
        return (AuthenticationDomain)EOUtilities.objectMatchingValues(
            context, ENTITY_NAME, keysAndValues);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve object according to the <code>FetchAll</code>
     * fetch specification.
     *
     * @param context The editing context to use
     * @return an NSArray of the entities retrieved
     */
    public static NSArray<AuthenticationDomain> objectsForFetchAll(
            EOEditingContext context
        )
    {
        EOFetchSpecification spec = EOFetchSpecification
            .fetchSpecificationNamed( "FetchAll", "AuthenticationDomain" );

        NSArray<AuthenticationDomain> result = objectsWithFetchSpecification( context, spec );
        if (log.isDebugEnabled())
        {
            log.debug( "objectsForFetchAll(ec"
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

    static Logger log = Logger.getLogger( AuthenticationDomain.class );
}
