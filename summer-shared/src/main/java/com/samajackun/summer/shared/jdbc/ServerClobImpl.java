package com.samajackun.summer.shared.jdbc;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Map;
import java.util.WeakHashMap;

public class ServerClobImpl implements Clob, Serializable
{
	private static final long serialVersionUID=-3793172619545682905L;

	private static final Map<Integer, Clob> INSTANCES=new WeakHashMap<Integer, Clob>(4096);

	private final Integer instanceIndex;

	private transient Clob src;

	public ServerClobImpl(Clob src)
	{
		super();
		synchronized (INSTANCES)
		{
			this.instanceIndex=INSTANCES.size();
			INSTANCES.put(INSTANCES.size(), src);
		}
		this.src=src;
	}

	final Clob source()
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
	public String getSubString(long pos, int length)
		throws SQLException
	{
		return source().getSubString(pos, length);
	}

	@Override
	public Reader getCharacterStream()
		throws SQLException
	{
		return source().getCharacterStream();
	}

	@Override
	public InputStream getAsciiStream()
		throws SQLException
	{
		return source().getAsciiStream();
	}

	@Override
	public long position(String searchstr, long start)
		throws SQLException
	{
		return source().position(searchstr, start);
	}

	@Override
	public long position(Clob searchstr, long start)
		throws SQLException
	{
		return source().position(searchstr, start);
	}

	@Override
	public int setString(long pos, String str)
		throws SQLException
	{
		return source().setString(pos, str);
	}

	@Override
	public int setString(long pos, String str, int offset, int len)
		throws SQLException
	{
		return source().setString(pos, str, offset, len);
	}

	@Override
	public OutputStream setAsciiStream(long pos)
		throws SQLException
	{
		return source().setAsciiStream(pos);
	}

	@Override
	public Writer setCharacterStream(long pos)
		throws SQLException
	{
		return source().setCharacterStream(pos);
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
	public Reader getCharacterStream(long pos, long length)
		throws SQLException
	{
		return source().getCharacterStream(pos, length);
	};

}
