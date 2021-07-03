package com.samajackun.summer.shared.jdbc;

import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.Map;
import java.util.WeakHashMap;

public class ServerStructImpl implements Serializable, Struct
{
	private static final long serialVersionUID=5114285564276466987L;

	private static final Map<Integer, Struct> INSTANCES=new WeakHashMap<Integer, Struct>(4096);

	private final Integer instanceIndex;

	private transient Struct src;

	public ServerStructImpl(Struct src)
	{
		super();
		synchronized (INSTANCES)
		{
			this.instanceIndex=INSTANCES.size();
			INSTANCES.put(INSTANCES.size(), src);
		}
		this.src=src;
	}

	final Struct source()
	{
		if (this.src == null)
		{
			this.src=INSTANCES.get(this.instanceIndex);
		}
		return this.src;
	}

	@Override
	public String getSQLTypeName()
		throws SQLException
	{
		return source().getSQLTypeName();
	}

	@Override
	public Object[] getAttributes()
		throws SQLException
	{
		return source().getAttributes();
	}

	@Override
	public Object[] getAttributes(Map<String, Class<?>> map)
		throws SQLException
	{
		return source().getAttributes(map);
	}

}
