package com.samajackun.summer.core;

public class IllegalCharacterInIdentifierException extends IllegalArgumentException
{
	private static final long serialVersionUID=8563928023178788712L;

	private final char c;

	public IllegalCharacterInIdentifierException(char c)
	{
		super("Illegal character '" + c + "' in identifier");
		this.c=c;
	}

	public char getChar()
	{
		return this.c;
	}

}
