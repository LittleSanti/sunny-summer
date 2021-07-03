package com.samajackun.summer.core.path;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A token is an array of characters with these restrictions:
 * <ul>
 * <li>It is not null.
 * <li>It is limited in length (upper and/or lower bounded).
 * <li>The allowed characters are limited to a certain alphabet.
 * </ul>
 * 
 * @author Santi
 * @version 2014.00
 */
public abstract class AbstractToken
{
	private final char[] value;

	private transient String stringValue;

	protected AbstractToken(char[] value, int minLen, int maxLen, Pattern alphabet) throws TokenException
	{
		super();
		if (value == null)
		{
			throw new NullPointerException();
		}
		if (value.length < minLen)
		{
			throw new MinBoundTokenException(getClass().getName(), minLen);
		}
		if (value.length > maxLen)
		{
			throw new MaxBoundTokenException(getClass().getName(), maxLen);
		}
		validateChars(value, alphabet);
		char[] newValue=new char[value.length];
		System.arraycopy(value, 0, newValue, 0, value.length);
		this.value=newValue;
	}

	public AbstractToken(String value, int minLen, int maxLen, Pattern alphabet) throws TokenException
	{
		this(value.toCharArray(), minLen, maxLen, alphabet);
	}

	protected void validateChars(char[] value, Pattern alphabet)
		throws FormatTokenException
	{
		if (alphabet != null)
		{
			Matcher matcher=alphabet.matcher(new CharArrayCharSequence(value));
			if (!matcher.matches())
			{
				throw new IllegalAlphabetFormatTokenException(getClass().getName(), alphabet);
			}
		}
	}

	@Override
	public int hashCode()
	{
		final int prime=31;
		int result=prime + Arrays.hashCode(this.value);
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof AbstractToken))
		{
			return false;
		}
		AbstractToken other=(AbstractToken)obj;
		if (!Arrays.equals(this.value, other.value))
		{
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		if (this.stringValue == null)
		{
			this.stringValue=new String(this.value);
		}
		return this.stringValue;
	}

}
