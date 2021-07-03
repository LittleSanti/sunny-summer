package com.samajackun.summer.module;

public class Service
{
	private final Class<?> interfaceClass;

	private final Object proxy;

	public Service(Class<?> interfaceClass, Object proxy)
	{
		super();
		this.interfaceClass=interfaceClass;
		this.proxy=proxy;
	}

	public Class<?> getInterfaceClass()
	{
		return this.interfaceClass;
	}

	public Object getProxy()
	{
		return this.proxy;
	}

}
