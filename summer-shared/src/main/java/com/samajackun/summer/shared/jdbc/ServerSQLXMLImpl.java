package com.samajackun.summer.shared.jdbc;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.util.Map;
import java.util.WeakHashMap;

import javax.xml.transform.Result;
import javax.xml.transform.Source;

public class ServerSQLXMLImpl implements Serializable, SQLXML
{
	private static final long serialVersionUID=-7052942883055549650L;

	private static final Map<Integer, SQLXML> INSTANCES=new WeakHashMap<Integer, SQLXML>(4096);

	private final Integer instanceIndex;

	private transient SQLXML src;

	public ServerSQLXMLImpl(SQLXML src)
	{
		super();
		synchronized (INSTANCES)
		{
			this.instanceIndex=INSTANCES.size();
			INSTANCES.put(INSTANCES.size(), src);
		}
		this.src=src;
	}

	final SQLXML source()
	{
		if (this.src == null)
		{
			this.src=INSTANCES.get(this.instanceIndex);
		}
		return this.src;
	}

	@Override
	public int hashCode()
	{
		return source().hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		return source().equals(obj);
	}

	@Override
	public void free()
		throws SQLException
	{
		source().free();
	}

	@Override
	public InputStream getBinaryStream()
		throws SQLException
	{
		return source().getBinaryStream();
	}

	@Override
	public OutputStream setBinaryStream()
		throws SQLException
	{
		return source().setBinaryStream();
	}

	@Override
	public String toString()
	{
		return source().toString();
	}

	@Override
	public Reader getCharacterStream()
		throws SQLException
	{
		return source().getCharacterStream();
	}

	@Override
	public Writer setCharacterStream()
		throws SQLException
	{
		return source().setCharacterStream();
	}

	@Override
	public String getString()
		throws SQLException
	{
		return source().getString();
	}

	@Override
	public void setString(String value)
		throws SQLException
	{
		source().setString(value);
	}

	@Override
	public <T extends Source> T getSource(Class<T> sourceClass)
		throws SQLException
	{
		return source().getSource(sourceClass);
	}

	@Override
	public <T extends Result> T setResult(Class<T> resultClass)
		throws SQLException
	{
		return source().setResult(resultClass);
	}

}
