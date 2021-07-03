package com.samajackun.summer.shared.streams;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServerInputStreamImpl extends InputStream implements Serializable
{
	private static final long serialVersionUID=-7799443735658634488L;

	private final String clientHost;

	private final int clientPort;

	private final String clientService;

	private transient ClientInputStream clientPeer;

	public ServerInputStreamImpl(String clientHost, int clientPort, String clientService)
	{
		super();
		this.clientHost=clientHost;
		this.clientPort=clientPort;
		this.clientService=clientService;
	}

	private ClientInputStream clientPeer()
		throws IOException
	{
		if (this.clientPeer == null)
		{
			try
			{
				this.clientPeer=(ClientInputStreamImpl)LocateRegistry.getRegistry(this.clientHost, this.clientPort).lookup(this.clientService);
			}
			catch (RemoteException | NotBoundException e)
			{
				throw new IOException(e);
			}
		}
		return this.clientPeer;
	}

	@Override
	public int read()
		throws IOException
	{
		return clientPeer().read();
	}

	@Override
	public int read(byte[] b)
		throws IOException
	{
		return clientPeer().read(b);
	}

	@Override
	public int read(byte[] b, int off, int len)
		throws IOException
	{
		return clientPeer().read(b, off, len);
	}

	@Override
	public long skip(long n)
		throws IOException
	{
		return clientPeer().skip(n);
	}

	@Override
	public int available()
		throws IOException
	{
		return clientPeer().available();
	}

	@Override
	public void close()
		throws IOException
	{
		clientPeer().close();
	}

	@Override
	public synchronized void mark(int readlimit)
	{
		try
		{
			clientPeer().mark(readlimit);
		}
		catch (IOException e)
		{
			// TODO
			throw new RuntimeException(e);
		}
	}

	@Override
	public synchronized void reset()
		throws IOException
	{
		clientPeer().reset();
	}

	@Override
	public boolean markSupported()
	{
		try
		{
			return clientPeer().markSupported();
		}
		catch (IOException e)
		{
			// TODO
			throw new RuntimeException(e);
		}
	}
}
