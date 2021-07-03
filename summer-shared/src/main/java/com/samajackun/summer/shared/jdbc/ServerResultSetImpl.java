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
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import java.util.WeakHashMap;

public class ServerResultSetImpl implements ResultSet, Serializable
{
	private static final long serialVersionUID=-6678754795516201299L;

	private static final Map<Integer, ResultSet> INSTANCES=new WeakHashMap<Integer, ResultSet>(4096);

	private final Integer instanceIndex;

	private transient ResultSet src;

	private final ServerStatementImpl ownerStatement;

	public ServerResultSetImpl(ServerStatementImpl ownerStatement, ResultSet src)
	{
		super();
		synchronized (INSTANCES)
		{
			this.instanceIndex=INSTANCES.size();
			INSTANCES.put(INSTANCES.size(), src);
		}
		this.ownerStatement=ownerStatement;
		this.src=src;
	}

	public ServerResultSetImpl(ResultSet src)
	{
		this(null, src);
	}

	final ResultSet source()
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
	public boolean isWrapperFor(Class<?> iface)
		throws SQLException
	{
		return source().isWrapperFor(iface);
	}

	@Override
	public boolean next()
		throws SQLException
	{
		return source().next();
	}

	@Override
	public void close()
		throws SQLException
	{
		source().close();
	}

	@Override
	public boolean wasNull()
		throws SQLException
	{
		return source().wasNull();
	}

	@Override
	public String getString(int columnIndex)
		throws SQLException
	{
		return source().getString(columnIndex);
	}

	@Override
	public boolean getBoolean(int columnIndex)
		throws SQLException
	{
		return source().getBoolean(columnIndex);
	}

	@Override
	public byte getByte(int columnIndex)
		throws SQLException
	{
		return source().getByte(columnIndex);
	}

	@Override
	public short getShort(int columnIndex)
		throws SQLException
	{
		return source().getShort(columnIndex);
	}

	@Override
	public int getInt(int columnIndex)
		throws SQLException
	{
		return source().getInt(columnIndex);
	}

	@Override
	public long getLong(int columnIndex)
		throws SQLException
	{
		return source().getLong(columnIndex);
	}

	@Override
	public float getFloat(int columnIndex)
		throws SQLException
	{
		return source().getFloat(columnIndex);
	}

	@Override
	public double getDouble(int columnIndex)
		throws SQLException
	{
		return source().getDouble(columnIndex);
	}

	@Override
	@Deprecated
	public BigDecimal getBigDecimal(int columnIndex, int scale)
		throws SQLException
	{
		return source().getBigDecimal(columnIndex, scale);
	}

	@Override
	public byte[] getBytes(int columnIndex)
		throws SQLException
	{
		return source().getBytes(columnIndex);
	}

	@Override
	public Date getDate(int columnIndex)
		throws SQLException
	{
		return source().getDate(columnIndex);
	}

	@Override
	public Time getTime(int columnIndex)
		throws SQLException
	{
		return source().getTime(columnIndex);
	}

	@Override
	public Timestamp getTimestamp(int columnIndex)
		throws SQLException
	{
		return source().getTimestamp(columnIndex);
	}

	@Override
	public InputStream getAsciiStream(int columnIndex)
		throws SQLException
	{
		return source().getAsciiStream(columnIndex);
	}

	@Override
	@Deprecated
	public InputStream getUnicodeStream(int columnIndex)
		throws SQLException
	{
		return source().getUnicodeStream(columnIndex);
	}

	@Override
	public InputStream getBinaryStream(int columnIndex)
		throws SQLException
	{
		return source().getBinaryStream(columnIndex);
	}

	@Override
	public String getString(String columnLabel)
		throws SQLException
	{
		return source().getString(columnLabel);
	}

	@Override
	public boolean getBoolean(String columnLabel)
		throws SQLException
	{
		return source().getBoolean(columnLabel);
	}

	@Override
	public byte getByte(String columnLabel)
		throws SQLException
	{
		return source().getByte(columnLabel);
	}

