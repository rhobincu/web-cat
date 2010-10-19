/*==========================================================================*\
 |  $Id: GradeStudentSubmissionPage.java,v 1.6 2010/10/19 18:37:37 aallowat Exp $
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

import com.webobjects.appserver.*;
import com.webobjects.foundation.*;
import er.extensions.appserver.ERXDisplayGroup;
import org.apache.log4j.Logger;
import org.webcat.core.*;

// -------------------------------------------------------------------------
/**
 * Allow the user to enter/edit "TA" comments for a submission.
 *
 * @author  Stephen Edwards
 * @author  Last changed by $Author: aallowat $
 * @version $Revision: 1.6 $, $Date: 2010/10/19 18:37:37 $
 */
public class GradeStudentSubmissionPage
    extends GraderComponent
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Default constructor.
     * @param context The page's context
     */
    public GradeStudentSubmissionPage( WOContext context )
    {
        super( context );
    }


    //~ KVC Attributes (must be public) .......................................

    public SubmissionResult    result;
    public Submission          submission;
    public ERXDisplayGroup<SubmissionFileStats> statsDisplayGroup;
    // For iterating over display group
    public SubmissionFileStats stats;
    public int                 index;

    /** true if submission file stats are recorded for this submission */
    public boolean hasFileStats = false;

    public String javascriptText = "<script type=\"text/javascript\">\n"
        + "var editor = null;\n"
        + "function initEditor() {\n"
        + "editor = new HTMLArea(\"source\");\n"
        + "editor.generate();\n"
        + "}\n"
        + "</script>\n";

    public NSArray<Byte> formats = SubmissionResult.formats;
    public byte aFormat;

    public NSArray<UserSubmissionPair> availableSubmissions;
    public int                         thisSubmissionIndex;

    //~ Methods ...............................................................

    // ----------------------------------------------------------
    protected void beforeAppendToResponse(
        WOResponse response, WOContext context)
    {
        if (result == null)
        {
            if (submission == null)
            {
                if ( prefs().submission() == null )
                {
                    throw new RuntimeException( "null submission selected" );
                }
                submission = prefs().submission();
            }
            if ( submission.result() == null )
            {
                throw new RuntimeException( "null submission result" );
            }
            result = submission.result();
        }
        if (log.isDebugEnabled())
        {
            log.debug( "result = " + result);
            if (result != null)
            {
                log.debug( "result = " + result.hashCode());
                log.debug( "result EC = " + result.editingContext().hashCode());
                log.debug( "snapshot = " + result.snapshot());
            }
        }
        hasFileStats = ( result.submissionFileStats().count() > 0 );
        statsDisplayGroup.setObjectArray( result.submissionFileStats() );
        showCoverageData = null;
        priorComments = result.comments();
        super.beforeAppendToResponse( response, context );
    }


    // ----------------------------------------------------------
    public void saveGrading()
    {
        result.addCommentByLineFor(user(), priorComments);
    }


    // ----------------------------------------------------------
    public WOComponent next()
    {
        return applyLocalChanges()
            ? super.next()
            : null;
    }


    // ----------------------------------------------------------
    public WOComponent saveThenList()
    {
        return next();
    }


    // ----------------------------------------------------------
    public int indexOfNextSubmission()
    {
        int index = thisSubmissionIndex;

        do
        {
            index++;
        } while (index < availableSubmissions.count()
                && !availableSubmissions.objectAtIndex(
                        index).userHasSubmission());

        return index;
    }


    // ----------------------------------------------------------
    public WOComponent saveThenNextStudent()
    {
        if (availableSubmissions == null
            || indexOfNextSubmission() >= availableSubmissions.count() - 1)
        {
            // If there's no place to go, then go back to the list
            return saveThenList();
        }

        if (applyLocalChanges())
        {
            thisSubmissionIndex = indexOfNextSubmission();

            Submission target = availableSubmissions
                .objectAtIndex(thisSubmissionIndex).submission();
            prefs().setSubmissionRelationship(target);
            prefs().setSubmissionFileStatsRelationship(null);
            submission = target;
            result = null;
        }

        return null;
    }


    // ----------------------------------------------------------
    public WOComponent cancel()
    {
        super.cancel();
        return super.next();
    }


    // ----------------------------------------------------------
    public WOComponent defaultAction()
    {
        log.debug( "defaultAction()" );
        log.debug( "form values = " + context().request().formValues() );
        return null;
    }


