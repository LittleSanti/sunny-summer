package com.samajackun.summer.core.path;

public class IllegalCharFormatTokenException extends FormatTokenException
{
	private static final long serialVersionUID=-3912047019061699572L;

	private final char c;

	public IllegalCharFormatTokenException(String tokenType, char c)
	{
		super("Illegal character '" + c + "' in a token of type " + tokenType);
		this.c=c;
	}

	public char getChar()
	{
		return this.c;
	}
}
