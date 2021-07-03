package com.samajackun.summer.shared.jdbc;

import java.io.Serializable;
import java.sql.RowId;
import java.util.Map;
import java.util.WeakHashMap;

public class ServerRowIdImpl implements Serializable, RowId
{
	private static final long serialVersionUID=-3455860014468254305L;

	private static final Map<Integer, RowId> INSTANCES=new WeakHashMap<Integer, RowId>(4096);

	private final Integer instanceIndex;

	private transient RowId src;

	public ServerRowIdImpl(RowId src)
	{
		super();
		synchronized (INSTANCES)
		{
			this.instanceIndex=INSTANCES.size();
			INSTANCES.put(INSTANCES.size(), src);
		}
		this.src=src;
	}

	final RowId source()
	{
		if (this.src == null)
		{
			this.src=INSTANCES.get(this.instanceIndex);
		}
		return this.src;
	}

	@Override
	public boolean equals(Object obj)
	{
		return source().equals(obj);
	}

	@Override
	public byte[] getBytes()
	{
		return source().getBytes();
	}

	@Override
	public String toString()
	{
		return source().toString();
	}

	@Override
	public int hashCode()
	{
		return source().hashCode();
	}
}