//    // ----------------------------------------------------------
//    public WOComponent finish()
//    {
//        log.debug( "finish()" );
//        return super.finish();
//    }
//
//
//    // ----------------------------------------------------------
//    public WOComponent apply()
//    {
//        log.debug( "apply()" );
//        return super.apply();
//    }
//
//
//    // ----------------------------------------------------------
//    public WOActionResults invokeAction( WORequest arg0, WOContext arg1 )
//    {
//        log.debug( "invokeAction(): request.formValues = " + arg0.formValues() );
//        log.debug( "invokeAction(): request = " + arg0 );
////         log.debug( "invokeAction(): context = " + arg1 );
//        log.debug( "invokeAction(): senderID = " + arg1.senderID() );
//        log.debug( "invokeAction(): elementID = " + arg1.elementID() );
//        wcSession().logExtraInfo( log, org.apache.log4j.Level.DEBUG, arg1 );
//        return super.invokeAction( arg0, arg1 );
//    }


    // ----------------------------------------------------------
    public boolean applyLocalChanges()
    {
        saveGrading();
        log.debug("Before commiting, result = " + result.snapshot());
        return super.applyLocalChanges();
    }


    // ----------------------------------------------------------
//    public WOComponent downloadSubmission()
//    {
//        saveGrading();
//        DeliverFile filePage = pageWithName(DeliverFile.class);
//        filePage.setFileName( new java.io.File(
//            prefs().submission().resultDirName(),
//            "../" + prefs().submission().fileName() ) );
//        filePage.setContentType( "application/octet-stream" );
//        filePage.setStartDownload( true );
//        return nextPage;
//    }
//

    // ----------------------------------------------------------
    public WOComponent fileStatsDetails()
    {
        log.debug( "fileStatsDetails()" );
        prefs().setSubmissionFileStatsRelationship( stats );
        WCComponent statsPage = pageWithName(EditFileCommentsPage.class);
        statsPage.nextPage = this;
        return statsPage;
    }


    // ----------------------------------------------------------
