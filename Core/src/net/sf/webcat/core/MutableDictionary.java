/*==========================================================================*\
 |  $Id: MutableDictionary.java,v 1.2 2007/01/30 02:21:50 stedwar2 Exp $
 |*-------------------------------------------------------------------------*|
 |  Copyright (C) 2006 Virginia Tech
 |
 |  This file is part of Web-CAT.
 |
 |  Web-CAT is free software; you can redistribute it and/or modify
 |  it under the terms of the GNU General Public License as published by
 |  the Free Software Foundation; either version 2 of the License, or
 |  (at your option) any later version.
 |
 |  Web-CAT is distributed in the hope that it will be useful,
 |  but WITHOUT ANY WARRANTY; without even the implied warranty of
 |  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 |  GNU General Public License for more details.
 |
 |  You should have received a copy of the GNU General Public License
 |  along with Web-CAT; if not, write to the Free Software
 |  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA
 |
 |  Project manager: Stephen Edwards <edwards@cs.vt.edu>
 |  Virginia Tech CS Dept, 660 McBryde Hall (0106), Blacksburg, VA 24061 USA
\*==========================================================================*/

package net.sf.webcat.core;

import com.webobjects.foundation.*;
import er.extensions.ERXFileUtilities;
import java.io.*;
import java.util.Enumeration;
import org.apache.log4j.Logger;

//-------------------------------------------------------------------------
/**
 *  A customized subcass of NSDictionary that can be used as the value class
 *  for an EO attribute.  Based on the info in
 *  <a href="http://docs.info.apple.com/article.html?artnum=75173">http://docs.info.apple.com/article.html?artnum=75173</a>.
 *
 *  <b>NOTE:</b> there appears to be a bug when using MySQL that prevents
 *  custom value classes from working properly.  As a result, we have
 *  implemented our own portable version using EOGenerator, our customized
 *  EO Java template, and special values stored in the attribute's userInfo
 *  dictionary.  See the preferences attribute in the User class of the
 *  Core model for an example of how to set up a custom value class that
 *  works in MySQL.  Also, the userInfo dictionary is not inherited from
 *  an attribute's prototype, so each value class must provide its own
 *  copy of the userInfo dictionary in the EOModel.
 * 
 *  This custom solution also allows us to keep mutable attributes in an
 *  EO, since the EOGenerator template plus the new notifications provided
 *  by its ERXGenericRecord base class allow the value to be appropriately
 *  handled and updates to be safely recorded.  For this to work, a
 *  mutable class must provide both a {@link #hasChanged()} and a
 *  {@link #setHasChanged(boolean)} method (see below).
 * 
 *  @author  Stephen Edwards
 *  @version $Id: MutableDictionary.java,v 1.2 2007/01/30 02:21:50 stedwar2 Exp $
 */
