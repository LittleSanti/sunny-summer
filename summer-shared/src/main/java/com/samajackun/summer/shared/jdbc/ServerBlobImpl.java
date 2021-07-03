package com.samajackun.summer.shared.jdbc;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Map;
import java.util.WeakHashMap;

// FIXME Falta hacer wrap/unwrap de los InputStream y OutputStream.
public class ServerBlobImpl implements Serializable, Blob
{
	private static final long serialVersionUID=-7926014250251666099L;

	private static final Map<Integer, Blob> INSTANCES=new WeakHashMap<Integer, Blob>(4096);

	private final Integer instanceIndex;

	private transient Blob src;

	public ServerBlobImpl(Blob src)
	{
		super();
		synchronized (INSTANCES)
		{
			this.instanceIndex=INSTANCES.size();
			INSTANCES.put(INSTANCES.size(), src);
		}
		this.src=src;
	}

	final Blob source()
	{
		if (this.src == null)
		{
			this.src=INSTANCES.get(this.instanceIndex);
		}
		return this.src;
	}

	@Override
	public long length()
		throws SQLException
	{
		return source().length();
	}

	@Override
	public byte[] getBytes(long pos, int length)
		throws SQLException
	{
		return source().getBytes(pos, length);
	}

	@Override
	public InputStream getBinaryStream()
		throws SQLException
	{
		return source().getBinaryStream();
	}

	@Override
	public long position(byte[] pattern, long start)
		throws SQLException
	{
		return source().position(pattern, start);
	}

	@Override
	public long position(Blob pattern, long start)
		throws SQLException
	{
		return source().position(((ServerBlobImpl)pattern).source(), start);
	}

	@Override
	public int setBytes(long pos, byte[] bytes)
		throws SQLException
	{
		return source().setBytes(pos, bytes);
	}

	@Override
	public int setBytes(long pos, byte[] bytes, int offset, int len)
		throws SQLException
	{
		return source().setBytes(pos, bytes, offset, len);
	}

	@Override
	public OutputStream setBinaryStream(long pos)
		throws SQLException
	{
		return source().setBinaryStream(pos);
	}

	@Override
	public void truncate(long len)
		throws SQLException
	{
		source().truncate(len);
	}

	@Override
	public void free()
		throws SQLException
	{
		source().free();
	}

	@Override
	public InputStream getBinaryStream(long pos, long length)
		throws SQLException
	{
		return source().getBinaryStream(pos, length);
	}

}
