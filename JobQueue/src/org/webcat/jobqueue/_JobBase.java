/*==========================================================================*\
 |  _JobBase.java
 |*-------------------------------------------------------------------------*|
 |  Created by eogenerator
 |  DO NOT EDIT.  Make changes to JobBase.java instead.
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

package org.webcat.jobqueue;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import er.extensions.eof.ERXEOControlUtilities;
import er.extensions.eof.ERXKey;
import org.apache.log4j.Logger;
import org.webcat.core.EOBasedKeyGenerator;
import org.webcat.woextensions.WCFetchSpecification;

// -------------------------------------------------------------------------
/**
 * An automatically generated EOGenericRecord subclass.  DO NOT EDIT.
 * To change, use EOModeler, or make additions in
 * JobBase.java.
 *
 * @author Generated by eogenerator
 * @version version suppressed to control auto-generation
 */
public abstract class _JobBase
    extends er.extensions.eof.ERXGenericRecord
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new _JobBase object.
     */
    public _JobBase()
    {
        super();
    }


    //~ Constants (for key names) .............................................

    // Relationship to base slice ---
    private static final String BASE_PREFIX = "jobBase";
    private static final String BASE_PREFIX_DOT = BASE_PREFIX + ".";
    // Attributes ---
    public static final String ENQUEUE_TIME_KEY = BASE_PREFIX_DOT + "enqueueTime";
    public static final ERXKey<NSTimestamp> enqueueTime =
        new ERXKey<NSTimestamp>(ENQUEUE_TIME_KEY);
    public static final String IS_CANCELLED_KEY = BASE_PREFIX_DOT + "isCancelled";
    public static final ERXKey<Integer> isCancelled =
        new ERXKey<Integer>(IS_CANCELLED_KEY);
    public static final String IS_READY_KEY = BASE_PREFIX_DOT + "isReady";
    public static final ERXKey<Integer> isReady =
        new ERXKey<Integer>(IS_READY_KEY);
    public static final String PRIORITY_KEY = BASE_PREFIX_DOT + "priority";
    public static final ERXKey<Integer> priority =
        new ERXKey<Integer>(PRIORITY_KEY);
    public static final String PROGRESS_KEY = BASE_PREFIX_DOT + "progress";
    public static final ERXKey<Double> progress =
        new ERXKey<Double>(PROGRESS_KEY);
    public static final String PROGRESS_MESSAGE_KEY = BASE_PREFIX_DOT + "progressMessage";
    public static final ERXKey<String> progressMessage =
        new ERXKey<String>(PROGRESS_MESSAGE_KEY);
    public static final String SCHEDULED_TIME_KEY = BASE_PREFIX_DOT + "scheduledTime";
    public static final ERXKey<NSTimestamp> scheduledTime =
        new ERXKey<NSTimestamp>(SCHEDULED_TIME_KEY);
    public static final String SUSPENSION_REASON_KEY = BASE_PREFIX_DOT + "suspensionReason";
    public static final ERXKey<String> suspensionReason =
        new ERXKey<String>(SUSPENSION_REASON_KEY);
    // To-one relationships ---
    public static final String USER_KEY = BASE_PREFIX_DOT + "user";
    public static final ERXKey<org.webcat.core.User> user =
        new ERXKey<org.webcat.core.User>(USER_KEY);
    public static final String WORKER_KEY = BASE_PREFIX_DOT + "worker";
    public static final ERXKey<org.webcat.jobqueue.WorkerDescriptor> worker =
        new ERXKey<org.webcat.jobqueue.WorkerDescriptor>(WORKER_KEY);
    // To-many relationships ---
    // Fetch specifications ---
    public static final String NEXT_JOB_FSPEC = "nextJob";

    public transient final EOBasedKeyGenerator generateKey =
        new EOBasedKeyGenerator(this);


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Get a local instance of this object in another editing context.
     * @param editingContext The target editing context
     * @return An instance of this object in the target editing context
     */
    public JobBase localInstance(EOEditingContext editingContext)
    {
        return (JobBase)EOUtilities.localInstanceOfObject(
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
            editingContext().committedSnapshotForObject(this));
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
                editingContext() , this).objectForKey("id");
        }
        catch (Exception e)
        {
            return er.extensions.eof.ERXConstant.ZeroInteger;
        }
    }

    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>enqueueTime</code> value.
     * @return the value of the attribute
     */
    public NSTimestamp enqueueTime()
    {
        return (NSTimestamp)storedValueForKey( "enqueueTime" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>enqueueTime</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setEnqueueTime( NSTimestamp value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setEnqueueTime("
                + value + "): was " + enqueueTime() );
        }
        takeStoredValueForKey( value, "enqueueTime" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>isCancelled</code> value.
     * @return the value of the attribute
     */
    public boolean isCancelled()
    {
        Integer returnValue =
            (Integer)storedValueForKey( "isCancelled" );
        return ( returnValue == null )
            ? false
            : ( returnValue.intValue() > 0 );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>isCancelled</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setIsCancelled( boolean value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setIsCancelled("
                + value + "): was " + isCancelled() );
        }
        Integer actual =
            er.extensions.eof.ERXConstant.integerForInt( value ? 1 : 0 );
            setIsCancelledRaw( actual );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>isCancelled</code> value.
     * @return the value of the attribute
     */
    public Integer isCancelledRaw()
    {
        return (Integer)storedValueForKey( "isCancelled" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>isCancelled</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setIsCancelledRaw( Integer value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setIsCancelledRaw("
                + value + "): was " + isCancelledRaw() );
        }
        takeStoredValueForKey( value, "isCancelled" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>isReady</code> value.
     * @return the value of the attribute
     */
    public boolean isReady()
    {
        Integer returnValue =
            (Integer)storedValueForKey( "isReady" );
        return ( returnValue == null )
            ? false
            : ( returnValue.intValue() > 0 );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>isReady</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setIsReady( boolean value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setIsReady("
                + value + "): was " + isReady() );
        }
        Integer actual =
            er.extensions.eof.ERXConstant.integerForInt( value ? 1 : 0 );
            setIsReadyRaw( actual );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>isReady</code> value.
     * @return the value of the attribute
     */
    public Integer isReadyRaw()
    {
        return (Integer)storedValueForKey( "isReady" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>isReady</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setIsReadyRaw( Integer value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setIsReadyRaw("
                + value + "): was " + isReadyRaw() );
        }
        takeStoredValueForKey( value, "isReady" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>priority</code> value.
     * @return the value of the attribute
     */
    public int priority()
    {
        Integer returnValue =
            (Integer)storedValueForKey( "priority" );
        return ( returnValue == null )
            ? 0
            : returnValue.intValue();
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>priority</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setPriority( int value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setPriority("
                + value + "): was " + priority() );
        }
        Integer actual =
            er.extensions.eof.ERXConstant.integerForInt( value );
            setPriorityRaw( actual );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>priority</code> value.
     * @return the value of the attribute
     */
    public Integer priorityRaw()
    {
        return (Integer)storedValueForKey( "priority" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>priority</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setPriorityRaw( Integer value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setPriorityRaw("
                + value + "): was " + priorityRaw() );
        }
        takeStoredValueForKey( value, "priority" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>progress</code> value.
     * @return the value of the attribute
     */
    public double progress()
    {
        Double returnValue =
            (Double)storedValueForKey( "progress" );
        return ( returnValue == null )
            ? 0.0
            : returnValue.doubleValue();
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>progress</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setProgress( double value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setProgress("
                + value + "): was " + progress() );
        }
        Double actual =
            new Double( value );
            setProgressRaw( actual );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>progress</code> value.
     * @return the value of the attribute
     */
    public Double progressRaw()
    {
        return (Double)storedValueForKey( "progress" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>progress</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setProgressRaw( Double value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setProgressRaw("
                + value + "): was " + progressRaw() );
        }
        takeStoredValueForKey( value, "progress" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>progressMessage</code> value.
     * @return the value of the attribute
     */
    public String progressMessage()
    {
        return (String)storedValueForKey( "progressMessage" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>progressMessage</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setProgressMessage( String value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setProgressMessage("
                + value + "): was " + progressMessage() );
        }
        takeStoredValueForKey( value, "progressMessage" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>scheduledTime</code> value.
     * @return the value of the attribute
     */
    public NSTimestamp scheduledTime()
    {
        return (NSTimestamp)storedValueForKey( "scheduledTime" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>scheduledTime</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setScheduledTime( NSTimestamp value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setScheduledTime("
                + value + "): was " + scheduledTime() );
        }
        takeStoredValueForKey( value, "scheduledTime" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>suspensionReason</code> value.
     * @return the value of the attribute
     */
    public String suspensionReason()
    {
        return (String)storedValueForKey( "suspensionReason" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>suspensionReason</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setSuspensionReason( String value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setSuspensionReason("
                + value + "): was " + suspensionReason() );
        }
        takeStoredValueForKey( value, "suspensionReason" );
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
     * Retrieve the entity pointed to by the <code>worker</code>
     * relationship.
     * @return the entity in the relationship
     */
    public org.webcat.jobqueue.WorkerDescriptor worker()
    {
        return (org.webcat.jobqueue.WorkerDescriptor)storedValueForKey( "worker" );
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>worker</code>
     * relationship (DO NOT USE--instead, use
     * <code>setWorkerRelationship()</code>.
     * This method is provided for WebObjects use.
     *
     * @param value The new entity to relate to
     */
    public void setWorker( org.webcat.jobqueue.WorkerDescriptor value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setWorker("
                + value + "): was " + worker() );
        }
        takeStoredValueForKey( value, "worker" );
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>worker</code>
     * relationship.  This method is a type-safe version of
     * <code>addObjectToBothSidesOfRelationshipWithKey()</code>.
     *
     * @param value The new entity to relate to
     */
    public void setWorkerRelationship(
        org.webcat.jobqueue.WorkerDescriptor value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setWorkerRelationship("
                + value + "): was " + worker() );
        }
        if ( value == null )
        {
            org.webcat.jobqueue.WorkerDescriptor object = worker();
            if ( object != null )
                removeObjectFromBothSidesOfRelationshipWithKey( object, "worker" );
        }
        else
        {
            addObjectToBothSidesOfRelationshipWithKey( value, "worker" );
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


    // ----------------------------------------------------------
    /**
     * Hack to workaround the need to include the base relationship when
     * fetching objects vs. getting/setting their values via KVC.
     *
     * @param key the key to access
     * @return the value of the key
     */
    public Object valueForKey(String key)
    {
        if (key.equals(BASE_PREFIX))
        {
            return this;
        }

        if (key.startsWith(BASE_PREFIX_DOT))
        {
            key = key.substring(BASE_PREFIX_DOT.length());
        }

        return super.valueForKey(key);
    }


    // ----------------------------------------------------------
    /**
     * Hack to workaround the need to include the base relationship when
     * fetching objects vs. getting/setting their values via KVC.
     *
     * @param value the new value of the key
     * @param key the key to access
     */
    public void takeValueForKey(Object value, String key)
    {
        if (key.startsWith(BASE_PREFIX_DOT))
        {
            key = key.substring(BASE_PREFIX_DOT.length());
        }

        super.takeValueForKey(value, key);
    }


    //~ Instance/static variables .............................................

    static Logger log = Logger.getLogger(JobBase.class);
}
