package com.samajackun.summer.shared.jdbc;

import java.io.Serializable;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;

public class ServerExecutorImpl implements Executor, Serializable
{
	private static final long serialVersionUID=-8339961715268988364L;

	private static final Map<Integer, Executor> INSTANCES=new WeakHashMap<Integer, Executor>(4096);

	private final Integer instanceIndex;

	private transient Executor src;

	public ServerExecutorImpl(Executor src)
	{
		super();
		synchronized (INSTANCES)
		{
			this.instanceIndex=INSTANCES.size();
			INSTANCES.put(INSTANCES.size(), src);
		}
		this.src=src;
	}

	final Executor source()
	{
		if (this.src == null)
		{
			this.src=INSTANCES.get(this.instanceIndex);
		}
		return this.src;
	}

	@Override
	public void execute(Runnable command)
	{
		// FIXME No hago unwrap porque el runnable ha sido fabricado en remoto:
		// Si ha llegado aquí => es serializable.
		source().execute(command);
	}

}