//    public WOComponent previewStudentFeedback()
//    {
//        saveGrading();
//        FinalReportPage reportPage = pageWithName(FinalReportPage.class);
//        reportPage.nextPage = this;
//        reportPage.showReturnToGrading = true;
//        return reportPage;
//    }


    // ----------------------------------------------------------
    public WOComponent selectSubmission()
    {
        saveGrading();
        PickSubmissionPage submissionPage =
            pageWithName(PickSubmissionPage.class);
        prefs().setSubmissionRelationship(submission);
        submissionPage.nextPage = this;
        submissionPage.sideStepTitle = "Pick submission to grade";
        submission = null;
        result = null;
        return submissionPage;
    }


    // ----------------------------------------------------------
    public String coverageMeter()
    {
        return FinalReportPage.meter( ( (double)stats.elementsCovered() ) /
                                      ( (double)stats.elements() ) );
    }


    // ----------------------------------------------------------
    public boolean gradingDone()
    {
        return result.status() == Status.CHECK;
    }


    // ----------------------------------------------------------
    public void setGradingDone( boolean done )
    {
        boolean canSet = done;
        if ( done )
        {
            // Not ready for this part yet
//            for ( int i = 0; i < statsDisplayGroup.allObjects().count(); i++ )
//            {
//                if ( ( (SubmissionFileStats) statsDisplayGroup
//                         .allObjects().objectAtIndex( i ) ).statusAsInt()
//                     != WCCoreTask.TASK_DONE )
//                {
//                    canSet = false;
//                    errorMessage = "Please finish commenting on all classes "
//                                   + "listed before marking this submission "
//                                   + "as completely graded.";
//                    break;
//                }
//            }
        }
        if ( canSet )
        {
            result.setStatus( Status.CHECK );
            for (Submission sub : result.submissions())
            {
                sub.emailNotificationToStudent(
                    "has been updated by the course staff" );
            }
        }
        else
        {
            result.setStatus( Status.UNFINISHED );
        }
    }


    // ----------------------------------------------------------
    public WOComponent regradeActionOk()
    {
        if (!applyLocalChanges()) return null;
        Submission sub = prefs().submission();
        sub.requeueForGrading( localContext() );
        prefs().setSubmissionRelationship( null );
        applyLocalChanges();
        Grader.getInstance().graderQueue().enqueue( null );
        return back();
    }


    // ----------------------------------------------------------
    public WOActionResults regrade()
    {
        saveGrading();
        if (hasMessages())
        {
            return displayMessages();
        }

        return new ConfirmingAction(this)
        {
            @Override
            protected String confirmationTitle()
            {
                return "Confirm Regrade of This Submission?";
            }

            @Override
            protected String confirmationMessage()
            {
                return "<p>This action will <b>regrade this submission</b> "
                    + "for the selected student.</p><p>This will also "
                    + "<b>delete all prior results</b> for the submission "
                    + "and <b>delete all TA comments and scoring</b> that "
                    + "have been recorded for the submission.</p><p>This "
                    + "submission will be re-queued for grading, and the "
                    + "student will receive an e-mail message when new "
                    + "results are available.</p><p>Regrade this "
                    + "submission?</p>";
            }

            @Override
            protected WOActionResults performStandardAction()
            {
                return regradeActionOk();
            }
        };
    }


    // ----------------------------------------------------------
    public String formatLabel()
    {
        return SubmissionResult.formatStrings.objectAtIndex( aFormat );
    }


    // ----------------------------------------------------------
    public Boolean showCoverageData()
    {
        if ( showCoverageData == null )
        {
            showCoverageData = Boolean.FALSE;
            if ( hasFileStats )
            {
                for (SubmissionFileStats sfs : statsDisplayGroup.allObjects())
                {
                    if ( sfs.elementsRaw() != null )
                    {
                        showCoverageData = Boolean.TRUE;
                        break;
                    }
                }
            }
        }
        return showCoverageData;
    }


    // ----------------------------------------------------------
    public WOComponent fullPrintableReport()
    {
        FullPrintableReport report =
            pageWithName(FullPrintableReport.class);
        report.result = result;
        report.nextPage = this;
        return report;
    }


    // ----------------------------------------------------------
    public Byte commentFormat()
    {
        Byte format = SubmissionResult.formats.get(0);
        if (result != null)
        {
            format = SubmissionResult.formats.get(result.commentFormat());
        }
        return format;
    }


    // ----------------------------------------------------------
    public void setCommentFormat(Byte format)
    {
        if (format != null && result != null)
        {
            result.setCommentFormat(format.byteValue());
        }
    }


    // ----------------------------------------------------------
    public Boolean showAutoGradedComments()
    {
        if (showAutoGradedComments == null)
        {
            if (result.submission().assignmentOffering().assignment()
                    .submissionProfile().toolPoints() > 0.0)
            {
                showAutoGradedComments = Boolean.TRUE;
            }
            else
            {
                showAutoGradedComments = Boolean.FALSE;
                for (int i = 0; i < result.submissionFileStats().count(); i++)
                {
                    SubmissionFileStats thisStats =
                        result.submissionFileStats().objectAtIndex(i);
                    if (thisStats.remarks() > 0)
                    {
                        showAutoGradedComments = Boolean.TRUE;
                        break;
                    }
                }
            }
        }
        return showAutoGradedComments;
    }


    //~ Instance/static variables .............................................

    private Boolean showCoverageData;
    private String priorComments;
    private Boolean showAutoGradedComments;
    static Logger log = Logger.getLogger( GradeStudentSubmissionPage.class );
}
