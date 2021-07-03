package com.samajackun.summer.core.path;

public class IdentifierFormatException extends IllegalArgumentException
{
	private static final long serialVersionUID=2851260334201967351L;

	public IdentifierFormatException()
	{
		super();
	}

	public IdentifierFormatException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public IdentifierFormatException(String message)
	{
		super(message);
	}
}
