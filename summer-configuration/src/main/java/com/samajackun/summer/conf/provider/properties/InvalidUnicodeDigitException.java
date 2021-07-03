package com.samajackun.summer.conf.provider.properties;

public class InvalidUnicodeDigitException extends UnicodeFormatException
{
	private static final long serialVersionUID=2815064023919159504L;

	private final char digit;

	public InvalidUnicodeDigitException(char digit)
	{
		super("Invalid unicode digit: '" + digit + "'");
		this.digit=digit;
	}

	public char getDigit()
	{
		return this.digit;
	}

}
