package com.samajackun.summer.module;

import com.samajackun.summer.core.Identifier;

public class ServiceNotFoundException extends ModuleException
{
	private static final long serialVersionUID=6609203685293614031L;

	private final Identifier service;

	public ServiceNotFoundException(Identifier module, Identifier service)
	{
		super(module, "Service " + service + " not found at module " + module);
		this.service=service;
	}

	public Identifier getService()
	{
		return this.service;
	}
}
