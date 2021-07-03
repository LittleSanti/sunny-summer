package com.samajackun.summer.shared;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.samajackun.summer.shared.jdbc.ServerConnectionImpl;

public class ConnectionFactoryImpl extends UnicastRemoteObject implements ConnectionFactory
{
	private static final long serialVersionUID=1L;

	public ConnectionFactoryImpl() throws RemoteException
	{
		super();
	}

	public ConnectionFactoryImpl(int port) throws RemoteException
	{
		super(port);
	}

	@Override
	public ServerConnectionImpl createConnection(String profile)
		throws RemoteException
	{
		// TODO
		return null;
	}

}
