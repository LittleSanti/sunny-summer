package com.samajackun.summer.shared.jdbc;

import java.io.Serializable;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;

public class ServerConnectionImpl implements Connection, Serializable
{
	private static final long serialVersionUID=-4748249750044758336L;

	private static final Map<Integer, Connection> INSTANCES=new WeakHashMap<Integer, Connection>(4096);

	private final Integer instanceIndex;

	private transient Connection src;

	public ServerConnectionImpl(Connection src)
	{
		super();
		synchronized (INSTANCES)
		{
			this.instanceIndex=INSTANCES.size();
			INSTANCES.put(INSTANCES.size(), src);
		}
		this.src=src;
	}

	final Connection source()
	{
		if (this.src == null)
		{
			this.src=INSTANCES.get(this.instanceIndex);
		}
		return this.src;
	}

	@Override
	public <T> T unwrap(Class<T> iface)
					throws SQLException

	{
		return source().unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface)
					throws SQLException

	{
		return source().isWrapperFor(iface);
	}

	@Override
	public Statement createStatement()
					throws SQLException

	{
		return new ServerStatementImpl(this, source().createStatement());
	}

	@Override
	public PreparedStatement prepareStatement(String sql)
					throws SQLException

	{
		return new ServerPreparedStatementImpl(this, source().prepareStatement(sql));
	}

	@Override
	public CallableStatement prepareCall(String sql)
					throws SQLException

	{
		return new ServerCallableStatementImpl(this, source().prepareCall(sql));
	}

	@Override
	public String nativeSQL(String sql)
					throws SQLException

	{
		return source().nativeSQL(sql);
	}

	@Override
	public void setAutoCommit(boolean autoCommit)
					throws SQLException

	{
		source().setAutoCommit(autoCommit);
	}

	@Override
	public boolean getAutoCommit()
					throws SQLException

	{
		return source().getAutoCommit();
	}

	@Override
	public void commit()
					throws SQLException

	{
		source().commit();
	}

	@Override
	public void rollback()
					throws SQLException

	{
		source().rollback();
	}

	@Override
	public void close()
					throws SQLException
	{
		source().close();
	}

	@Override
	public boolean isClosed()
					throws SQLException

	{
		return source().isClosed();
	}

	@Override
	public DatabaseMetaData getMetaData()
					throws SQLException

	{
		return new ServerDatabaseMetaDataImpl(this, source().getMetaData());
	}

	@Override
	public void setReadOnly(boolean readOnly)
					throws SQLException

	{
		source().setReadOnly(readOnly);
	}

	@Override
	public boolean isReadOnly()
					throws SQLException

	{
		return source().isReadOnly();
	}

	@Override
	public void setCatalog(String catalog)
					throws SQLException

	{
		source().setCatalog(catalog);
	}

	@Override
	public String getCatalog()
					throws SQLException

	{
		return source().getCatalog();
	}

	@Override
	public void setTransactionIsolation(int level)
					throws SQLException

	{
		source().setTransactionIsolation(level);
	}

	@Override
	public int getTransactionIsolation()
					throws SQLException

	{
		return source().getTransactionIsolation();
	}

	@Override
	public SQLWarning getWarnings()
					throws SQLException

	{
		return source().getWarnings();
	}

	@Override
	public void clearWarnings()
					throws SQLException

	{
		source().clearWarnings();
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency)
					throws SQLException

