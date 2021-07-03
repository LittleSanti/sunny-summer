package com.samajackun.summer.core;

public class FactoryError extends Error
{
	private static final long serialVersionUID=-1106664297264233239L;

	public FactoryError()
	{
	}

	public FactoryError(String message)
	{
		super(message);
	}

	public FactoryError(Throwable cause)
	{
		super(cause);
	}

	public FactoryError(String message, Throwable cause)
	{
		super(message, cause);
	}

	public FactoryError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
