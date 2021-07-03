package com.samajackun.summer.shared.jdbc;

import java.io.Serializable;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;
import java.util.WeakHashMap;

public class ServerResultSetMetaDataImpl implements ResultSetMetaData, Serializable
{
	private static final long serialVersionUID=5311985543546823031L;

	private static final Map<Integer, ResultSetMetaData> INSTANCES=new WeakHashMap<Integer, ResultSetMetaData>(4096);

	private final Integer instanceIndex;

	private transient ResultSetMetaData src;

	public ServerResultSetMetaDataImpl(ResultSetMetaData src)
	{
		super();
		synchronized (INSTANCES)
		{
			this.instanceIndex=INSTANCES.size();
			INSTANCES.put(INSTANCES.size(), src);
		}
		this.src=src;
	}

	final ResultSetMetaData source()
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
	public int getColumnCount()
		throws SQLException
	{
		return source().getColumnCount();
	}

	@Override
	public boolean isAutoIncrement(int column)
		throws SQLException
	{
		return source().isAutoIncrement(column);
	}

	@Override
	public boolean isCaseSensitive(int column)
		throws SQLException
	{
		return source().isCaseSensitive(column);
	}

	@Override
	public boolean isSearchable(int column)
		throws SQLException
	{
		return source().isSearchable(column);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface)
		throws SQLException
	{
		return source().isWrapperFor(iface);
	}

	@Override
	public boolean isCurrency(int column)
		throws SQLException
	{
		return source().isCurrency(column);
	}

	@Override
	public int isNullable(int column)
		throws SQLException
	{
		return source().isNullable(column);
	}

	@Override
	public boolean isSigned(int column)
		throws SQLException
	{
		return source().isSigned(column);
	}

	@Override
	public int getColumnDisplaySize(int column)
		throws SQLException
	{
		return source().getColumnDisplaySize(column);
	}

	@Override
	public String getColumnLabel(int column)
		throws SQLException
	{
		return source().getColumnLabel(column);
	}

	@Override
	public String getColumnName(int column)
		throws SQLException
	{
		return source().getColumnName(column);
	}

	@Override
	public String getSchemaName(int column)
		throws SQLException
	{
		return source().getSchemaName(column);
	}

	@Override
	public int getPrecision(int column)
		throws SQLException
	{
		return source().getPrecision(column);
	}

	@Override
	public int getScale(int column)
		throws SQLException
	{
		return source().getScale(column);
	}

	@Override
	public String getTableName(int column)
		throws SQLException
	{
		return source().getTableName(column);
	}

	@Override
	public String getCatalogName(int column)
		throws SQLException
	{
		return source().getCatalogName(column);
	}

	@Override
	public int getColumnType(int column)
		throws SQLException
	{
		return source().getColumnType(column);
	}

	@Override
	public String getColumnTypeName(int column)
		throws SQLException
	{
		return source().getColumnTypeName(column);
	}

	@Override
	public boolean isReadOnly(int column)
		throws SQLException
	{
		return source().isReadOnly(column);
	}

	@Override
	public boolean isWritable(int column)
		throws SQLException
	{
		return source().isWritable(column);
	}

	@Override
	public boolean isDefinitelyWritable(int column)
		throws SQLException
	{
		return source().isDefinitelyWritable(column);
	}

	@Override
	public String getColumnClassName(int column)
		throws SQLException
	{
		return source().getColumnClassName(column);
	}

}
