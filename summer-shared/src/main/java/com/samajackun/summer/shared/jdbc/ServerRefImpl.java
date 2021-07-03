package com.samajackun.summer.shared.jdbc;

import java.io.Serializable;
import java.sql.Ref;
import java.sql.SQLException;
import java.util.Map;
import java.util.WeakHashMap;

public class ServerRefImpl implements Serializable, Ref
{
	private static final long serialVersionUID=-6209037004423699886L;

	private static final Map<Integer, Ref> INSTANCES=new WeakHashMap<Integer, Ref>(4096);

	private final Integer instanceIndex;

	private transient Ref src;

	public ServerRefImpl(Ref src)
	{
		super();
		synchronized (INSTANCES)
		{
			this.instanceIndex=INSTANCES.size();
			INSTANCES.put(INSTANCES.size(), src);
		}
		this.src=src;
	}

	final Ref source()
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
	public Object getObject(Map<String, Class<?>> map)
		throws SQLException
	{
		return source().getObject(map);
	}

	@Override
	public Object getObject()
		throws SQLException
	{
		return source().getObject();
	}

	@Override
	public void setObject(Object value)
		throws SQLException
	{
		source().setObject(value);
	}

}
