package com.samajackun.summer.core.path;

public class IllegalEmptyPathException extends FormatTokenException
{
	private static final long serialVersionUID=4397419264819647634L;

	public IllegalEmptyPathException()
	{
		super("Illegal empty path");
	}

}
