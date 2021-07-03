package com.samajackun.summer.shared.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
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

public class ServerCallableStatementImpl extends ServerPreparedStatementImpl implements CallableStatement, Serializable
{
	private static final long serialVersionUID=1115537981611423431L;

	private static final Map<Integer, CallableStatement> INSTANCES=new WeakHashMap<Integer, CallableStatement>(4096);

	private final Integer instanceIndex;

	private transient CallableStatement src;

	public ServerCallableStatementImpl(ServerConnectionImpl connection, CallableStatement src)
	{
		super(connection, src);
		synchronized (INSTANCES)
		{
			this.instanceIndex=INSTANCES.size();
			INSTANCES.put(INSTANCES.size(), src);
		}
		this.src=src;
	}

	@Override
	CallableStatement source()
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
	public void registerOutParameter(int parameterIndex, int sqlType)
		throws SQLException
	{
		source().registerOutParameter(parameterIndex, sqlType);
	}

	@Override
	public void registerOutParameter(int parameterIndex, int sqlType, int scale)
		throws SQLException
	{
		source().registerOutParameter(parameterIndex, sqlType, scale);
	}

	@Override
	public boolean wasNull()
		throws SQLException
	{
		return source().wasNull();
	}

	@Override
	public String getString(int parameterIndex)
		throws SQLException
	{
		return source().getString(parameterIndex);
	}

	@Override
	public boolean getBoolean(int parameterIndex)
		throws SQLException
	{
		return source().getBoolean(parameterIndex);
	}

	@Override
	public byte getByte(int parameterIndex)
		throws SQLException
	{
		return source().getByte(parameterIndex);
	}

	@Override
	public short getShort(int parameterIndex)
		throws SQLException
	{
		return source().getShort(parameterIndex);
	}

	@Override
	public void setQueryTimeout(int seconds)
		throws SQLException
	{
		source().setQueryTimeout(seconds);
	}

	@Override
	public void setBigDecimal(int parameterIndex, BigDecimal x)
		throws SQLException
	{
		source().setBigDecimal(parameterIndex, x);
	}

	@Override
	public int getInt(int parameterIndex)
		throws SQLException
	{
		return source().getInt(parameterIndex);
	}

	@Override
	public void setString(int parameterIndex, String x)
		throws SQLException
	{
		source().setString(parameterIndex, x);
	}

	@Override
	public long getLong(int parameterIndex)
		throws SQLException
	{
		return source().getLong(parameterIndex);
	}

	@Override
	public void setBytes(int parameterIndex, byte[] x)
		throws SQLException
	{
		source().setBytes(parameterIndex, x);
	}

	@Override
	public float getFloat(int parameterIndex)
		throws SQLException
	{
		return source().getFloat(parameterIndex);
	}

	@Override
	public void cancel()
		throws SQLException
	{
		source().cancel();
	}

	@Override
	public double getDouble(int parameterIndex)
		throws SQLException
	{
		return source().getDouble(parameterIndex);
	}

	@Override
	public void setDate(int parameterIndex, Date x)
		throws SQLException
	{
		source().setDate(parameterIndex, x);
	}

