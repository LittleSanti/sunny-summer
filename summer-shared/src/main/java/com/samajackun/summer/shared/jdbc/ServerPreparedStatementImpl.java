package com.samajackun.summer.shared.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import java.util.WeakHashMap;

public class ServerPreparedStatementImpl extends ServerStatementImpl implements PreparedStatement, Serializable
{
	private static final long serialVersionUID=4576132703714322875L;

	private static final Map<Integer, PreparedStatement> INSTANCES=new WeakHashMap<Integer, PreparedStatement>(4096);

	private final Integer instanceIndex;

	private transient PreparedStatement src;

	public ServerPreparedStatementImpl(ServerConnectionImpl owner, PreparedStatement src)
	{
		super(owner, src);
		synchronized (INSTANCES)
		{
			this.instanceIndex=INSTANCES.size();
			INSTANCES.put(INSTANCES.size(), src);
		}
		this.src=src;
	}

	@Override
	PreparedStatement source()
	{
		if (this.src == null)
		{
			this.src=INSTANCES.get(this.instanceIndex);
		}
		return this.src;
	}

	@Override
	public <T> T unwrap(Class<T> iface)
		throws SQLException
	{
		return source().unwrap(iface);
	}

	@Override
	public ResultSet executeQuery()
		throws SQLException
	{
		return createResultSet(source().executeQuery());
	}

	@Override
	public int executeUpdate()
		throws SQLException
	{
		return source().executeUpdate();
	}

	@Override
	public void setNull(int parameterIndex, int sqlType)
		throws SQLException
	{
		source().setNull(parameterIndex, sqlType);
	}

	@Override
	public void close()
		throws SQLException
	{
		source().close();
	}

	@Override
	public int getMaxFieldSize()
		throws SQLException
	{
		return source().getMaxFieldSize();
	}

	@Override
	public void setBoolean(int parameterIndex, boolean x)
		throws SQLException
	{
		source().setBoolean(parameterIndex, x);
	}

	@Override
	public void setByte(int parameterIndex, byte x)
		throws SQLException
	{
		source().setByte(parameterIndex, x);
	}

	@Override
	public void setMaxFieldSize(int max)
		throws SQLException
	{
		source().setMaxFieldSize(max);
	}

	@Override
	public void setShort(int parameterIndex, short x)
		throws SQLException
	{
		source().setShort(parameterIndex, x);
	}

	@Override
	public int getMaxRows()
		throws SQLException
	{
		return source().getMaxRows();
	}

	@Override
	public void setInt(int parameterIndex, int x)
		throws SQLException
	{
		source().setInt(parameterIndex, x);
	}

	@Override
	public void setMaxRows(int max)
		throws SQLException
	{
		source().setMaxRows(max);
	}

	@Override
	public void setLong(int parameterIndex, long x)
		throws SQLException
	{
		source().setLong(parameterIndex, x);
	}

	@Override
	public void setEscapeProcessing(boolean enable)
		throws SQLException
	{
		source().setEscapeProcessing(enable);
	}

	@Override
	public void setFloat(int parameterIndex, float x)
		throws SQLException
	{
		source().setFloat(parameterIndex, x);
	}

	@Override
	public void setDouble(int parameterIndex, double x)
		throws SQLException
	{
		source().setDouble(parameterIndex, x);
	}

	@Override
	public void setBigDecimal(int parameterIndex, BigDecimal x)
		throws SQLException
	{
		source().setBigDecimal(parameterIndex, x);
	}

	@Override
	public void setString(int parameterIndex, String x)
		throws SQLException
	{
		source().setString(parameterIndex, x);
	}

	@Override
	public void setBytes(int parameterIndex, byte[] x)
		throws SQLException
	{
		source().setBytes(parameterIndex, x);
	}

	@Override
	public void cancel()
		throws SQLException
	{
		source().cancel();
	}

	@Override
	public void setDate(int parameterIndex, Date x)
		throws SQLException
	{
		source().setDate(parameterIndex, x);
	}

	@Override
	public void setTime(int parameterIndex, Time x)
		throws SQLException
	{
		source().setTime(parameterIndex, x);
	}

	@Override
	public void clearWarnings()
		throws SQLException
	{
		source().clearWarnings();
	}

	@Override
	public void setCursorName(String name)
		throws SQLException
	{
		source().setCursorName(name);
	}

