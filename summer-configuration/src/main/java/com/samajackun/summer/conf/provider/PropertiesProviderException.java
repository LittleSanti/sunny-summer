package com.samajackun.summer.conf.provider;

public class PropertiesProviderException extends Exception
{
	private static final long serialVersionUID=8477646332880855622L;

	public PropertiesProviderException(String message)
	{
		super(message);
	}

	public PropertiesProviderException(Throwable cause)
	{
		super(cause);
	}

	public PropertiesProviderException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