	@Override
	@Deprecated
	public BigDecimal getBigDecimal(int parameterIndex, int scale)
		throws SQLException
	{
		return source().getBigDecimal(parameterIndex, scale);
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
	public byte[] getBytes(int parameterIndex)
		throws SQLException
	{
		return source().getBytes(parameterIndex);
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, int length)
		throws SQLException
	{
		source().setAsciiStream(parameterIndex, x, length);
	}

	@Override
	public Date getDate(int parameterIndex)
		throws SQLException
	{
		return source().getDate(parameterIndex);
	}

	@Override
	public boolean execute(String sql)
		throws SQLException
	{
		return source().execute(sql);
	}

	@Override
	public Time getTime(int parameterIndex)
		throws SQLException
	{
		return source().getTime(parameterIndex);
	}

	@Override
	@Deprecated
	public void setUnicodeStream(int parameterIndex, InputStream x, int length)
		throws SQLException
	{
		source().setUnicodeStream(parameterIndex, x, length);
	}

	@Override
	public Timestamp getTimestamp(int parameterIndex)
		throws SQLException
	{
		return source().getTimestamp(parameterIndex);
	}

	@Override
	public Object getObject(int parameterIndex)
		throws SQLException
	{
		return source().getObject(parameterIndex);
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
	public BigDecimal getBigDecimal(int parameterIndex)
		throws SQLException
	{
		return source().getBigDecimal(parameterIndex);
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
	public Object getObject(int parameterIndex, Map<String, Class<?>> map)
		throws SQLException
	{
		return source().getObject(parameterIndex, map);
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
	public Ref getRef(int parameterIndex)
		throws SQLException
	{
		return source().getRef(parameterIndex);
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
	public Blob getBlob(int parameterIndex)
		throws SQLException
	{
		return source().getBlob(parameterIndex);
	}

	@Override
	public void setFetchSize(int rows)
		throws SQLException
	{
		source().setFetchSize(rows);
	}

	@Override
	public Clob getClob(int parameterIndex)
		throws SQLException
	{
		return source().getClob(parameterIndex);
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
	public Array getArray(int parameterIndex)
		throws SQLException
	{
		return source().getArray(parameterIndex);
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
	public Date getDate(int parameterIndex, Calendar cal)
		throws SQLException
	{
		return source().getDate(parameterIndex, cal);
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
	public Time getTime(int parameterIndex, Calendar cal)
		throws SQLException
	{
		return source().getTime(parameterIndex, cal);
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
	public Timestamp getTimestamp(int parameterIndex, Calendar cal)
		throws SQLException
	{
		return source().getTimestamp(parameterIndex, cal);
	}

	@Override
	public void setRef(int parameterIndex, Ref x)
		throws SQLException
	{
		source().setRef(parameterIndex, x);
	}

	@Override
	public void registerOutParameter(int parameterIndex, int sqlType, String typeName)
		throws SQLException
	{
		source().registerOutParameter(parameterIndex, sqlType, typeName);
	}

	@Override
	public void setBlob(int parameterIndex, Blob x)
		throws SQLException
	{
		source().setBlob(parameterIndex, x);
	}

	@Override
	public void setClob(int parameterIndex, Clob x)
		throws SQLException
	{
		source().setClob(parameterIndex, x);
	}

	@Override
	public void setArray(int parameterIndex, Array x)
		throws SQLException
	{
		source().setArray(parameterIndex, x);
	}

	@Override
	public void registerOutParameter(String parameterName, int sqlType)
		throws SQLException
	{
		source().registerOutParameter(parameterName, sqlType);
	}

	@Override
	public ResultSetMetaData getMetaData()
		throws SQLException
	{
		return source().getMetaData();
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
	public void registerOutParameter(String parameterName, int sqlType, int scale)
		throws SQLException
	{
		source().registerOutParameter(parameterName, sqlType, scale);
	}

	@Override
	public ResultSet getGeneratedKeys()
		throws SQLException
	{
		return source().getGeneratedKeys();
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
	public void registerOutParameter(String parameterName, int sqlType, String typeName)
		throws SQLException
	{
		source().registerOutParameter(parameterName, sqlType, typeName);
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
	public URL getURL(int parameterIndex)
		throws SQLException
	{
		return source().getURL(parameterIndex);
	}

	@Override
	public void setURL(String parameterName, URL val)
		throws SQLException
	{
		source().setURL(parameterName, val);
	}

	@Override
	public void setURL(int parameterIndex, URL x)
		throws SQLException
	{
		source().setURL(parameterIndex, x);
	}

	@Override
	public void setNull(String parameterName, int sqlType)
		throws SQLException
	{
		source().setNull(parameterName, sqlType);
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
		return source().getParameterMetaData();
	}

	@Override
	public void setBoolean(String parameterName, boolean x)
		throws SQLException
	{
		source().setBoolean(parameterName, x);
	}

	@Override
	public void setRowId(int parameterIndex, RowId x)
		throws SQLException
	{
		source().setRowId(parameterIndex, x);
	}

	@Override
	public void setByte(String parameterName, byte x)
		throws SQLException
	{
		source().setByte(parameterName, x);
	}

	@Override
	public void setNString(int parameterIndex, String value)
		throws SQLException
	{
		source().setNString(parameterIndex, value);
	}

	@Override
	public void setShort(String parameterName, short x)
		throws SQLException
	{
		source().setShort(parameterName, x);
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
	public void setInt(String parameterName, int x)
		throws SQLException
	{
		source().setInt(parameterName, x);
	}

	@Override
	public void setLong(String parameterName, long x)
		throws SQLException
	{
		source().setLong(parameterName, x);
	}

	@Override
	public void setNClob(int parameterIndex, NClob value)
		throws SQLException
	{
		source().setNClob(parameterIndex, value);
	}

	@Override
	public void setFloat(String parameterName, float x)
		throws SQLException
	{
		source().setFloat(parameterName, x);
	}

	@Override
	public void setClob(int parameterIndex, Reader reader, long length)
		throws SQLException
	{
		source().setClob(parameterIndex, reader, length);
	}

	@Override
	public void setDouble(String parameterName, double x)
		throws SQLException
	{
		source().setDouble(parameterName, x);
	}

	@Override
	public boolean execute(String sql, int[] columnIndexes)
		throws SQLException
	{
		return source().execute(sql, columnIndexes);
	}

	@Override
	public void setBigDecimal(String parameterName, BigDecimal x)
		throws SQLException
	{
		source().setBigDecimal(parameterName, x);
	}

	@Override
	public void setBlob(int parameterIndex, InputStream inputStream, long length)
		throws SQLException
	{
		source().setBlob(parameterIndex, inputStream, length);
	}

	@Override
	public void setString(String parameterName, String x)
		throws SQLException
	{
		source().setString(parameterName, x);
	}

	@Override
	public void setBytes(String parameterName, byte[] x)
		throws SQLException
	{
		source().setBytes(parameterName, x);
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
	public void setDate(String parameterName, Date x)
		throws SQLException
	{
		source().setDate(parameterName, x);
	}

	@Override
	public void setTime(String parameterName, Time x)
		throws SQLException
	{
		source().setTime(parameterName, x);
	}

	@Override
	public void setSQLXML(int parameterIndex, SQLXML xmlObject)
		throws SQLException
	{
		source().setSQLXML(parameterIndex, xmlObject);
	}

	@Override
	public void setTimestamp(String parameterName, Timestamp x)
		throws SQLException
	{
		source().setTimestamp(parameterName, x);
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
	public void setAsciiStream(String parameterName, InputStream x, int length)
		throws SQLException
	{
		source().setAsciiStream(parameterName, x, length);
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
	public void setBinaryStream(String parameterName, InputStream x, int length)
		throws SQLException
	{
		source().setBinaryStream(parameterName, x, length);
	}

	@Override
	public boolean isPoolable()
		throws SQLException
	{
		return source().isPoolable();
	}

	@Override
	public void setObject(String parameterName, Object x, int targetSqlType, int scale)
		throws SQLException
	{
		source().setObject(parameterName, x, targetSqlType, scale);
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
	public void setObject(String parameterName, Object x, int targetSqlType)
		throws SQLException
	{
		source().setObject(parameterName, x, targetSqlType);
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader, long length)
		throws SQLException
	{
		source().setCharacterStream(parameterIndex, reader, length);
	}

	@Override
	public void setObject(String parameterName, Object x)
		throws SQLException
	{
		source().setObject(parameterName, x);
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
	public void setCharacterStream(String parameterName, Reader reader, int length)
		throws SQLException
	{
		source().setCharacterStream(parameterName, reader, length);
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader)
		throws SQLException
	{
		source().setCharacterStream(parameterIndex, reader);
	}

	@Override
	public void setDate(String parameterName, Date x, Calendar cal)
		throws SQLException
	{
		source().setDate(parameterName, x, cal);
	}

	@Override
	public void setNCharacterStream(int parameterIndex, Reader value)
		throws SQLException
	{
		source().setNCharacterStream(parameterIndex, value);
	}

	@Override
	public void setTime(String parameterName, Time x, Calendar cal)
		throws SQLException
	{
		source().setTime(parameterName, x, cal);
	}

	@Override
	public void setClob(int parameterIndex, Reader reader)
		throws SQLException
	{
		source().setClob(parameterIndex, reader);
	}

	@Override
	public void setTimestamp(String parameterName, Timestamp x, Calendar cal)
		throws SQLException
	{
		source().setTimestamp(parameterName, x, cal);
	}

	@Override
	public void setNull(String parameterName, int sqlType, String typeName)
		throws SQLException
	{
		source().setNull(parameterName, sqlType, typeName);
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

	@Override
	public String getString(String parameterName)
		throws SQLException
	{
		return source().getString(parameterName);
	}

	@Override
	public boolean getBoolean(String parameterName)
		throws SQLException
	{
		return source().getBoolean(parameterName);
	}

	@Override
	public byte getByte(String parameterName)
		throws SQLException
	{
		return source().getByte(parameterName);
	}

	@Override
	public short getShort(String parameterName)
		throws SQLException
	{
		return source().getShort(parameterName);
	}

	@Override
	public int getInt(String parameterName)
		throws SQLException
	{
		return source().getInt(parameterName);
	}

	@Override
	public long getLong(String parameterName)
		throws SQLException
	{
		return source().getLong(parameterName);
	}

	@Override
	public float getFloat(String parameterName)
		throws SQLException
	{
		return source().getFloat(parameterName);
	}

	@Override
	public double getDouble(String parameterName)
		throws SQLException
	{
		return source().getDouble(parameterName);
	}

	@Override
	public byte[] getBytes(String parameterName)
		throws SQLException
	{
		return source().getBytes(parameterName);
	}

	@Override
	public Date getDate(String parameterName)
		throws SQLException
	{
		return source().getDate(parameterName);
	}

	@Override
	public Time getTime(String parameterName)
		throws SQLException
	{
		return source().getTime(parameterName);
	}

	@Override
	public Timestamp getTimestamp(String parameterName)
		throws SQLException
	{
		return source().getTimestamp(parameterName);
	}

	@Override
	public Object getObject(String parameterName)
		throws SQLException
	{
		return source().getObject(parameterName);
	}

	@Override
	public BigDecimal getBigDecimal(String parameterName)
		throws SQLException
	{
		return source().getBigDecimal(parameterName);
	}

	@Override
	public Object getObject(String parameterName, Map<String, Class<?>> map)
		throws SQLException
	{
		return source().getObject(parameterName, map);
	}

	@Override
	public Ref getRef(String parameterName)
		throws SQLException
	{
		return source().getRef(parameterName);
	}

	@Override
	public Blob getBlob(String parameterName)
		throws SQLException
	{
		return source().getBlob(parameterName);
	}

	@Override
	public Clob getClob(String parameterName)
		throws SQLException
	{
		return source().getClob(parameterName);
	}

	@Override
	public Array getArray(String parameterName)
		throws SQLException
	{
		return source().getArray(parameterName);
	}

	@Override
	public Date getDate(String parameterName, Calendar cal)
		throws SQLException
	{
		return source().getDate(parameterName, cal);
	}

	@Override
	public Time getTime(String parameterName, Calendar cal)
		throws SQLException
	{
		return source().getTime(parameterName, cal);
	}

	@Override
	public Timestamp getTimestamp(String parameterName, Calendar cal)
		throws SQLException
	{
		return source().getTimestamp(parameterName, cal);
	}

	@Override
	public URL getURL(String parameterName)
		throws SQLException
	{
		return source().getURL(parameterName);
	}

	@Override
	public RowId getRowId(int parameterIndex)
		throws SQLException
	{
		return source().getRowId(parameterIndex);
	}

	@Override
	public RowId getRowId(String parameterName)
		throws SQLException
	{
		return source().getRowId(parameterName);
	}

	@Override
	public void setRowId(String parameterName, RowId x)
		throws SQLException
	{
		source().setRowId(parameterName, x);
	}

	@Override
	public void setNString(String parameterName, String value)
		throws SQLException
	{
		source().setNString(parameterName, value);
	}

	@Override
	public void setNCharacterStream(String parameterName, Reader value, long length)
		throws SQLException
	{
		source().setNCharacterStream(parameterName, value, length);
	}

	@Override
	public void setNClob(String parameterName, NClob value)
		throws SQLException
	{
		source().setNClob(parameterName, value);
	}

	@Override
	public void setClob(String parameterName, Reader reader, long length)
		throws SQLException
	{
		source().setClob(parameterName, reader, length);
	}

	@Override
	public void setBlob(String parameterName, InputStream inputStream, long length)
		throws SQLException
	{
		source().setBlob(parameterName, inputStream, length);
	}

	@Override
	public void setNClob(String parameterName, Reader reader, long length)
		throws SQLException
	{
		source().setNClob(parameterName, reader, length);
	}

	@Override
	public NClob getNClob(int parameterIndex)
		throws SQLException
	{
		return source().getNClob(parameterIndex);
	}

	@Override
	public NClob getNClob(String parameterName)
		throws SQLException
	{
		return source().getNClob(parameterName);
	}

	@Override
	public void setSQLXML(String parameterName, SQLXML xmlObject)
		throws SQLException
	{
		source().setSQLXML(parameterName, xmlObject);
	}

	@Override
	public SQLXML getSQLXML(int parameterIndex)
		throws SQLException
	{
		return source().getSQLXML(parameterIndex);
	}

	@Override
	public SQLXML getSQLXML(String parameterName)
		throws SQLException
	{
		return source().getSQLXML(parameterName);
	}

	@Override
	public String getNString(int parameterIndex)
		throws SQLException
	{
		return source().getNString(parameterIndex);
	}

	@Override
	public String getNString(String parameterName)
		throws SQLException
	{
		return source().getNString(parameterName);
	}

	@Override
	public Reader getNCharacterStream(int parameterIndex)
		throws SQLException
	{
		return source().getNCharacterStream(parameterIndex);
	}

	@Override
	public Reader getNCharacterStream(String parameterName)
		throws SQLException
	{
		return source().getNCharacterStream(parameterName);
	}

	@Override
	public Reader getCharacterStream(int parameterIndex)
		throws SQLException
	{
		return source().getCharacterStream(parameterIndex);
	}

	@Override
	public Reader getCharacterStream(String parameterName)
		throws SQLException
	{
		return source().getCharacterStream(parameterName);
	}

	@Override
	public void setBlob(String parameterName, Blob x)
		throws SQLException
	{
		source().setBlob(parameterName, x);
	}

	@Override
	public void setClob(String parameterName, Clob x)
		throws SQLException
	{
		source().setClob(parameterName, x);
	}

	@Override
	public void setAsciiStream(String parameterName, InputStream x, long length)
		throws SQLException
	{
		source().setAsciiStream(parameterName, x, length);
	}

	@Override
	public void setBinaryStream(String parameterName, InputStream x, long length)
		throws SQLException
	{
		source().setBinaryStream(parameterName, x, length);
	}

	@Override
	public void setCharacterStream(String parameterName, Reader reader, long length)
		throws SQLException
	{
		source().setCharacterStream(parameterName, reader, length);
	}

	@Override
	public void setAsciiStream(String parameterName, InputStream x)
		throws SQLException
	{
		source().setAsciiStream(parameterName, x);
	}

	@Override
	public void setBinaryStream(String parameterName, InputStream x)
		throws SQLException
	{
		source().setBinaryStream(parameterName, x);
	}

	@Override
	public void setCharacterStream(String parameterName, Reader reader)
		throws SQLException
	{
		source().setCharacterStream(parameterName, reader);
	}

	@Override
	public void setNCharacterStream(String parameterName, Reader value)
		throws SQLException
	{
		source().setNCharacterStream(parameterName, value);
	}

	@Override
	public void setClob(String parameterName, Reader reader)
		throws SQLException
	{
		source().setClob(parameterName, reader);
	}

	@Override
	public void setBlob(String parameterName, InputStream inputStream)
		throws SQLException
	{
		source().setBlob(parameterName, inputStream);
	}

	@Override
	public void setNClob(String parameterName, Reader reader)
		throws SQLException
	{
		source().setNClob(parameterName, reader);
	}

	@Override
	public <T> T getObject(int parameterIndex, Class<T> type)
		throws SQLException
	{
		return source().getObject(parameterIndex, type);
	}

	@Override
	public <T> T getObject(String parameterName, Class<T> type)
		throws SQLException
	{
		return source().getObject(parameterName, type);
	}
}
