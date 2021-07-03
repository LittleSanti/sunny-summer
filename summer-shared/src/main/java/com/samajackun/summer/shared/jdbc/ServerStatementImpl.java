package com.samajackun.summer.shared.jdbc;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.Map;
import java.util.WeakHashMap;

public class ServerStatementImpl implements Statement, Serializable
{
	private static final long serialVersionUID=-8100894397136956045L;

	private static final Map<Integer, Statement> INSTANCES=new WeakHashMap<Integer, Statement>(4096);

	private final Integer instanceIndex;

	private transient Statement src;

	private final ServerConnectionImpl owner;

	private ServerResultSetImpl currentResultSet;

	public ServerStatementImpl(ServerConnectionImpl owner, Statement src)
	{
		super();
		synchronized (INSTANCES)
		{
			this.instanceIndex=INSTANCES.size();
			INSTANCES.put(INSTANCES.size(), src);
		}
		this.owner=owner;
		this.src=src;
	}

	Statement source()
	{
		if (this.src == null)
		{
			this.src=INSTANCES.get(this.instanceIndex);
		}
		return this.src;
	}

	ServerResultSetImpl createResultSet(ResultSet rs)
	{
		this.currentResultSet=new ServerResultSetImpl(this, rs);
		return this.currentResultSet;
	}

	@Override
	public <T> T unwrap(Class<T> iface)
		throws SQLException
	{
		return source().unwrap(iface);
	}

	@Override
	public ResultSet executeQuery(String sql)
		throws SQLException
	{
		return createResultSet(source().executeQuery(sql));
	}

	@Override
	public boolean isWrapperFor(Class<?> iface)
		throws SQLException
	{
		return source().isWrapperFor(iface);
	}

	@Override
	public int executeUpdate(String sql)
		throws SQLException
	{
		return source().executeUpdate(sql);
	}

	@Override
	public void close()
		throws SQLException
	{
		source().close();
	}

	@Override
	public int getMaxFieldSize()
		throws SQLException
	{
		return source().getMaxFieldSize();
	}

	@Override
	public void setMaxFieldSize(int max)
		throws SQLException
	{
		source().setMaxFieldSize(max);
	}

	@Override
	public int getMaxRows()
		throws SQLException
	{
		return source().getMaxRows();
	}

	@Override
	public void setMaxRows(int max)
		throws SQLException
	{
		source().setMaxRows(max);
	}

	@Override
	public void setEscapeProcessing(boolean enable)
		throws SQLException
	{
		source().setEscapeProcessing(enable);
	}

	@Override
	public int getQueryTimeout()
		throws SQLException
	{
		return source().getQueryTimeout();
	}

	@Override
	public void setQueryTimeout(int seconds)
		throws SQLException
	{
		source().setQueryTimeout(seconds);
	}

	@Override
	public void cancel()
		throws SQLException
	{
		source().cancel();
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
	public void setCursorName(String name)
		throws SQLException
	{
		source().setCursorName(name);
	}

	@Override
	public boolean execute(String sql)
		throws SQLException
	{
		return source().execute(sql);
	}

	@Override
	public ResultSet getResultSet()
		throws SQLException
	{
		return this.currentResultSet;
	}

	@Override
	public int getUpdateCount()
		throws SQLException
	{
		return source().getUpdateCount();
	}

	@Override
	public boolean getMoreResults()
		throws SQLException
	{
		return source().getMoreResults();
	}

	@Override
	public void setFetchDirection(int direction)
		throws SQLException
	{
		source().setFetchDirection(direction);
	}

	@Override
	public int getFetchDirection()
		throws SQLException
	{
		return source().getFetchDirection();
	}

	@Override
	public void setFetchSize(int rows)
		throws SQLException
	{
		source().setFetchSize(rows);
	}

	@Override
	public int getFetchSize()
		throws SQLException
	{
		return source().getFetchSize();
	}

	@Override
	public int getResultSetConcurrency()
		throws SQLException
	{
		return source().getResultSetConcurrency();
	}

	@Override
	public int getResultSetType()
		throws SQLException
	{
		return source().getResultSetType();
	}

	@Override
	public void addBatch(String sql)
		throws SQLException
	{
		source().addBatch(sql);
	}

	@Override
	public void clearBatch()
		throws SQLException
	{
		source().clearBatch();
	}

	@Override
	public int[] executeBatch()
		throws SQLException
	{
		return source().executeBatch();
	}

	@Override
	public Connection getConnection()
		throws SQLException
	{
		return this.owner;
	}

	@Override
	public boolean getMoreResults(int current)
		throws SQLException
	{
		return source().getMoreResults(current);
	}

	@Override
	public ResultSet getGeneratedKeys()
		throws SQLException
	{
		return new ServerResultSetImpl(null, source().getGeneratedKeys());
	}

	@Override
	public int executeUpdate(String sql, int autoGeneratedKeys)
		throws SQLException
	{
		return source().executeUpdate(sql, autoGeneratedKeys);
	}

	@Override
	public int executeUpdate(String sql, int[] columnIndexes)
		throws SQLException
	{
		return source().executeUpdate(sql, columnIndexes);
	}

	@Override
	public int executeUpdate(String sql, String[] columnNames)
		throws SQLException
	{
		return source().executeUpdate(sql, columnNames);
	}

	@Override
	public boolean execute(String sql, int autoGeneratedKeys)
		throws SQLException
	{
		return source().execute(sql, autoGeneratedKeys);
	}

	@Override
	public boolean execute(String sql, int[] columnIndexes)
		throws SQLException
	{
		return source().execute(sql, columnIndexes);
	}

	@Override
	public boolean execute(String sql, String[] columnNames)
		throws SQLException
	{
		return source().execute(sql, columnNames);
	}

	@Override
	public int getResultSetHoldability()
		throws SQLException
	{
		return source().getResultSetHoldability();
	}

	@Override
	public boolean isClosed()
		throws SQLException
	{
		return source().isClosed();
	}

	@Override
	public void setPoolable(boolean poolable)
		throws SQLException
	{
		source().setPoolable(poolable);
	}

	@Override
	public boolean isPoolable()
		throws SQLException
	{
		return source().isPoolable();
	}

	@Override
	public void closeOnCompletion()
		throws SQLException
	{
		source().closeOnCompletion();
	}

	@Override
	public boolean isCloseOnCompletion()
		throws SQLException
	{
		return source().isCloseOnCompletion();
	}

}
