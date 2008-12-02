/*==========================================================================*\
 |  $Id: Submission.java,v 1.18 2008/12/02 22:23:20 aallowat Exp $
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

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import er.extensions.eof.ERXConstant;
import er.extensions.eof.ERXEOControlUtilities;
import er.extensions.eof.ERXQ;
import java.io.File;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import net.sf.webcat.core.*;
import org.apache.log4j.Logger;

// -------------------------------------------------------------------------
/**
 *  Represents a single student assignment submission.
 *
 *  @author Stephen Edwards
 *  @version $Id: Submission.java,v 1.18 2008/12/02 22:23:20 aallowat Exp $
 */
public class Submission
    extends _Submission
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new Submission object.
     */
    public Submission()
    {
        super();
    }


    //~ Constants (for key names) .............................................

    public static final String ID_FORM_KEY = "sid";


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Get a short (no longer than 60 characters) description of this
     * submission, which currently returns {@link #dirName()}.
     * @return the description
     */
    public String userPresentableDescription()
    {
        return dirName();
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the name of the directory where this submission is stored.
     * @return the directory name
     */
    public String dirName()
    {
        if ( partnerLink() && result() != null )
        {
            return result().submission().dirName();
        }
        else
        {
            StringBuffer dir =
                ( user() == null )
                ? new StringBuffer("<null>")
                : user().authenticationDomain().submissionBaseDirBuffer();
            if ( assignmentOffering() != null )
            {
                assignmentOffering().addSubdirTo( dir );
            }
            else
            {
                dir.append( "/ASSIGNMENT" );
            }
            dir.append( '/' );
            dir.append( ( user() == null )
                ? new StringBuffer("<null>")
                : user().userName() );
            dir.append( '/' );
            dir.append( submitNumber() );
            return dir.toString();
        }
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the submission file as a File object.
     * @return the file for this submission
     */
    public File file()
    {
        return new File( dirName(), fileName() );
    }


    // ----------------------------------------------------------
    public String toString()
    {
        if ( fileName() != null )
            return file().getPath();
        else
            return dirName();
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
            String subInfo = null;
            try
            {
                subInfo = toString();
            }
            catch (Exception ee)
            {
                subInfo = ee.toString();
            }
            Application.emailExceptionToAdmins(
                e, null, "An exception was generated trying to retrieve the "
                + "id for a submission.\n\nSubmission = " + subInfo );
            return ERXConstant.ZeroInteger;
        }
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the submission file as a File object.
     * @return the file for this submission
     */
    public String resultDirName()
    {
        return dirName() +  "/Results";
    }


    // ----------------------------------------------------------
    /**
     * Converts a time to its human-readable format.  Most useful
     * when the time is "small," like a difference between two
     * other time stamps.
     *
     * @param time The time to convert
     * @return     A human-readable version of the time
     */
    public static String getStringTimeRepresentation( long time )
    {
        long days;
        long hours;
        long minutes;
        // long seconds;
        // long milliseconds;
        StringBuffer result = new StringBuffer();

        days = time / 86400000;
        time %= 86400000;
        hours = time / 3600000;
        time %= 3600000;
        minutes = time / 60000;
        time %= 60000;
        // seconds      = time / 1000;
        // milliseconds = time % 1000;

        if ( days > 0 )
        {
            result.append( days );
            result.append( " day" );
            if ( days > 1 )
                result.append( 's' );
        }
        if ( hours > 0 )
        {
            if ( result.length() > 0 )
                result.append( ", " );
            result.append( hours );
            result.append( " hr" );
            if ( hours > 1 )
                result.append( 's' );
        }
        if ( minutes > 0 )
        {
            if ( result.length() > 0 )
                result.append( ", " );
            result.append( minutes );
            result.append( " min" );
            if ( minutes > 1 )
                result.append( 's' );
        }
        return result.toString();
    }


    // ----------------------------------------------------------
    /**
     * Returns a string version of the given file size.
     *
     * @param  size the file size to convert
     * @return the file size as a string
     */
    public static String fileSizeAsString( long size )
    {
        StringBuffer result = new StringBuffer( 10 );
        if ( size < 1024L )
        {
            result.append( size );
            result.append( " bytes" );
        }
        else if ( size < 1048576L )
        {
            double sz = size / 1024.0;
            DecimalFormat fmt = new DecimalFormat( "0.0" );
            fmt.format( sz, result,
                        new FieldPosition( DecimalFormat.FRACTION_FIELD ) );
            result.append( "kb" );
        }
        else
        {
            double sz = size / 1048576.0;
            DecimalFormat fmt = new DecimalFormat( "0.0" );
            fmt.format( sz, result,
                        new FieldPosition( DecimalFormat.FRACTION_FIELD ) );
            result.append( "mb" );
        }
        return result.toString();
    }


    // ----------------------------------------------------------
    /**
     * Checks whether the uploaded file is an archive.
     * @return true if the uploaded file is a zip or jar file
     */
//    public boolean fileIsArchive()
//    {
//        String fileName = fileName().toLowerCase();
//        return (    fileName != null
//                 && (    fileName.endsWith( ".zip" )
//                      || fileName.endsWith( ".jar" ) ) );
//    }


    // ----------------------------------------------------------
    public EnqueuedJob enqueuedJob()
    {
        NSArray jobs = enqueuedJobs();
        if ( jobs != null  &&  jobs.count() > 0 )
        {
            if ( jobs.count() > 1 )
            {
                log.error( "too many jobs for submission " + this );
            }
            return (EnqueuedJob)jobs.objectAtIndex( 0 );
        }
        else
        {
            return null;
        }
    }


    // ----------------------------------------------------------
    public Submission partnerSubmission( User             partner,
                                         int              submitNumber,
                                         EOEditingContext ec )
    {
        Submission newSubmission = new Submission();
        ec.insertObject( newSubmission );
        newSubmission.setFileName( fileName() );
        newSubmission.setPartnerLink( true );
        newSubmission.setSubmitNumber( submitNumber );
        newSubmission.setSubmitTime( submitTime() );
        newSubmission.setAssignmentOfferingRelationship(
            assignmentOffering() );
        newSubmission.setResultRelationship( result() );
        newSubmission.setUserRelationship( partner );
        // ec.saveChanges();
        return newSubmission;
    }


    // ----------------------------------------------------------
    /**
     * Delete all the result information for this submission, including
     * all partner links.  This method uses the submission's current
     * editing context to make changes, but does <b>not</b> commit those
     * changes to the database (the caller must use
     * <code>saveChanges()</code>).
     */
    public void deleteResultsAndRemovePartners()
    {
        SubmissionResult result = result();
        if ( result != null )
        {
            log.debug( "removing SubmissionResult " + result );
            result.setIsMostRecent( false );
            NSArray subs = result.submissions();
            for ( int i = 0; i < subs.count(); i++ )
            {
                Submission s = (Submission)subs.objectAtIndex( i );
                s.setResultRelationship( null );
                if ( s.partnerLink() )
                {
                    log.debug( "deleting partner Submission " + s );
                    editingContext().deleteObject( s );
                }
            }
            editingContext().deleteObject( result );
        }
    }


    // ----------------------------------------------------------
    /**
     * Delete all the result information for this submission, including
     * all partner links, and requeue it for grading.  This method uses the
     * submission's current editing context to make changes, but does
     * <b>not</b> commit those changes to the database (the caller must
     * use <code>saveChanges()</code>).
     * @param ec the editing context in which to make the changes (can be
     * different than the editing context that owns this object)
     */
    public void requeueForGrading( EOEditingContext ec )
    {
        if ( enqueuedJob() == null )
        {
            Submission me = this;
            if ( ec != editingContext() )
            {
                me = localInstance( ec );
            }
            me.deleteResultsAndRemovePartners();
            log.debug( "creating new job for Submission " + this );
            EnqueuedJob job = new EnqueuedJob();
            job.setQueueTime( new NSTimestamp() );
            job.setRegrading( true );
            ec.insertObject( job );
            job.setSubmissionRelationship( me );
        }
    }


    // ----------------------------------------------------------
    public String permalink()
    {
        if ( cachedPermalink == null )
        {
            cachedPermalink = Application.configurationProperties()
                .getProperty( "base.url" )
                + "?page=MostRecent&"
                + ID_FORM_KEY + "=" + id();
        }
        return cachedPermalink;
    }


    // ----------------------------------------------------------
    public void emailNotificationToStudent( String message )
    {
        WCProperties properties =
            new WCProperties( Application.configurationProperties() );
        user().addPropertiesTo( properties );
        if ( properties.getProperty( "login.url" ) == null )
        {
            String dest = Application.application().servletConnectURL();
            properties.setProperty( "login.url", dest );
        }
        properties.setProperty( "submission.number",
            Integer.toString( submitNumber() ) );
        properties.setProperty( "message", message );
        AssignmentOffering assignment = assignmentOffering();
        if ( assignment != null )
        {
            properties.setProperty( "assignment.title",
                assignment.titleString() );
        }
        properties.setProperty(  "submission.result.link", permalink() );

        net.sf.webcat.core.Application.sendSimpleEmail(
            user().email(),
            properties.stringForKeyWithDefault(
                "submission.email.title",
                "[Grader] results available: #${submission.number}, "
                + "${assignment.title}" ),
            properties.stringForKeyWithDefault(
                "submission.email.body",
                "The feedback report for ${assignment.title}\n"
                + "submission number ${submission.number} ${message}.\n\n"
                + "Log in to Web-CAT to view the report:\n\n"
                + "${submission.result.link}\n" )
            );
    }


    // ----------------------------------------------------------
    /**
     * Returns a value indicating if this submission is the "submission for
     * grading" of the user who submitted it for a particular assignment
     * offering. The "submission for grading" is the one that would be exported
     * by the grader and the one that should normally be considered for
     * reporting; specifically, it is either the most recent graded submission,
     * or if none have yet been graded, it is the most recent overall
     * submission.
     *
     * @return true if this submission is the "submission for grading" for its
     *     user and assignment offering; false if otherwise.
     */
    @Override
    public boolean isSubmissionForGrading()
    {
        // Migrate the property value if it doesn't yet exist.

        if (isSubmissionForGradingRaw() == null)
        {
            if (user() == null || assignmentOffering() == null)
            {
                setIsSubmissionForGrading(false);
                return false;
            }

            Submission primarySubmission = null;
            Submission latestGradedSubmission = null;
    
            NSArray<Submission> thisSubmissionSet =
                EOUtilities.objectsMatchingValues(
                    editingContext(),
                    Submission.ENTITY_NAME,
                    new NSDictionary(
                        new Object[] {
                            user(),
                            assignmentOffering()
                        },
                        new Object[] {
                            Submission.USER_KEY,
                            Submission.ASSIGNMENT_OFFERING_KEY
                        }
                    )
                );

            // Iterate over the whole submission set and find the submission
            // for grading (which is either the last submission is none are
            // graded, or the latest of those that are graded).

            for ( Submission sub : thisSubmissionSet )
            {
                if ( primarySubmission == null )
                {
                    primarySubmission = sub;
                }
                else if ( sub.submitNumberRaw() != null )
                {
                    int num = sub.submitNumber();

                    if ( num > primarySubmission.submitNumber() )
                    {
                        primarySubmission = sub;
                    }
                }

                if ( sub.result() != null )
                {
                    if ( sub.result().status() != Status.TO_DO )
                    {
                        if ( latestGradedSubmission == null )
                        {
                            latestGradedSubmission = sub;
                        }
                        else if ( sub.submitNumberRaw() != null )
                        {
                            int num = sub.submitNumber();
                            if ( num > latestGradedSubmission.submitNumber() )
                            {
                                latestGradedSubmission = sub;
                            }
                        }
                    }
                }
            }

            if ( latestGradedSubmission != null )
            {
                primarySubmission = latestGradedSubmission;
            }

            // Now that the entire submission chain is fetched, update the
            // isSubmissionForGrading property among all of them.

            for (Submission sub : thisSubmissionSet)
            {
                boolean isSubForGrading = sub.equals(primarySubmission);
                sub.setIsSubmissionForGrading(isSubForGrading);
            }
        }
        
        return super.isSubmissionForGrading();
    }


    // ----------------------------------------------------------
    /**
     * Gets the array of all submissions in the submission chain that contains
     * this submission (that is, all submissions for this submission's user and
     * assignment offering). The returned array is sorted by submission time in
     * ascending order.
     *
     * @return an NSArray containing the Submission objects in this submission
     *     chain
     */
    public NSArray<Submission> allSubmissions()
    {
        if (user() == null || assignmentOffering() == null)
            return NSArray.EmptyArray;

        return objectsForAllForAssignmentOfferingAndUser(editingContext(),
                assignmentOffering(), user());
    }


    // ----------------------------------------------------------
    /**
     * Gets the submission in this submission chain that represents the
     * "submission for grading". If no manual grading has yet occurred, then
     * this is equivalent to {@link #latestSubmission()}. Otherwise, if a TA
     * has manually graded one or more submissions, then this method returns
     * the latest of those.
     *
     * @return the submission for grading in this submission chain
     */
    public Submission gradedSubmission()
    {
        // TODO replace this code with a fetch specification when the
        // isSubmissionForGrading property is reified in the database.

        NSArray<Submission> subs = allSubmissions();

        for (Submission sub : subs)
        {
            if (sub.isSubmissionForGrading())
            {
                return sub;
            }
        }

        return null;
    }


    // ----------------------------------------------------------
    /**
     * Gets the earliest (in other words, first) submission made in this
     * submission chain.
     *
     * @return the earliest submission in the submission chain
     */
    public Submission earliestSubmission()
    {
        if (user() == null || assignmentOffering() == null) return null;

        NSArray<Submission> subs =
            objectsForEarliestForAssignmentOfferingAndUser(editingContext(),
                    assignmentOffering(), user());

        if (subs != null && subs.count() >= 1)
        {
            return subs.objectAtIndex(0);
        }
        else
        {
            return null;
        }
    }


    // ----------------------------------------------------------
    /**
     * Gets the latest (in other words, last) submission made in this
     * submission chain. Typically clients should prefer to use the
     * {@link #gradedSubmission()} method over this one, depending on their
     * policy regarding the grading of submissions that are not the most recent
     * one; this method exists for symmetry and to allow clients to distinguish
     * between the graded submission and the last one, if necessary.
     *
     * @return the latest submission in the submission chain
     */
    public Submission latestSubmission()
    {
        if (user() == null || assignmentOffering() == null) return null;

        NSArray<Submission> subs =
            objectsForLatestForAssignmentOfferingAndUser(editingContext(),
                    assignmentOffering(), user());

        if (subs != null && subs.count() >= 1)
        {
            return subs.objectAtIndex(0);
        }
        else
        {
            return null;
        }
    }


    // ----------------------------------------------------------
    /**
     * Gets the previous submission to this one in the submission chain.
     *
     * @return the previous submission, or null if it is the first one (or
     *     there was an error)
     */
    public Submission previousSubmission()
    {
        if (user() == null || assignmentOffering() == null ||
                submitNumberRaw() == null) return null;

        int submitNo = submitNumber();

        if (submitNo == 1) return null;

        NSArray<Submission> subs =
            objectsForSpecificSubmission(editingContext(),
                    assignmentOffering(), submitNo - 1, user());

        if (subs != null && subs.count() >= 1)
        {
            return subs.objectAtIndex(0);
        }
        else
        {
            return null;
        }
    }


    // ----------------------------------------------------------
    /**
     * Gets the next submission following this one in the submission chain.
     *
     * @return the next submission, or null if it is the first one (or
     *     there was an error)
     */
    public Submission nextSubmission()
    {
        if (user() == null || assignmentOffering() == null ||
                submitNumberRaw() == null) return null;

        int submitNo = submitNumber();

        EOQualifier q =
            ERXQ.equals("user", user()).and(
                ERXQ.equals("assignmentOffering", assignmentOffering()));

        int count = ERXEOControlUtilities.objectCountWithQualifier(
                editingContext(), ENTITY_NAME, q);

        if (submitNo == count) return null;

        NSArray<Submission> subs =
            objectsForSpecificSubmission(editingContext(),
                    assignmentOffering(), submitNo + 1, user());

        if (subs != null && subs.count() >= 1)
        {
            return subs.objectAtIndex(0);
        }
        else
        {
            return null;
        }
    }


    // ----------------------------------------------------------
    /**
     * Gets the earliest submission in the submission chain for this user and
     * assignment offering that has valid non-zero coverage data.
     * 
     * @return the earliest submission with valid non-zero coverage data, or
     *     null if there is no submission satisfying this
     */
    public Submission earliestSubmissionWithCoverage()
    {
        NSArray<Submission> subs = allSubmissions();
        
        for(Submission submission : subs)
        {
            if (submission.hasCoverage())
            {
                return submission;
            }
        }
        
        return null;
    }
    
    
    // ----------------------------------------------------------
    /**
     * Gets a value indicating whether the submission has valid non-zero
     * coverage data. This implies that the submission compiled without error
     * and executed at least partially (but only for plug-ins that collect code
     * coverage statistics).
     * 
     * @return true if the submission has valid non-zero coverage data,
     *     otherwise false.
     */
    public boolean hasCoverage()
    {
        NSArray<SubmissionFileStats> files = result().submissionFileStats();
        
        int totalElements = 0;

        for(SubmissionFileStats file : files)
        {
            totalElements += file.elements();
        }
        
        return (totalElements > 0);
    }
    
    
    // ----------------------------------------------------------
    /**
     * Gets the earliest submission in the submission chain for this user and
     * assignment offering that has valid lines-of-code data.
     * 
     * @return the earliest submission with valid lines-of-code data, or
     *     null if there is no submission satisfying this
     */
    public Submission earliestSubmissionWithLOC()
    {
        NSArray<Submission> subs = allSubmissions();
        
        for(Submission submission : subs)
        {
            if (submission.hasLOC())
            {
                return submission;
            }
        }
        
        return null;
    }
    
    
    // ----------------------------------------------------------
    /**
     * Gets a value indicating whether the submission has valid lines-of-code
     * data.
     * 
     * @return true if the submission has valid non-zero lines-of-code data,
     *     otherwise false.
     */
    public boolean hasLOC()
    {
        NSArray<SubmissionFileStats> files = result().submissionFileStats();
        
        int totalLOC = 0;

        for(SubmissionFileStats file : files)
        {
            totalLOC += file.loc();
        }
        
        return (totalLOC > 0);
    }
    
    
    // ----------------------------------------------------------
    /**
     * Gets the earliest submission in the submission chain for this user and
     * assignment offering that has a non-zero correctness score.
     * 
     * @return the earliest submission with a non-zero correctness score, or
     *     null if there is no submission satisfying this
     */
    public Submission earliestSubmissionWithCorrectnessScore()
    {
        NSArray<Submission> subs = allSubmissions();
        
        for(Submission submission : subs)
        {
            if (submission.hasCorrectnessScore())
            {
                return submission;
            }
        }
        
        return null;
    }
    
    
    // ----------------------------------------------------------
    /**
     * Gets a value indicating whether the submission has a non-zero
     * correctness score. This implies that the code ran, but the converse is
     * not true of course -- not all code that runs attains a non-zero
     * correctness score.
     * 
     * @return true if the submission has a non-zero correctness score.
     */
    public boolean hasCorrectnessScore()
    {
        return result().correctnessScore() > 0;
    }


    // ----------------------------------------------------------
    /**
     * Gets the earliest submission in the submission chain for this user and
     * assignment offering that has a non-zero correctness score.
     * 
     * @return the earliest submission with a non-zero correctness score, or
     *     null if there is no submission satisfying this
     */
    public Submission earliestSubmissionWithAnyData()
    {
        NSArray<Submission> subs = allSubmissions();
        
        for(Submission submission : subs)
        {
            if (submission.hasAnyData())
            {
                return submission;
            }
        }
        
        return null;
    }
    
    
    // ----------------------------------------------------------
    /**
     * Gets a value indicating whether the submission has any data of interest
     * as generated by the grading plug-ins and grading process. For now, this
     * includes the following items: coverage data, lines-of-code, correctness
     * score.
     * 
     * @return true if the submission has any valid grading data, otherwise
     *     false.
     */
    public boolean hasAnyData()
    {
        // This method is written like this to effectively short-circuit things
        // the first time any data is found, rather than wasting time
        // inefficiently pulling all the data that we're interested in.

        if (hasCoverage())
            return true;
        
        if (hasLOC())
            return true;

        if (hasCorrectnessScore())
            return true;

        return false;
    }


    // ----------------------------------------------------------
    @Override
    public void mightDelete()
    {
        log.debug("mightDelete()");
        subdirToDelete = dirName();
        super.mightDelete();
    }


    // ----------------------------------------------------------
    @Override
    public void didDelete( EOEditingContext context )
    {
        log.debug("didDelete()");
        super.didDelete( context );
        // should check to see if this is a child ec
        EOObjectStore parent = context.parentObjectStore();
        if (parent == null || !(parent instanceof EOEditingContext))
        {
            if (subdirToDelete != null)
            {
                File dir = new File(subdirToDelete);
                if (dir.exists())
                {
                    net.sf.webcat.archives.FileUtilities.deleteDirectory(dir);
                }
            }
        }
    }


    //~ Instance/static variables .............................................

    private String cachedPermalink;
    private String subdirToDelete;
    static Logger log = Logger.getLogger( Submission.class );
}
