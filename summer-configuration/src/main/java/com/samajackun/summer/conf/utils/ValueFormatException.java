package com.samajackun.summer.conf.utils;

public class ValueFormatException extends IllegalArgumentException
{
	private static final long serialVersionUID=-1602596758232738005L;

	private final String propertyName;

	private final String format;

	private final String value;

	public ValueFormatException(String propertyName, String format, String value)
	{
		super("Value '" + value + "' for property '" + propertyName + "' must have format '" + format + "'");
		this.propertyName=propertyName;
		this.format=format;
		this.value=value;
	}

	public String getPropertyName()
	{
		return this.propertyName;
	}

	public String getFormat()
	{
		return this.format;
	}

	public String getValue()
	{
		return this.value;
	}
}
