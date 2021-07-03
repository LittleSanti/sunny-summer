package com.samajackun.summer.core.path;

public class ConverterException extends Exception
{
	private static final long serialVersionUID=-2103022700548265634L;

	public ConverterException()
	{
	}

	public ConverterException(String message)
	{
		super(message);
	}

	public ConverterException(Throwable cause)
	{
		super(cause);
	}

	public ConverterException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ConverterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
