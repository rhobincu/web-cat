/*==========================================================================*\
 |  _ReportParameterValue.java
 |*-------------------------------------------------------------------------*|
 |  Created by eogenerator
 |  DO NOT EDIT.  Make changes to ReportParameterValue.java instead.
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

package net.sf.webcat.reporter;

import com.webobjects.foundation.*;
import com.webobjects.eocontrol.*;
import java.util.Enumeration;

// -------------------------------------------------------------------------
/**
 * An automatically generated EOGenericRecord subclass.  DO NOT EDIT.
 * To change, use EOModeler, or make additions in
 * ReportParameterValue.java.
 *
 * @author Generated by eogenerator
 * @version version suppressed to control auto-generation
 */
public abstract class _ReportParameterValue
    extends er.extensions.ERXGenericRecord
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new _ReportParameterValue object.
     */
    public _ReportParameterValue()
    {
        super();
    }


    //~ Constants (for key names) .............................................

    // Attributes ---
    public static final String PARAMETER_VALUE_DATA_KEY = "parameterValueData";
    // To-one relationships ---
    public static final String REPORT_PARAMETER_KEY = "reportParameter";
    // To-many relationships ---
    public static final String ENTITY_NAME = "ReportParameterValue";


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>parameterValueData</code> value.
     * @return the value of the attribute
     */
    public NSData parameterValueData()
    {
        return (NSData)storedValueForKey( "parameterValueData" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>parameterValueData</code>
     * property.
     * 
     * @param value The new value for this property
     */
    public void setParameterValueData( NSData value )
    {
        takeStoredValueForKey( value, "parameterValueData" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the entity pointed to by the <code>reportParameter</code>
     * relationship.
     * @return the entity in the relationship
     */
    public net.sf.webcat.reporter.ReportParameter reportParameter()
    {
        return (net.sf.webcat.reporter.ReportParameter)storedValueForKey( "reportParameter" );
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>authenticationDomain</code>
     * relationship (DO NOT USE--instead, use
     * <code>setReportParameterRelationship()</code>.
     * This method is provided for WebObjects use.
     * 
     * @param value The new entity to relate to
     */
    public void setReportParameter( net.sf.webcat.reporter.ReportParameter value )
    {
        takeStoredValueForKey( value, "reportParameter" );
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>authenticationDomain</code>
     * relationship.  This method is a type-safe version of
     * <code>addObjectToBothSidesOfRelationshipWithKey()</code>.
     * 
     * @param value The new entity to relate to
     */
    public void setReportParameterRelationship(
        net.sf.webcat.reporter.ReportParameter value )
    {
        if ( value == null )
        {
            net.sf.webcat.reporter.ReportParameter object = reportParameter();
            if ( object != null )
                removeObjectFromBothSidesOfRelationshipWithKey( object, "reportParameter" );
        }
        else
        {
            addObjectToBothSidesOfRelationshipWithKey( value, "reportParameter" );
        }
    }


}