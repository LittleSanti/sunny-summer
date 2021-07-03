package com.samajackun.summer.shared.jdbc;

import java.io.Serializable;
import java.sql.ParameterMetaData;
import java.sql.SQLException;
import java.util.Map;
import java.util.WeakHashMap;

public class ServerParameterMetaDataImpl implements Serializable, ParameterMetaData
{
	private static final long serialVersionUID=7307157326865976349L;

	private static final Map<Integer, ParameterMetaData> INSTANCES=new WeakHashMap<Integer, ParameterMetaData>(4096);

	private final Integer instanceIndex;

	private transient ParameterMetaData src;

	public ServerParameterMetaDataImpl(ParameterMetaData src)
	{
		super();
		synchronized (INSTANCES)
		{
			this.instanceIndex=INSTANCES.size();
			INSTANCES.put(INSTANCES.size(), src);
		}
		this.src=src;
	}

	final ParameterMetaData source()
	{
		if (this.src == null)
		{
			this.src=INSTANCES.get(this.instanceIndex);
		}
		return this.src;
	}

	@Override
	public int getParameterCount()
		throws SQLException
	{
		return source().getParameterCount();
	}

	@Override
	public <T> T unwrap(Class<T> iface)
		throws SQLException
	{
		return source().unwrap(iface);
	}

	@Override
	public int isNullable(int param)
		throws SQLException
	{
		return source().isNullable(param);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface)
		throws SQLException
	{
		return source().isWrapperFor(iface);
	}

	@Override
	public boolean isSigned(int param)
		throws SQLException
	{
		return source().isSigned(param);
	}

	@Override
	public int getPrecision(int param)
		throws SQLException
	{
		return source().getPrecision(param);
	}

	@Override
	public int getScale(int param)
		throws SQLException
	{
		return source().getScale(param);
	}

	@Override
	public int getParameterType(int param)
		throws SQLException
	{
		return source().getParameterType(param);
	}

	@Override
	public String getParameterTypeName(int param)
		throws SQLException
	{
		return source().getParameterTypeName(param);
	}

	@Override
	public String getParameterClassName(int param)
		throws SQLException
	{
		return source().getParameterClassName(param);
	}

	@Override
	public int getParameterMode(int param)
		throws SQLException
	{
		return source().getParameterMode(param);
	}

}
