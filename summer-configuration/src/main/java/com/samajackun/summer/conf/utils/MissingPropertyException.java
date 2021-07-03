package com.samajackun.summer.conf.utils;

public class MissingPropertyException extends IllegalArgumentException
{
	private static final long serialVersionUID=-6847149086482412418L;

	private final String propertyName;

	public MissingPropertyException(String propertyName)
	{
		super("Missing system property '" + propertyName + "'");
		this.propertyName=propertyName;
	}

	public String getPropertyName()
	{
		return this.propertyName;
	}
}
