package com.samajackun.summer.shared.streams;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInputStream extends Remote
{
	public int read()
		throws IOException,
		RemoteException;

	public int read(byte[] b)
		throws IOException,
		RemoteException;

	public int read(byte[] b, int off, int len)
		throws IOException,
		RemoteException;

	public long skip(long n)
		throws IOException,
		RemoteException;

	public int available()
		throws IOException,
		RemoteException;

	public void close()
		throws IOException,
		RemoteException;

	public void mark(int readlimit)
		throws RemoteException;

	public void reset()
		throws IOException,
		RemoteException;

	public boolean markSupported()
		throws RemoteException;
}