	{
		return new ServerStatementImpl(this, source().createStatement(resultSetType, resultSetConcurrency));
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
					throws SQLException

	{
		return new ServerPreparedStatementImpl(this, source().prepareStatement(sql, resultSetType, resultSetConcurrency));
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
					throws SQLException

	{
		return new ServerCallableStatementImpl(this, source().prepareCall(sql, resultSetType, resultSetConcurrency));
	}

	@Override
	public Map<String, Class<?>> getTypeMap()
					throws SQLException
					{
		return new HashMap<String, Class<?>>(source().getTypeMap());
					}

	@Override
	public void setTypeMap(Map<String, Class<?>> map)
					throws SQLException
	{
		// No creo necesario hacer unwrap.
		source().setTypeMap(map);
	}

	@Override
	public void setHoldability(int holdability)
					throws SQLException

	{
		source().setHoldability(holdability);
	}

	@Override
	public int getHoldability()
					throws SQLException

	{
		return source().getHoldability();
	}

	@Override
	public Savepoint setSavepoint()
					throws SQLException

	{
		return new ServerSavepointImpl(source().setSavepoint());
	}

	@Override
	public Savepoint setSavepoint(String name)
					throws SQLException

	{
		return new ServerSavepointImpl(source().setSavepoint(name));
	}

	@Override
	public void rollback(Savepoint savepoint)
					throws SQLException

	{
		source().rollback(((ServerSavepointImpl)savepoint).source());
	}

	@Override
	public void releaseSavepoint(Savepoint savepoint)
					throws SQLException

	{
		source().releaseSavepoint(((ServerSavepointImpl)savepoint).source());
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
					throws SQLException

	{
		return new ServerStatementImpl(this, source().createStatement(resultSetType, resultSetConcurrency, resultSetHoldability));
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability)
					throws SQLException

	{
		return new ServerPreparedStatementImpl(this, source().prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability));
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability)
					throws SQLException

	{
		return new ServerCallableStatementImpl(this, source().prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability));
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
					throws SQLException

	{
		return new ServerPreparedStatementImpl(this, source().prepareStatement(sql, autoGeneratedKeys));
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes)
					throws SQLException

	{
		return new ServerPreparedStatementImpl(this, source().prepareStatement(sql, columnIndexes));
	}

	@Override
	public PreparedStatement prepareStatement(String sql, String[] columnNames)
					throws SQLException

	{
		return new ServerPreparedStatementImpl(this, source().prepareStatement(sql, columnNames));
	}

	@Override
	public Clob createClob()
					throws SQLException

	{
		return new ServerClobImpl(source().createClob());
	}

	@Override
	public Blob createBlob()
					throws SQLException

	{
		return new ServerBlobImpl(source().createBlob());
	}

	@Override
	public NClob createNClob()
					throws SQLException

	{
		return new ServerNClobImpl(source().createNClob());
	}

	@Override
	public SQLXML createSQLXML()
					throws SQLException

	{
		return new ServerSQLXMLImpl(source().createSQLXML());
	}

	@Override
	public boolean isValid(int timeout)
					throws SQLException

	{
		return source().isValid(timeout);
	}

	@Override
	public void setClientInfo(String name, String value)
					throws SQLClientInfoException
	{
		source().setClientInfo(name, value);
	}

	@Override
	public void setClientInfo(Properties properties)
					throws SQLClientInfoException
	{
		source().setClientInfo(properties);
	}

	@Override
	public String getClientInfo(String name)
					throws SQLException

	{
		return source().getClientInfo(name);
	}

	@Override
	public Properties getClientInfo()
					throws SQLException

	{
		return source().getClientInfo();
	}

	@Override
	public Array createArrayOf(String typeName, Object[] elements)
					throws SQLException

	{
		return new ServerArrayImpl(source().createArrayOf(typeName, elements));
	}

	@Override
	public Struct createStruct(String typeName, Object[] attributes)
					throws SQLException

	{
		return new ServerStructImpl(source().createStruct(typeName, attributes));
	}

	@Override
	public void setSchema(String schema)
					throws SQLException

	{
		source().setSchema(schema);
	}

	@Override
	public String getSchema()
					throws SQLException

	{
		return source().getSchema();
	}

	@Override
	public void abort(Executor executor)
					throws SQLException

	{
		source().abort(((ServerExecutorImpl)executor).source());
	}

	@Override
	public void setNetworkTimeout(Executor executor, int milliseconds)
					throws SQLException

	{
		source().setNetworkTimeout(((ServerExecutorImpl)executor).source(), milliseconds);
	}

	@Override
	public int getNetworkTimeout()
					throws SQLException

	{
		return source().getNetworkTimeout();
	}

}