	@Override
	public short getShort(String columnLabel)
		throws SQLException
	{
		return source().getShort(columnLabel);
	}

	@Override
	public int getInt(String columnLabel)
		throws SQLException
	{
		return source().getInt(columnLabel);
	}

	@Override
	public long getLong(String columnLabel)
		throws SQLException
	{
		return source().getLong(columnLabel);
	}

	@Override
	public float getFloat(String columnLabel)
		throws SQLException
	{
		return source().getFloat(columnLabel);
	}

	@Override
	public double getDouble(String columnLabel)
		throws SQLException
	{
		return source().getDouble(columnLabel);
	}

	@Override
	@Deprecated
	public BigDecimal getBigDecimal(String columnLabel, int scale)
		throws SQLException
	{
		return source().getBigDecimal(columnLabel, scale);
	}

	@Override
	public byte[] getBytes(String columnLabel)
		throws SQLException
	{
		return source().getBytes(columnLabel);
	}

	@Override
	public Date getDate(String columnLabel)
		throws SQLException
	{
		return source().getDate(columnLabel);
	}

	@Override
	public Time getTime(String columnLabel)
		throws SQLException
	{
		return source().getTime(columnLabel);
	}

	@Override
	public Timestamp getTimestamp(String columnLabel)
		throws SQLException
	{
		return source().getTimestamp(columnLabel);
	}

	@Override
	public InputStream getAsciiStream(String columnLabel)
		throws SQLException
	{
		return source().getAsciiStream(columnLabel);
	}

	@Override
	@Deprecated
	public InputStream getUnicodeStream(String columnLabel)
		throws SQLException
	{
		return source().getUnicodeStream(columnLabel);
	}

	@Override
	public InputStream getBinaryStream(String columnLabel)
		throws SQLException
	{
		return source().getBinaryStream(columnLabel);
	}

	@Override
	public SQLWarning getWarnings()
		throws SQLException
	{
		return source().getWarnings();
	}

	@Override
	public void clearWarnings()
		throws SQLException
	{
		source().clearWarnings();
	}

	@Override
	public String getCursorName()
		throws SQLException
	{
		return source().getCursorName();
	}

	@Override
	public ResultSetMetaData getMetaData()
		throws SQLException
	{
		return new ServerResultSetMetaDataImpl(source().getMetaData());
	}

	@Override
	public Object getObject(int columnIndex)
		throws SQLException
	{
		return source().getObject(columnIndex);
	}

	@Override
	public Object getObject(String columnLabel)
		throws SQLException
	{
		return source().getObject(columnLabel);
	}

	@Override
	public int findColumn(String columnLabel)
		throws SQLException
	{
		return source().findColumn(columnLabel);
	}

	@Override
	public Reader getCharacterStream(int columnIndex)
		throws SQLException
	{
		return source().getCharacterStream(columnIndex);
	}

	@Override
	public Reader getCharacterStream(String columnLabel)
		throws SQLException
	{
		return source().getCharacterStream(columnLabel);
	}

	@Override
	public BigDecimal getBigDecimal(int columnIndex)
		throws SQLException
	{
		return source().getBigDecimal(columnIndex);
	}

	@Override
	public BigDecimal getBigDecimal(String columnLabel)
		throws SQLException
	{
		return source().getBigDecimal(columnLabel);
	}

	@Override
	public boolean isBeforeFirst()
		throws SQLException
	{
		return source().isBeforeFirst();
	}

	@Override
	public boolean isAfterLast()
		throws SQLException
	{
		return source().isAfterLast();
	}

	@Override
	public boolean isFirst()
		throws SQLException
	{
		return source().isFirst();
	}

	@Override
	public boolean isLast()
		throws SQLException
	{
		return source().isLast();
	}

	@Override
	public void beforeFirst()
		throws SQLException
	{
		source().beforeFirst();
	}

	@Override
	public void afterLast()
		throws SQLException
	{
		source().afterLast();
	}

	@Override
	public boolean first()
		throws SQLException
	{
		return source().first();
	}

