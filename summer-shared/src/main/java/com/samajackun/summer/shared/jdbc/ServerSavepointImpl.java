package com.samajackun.summer.shared.jdbc;

import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Map;
import java.util.WeakHashMap;

public class ServerSavepointImpl implements Serializable, Savepoint
{
	private static final long serialVersionUID=-3026982935202248879L;

	private static final Map<Integer, Savepoint> INSTANCES=new WeakHashMap<Integer, Savepoint>(4096);

	private final Integer instanceIndex;

	private transient Savepoint src;

	public ServerSavepointImpl(Savepoint src)
	{
		super();
		synchronized (INSTANCES)
		{
			this.instanceIndex=INSTANCES.size();
			INSTANCES.put(INSTANCES.size(), src);
		}
		this.src=src;
	}

	final Savepoint source()
	{
		if (this.src == null)
		{
			this.src=INSTANCES.get(this.instanceIndex);
		}
		return this.src;
	}

	@Override
	public int getSavepointId()
		throws SQLException
	{
		return source().getSavepointId();
	}

	@Override
	public String getSavepointName()
		throws SQLException
	{
		return source().getSavepointName();
	}

}
