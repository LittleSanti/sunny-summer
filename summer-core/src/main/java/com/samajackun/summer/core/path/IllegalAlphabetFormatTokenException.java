package com.samajackun.summer.core.path;

import java.util.regex.Pattern;

public class IllegalAlphabetFormatTokenException extends FormatTokenException
{
	private static final long serialVersionUID=-553020757041910911L;

	private final Pattern alphabet;

	public IllegalAlphabetFormatTokenException(String tokenType, Pattern alphabet)
	{
		super("A token of type " + tokenType + " does not conform to alphabet " + alphabet.pattern());
		this.alphabet=alphabet;
	}

	public Pattern getAlphabet()
	{
		return this.alphabet;
	}
}