	@Override
	public boolean last()
		throws SQLException
	{
		return source().last();
	}

	@Override
	public int getRow()
		throws SQLException
	{
		return source().getRow();
	}

	@Override
	public boolean absolute(int row)
		throws SQLException
	{
		return source().absolute(row);
	}

	@Override
	public boolean relative(int rows)
		throws SQLException
	{
		return source().relative(rows);
	}

	@Override
	public boolean previous()
		throws SQLException
	{
		return source().previous();
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
	public int getType()
		throws SQLException
	{
		return source().getType();
	}

	@Override
	public int getConcurrency()
		throws SQLException
	{
		return source().getConcurrency();
	}

	@Override
	public boolean rowUpdated()
		throws SQLException
	{
		return source().rowUpdated();
	}

	@Override
	public boolean rowInserted()
		throws SQLException
	{
		return source().rowInserted();
	}

	@Override
	public boolean rowDeleted()
		throws SQLException
	{
		return source().rowDeleted();
	}

	@Override
	public void updateNull(int columnIndex)
		throws SQLException
	{
		source().updateNull(columnIndex);
	}

	@Override
	public void updateBoolean(int columnIndex, boolean x)
		throws SQLException
	{
		source().updateBoolean(columnIndex, x);
	}

	@Override
	public void updateByte(int columnIndex, byte x)
		throws SQLException
	{
		source().updateByte(columnIndex, x);
	}

	@Override
	public void updateShort(int columnIndex, short x)
		throws SQLException
	{
		source().updateShort(columnIndex, x);
	}

	@Override
	public void updateInt(int columnIndex, int x)
		throws SQLException
	{
		source().updateInt(columnIndex, x);
	}

	@Override
	public void updateLong(int columnIndex, long x)
		throws SQLException
	{
		source().updateLong(columnIndex, x);
	}

	@Override
	public void updateFloat(int columnIndex, float x)
		throws SQLException
	{
		source().updateFloat(columnIndex, x);
	}

	@Override
	public void updateDouble(int columnIndex, double x)
		throws SQLException
	{
		source().updateDouble(columnIndex, x);
	}

	@Override
	public void updateBigDecimal(int columnIndex, BigDecimal x)
		throws SQLException
	{
		source().updateBigDecimal(columnIndex, x);
	}

	@Override
	public void updateString(int columnIndex, String x)
		throws SQLException
	{
		source().updateString(columnIndex, x);
	}

	@Override
	public void updateBytes(int columnIndex, byte[] x)
		throws SQLException
	{
		source().updateBytes(columnIndex, x);
	}

	@Override
	public void updateDate(int columnIndex, Date x)
		throws SQLException
	{
		source().updateDate(columnIndex, x);
	}

	@Override
	public void updateTime(int columnIndex, Time x)
		throws SQLException
	{
		source().updateTime(columnIndex, x);
	}

	@Override
	public void updateTimestamp(int columnIndex, Timestamp x)
		throws SQLException
	{
		source().updateTimestamp(columnIndex, x);
	}

	@Override
	public void updateAsciiStream(int columnIndex, InputStream x, int length)
		throws SQLException
	{
		source().updateAsciiStream(columnIndex, x, length);
	}

	@Override
	public void updateBinaryStream(int columnIndex, InputStream x, int length)
		throws SQLException
	{
		source().updateBinaryStream(columnIndex, x, length);
	}

	@Override
	public void updateCharacterStream(int columnIndex, Reader x, int length)
		throws SQLException
	{
		source().updateCharacterStream(columnIndex, x, length);
	}

	@Override
	public void updateObject(int columnIndex, Object x, int scaleOrLength)
		throws SQLException
	{
		source().updateObject(columnIndex, x, scaleOrLength);
	}

	@Override
	public void updateObject(int columnIndex, Object x)
		throws SQLException
	{
		source().updateObject(columnIndex, x);
	}

	@Override
	public void updateNull(String columnLabel)
		throws SQLException
	{
		source().updateNull(columnLabel);
	}

	@Override
	public void updateBoolean(String columnLabel, boolean x)
		throws SQLException
	{
		source().updateBoolean(columnLabel, x);
	}

	@Override
	public void updateByte(String columnLabel, byte x)
		throws SQLException
	{
		source().updateByte(columnLabel, x);
	}

	@Override
	public void updateShort(String columnLabel, short x)
		throws SQLException
	{
		source().updateShort(columnLabel, x);
	}

	@Override
	public void updateInt(String columnLabel, int x)
		throws SQLException
	{
		source().updateInt(columnLabel, x);
	}

	@Override
	public void updateLong(String columnLabel, long x)
		throws SQLException
	{
		source().updateLong(columnLabel, x);
	}

	@Override
	public void updateFloat(String columnLabel, float x)
		throws SQLException
	{
		source().updateFloat(columnLabel, x);
	}

	@Override
	public void updateDouble(String columnLabel, double x)
		throws SQLException
	{
		source().updateDouble(columnLabel, x);
	}

	@Override
	public void updateBigDecimal(String columnLabel, BigDecimal x)
		throws SQLException
	{
		source().updateBigDecimal(columnLabel, x);
	}

	@Override
	public void updateString(String columnLabel, String x)
		throws SQLException
	{
		source().updateString(columnLabel, x);
	}

	@Override
	public void updateBytes(String columnLabel, byte[] x)
		throws SQLException
	{
		source().updateBytes(columnLabel, x);
	}

	@Override
	public void updateDate(String columnLabel, Date x)
		throws SQLException
	{
		source().updateDate(columnLabel, x);
	}

	@Override
	public void updateTime(String columnLabel, Time x)
		throws SQLException
	{
		source().updateTime(columnLabel, x);
	}

	@Override
	public void updateTimestamp(String columnLabel, Timestamp x)
		throws SQLException
	{
		source().updateTimestamp(columnLabel, x);
	}

	@Override
	public void updateAsciiStream(String columnLabel, InputStream x, int length)
		throws SQLException
	{
		source().updateAsciiStream(columnLabel, x, length);
	}

	@Override
	public void updateBinaryStream(String columnLabel, InputStream x, int length)
		throws SQLException
	{
		source().updateBinaryStream(columnLabel, x, length);
	}

	@Override
	public void updateCharacterStream(String columnLabel, Reader reader, int length)
		throws SQLException
	{
		source().updateCharacterStream(columnLabel, reader, length);
	}

	@Override
	public void updateObject(String columnLabel, Object x, int scaleOrLength)
		throws SQLException
	{
		source().updateObject(columnLabel, x, scaleOrLength);
	}

	@Override
	public void updateObject(String columnLabel, Object x)
		throws SQLException
	{
		source().updateObject(columnLabel, x);
	}

	@Override
	public void insertRow()
		throws SQLException
	{
		source().insertRow();
	}

	@Override
	public void updateRow()
		throws SQLException
	{
		source().updateRow();
	}

	@Override
	public void deleteRow()
		throws SQLException
	{
		source().deleteRow();
	}

	@Override
	public void refreshRow()
		throws SQLException
	{
		source().refreshRow();
	}

	@Override
	public void cancelRowUpdates()
		throws SQLException
	{
		source().cancelRowUpdates();
	}

	@Override
	public void moveToInsertRow()
		throws SQLException
	{
		source().moveToInsertRow();
	}

	@Override
	public void moveToCurrentRow()
		throws SQLException
	{
		source().moveToCurrentRow();
	}

	@Override
	public Statement getStatement()
		throws SQLException
	{
		return this.ownerStatement;
	}

	@Override
	public Object getObject(int columnIndex, Map<String, Class<?>> map)
		throws SQLException
	{
		return source().getObject(columnIndex, map);
	}

	@Override
	public Ref getRef(int columnIndex)
		throws SQLException
	{
		return new ServerRefImpl(source().getRef(columnIndex));
	}

	@Override
	public Blob getBlob(int columnIndex)
		throws SQLException
	{
		return new ServerBlobImpl(source().getBlob(columnIndex));
	}

	@Override
	public Clob getClob(int columnIndex)
		throws SQLException
	{
		return new ServerClobImpl(source().getClob(columnIndex));
	}

	@Override
	public Array getArray(int columnIndex)
		throws SQLException
	{
		return new ServerArrayImpl(source().getArray(columnIndex));
	}

	@Override
	public Object getObject(String columnLabel, Map<String, Class<?>> map)
		throws SQLException
	{
		return source().getObject(columnLabel, map);
	}

	@Override
	public Ref getRef(String columnLabel)
		throws SQLException
	{
		return new ServerRefImpl(source().getRef(columnLabel));
	}

	@Override
	public Blob getBlob(String columnLabel)
		throws SQLException
	{
		return new ServerBlobImpl(source().getBlob(columnLabel));
	}

	@Override
	public Clob getClob(String columnLabel)
		throws SQLException
	{
		return new ServerClobImpl(source().getClob(columnLabel));
	}

	@Override
	public Array getArray(String columnLabel)
		throws SQLException
	{
		return new ServerArrayImpl(source().getArray(columnLabel));
	}

	@Override
	public Date getDate(int columnIndex, Calendar cal)
		throws SQLException
	{
		return source().getDate(columnIndex, cal);
	}

	@Override
	public Date getDate(String columnLabel, Calendar cal)
		throws SQLException
	{
		return source().getDate(columnLabel, cal);
	}

	@Override
	public Time getTime(int columnIndex, Calendar cal)
		throws SQLException
	{
		return source().getTime(columnIndex, cal);
	}

	@Override
	public Time getTime(String columnLabel, Calendar cal)
		throws SQLException
	{
		return source().getTime(columnLabel, cal);
	}

	@Override
	public Timestamp getTimestamp(int columnIndex, Calendar cal)
		throws SQLException
	{
		return source().getTimestamp(columnIndex, cal);
	}

	@Override
	public Timestamp getTimestamp(String columnLabel, Calendar cal)
		throws SQLException
	{
		return source().getTimestamp(columnLabel, cal);
	}

	@Override
	public URL getURL(int columnIndex)
		throws SQLException
	{
		return source().getURL(columnIndex);
	}

	@Override
	public URL getURL(String columnLabel)
		throws SQLException
	{
		return source().getURL(columnLabel);
	}

	@Override
	public void updateRef(int columnIndex, Ref x)
		throws SQLException
	{
		source().updateRef(columnIndex, ((ServerRefImpl)x).source());
	}

	@Override
	public void updateRef(String columnLabel, Ref x)
		throws SQLException
	{
		source().updateRef(columnLabel, ((ServerRefImpl)x).source());
	}

	@Override
	public void updateBlob(int columnIndex, Blob x)
		throws SQLException
	{
		source().updateBlob(columnIndex, ((ServerBlobImpl)x).source());
	}

	@Override
	public void updateBlob(String columnLabel, Blob x)
		throws SQLException
	{
		source().updateBlob(columnLabel, ((ServerBlobImpl)x).source());
	}

	@Override
	public void updateClob(int columnIndex, Clob x)
		throws SQLException
	{
		source().updateClob(columnIndex, ((ServerClobImpl)x).source());
	}

	@Override
	public void updateClob(String columnLabel, Clob x)
		throws SQLException
	{
		source().updateClob(columnLabel, ((ServerClobImpl)x).source());
	}

	@Override
	public void updateArray(int columnIndex, Array x)
		throws SQLException
	{
		source().updateArray(columnIndex, ((ServerArrayImpl)x).source());
	}

	@Override
	public void updateArray(String columnLabel, Array x)
		throws SQLException
	{
		source().updateArray(columnLabel, ((ServerArrayImpl)x).source());
	}

	@Override
	public RowId getRowId(int columnIndex)
		throws SQLException
	{
		return new ServerRowIdImpl(source().getRowId(columnIndex));
	}

	@Override
	public RowId getRowId(String columnLabel)
		throws SQLException
	{
		return new ServerRowIdImpl(source().getRowId(columnLabel));
	}

	@Override
	public void updateRowId(int columnIndex, RowId x)
		throws SQLException
	{
		source().updateRowId(columnIndex, ((ServerRowIdImpl)x).source());
	}

	@Override
	public void updateRowId(String columnLabel, RowId x)
		throws SQLException
	{
		source().updateRowId(columnLabel, ((ServerRowIdImpl)x).source());
	}

	@Override
	public int getHoldability()
		throws SQLException
	{
		return source().getHoldability();
	}

	@Override
	public boolean isClosed()
		throws SQLException
	{
		return source().isClosed();
	}

	@Override
	public void updateNString(int columnIndex, String nString)
		throws SQLException
	{
		source().updateNString(columnIndex, nString);
	}

	@Override
	public void updateNString(String columnLabel, String nString)
		throws SQLException
	{
		source().updateNString(columnLabel, nString);
	}

	@Override
	public void updateNClob(int columnIndex, NClob nClob)
		throws SQLException
	{
		source().updateNClob(columnIndex, ((ServerNClobImpl)nClob).source());
	}

	@Override
	public void updateNClob(String columnLabel, NClob nClob)
		throws SQLException
	{
		source().updateNClob(columnLabel, ((ServerNClobImpl)nClob).source());
	}

	@Override
	public NClob getNClob(int columnIndex)
		throws SQLException
	{
		return new ServerNClobImpl(source().getNClob(columnIndex));
	}

	@Override
	public NClob getNClob(String columnLabel)
		throws SQLException
	{
		return new ServerNClobImpl(source().getNClob(columnLabel));
	}

	@Override
	public SQLXML getSQLXML(int columnIndex)
		throws SQLException
	{
		return new ServerSQLXMLImpl(source().getSQLXML(columnIndex));
	}

	@Override
	public SQLXML getSQLXML(String columnLabel)
		throws SQLException
	{
		return new ServerSQLXMLImpl(source().getSQLXML(columnLabel));
	}

	@Override
	public void updateSQLXML(int columnIndex, SQLXML xmlObject)
		throws SQLException
	{
		source().updateSQLXML(columnIndex, ((ServerSQLXMLImpl)xmlObject).source());
	}

	@Override
	public void updateSQLXML(String columnLabel, SQLXML xmlObject)
		throws SQLException
	{
		source().updateSQLXML(columnLabel, ((ServerSQLXMLImpl)xmlObject).source());
	}

	@Override
	public String getNString(int columnIndex)
		throws SQLException
	{
		return source().getNString(columnIndex);
	}

	@Override
	public String getNString(String columnLabel)
		throws SQLException
	{
		return source().getNString(columnLabel);
	}

	@Override
	public Reader getNCharacterStream(int columnIndex)
		throws SQLException
	{
		return source().getNCharacterStream(columnIndex);
	}

	@Override
	public Reader getNCharacterStream(String columnLabel)
		throws SQLException
	{
		return source().getNCharacterStream(columnLabel);
	}

	@Override
	public void updateNCharacterStream(int columnIndex, Reader x, long length)
		throws SQLException
	{
		source().updateNCharacterStream(columnIndex, x, length);
	}

	@Override
	public void updateNCharacterStream(String columnLabel, Reader reader, long length)
		throws SQLException
	{
		source().updateNCharacterStream(columnLabel, reader, length);
	}

	@Override
	public void updateAsciiStream(int columnIndex, InputStream x, long length)
		throws SQLException
	{
		source().updateAsciiStream(columnIndex, x, length);
	}

	@Override
	public void updateBinaryStream(int columnIndex, InputStream x, long length)
		throws SQLException
	{
		source().updateBinaryStream(columnIndex, x, length);
	}

	@Override
	public void updateCharacterStream(int columnIndex, Reader x, long length)
		throws SQLException
	{
		source().updateCharacterStream(columnIndex, x, length);
	}

	@Override
	public void updateAsciiStream(String columnLabel, InputStream x, long length)
		throws SQLException
	{
		source().updateAsciiStream(columnLabel, x, length);
	}

	@Override
	public void updateBinaryStream(String columnLabel, InputStream x, long length)
		throws SQLException
	{
		source().updateBinaryStream(columnLabel, x, length);
	}

	@Override
	public void updateCharacterStream(String columnLabel, Reader reader, long length)
		throws SQLException
	{
		source().updateCharacterStream(columnLabel, reader, length);
	}

	@Override
	public void updateBlob(int columnIndex, InputStream inputStream, long length)
		throws SQLException
	{
		source().updateBlob(columnIndex, inputStream, length);
	}

	@Override
	public void updateBlob(String columnLabel, InputStream inputStream, long length)
		throws SQLException
	{
		source().updateBlob(columnLabel, inputStream, length);
	}

	@Override
	public void updateClob(int columnIndex, Reader reader, long length)
		throws SQLException
	{
		source().updateClob(columnIndex, reader, length);
	}

	@Override
	public void updateClob(String columnLabel, Reader reader, long length)
		throws SQLException
	{
		source().updateClob(columnLabel, reader, length);
	}

	@Override
	public void updateNClob(int columnIndex, Reader reader, long length)
		throws SQLException
	{
		source().updateNClob(columnIndex, reader, length);
	}

	@Override
	public void updateNClob(String columnLabel, Reader reader, long length)
		throws SQLException
	{
		source().updateNClob(columnLabel, reader, length);
	}

	@Override
	public void updateNCharacterStream(int columnIndex, Reader x)
		throws SQLException
	{
		source().updateNCharacterStream(columnIndex, x);
	}

	@Override
	public void updateNCharacterStream(String columnLabel, Reader reader)
		throws SQLException
	{
		source().updateNCharacterStream(columnLabel, reader);
	}

	@Override
	public void updateAsciiStream(int columnIndex, InputStream x)
		throws SQLException
	{
		source().updateAsciiStream(columnIndex, x);
	}

	@Override
	public void updateBinaryStream(int columnIndex, InputStream x)
		throws SQLException
	{
		source().updateBinaryStream(columnIndex, x);
	}

	@Override
	public void updateCharacterStream(int columnIndex, Reader x)
		throws SQLException
	{
		source().updateCharacterStream(columnIndex, x);
	}

	@Override
	public void updateAsciiStream(String columnLabel, InputStream x)
		throws SQLException
	{
		source().updateAsciiStream(columnLabel, x);
	}

	@Override
	public void updateBinaryStream(String columnLabel, InputStream x)
		throws SQLException
	{
		source().updateBinaryStream(columnLabel, x);
	}

	@Override
	public void updateCharacterStream(String columnLabel, Reader reader)
		throws SQLException
	{
		source().updateCharacterStream(columnLabel, reader);
	}

	@Override
	public void updateBlob(int columnIndex, InputStream inputStream)
		throws SQLException
	{
		source().updateBlob(columnIndex, inputStream);
	}

	@Override
	public void updateBlob(String columnLabel, InputStream inputStream)
		throws SQLException
	{
		source().updateBlob(columnLabel, inputStream);
	}

	@Override
	public void updateClob(int columnIndex, Reader reader)
		throws SQLException
	{
		source().updateClob(columnIndex, reader);
	}

	@Override
	public void updateClob(String columnLabel, Reader reader)
		throws SQLException
	{
		source().updateClob(columnLabel, reader);
	}

	@Override
	public void updateNClob(int columnIndex, Reader reader)
		throws SQLException
	{
		source().updateNClob(columnIndex, reader);
	}

	@Override
	public void updateNClob(String columnLabel, Reader reader)
		throws SQLException
	{
		source().updateNClob(columnLabel, reader);
	}

	@Override
	public <T> T getObject(int columnIndex, Class<T> type)
		throws SQLException
	{
		return source().getObject(columnIndex, type);
	}

	@Override
	public <T> T getObject(String columnLabel, Class<T> type)
		throws SQLException
	{
		return source().getObject(columnLabel, type);
	}

}
