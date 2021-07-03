package com.samajackun.summer.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.samajackun.summer.shared.jdbc.ServerConnectionImpl;

public interface ConnectionFactory extends Remote
{
	public ServerConnectionImpl createConnection(String profile)
		throws RemoteException;
}
