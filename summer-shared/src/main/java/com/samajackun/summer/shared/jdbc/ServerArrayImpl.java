package com.samajackun.summer.shared.jdbc;

import java.io.Serializable;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.WeakHashMap;

// FIXME Los métodos que retornan Object están pendientes de hacer wrap. Pero como no sabemos la clase... :-(
public class ServerArrayImpl implements Serializable, Array
{
	private static final long serialVersionUID=-2917548185118865749L;

	private static final Map<Integer, Array> INSTANCES=new WeakHashMap<Integer, Array>(4096);

	private final Integer instanceIndex;

	private transient Array src;

	public ServerArrayImpl(Array src)
	{
		super();
		synchronized (INSTANCES)
		{
			this.instanceIndex=INSTANCES.size();
			INSTANCES.put(INSTANCES.size(), src);
		}
		this.src=src;
	}

	final Array source()
	{
		if (this.src == null)
		{
			this.src=INSTANCES.get(this.instanceIndex);
		}
		return this.src;
	}

	@Override
	public String getBaseTypeName()
		throws SQLException
	{
		return source().getBaseTypeName();
	}

	@Override
	public int getBaseType()
		throws SQLException
	{
		return source().getBaseType();
	}

	@Override
	public Object getArray()
		throws SQLException
	{
		return source().getArray();
	}

	@Override
	public Object getArray(Map<String, Class<?>> map)
		throws SQLException
	{
		return source().getArray(map);
	}

	@Override
	public Object getArray(long index, int count)
		throws SQLException
	{
		return source().getArray(index, count);
	}

	@Override
	public Object getArray(long index, int count, Map<String, Class<?>> map)
		throws SQLException
	{
		return source().getArray(index, count, map);
	}

	@Override
	public ResultSet getResultSet()
		throws SQLException
	{
		return source().getResultSet();
	}

	@Override
	public ResultSet getResultSet(Map<String, Class<?>> map)
		throws SQLException
	{
		return new ServerResultSetImpl(source().getResultSet(map));
	}

	@Override
	public ResultSet getResultSet(long index, int count)
		throws SQLException
	{
		return new ServerResultSetImpl(source().getResultSet(index, count));
	}

	@Override
	public ResultSet getResultSet(long index, int count, Map<String, Class<?>> map)
		throws SQLException
	{
		return new ServerResultSetImpl(source().getResultSet(index, count, map));
	}

	@Override
	public void free()
		throws SQLException
	{
		source().free();
	}

}
