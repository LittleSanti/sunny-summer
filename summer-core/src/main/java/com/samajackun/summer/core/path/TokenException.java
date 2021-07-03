package com.samajackun.summer.core.path;

public class TokenException extends IllegalArgumentException
{
	private static final long serialVersionUID=2851260334201967351L;

	public TokenException()
	{
		super();
	}

	public TokenException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public TokenException(String message)
	{
		super(message);
	}
}
