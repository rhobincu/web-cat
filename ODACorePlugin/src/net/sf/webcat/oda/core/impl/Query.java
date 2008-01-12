/*
 *************************************************************************
 * Copyright (c) 2007 <<Your Company Name here>>
 *  
 *************************************************************************
 */

package net.sf.webcat.oda.core.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import net.sf.webcat.oda.IWebCATResultSet;
import net.sf.webcat.oda.IWebCATResultSetProvider;
import net.sf.webcat.oda.RelationInformation;
import net.sf.webcat.oda.WebCATDataException;

import org.eclipse.datatools.connectivity.oda.IParameterMetaData;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.SortSpec;

/**
 * Implementation class of IQuery for an ODA runtime driver.
 * <br>
 * For demo purpose, the auto-generated method stubs have
 * hard-coded implementation that returns a pre-defined set
 * of meta-data and query results.
 * A custom ODA driver is expected to implement own data source specific
 * behavior in its place.
 * 
 * @author Tony Allevato (Virginia Tech Computer Science)
 */
public class Query implements IQuery
{
	// === Methods ============================================================

	// ------------------------------------------------------------------------
	public Query(IWebCATResultSetProvider resultSets)
	{
		this.resultSets = resultSets;
	}


	// ------------------------------------------------------------------------
	public void prepare(String queryText) throws OdaException
	{
		relation = new RelationInformation(queryText);

		// Find the Web-CAT result set associated with this query.
		String dataSetUuid = relation.getDataSetUuid();
		results = resultSets.resultSetWithUuid(dataSetUuid);

		String[] expressions = new String[relation.getColumnCount()];
		for(int i = 0; i < relation.getColumnCount(); i++)
			expressions[i] = relation.getColumnExpression(i);

		try
		{
			results.prepare(relation.getEntityType(), expressions);
		}
		catch(WebCATDataException e)
		{
			throw new OdaException(e.getCause());
		}
	}


	// ------------------------------------------------------------------------
	public void setAppContext( Object context ) throws OdaException
	{
	    // do nothing; assumes no support for pass-through context
	}


	// ------------------------------------------------------------------------
	public void close() throws OdaException
	{
	}


	// ------------------------------------------------------------------------
	public IResultSetMetaData getMetaData() throws OdaException
	{
		return new ResultSetMetaData(relation);
	}


	// ------------------------------------------------------------------------
	public IResultSet executeQuery() throws OdaException
	{
		try
		{
			results.execute();
			IResultSet resultSet = new ResultSet(relation, results);
			resultSet.setMaxRows(getMaxRows());
			return resultSet;
		}
		catch(WebCATDataException e)
		{
			throw new OdaException(e.getCause());
		}
	}

	// ------------------------------------------------------------------------
	public void setProperty( String name, String value ) throws OdaException
	{
		// do nothing; assumes no data set query property
	}


	// ------------------------------------------------------------------------
	public void setMaxRows( int max ) throws OdaException
	{
	    maxRows = max;
	}


	// ------------------------------------------------------------------------
	public int getMaxRows() throws OdaException
	{
		return maxRows;
	}


	// ------------------------------------------------------------------------
	public void clearInParameters() throws OdaException
	{
		throw new UnsupportedOperationException();
	}


	// ------------------------------------------------------------------------
	public void setInt( String parameterName, int value ) throws OdaException
	{
		throw new UnsupportedOperationException();
	}


	// ------------------------------------------------------------------------
	public void setInt( int parameterId, int value ) throws OdaException
	{
		throw new UnsupportedOperationException();
	}


	// ------------------------------------------------------------------------
	public void setDouble( String parameterName, double value ) throws OdaException
	{
		throw new UnsupportedOperationException();
	}


	// ------------------------------------------------------------------------
	public void setDouble( int parameterId, double value ) throws OdaException
	{
		throw new UnsupportedOperationException();
	}


	// ------------------------------------------------------------------------
	public void setBigDecimal( String parameterName, BigDecimal value ) throws OdaException
	{
		throw new UnsupportedOperationException();
	}


	// ------------------------------------------------------------------------
	public void setBigDecimal( int parameterId, BigDecimal value ) throws OdaException
	{
		throw new UnsupportedOperationException();
	}


	// ------------------------------------------------------------------------
	public void setString( String parameterName, String value ) throws OdaException
	{
		throw new UnsupportedOperationException();
	}


	// ------------------------------------------------------------------------
	public void setString( int parameterId, String value ) throws OdaException
	{
		throw new UnsupportedOperationException();
	}


	// ------------------------------------------------------------------------
	public void setDate( String parameterName, Date value ) throws OdaException
	{
		throw new UnsupportedOperationException();
	}


	// ------------------------------------------------------------------------
	public void setDate( int parameterId, Date value ) throws OdaException
	{
		throw new UnsupportedOperationException();
	}


	// ------------------------------------------------------------------------
	public void setTime( String parameterName, Time value ) throws OdaException
	{
		throw new UnsupportedOperationException();
	}


	// ------------------------------------------------------------------------
	public void setTime( int parameterId, Time value ) throws OdaException
	{
		throw new UnsupportedOperationException();
	}


	// ------------------------------------------------------------------------
	public void setTimestamp( String parameterName, Timestamp value ) throws OdaException
	{
		throw new UnsupportedOperationException();
	}


	// ------------------------------------------------------------------------
	public void setTimestamp( int parameterId, Timestamp value ) throws OdaException
	{
		throw new UnsupportedOperationException();
	}


	// ------------------------------------------------------------------------
    public void setBoolean( String parameterName, boolean value )
            throws OdaException
    {
   		throw new UnsupportedOperationException();
    }


	// ------------------------------------------------------------------------
    public void setBoolean( int parameterId, boolean value )
            throws OdaException
    {
  		throw new UnsupportedOperationException();
    }
    

	// ------------------------------------------------------------------------
    public void setNull( String parameterName ) throws OdaException
    {
		throw new UnsupportedOperationException();
    }


	// ------------------------------------------------------------------------
    public void setNull( int parameterId ) throws OdaException
    {
    	throw new UnsupportedOperationException();
    }


	// ------------------------------------------------------------------------
	public int findInParameter( String parameterName ) throws OdaException
	{
		throw new UnsupportedOperationException();
	}


	// ------------------------------------------------------------------------
	public IParameterMetaData getParameterMetaData() throws OdaException
	{
		throw new UnsupportedOperationException();
	}


	// ------------------------------------------------------------------------
	public void setSortSpec( SortSpec sortBy ) throws OdaException
	{
        throw new UnsupportedOperationException();
	}


	// ------------------------------------------------------------------------
	public SortSpec getSortSpec() throws OdaException
	{
		throw new UnsupportedOperationException();
	}    


	// === Instance Variables =================================================
	
	/**
	 * The maximum number of rows to be returned in a result set generated by
	 * this query.
	 */
	private int maxRows;
	
	/**
	 * A RelationInformation object that describes the query.
	 */
	private RelationInformation relation;
	
	/**
	 * A result set provider that maintains the result sets for the report that
	 * opened this connection.
	 */
	private IWebCATResultSetProvider resultSets;

	/**
	 * The Web-CAT result set containing the data for this query.
	 */
	private IWebCATResultSet results;
}