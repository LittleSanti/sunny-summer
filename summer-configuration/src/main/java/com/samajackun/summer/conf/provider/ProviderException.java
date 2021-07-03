package com.samajackun.summer.conf.provider;

public class ProviderException extends Exception
{
	private static final long serialVersionUID=5354630421111393164L;

	public ProviderException()
	{
		super();
	}

	public ProviderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProviderException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ProviderException(String message)
	{
		super(message);
	}

	public ProviderException(Throwable cause)
	{
		super(cause);
	}
}
