package com.samajackun.summer.shared.streams;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientInputStreamImpl extends UnicastRemoteObject implements ClientInputStream
{
	private static final long serialVersionUID=-7079715242344720125L;

	private final InputStream input;

	public ClientInputStreamImpl(InputStream input) throws RemoteException
	{
		super();
		this.input=input;
	}

	public ServerInputStreamImpl getServerInputStream()
		throws MalformedURLException,
		RemoteException,
		AlreadyBoundException
	{
		// TODO Estos valores son sólo para la prueba de concepto.
		String host="localhost";
		int port=1099;
		String service="input" + System.currentTimeMillis();
		Naming.bind(service, this);
		return new ServerInputStreamImpl(host, port, service);
	}

	@Override
	public int read()
		throws IOException,
		RemoteException
	{
		return this.input.read();
	}

	@Override
	public int read(byte[] b)
		throws IOException,
		RemoteException
	{
		return this.input.read(b);
	}

	@Override
	public int read(byte[] b, int off, int len)
		throws IOException,
		RemoteException
	{
		return this.input.read(b, off, len);
	}

	@Override
	public long skip(long n)
		throws IOException,
		RemoteException
	{
		return this.input.skip(n);
	}

	@Override
	public int available()
		throws IOException,
		RemoteException
	{
		return this.input.available();
	}

	@Override
	public void close()
		throws IOException,
		RemoteException
	{
		this.input.close();
	}

	@Override
	public void mark(int readlimit)
		throws RemoteException
	{
		this.input.mark(readlimit);
	}

	@Override
	public void reset()
		throws IOException,
		RemoteException
	{
		this.input.reset();
	}

	@Override
	public boolean markSupported()
		throws RemoteException
	{
		return this.input.markSupported();
	}
}
