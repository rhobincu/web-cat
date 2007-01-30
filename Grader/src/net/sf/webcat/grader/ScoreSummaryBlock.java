/*==========================================================================*\
 |  $Id: ScoreSummaryBlock.java,v 1.2 2007/01/30 02:24:30 stedwar2 Exp $
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

package net.sf.webcat.grader;

import com.webobjects.appserver.*;
import er.extensions.*;
import net.sf.webcat.core.*;
import net.sf.webcat.grader.graphs.*;

import org.apache.log4j.Logger;

// -------------------------------------------------------------------------
/**
 *  Renders a descriptive table containing a submission result's basic
 *  identifying information.  An optional submission file stats object,
 *  if present, will be used to present file-specific data.
 *
 *  @author  Stephen Edwards
 *  @version $Id: ScoreSummaryBlock.java,v 1.2 2007/01/30 02:24:30 stedwar2 Exp $
 */
public class ScoreSummaryBlock
    extends GraderComponent
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Default constructor.
     * @param context The page's context
     */
    public ScoreSummaryBlock( WOContext context )
    {
        super( context );
    }


    //~ KVC Attributes (must be public) .......................................

    public boolean          allowScoreEdit   = false;
    public boolean          includeSeparator = true;
    public SubmissionResult result;
    public int              rowNumber;

    public static final String showGraphsKey = "FinalReportShowGraphs";


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    public void appendToResponse( WOResponse response, WOContext context )
    {
        rowNumber = 0;
        result = prefs().submission().result();
        super.appendToResponse( response, context );
    }


    // ----------------------------------------------------------
    public boolean hasTAGrade()
    {
        return prefs().submission().assignmentOffering().assignment()
            .submissionProfile().taPoints() > 0 || allowScoreEdit;
    }


    // ----------------------------------------------------------
    public Object taScore()
    {
        return ( result.status() == Status.CHECK )
            ? result.taScoreRaw()
            : null;
    }


    // ----------------------------------------------------------
    public String taMeter()
    {
        Number taPossibleNum = prefs().submission().assignmentOffering()
            .assignment().submissionProfile().taPointsRaw();
        double taPossible = ( taPossibleNum == null )
            ? 1.0 : taPossibleNum.doubleValue();
        Number taPtsNum = result.taScoreRaw();
        if ( taPtsNum == null ||
             ( !allowScoreEdit && result.status() != Status.CHECK ) )
        {
            return "&lt;Awaiting TA&gt;";
        }
        double taPts = taPtsNum.doubleValue();
        return FinalReportPage.meter( taPts / taPossible );
    }


    // ----------------------------------------------------------
    public String toolMeter()
    {
        Number toolPossibleNum = prefs().submission().assignmentOffering()
            .assignment().submissionProfile().toolPointsRaw();
        double toolPossible = ( toolPossibleNum == null )
            ? 1.0 : toolPossibleNum.doubleValue();
        double toolPts = result.toolScore();
        return FinalReportPage.meter( toolPts / toolPossible );
    }


    // ----------------------------------------------------------
    public String correctnessMeter()
    {
        double possible = prefs().submission().assignmentOffering()
            .assignment().submissionProfile().correctnessPoints();
        double pts = result.correctnessScore();
        return FinalReportPage.meter( pts / possible );
    }


    // ----------------------------------------------------------
    public String finalMeter()
    {
        double possible = prefs().submission().assignmentOffering()
            .assignment().submissionProfile().availablePoints();
        double pts = result.finalScore();
        return FinalReportPage.meter( pts / possible );
    }


    // ----------------------------------------------------------
    public WOComponent toggleShowGraphs()
    {
        boolean showGraphs = ERXValueUtilities.booleanValue(
            wcSession().userPreferences.objectForKey( showGraphsKey ) );
        log.debug( "toggleShowGraphs: was " + showGraphs );
        showGraphs = !showGraphs;
        wcSession().userPreferences.setObjectForKey(
            Boolean.valueOf( showGraphs ), showGraphsKey );
        return context().page();
    }


    // ----------------------------------------------------------
    public SubmissionResultDataset correctnessToolsDataset()
    {
        if ( correctnessToolsDataset == null )
        {
            correctnessToolsDataset = new SubmissionResultDataset(
                SubmissionResult.objectsForUser(
                    wcSession().localContext(),
                    prefs().assignmentOffering(),
                    result.submission().user() ),
                SubmissionResultDataset.TESTING_AND_STATIC_TOOLS_SCORE_SERIES );
        }
        return correctnessToolsDataset;
    }


    //~ Instance/static variables .............................................

    private SubmissionResultDataset correctnessToolsDataset;

    static Logger log = Logger.getLogger( ScoreSummaryBlock.class );
}
