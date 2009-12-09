/*==========================================================================*\
 |  $Id: SubmissionProfile.java,v 1.13 2009/12/09 05:01:35 aallowat Exp $
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

import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import er.extensions.foundation.ERXArrayUtilities;
import org.apache.log4j.Logger;

// -------------------------------------------------------------------------
/**
 * Contains all the submission options for an assignment.
 *
 * @author Stephen Edwards
 * @author  latest changes by: $Author: aallowat $
 * @version $Revision: 1.13 $ $Date: 2009/12/09 05:01:35 $
 */
public class SubmissionProfile
    extends _SubmissionProfile
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new SubmissionProfile object.
     */
    public SubmissionProfile()
    {
        super();
    }


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    public void awakeFromInsertion( EOEditingContext ec )
    {
        super.awakeFromInsertion( ec );
        setSubmissionMethod( (byte)0 );
    }


    // ----------------------------------------------------------
    /**
     * Get a short (no longer than 60 characters) description of this profile,
     * which currently returns its {@link #name()} and its author's uid.
     * @return the description
     */
    public String userPresentableDescription()
    {
        String result = name();
        net.sf.webcat.core.User author = author();
        if ( author != null )
        {
            result += " (" + author.userName() + ")";
        }
        return result;
    }


    // ----------------------------------------------------------
    /**
     * Format a long value as a string, using "k" or "m" for suffixes
     * if the value is evenly divisible by 1024 or 1024*1024.
     * @param The value to format
     * @return The formatted value
     */
    public static String formatSizeValue(long size)
    {
        if (size % 1048576 == 0)
        {
            return (size/1048576) + "m";
        }
        else if (size % 1024 == 0)
        {
            return (size/1024) + "k";
        }
        else
        {
            return Long.toString(size);
        }
    }


    // ----------------------------------------------------------
    /**
     * Format a long value as a string, using "k" or "m" for suffixes
     * if the value is evenly divisible by 1024 or 1024*1024.
     * @param The value to format
     * @return The formatted value
     */
    public static String formatSizeValue(Long size)
    {
        return (size == null)
            ? null
            : formatSizeValue(size.longValue());
    }


    // ----------------------------------------------------------
    public static long parseFormattedLong(String valueAsString)
        throws NumberFormatException
    {
        long multiplier = 1;
        if (valueAsString.endsWith("m") || valueAsString.endsWith("M"))
        {
            multiplier = 1024 * 1024;
            valueAsString =
                valueAsString.substring(0, valueAsString.length() - 1);
        }
        else if (valueAsString.endsWith("k") || valueAsString.endsWith("K"))
        {
            multiplier = 1024;
            valueAsString =
                valueAsString.substring(0, valueAsString.length() - 1);
        }

        if (valueAsString.contains("."))
        {
            return (long)(Double.parseDouble(valueAsString) * multiplier);
        }
        else
        {
            return Long.parseLong(valueAsString) * multiplier;
        }
    }


    // ----------------------------------------------------------
    public static long maxMaxFileUploadSize()
    {
        return net.sf.webcat.core.Application
            .configurationProperties()
            .longForKeyWithDefault("grader.maxFileUploadSize", 1048576L);
    }


    // ----------------------------------------------------------
    public static long defaultMaxFileUploadSize()
    {
        long result = net.sf.webcat.core.Application
            .configurationProperties()
            .longForKeyWithDefault("grader.defaultMaxFileUploadSize", 204800L);
        long max = maxMaxFileUploadSize();
        return result <= max ? result : max;
    }


    // ----------------------------------------------------------