	@Override
	public void setTimestamp(int parameterIndex, Timestamp x)
		throws SQLException
	{
		source().setTimestamp(parameterIndex, x);
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, int length)
		throws SQLException
	{
		source().setAsciiStream(parameterIndex, x, length);
	}

	@Override
	public boolean execute(String sql)
		throws SQLException
	{
		return source().execute(sql);
	}

	@Override
	@Deprecated
	public void setUnicodeStream(int parameterIndex, InputStream x, int length)
		throws SQLException
	{
		source().setUnicodeStream(parameterIndex, x, length);
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, int length)
		throws SQLException
	{
		source().setBinaryStream(parameterIndex, x, length);
	}

	@Override
	public int getUpdateCount()
		throws SQLException
	{
		return source().getUpdateCount();
	}

	@Override
	public boolean getMoreResults()
		throws SQLException
	{
		return source().getMoreResults();
	}

	@Override
	public void clearParameters()
		throws SQLException
	{
		source().clearParameters();
	}

	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType)
		throws SQLException
	{
		source().setObject(parameterIndex, x, targetSqlType);
	}

	@Override
	public void setFetchDirection(int direction)
		throws SQLException
	{
		source().setFetchDirection(direction);
	}

	@Override
	public int getFetchDirection()
		throws SQLException
	{
		return source().getFetchDirection();
	}

	@Override
	public void setObject(int parameterIndex, Object x)
		throws SQLException
	{
		source().setObject(parameterIndex, x);
	}

	@Override
	public void setFetchSize(int rows)
		throws SQLException
	{
		source().setFetchSize(rows);
	}

	@Override
	public int getFetchSize()
		throws SQLException
	{
		return source().getFetchSize();
	}

	@Override
	public int getResultSetConcurrency()
		throws SQLException
	{
		return source().getResultSetConcurrency();
	}

	@Override
	public boolean execute()
		throws SQLException
	{
		return source().execute();
	}

	@Override
	public int getResultSetType()
		throws SQLException
	{
		return source().getResultSetType();
	}

	@Override
	public void addBatch(String sql)
		throws SQLException
	{
		source().addBatch(sql);
	}

	@Override
	public void clearBatch()
		throws SQLException
	{
		source().clearBatch();
	}

	@Override
	public void addBatch()
		throws SQLException
	{
		source().addBatch();
	}

	@Override
	public int[] executeBatch()
		throws SQLException
	{
		return source().executeBatch();
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader, int length)
		throws SQLException
	{
		source().setCharacterStream(parameterIndex, reader, length);
	}

	@Override
	public void setRef(int parameterIndex, Ref x)
		throws SQLException
	{
		source().setRef(parameterIndex, ((ServerRefImpl)x).source());
	}

	@Override
	public void setBlob(int parameterIndex, Blob x)
		throws SQLException
	{
		source().setBlob(parameterIndex, ((ServerBlobImpl)x).source());
	}

	@Override
	public void setClob(int parameterIndex, Clob x)
		throws SQLException
	{
		source().setClob(parameterIndex, ((ServerClobImpl)x).source());
	}

	@Override
	public void setArray(int parameterIndex, Array x)
		throws SQLException
	{
		source().setArray(parameterIndex, ((ServerArrayImpl)x).source());
	}

	@Override
	public ResultSetMetaData getMetaData()
		throws SQLException
	{
		return new ServerResultSetMetaDataImpl(source().getMetaData());
	}

	@Override
	public boolean getMoreResults(int current)
		throws SQLException
	{
		return source().getMoreResults(current);
	}

	@Override
	public void setDate(int parameterIndex, Date x, Calendar cal)
		throws SQLException
	{
		source().setDate(parameterIndex, x, cal);
	}

	@Override
	public void setTime(int parameterIndex, Time x, Calendar cal)
		throws SQLException
	{
		source().setTime(parameterIndex, x, cal);
	}

	@Override
	public int executeUpdate(String sql, int autoGeneratedKeys)
		throws SQLException
	{
		return source().executeUpdate(sql, autoGeneratedKeys);
	}

	@Override
	public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal)
		throws SQLException
	{
		source().setTimestamp(parameterIndex, x, cal);
	}

	@Override
	public void setNull(int parameterIndex, int sqlType, String typeName)
		throws SQLException
	{
		source().setNull(parameterIndex, sqlType, typeName);
	}

	@Override
	public int executeUpdate(String sql, int[] columnIndexes)
		throws SQLException
	{
		return source().executeUpdate(sql, columnIndexes);
	}

	@Override
	public void setURL(int parameterIndex, URL x)
		throws SQLException
	{
		source().setURL(parameterIndex, x);
	}

	@Override
	public int executeUpdate(String sql, String[] columnNames)
		throws SQLException
	{
		return source().executeUpdate(sql, columnNames);
	}

	@Override
	public ParameterMetaData getParameterMetaData()
		throws SQLException
	{
		return new ServerParameterMetaDataImpl(source().getParameterMetaData());
	}

	@Override
	public void setRowId(int parameterIndex, RowId x)
		throws SQLException
	{
		source().setRowId(parameterIndex, x);
	}

	@Override
	public void setNString(int parameterIndex, String value)
		throws SQLException
	{
		source().setNString(parameterIndex, value);
	}

	@Override
	public boolean execute(String sql, int autoGeneratedKeys)
		throws SQLException
	{
		return source().execute(sql, autoGeneratedKeys);
	}

	@Override
	public void setNCharacterStream(int parameterIndex, Reader value, long length)
		throws SQLException
	{
		source().setNCharacterStream(parameterIndex, value, length);
	}

	@Override
	public void setNClob(int parameterIndex, NClob value)
		throws SQLException
	{
		source().setNClob(parameterIndex, ((ServerNClobImpl)value).source());
	}

	@Override
	public void setClob(int parameterIndex, Reader reader, long length)
		throws SQLException
	{
		source().setClob(parameterIndex, reader, length);
	}

	@Override
	public boolean execute(String sql, int[] columnIndexes)
		throws SQLException
	{
		return source().execute(sql, columnIndexes);
	}

	@Override
	public void setBlob(int parameterIndex, InputStream inputStream, long length)
		throws SQLException
	{
		source().setBlob(parameterIndex, inputStream, length);
	}

	@Override
	public void setNClob(int parameterIndex, Reader reader, long length)
		throws SQLException
	{
		source().setNClob(parameterIndex, reader, length);
	}

	@Override
	public boolean execute(String sql, String[] columnNames)
		throws SQLException
	{
		return source().execute(sql, columnNames);
	}

	@Override
	public void setSQLXML(int parameterIndex, SQLXML xmlObject)
		throws SQLException
	{
		source().setSQLXML(parameterIndex, ((ServerSQLXMLImpl)xmlObject).source());
	}

	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength)
		throws SQLException
	{
		source().setObject(parameterIndex, x, targetSqlType, scaleOrLength);
	}

	@Override
	public int getResultSetHoldability()
		throws SQLException
	{
		return source().getResultSetHoldability();
	}

	@Override
	public boolean isClosed()
		throws SQLException
	{
		return source().isClosed();
	}

	@Override
	public void setPoolable(boolean poolable)
		throws SQLException
	{
		source().setPoolable(poolable);
	}

	@Override
	public boolean isPoolable()
		throws SQLException
	{
		return source().isPoolable();
	}

	@Override
	public void closeOnCompletion()
		throws SQLException
	{
		source().closeOnCompletion();
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, long length)
		throws SQLException
	{
		source().setAsciiStream(parameterIndex, x, length);
	}

	@Override
	public boolean isCloseOnCompletion()
		throws SQLException
	{
		return source().isCloseOnCompletion();
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, long length)
		throws SQLException
	{
		source().setBinaryStream(parameterIndex, x, length);
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader, long length)
		throws SQLException
	{
		source().setCharacterStream(parameterIndex, reader, length);
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x)
		throws SQLException
	{
		source().setAsciiStream(parameterIndex, x);
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x)
		throws SQLException
	{
		source().setBinaryStream(parameterIndex, x);
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader)
		throws SQLException
	{
		source().setCharacterStream(parameterIndex, reader);
	}

	@Override
	public void setNCharacterStream(int parameterIndex, Reader value)
		throws SQLException
	{
		source().setNCharacterStream(parameterIndex, value);
	}

	@Override
	public void setClob(int parameterIndex, Reader reader)
		throws SQLException
	{
		source().setClob(parameterIndex, reader);
	}

	@Override
	public void setBlob(int parameterIndex, InputStream inputStream)
		throws SQLException
	{
		source().setBlob(parameterIndex, inputStream);
	}

	@Override
	public void setNClob(int parameterIndex, Reader reader)
		throws SQLException
	{
		source().setNClob(parameterIndex, reader);
	}

}
