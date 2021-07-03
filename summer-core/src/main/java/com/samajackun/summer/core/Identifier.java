package com.samajackun.summer.core;

import java.util.regex.Pattern;

import com.samajackun.summer.core.path.AbstractToken;
import com.samajackun.summer.core.path.TokenException;

public class Identifier extends AbstractToken
{
	public static final int MIN_LENGTH=1;

	public static final int MAX_LENGTH=255;

	public static final Pattern ALPHABET=Pattern.compile("[a-z]([a-zA-Z0-9_]*)");

	public Identifier(char[] value) throws TokenException
	{
		super(value, MIN_LENGTH, MAX_LENGTH, ALPHABET);
	}

	public Identifier(String value) throws TokenException
	{
		super(value, MIN_LENGTH, MAX_LENGTH, ALPHABET);
	}

}
