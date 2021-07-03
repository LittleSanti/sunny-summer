package com.samajackun.summer.module;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.samajackun.summer.core.Identifier;
import com.samajackun.summer.core.IdentifierPath;

public class Module
{
	private final IdentifierPath name;

	private final Map<Identifier, Service> services=new HashMap<Identifier, Service>();

	public Module(IdentifierPath name)
	{
		super();
		this.name=name;
	}

	void addService(Identifier serviceName, Service service)
	{
		this.services.put(serviceName, service);
	}

	public IdentifierPath getName()
	{
		return this.name;
	}

	public Service getService(Identifier serviceName)
		throws ServiceNotFoundException
	{
		Service service=this.services.get(serviceName);
		if (service == null)
		{
			throw new ServiceNotFoundException(this.name, serviceName);
		}
		return service;
	}

	public Module getModule(Identifier moduleName)
					throws ModuleNotFoundException
	{
		// TODO
		return null;
	}

	public Set<Identifier> getServiceNames()
	{
		return this.services.keySet();
	}

}