public class MutableDictionary
    extends er.extensions.ERXMutableDictionary
    implements MutableContainer
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates an empty dictionary.
     */
    public MutableDictionary()
    {
        super();
    }


    //----------------------------------------------------------
    /**
     * Creates a dictionary containing the keys and values found in
     * otherDictionary.
     * @param otherDictionary the input dictionary from which the duplicate
     *                        dictionary is to be created
     */
    public MutableDictionary( NSDictionary otherDictionary )
    {
        super( otherDictionary );
        resetChildParents( false );
    }


    //~ Methods ...............................................................

    //----------------------------------------------------------
    /**
     * Creates a new, distinct dictionary that contains all the same
     * values as this one.  This method is necessary to support custom
     * value class usage correctly.
     * @return The duplicate object
     */
    public Object clone()
    {
        return new MutableDictionary( this );
    }


    // ----------------------------------------------------------
    /**
     * This is the conversion method that serializes this dictionary for
     * storage in the database.  It uses java serialization to serialize
     * this object into bytes within an NSData object.  We're using this
     * instead of ERXMutableDictionary.toBlob() since it is slightly more
     * efficient and has better error checking.
     *
     * @return An NSData object containing the serialized bytes of this object.
     */
    public NSData archiveData()
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(
            kOverheadAdjustment + count() * kCountBytesFactor );
        NSData result = null;
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream( bos );
            oos.writeObject( this );
            oos.flush();
            oos.close();
            result = new NSData( bos.toByteArray() );
        }
        catch ( IOException ioe )
        {
            if ( NSLog.debugLoggingAllowedForLevelAndGroups(
                     NSLog.DebugLevelCritical, NSLog.DebugGroupArchiving ) )
            {
                NSLog.debug.appendln( ioe );
            }

            if ( kThrowOnError )
            {
                throw new NSForwardException( ioe );
            }
        }

        return result;
    }


    // ----------------------------------------------------------
    /**
     * This is the factory method used to recreate a dictionary from a
     * database attribute. It uses java Serialization to turn bytes from an
     * NSData into a reconstituted Object.  We're using this instead of
     * ERXMutableDictionary.fromBlob() since it is slightly more efficient
     * and has better error checking.
     *
     * @param data This is the NSData holding the previously serialized bytes.
     * @return The un-serialized Object.
     */
    public static MutableDictionary objectWithArchiveData( NSData data )
    {
        if ( data == null ) return new MutableDictionary();
        ByteArrayInputStream bis = new ByteArrayInputStream( data.bytes() );
        MutableDictionary result = null;
        Throwable exception = null;
        try
        {
            ObjectInputStream ois = new ObjectInputStream( bis );
            Object o = ois.readObject();
            if ( o instanceof MutableDictionary )
            {
                result = (MutableDictionary)o;
            }
            else if ( o instanceof NSDictionary )
            {
                result = new MutableDictionary( (NSDictionary)o );
            }
            else
            {
                exception = new ClassCastException( "objectWithArchiveData(): "
                    + "cannot cast " + o.getClass().getName() + " to "
                    + MutableDictionary.class.getName() );
                // result is already null
            }
        }
        catch ( IOException ioe )
        {
            exception = ioe;
        }
        catch ( ClassNotFoundException cnfe )
        {
            exception = cnfe;
        }

        if ( exception != null )
        {
            if ( NSLog.debugLoggingAllowedForLevelAndGroups(
                     NSLog.DebugLevelCritical, NSLog.DebugGroupArchiving ) )
            {
                NSLog.debug.appendln( exception );
            }

            if ( kThrowOnError )
            {
                throw new NSForwardException( exception );
            }
        }

        return result;
    }


    //----------------------------------------------------------
    /**
     * Create a dictionary from a property list string.
     * @param plist the property list to read from
     * @return a new MutableDictionary containing the keys and values from
     * the property list (The signature returns the base class type to be
     * compatible with the method in the base class, but the actual object
     * belongs to this subclass)
     */
    public static er.extensions.ERXMutableDictionary fromPropertyList(
        String plist )
    {
        NSDictionary dict = (NSDictionary)NSPropertyListSerialization.
            propertyListFromString( plist );
        return new MutableDictionary( dict );
    }


    //----------------------------------------------------------
    /**
     * Create a dictionary from a property list string.
     * @param file the file to read from
     * @return a new MutableDictionary containing the keys and values from
     * the property list contents of the given file
     * @throws IOException if a problem occurs reading from the file
     */
    public static MutableDictionary fromPropertyList( File file )
        throws IOException
    {
        String stringFromFile = ERXFileUtilities.stringFromFile( file );
        NSDictionary dict = (NSDictionary)NSPropertyListSerialization.
            propertyListFromString( stringFromFile );
        return new MutableDictionary( dict );
    }


    //----------------------------------------------------------
    /**
     * Test this object to see if it has been changed (mutated) since it
     * was last saved.
     * @return true if this dictionary has been changed
     */
    public boolean hasChanged()
    {
        log.debug( "hasChanged() = " + hasChanged );
        return hasChanged;
    }


    //----------------------------------------------------------
    /**
     * Mark this object as having changed (mutated) since it
     * was last saved.
     * @param value true if this dictionary has been changed
     */
    public void setHasChanged( boolean value )
    {
        log.debug( "setHasChanged() = " + value );
        hasChanged = value;
        if ( hasChanged )
        {
            if ( parent != null )
            {
                parent.setHasChanged( value );
            }
            if ( owner != null )
            {
                owner.mutableContainerHasChanged();
            }
        }
    }


    //----------------------------------------------------------
    /**
     * Set the enclosing container that holds this one, if any.
     * @param parent a reference to the enclosing container
     */
    public void setParent( MutableContainer parent )
    {
        this.parent = parent;
    }


    //----------------------------------------------------------
    /**
     * Set the owner of this container.
     * @param owner the owner of this container container
     */
    public void setOwner( MutableContainerOwner owner )
    {
        this.owner = owner;
    }


    //----------------------------------------------------------
    /**
     * Set the enclosing container that holds this one, if any.
     * Also, recursively cycle through all contained mutable containers,
     * resetting their parents to this object as well.
     * @param parent a reference to the enclosing container
     */
    public void setParentRecursively( MutableContainer parent )
    {
        this.parent = parent;
        for ( Enumeration e = objectEnumerator(); e.hasMoreElements(); )
        {
            Object o = e.nextElement();
            
            if ( o instanceof MutableContainer )
            {
                ( (MutableContainer)o ).setParentRecursively( this );
            }
        }
    }
    
    
    //----------------------------------------------------------
    /**
     * Examine all contained objects for mutable containers, and reset
     * the parent relationships for any that are found.  Any NS containers
     * found will be converted to mutable versions.
     * @param recurse if true, force the reset to cascade recursively down
     *                the tree, rather than just affecting this node's
     *                immediate children.
     */
    public void resetChildParents( boolean recurse )
    {
        Object[] keys = keysNoCopy();
        for ( int i = 0; i < keys.length; i++ )
        {
            Object o = objectForKey( keys[i] );
            if ( o instanceof MutableContainer )
            {
                MutableContainer mc = (MutableContainer)o;
                mc.setParent( this );
                if ( recurse )
                {
                    mc.resetChildParents( recurse );
                }
            }
            else if ( o instanceof NSDictionary )
            {
                MutableDictionary md = new MutableDictionary( (NSDictionary)o );
                setObjectForKey( md, keys[i] );
            }
            else if ( o instanceof NSArray )
            {
                MutableArray ma = new MutableArray( (NSArray)o );
                setObjectForKey( ma, keys[i] );
            }
        }
    }


    //----------------------------------------------------------
    /**
     * Examine all contained objects for mutable containers, and clear
     * the parent relationships for any that are found.
     */
    public void clearChildParents()
    {
        for ( Enumeration e = objectEnumerator(); e.hasMoreElements(); )
        {
            setParentIfPossible( e.nextElement(), null );
        }
    }


    //----------------------------------------------------------
    /**
     * Retrieve the enclosing container that holds this one, if any.
     * @return a reference to the enclosing container
     */
    public MutableContainer parent()
    {
        return parent;
    }


    // ----------------------------------------------------------
    protected Object setParentIfPossible( Object o, MutableContainer p )
    {
        if ( o instanceof MutableContainer )
        {
            ( (MutableContainer)o ).setParent( p );
        }
        return o;
    }


    // ----------------------------------------------------------
    protected Object convertToMutableIfPossible( Object o )
    {
        if ( o instanceof MutableContainer )
        {
            ( (MutableContainer)o ).setParent( this );
        }
        else if ( o instanceof NSDictionary )
        {
            MutableDictionary md = new MutableDictionary( (NSDictionary)o );
            md.setParent( this );
            o = md;
        }
        else if ( o instanceof NSArray )
        {
            MutableArray ma = new MutableArray( (NSArray)o );
            ma.setParent( this );
            o = ma;
        }
        return o;
    }


    //----------------------------------------------------------
    public void addEntriesFromDictionary( NSDictionary arg0 )
    {
        setHasChanged( true );
        clearChildParents();
        super.addEntriesFromDictionary( arg0 );
        resetChildParents( false );
    }


    // ----------------------------------------------------------
    public Object remove( Object arg0 )
    {
        setHasChanged( true );
        return setParentIfPossible( super.remove( arg0 ), null );
    }


    // ----------------------------------------------------------
    public void removeAllObjects()
    {
        setHasChanged( true );
        clearChildParents();
        super.removeAllObjects();
    }


    // ----------------------------------------------------------
    public Object removeObjectForKey( Object arg0 )
    {
        setHasChanged( true );
        return setParentIfPossible( super.removeObjectForKey( arg0 ), null );
    }


    // ----------------------------------------------------------
    public void removeObjectsForKeys( NSArray arg0 )
    {
        setHasChanged( true );
        for ( int i = 0; i < arg0.count(); i++ )
        {
            setParentIfPossible( objectForKey( arg0.objectAtIndex( i ) ),
                                 null );
        }
        super.removeObjectsForKeys( arg0 );
    }


    // ----------------------------------------------------------
    public void setDictionary( NSDictionary arg0 )
    {
        setHasChanged( true );
        clearChildParents();
        super.setDictionary( arg0 );
        resetChildParents( false );
    }


    //----------------------------------------------------------
    /**
     * Replace this container's contents by copying from another (and
     * assuming parent ownership over any subcontainers).  The container
     * is free to assume the argument is of a compatible container type.
     * @param other the container to copy from
     */
    public void copyFrom( MutableContainer other )
    {
        setDictionary( (NSDictionary)other );
    }


    // ----------------------------------------------------------
    public void setObjectForKey( Object arg0, Object arg1 )
    {
        setHasChanged( true );
        Object old = objectForKey( arg1 );
        if ( old != null )
        {
            setParentIfPossible( old, null );
        }
        super.setObjectForKey( convertToMutableIfPossible( arg0 ), arg1 );
    }


    // ----------------------------------------------------------
    public void takeValueForKey( Object arg0, String arg1 )
    {
        setHasChanged( true );
        Object old = valueForKey( arg1 );
        if ( old != null )
        {
            setParentIfPossible( old, null );
        }
        super.takeValueForKey( convertToMutableIfPossible( arg0 ), arg1 );
    }


    // ----------------------------------------------------------
    public void takeValueForKeyPath( Object arg0, String arg1 )
    {
        setHasChanged( true );
        Object old = valueForKeyPath( arg1 );
        if ( old != null )
        {
            setParentIfPossible( old, null );
        }
        super.takeValueForKeyPath( convertToMutableIfPossible( arg0 ), arg1 );
    }


    // ----------------------------------------------------------
    /* (non-Javadoc)
     * @see com.webobjects.foundation.NSDictionary#toString()
     */
//    public String toString()
//    {
//        return "[@" + Integer.toHexString( hashCode() ) + "]"
//            + super.toString();
//    }


    //~ Instance/static variables .............................................

    /**
     * This helps create the ByteArrayOutputStream with a good space estimate.
     */
    private static final int kOverheadAdjustment = 512;

    /**
     * This also helps create the ByteArrayOutputStream with a good space
     * estimate.
     */
    private static final int kCountBytesFactor = 16;

    /**
     * This determines, when an error occurs, if we should throw an
     * NSForwardException or just return null.
     */
    private static final boolean kThrowOnError = true;

    private boolean          hasChanged = false;
    private MutableContainer parent     = null;
    private transient MutableContainerOwner owner = null;

    static final long serialVersionUID = 7401998243093488226L;
    static Logger log = Logger.getLogger( MutableDictionary.class );
}
