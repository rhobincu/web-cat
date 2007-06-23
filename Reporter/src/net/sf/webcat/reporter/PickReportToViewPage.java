package net.sf.webcat.reporter;
// Generated by the WOLips Templateengine Plug-in at Mar 27, 2007 2:55:01 PM

import net.sf.webcat.core.MutableDictionary;

import com.webobjects.appserver.*;
import com.webobjects.eoaccess.EODatabaseDataSource;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSMutableDictionary;

public class PickReportToViewPage extends ReporterComponent
{
	public WODisplayGroup generatedReportsDisplayGroup;
	public GeneratedReport generatedReport;
	public int index;

	public WODisplayGroup enqueuedReportsDisplayGroup;
	public EnqueuedReportJob enqueuedReport;
	public int enqueuedIndex;

	public PickReportToViewPage(WOContext context)
    {
        super(context);
    }

    public void appendToResponse(WOResponse response, WOContext context)
    {
    	EODatabaseDataSource source = (EODatabaseDataSource)
    		generatedReportsDisplayGroup.dataSource();
    	
    	NSMutableDictionary bindings = new NSMutableDictionary();
    	bindings.setObjectForKey(wcSession().user(), "user");
    	source.setQualifierBindings(bindings);

    	source = (EODatabaseDataSource)
			enqueuedReportsDisplayGroup.dataSource();
	
    	bindings = new NSMutableDictionary();
    	bindings.setObjectForKey(wcSession().user(), "user");
    	source.setQualifierBindings(bindings);

    	super.appendToResponse(response, context);
    }
    
    public WOComponent viewReport()
    {
		setParameterSelectionsInSession(new MutableDictionary());
    	setReportUuidInSession(generatedReport.uuid());

    	commitReportRendering();

    	return pageWithName(GeneratedReportPage.class.getName());
    }

    public WOComponent viewReportProgress()
    {
		setParameterSelectionsInSession(new MutableDictionary());
    	setReportUuidInSession(enqueuedReport.uuid());

    	return pageWithName(GeneratedReportPage.class.getName());
    }

    public String enqueuedReportProgress()
    {
    	ProgressManager manager = ProgressManager.getInstance();
    	double done = manager.percentDoneOfJob(enqueuedReport.uuid());
    	int percent = (int)Math.floor(done * 100 + 0.5);
    	return "" + percent + "%";
    }
}