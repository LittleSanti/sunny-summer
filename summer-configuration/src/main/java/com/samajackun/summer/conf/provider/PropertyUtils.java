package com.samajackun.summer.conf.provider;

public final class PropertyUtils
{
	private PropertyUtils()
	{
	}

	public static <T> T required()
	{
		throw new IllegalArgumentException();
	}

	public static <T> T value(T t)
	{
		return t;
	}
}