//    public static boolean maxFileUploadSizeIsWithinLimits( long value )
//    {
//        return value > 0 && value < maxMaxFileUploadSize();
//    }


    // ----------------------------------------------------------
    public void setMaxFileUploadSize( int value )
    {
        if (value <= 0 )
        {
            setMaxFileUploadSizeRaw(null);
        }
        else if ( value < maxMaxFileUploadSize() )
        {
            super.setMaxFileUploadSize(value);
        }
        else
        {
            super.setMaxFileUploadSize( maxMaxFileUploadSize() );
        }
    }


    // ----------------------------------------------------------
    public long effectiveMaxFileUploadSize()
    {
        long value = maxFileUploadSize();
        return ( value > 0L ) ? value : defaultMaxFileUploadSize();
    }


    // ----------------------------------------------------------
    public double correctnessPoints()
    {
        double cp = availablePoints() - taPoints() - toolPoints();
        if ( cp < 0.0 ) cp = 0.0;
        return cp;
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>submissionMethod</code> value.
     * @return the value of the attribute
     */
    public String submissionMethodAsString()
    {
        int i = submissionMethod();
        return submitters[i];
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>submissionMethod</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setSubmissionMethodAsString( String value )
    {
        for ( byte i = 0; i < submitters.length; i++ )
        {
            if ( submitters[i].equals( value ) )
            {
                setSubmissionMethod( i );
                return;
            }
        }
        setSubmissionMethod( (byte)0 );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve all submission profiles used by assignments associated
     * with the given course.  Also guarantees that an additional
     * submission profile, if specified, is included in the list.
     *
     * @param context The editing context to use
     * @param user   The user who's profiles should be listed
     * @param course The course to match against
     * @param mine An additional submission profile to include--probably
     * one that wouldn't be picked up by the fetch specification, say because
     * it has been added but not yet committed.  This parameter is only
     * added to the result if it is not already in the results of the fetch.
     * @return an NSArray of the entities retrieved
     */
    public static NSArray profilesForCourseIncludingMine(
            EOEditingContext context,
            net.sf.webcat.core.User user,
            net.sf.webcat.core.Course course,
            SubmissionProfile mine
        )
    {
        NSMutableArray results =
            profilesForCourse( context, course ).mutableClone();
        ERXArrayUtilities.addObjectsFromArrayWithoutDuplicates(
            results,
            profilesForUser( context, user ) );
        if ( mine != null && !results.containsObject( mine ) )
        {
            results.addObject( mine );
        }
        return results;
    }


    // ----------------------------------------------------------
    public NSArray timeUnits()
    {
        return timeUnitsArray;
    }


    // ----------------------------------------------------------
    public NSArray submitters()
    {
        return submittersArray;
    }


    // ----------------------------------------------------------
    public static class TimeUnit
    {
        public TimeUnit( String name, long factor )
        {
            this.name   = name;
            this.factor = factor;
        }

        public String name()
        {
            return name;
        }

        public long factor()
        {
            return factor;
        }

        public Long unitsFromRaw( Long raw )
        {
            if (raw == null)
            {
                return raw;
            }
            else
            {
                return raw.longValue() / factor;
            }
        }

        public Long rawFromUnits( Long units )
        {
            if (units == null)
            {
                return units;
            }
            else
            {
                return units.longValue() * factor;
            }
        }

        public boolean isUnitFor( long raw )
        {
            return ( raw % factor ) == 0;
        }

        private String name;
        private long   factor;
    }


// If you add instance variables to store property values you
// should add empty implementions of the Serialization methods
// to avoid unnecessary overhead (the properties will be
// serialized for you in the superclass).

//    // ----------------------------------------------------------
//    /**
//     * Serialize this object (an empty implementation, since the
//     * superclass handles this responsibility).
//     * @param out the stream to write to
//     */
//    private void writeObject( java.io.ObjectOutputStream out )
//        throws java.io.IOException
//    {
//    }
//
//
//    // ----------------------------------------------------------
//    /**
//     * Read in a serialized object (an empty implementation, since the
//     * superclass handles this responsibility).
//     * @param in the stream to read from
//     */
//    private void readObject( java.io.ObjectInputStream in )
//        throws java.io.IOException, java.lang.ClassNotFoundException
//    {
//    }


    //~ Instance/static variables .............................................

    public static final TimeUnit[] timeUnits = new TimeUnit[] {
            new TimeUnit( "Minute(s)",                      60000L ),
            new TimeUnit( "Hour(s)",                    60L*60000L ),
            new TimeUnit( "Day(s)",                 24L*60L*60000L ),
            new TimeUnit( "Week(s)",             7L*24L*60L*60000L ),
            new TimeUnit( "Month(s) (30 days)", 30L*24L*60L*60000L )
        };

    public static final String[] submitters = new String[] {
        "Not listed for external submission",
        "List for BlueJ submitter",
        "List for Eclipse submitter"
    };

    public static final NSArray timeUnitsArray = new NSArray( timeUnits );

    public static final NSArray submittersArray = new NSArray( submitters );

    static Logger log = Logger.getLogger( SubmissionProfile.class );
}
