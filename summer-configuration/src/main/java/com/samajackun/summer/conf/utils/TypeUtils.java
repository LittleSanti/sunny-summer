package com.samajackun.summer.conf.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class TypeUtils
{
	public static String toString(Object value)
	{
		return value == null
			? null
			: (value instanceof String
				? (String)value
				: value.toString());
	}

	public static int toInt(Object value)
	{
		int typedValue;
		if (value == null)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			if (value instanceof Number)
			{
				typedValue=((Number)value).intValue();
			}
			else if (value instanceof String)
			{
				typedValue=Integer.parseInt((String)value);
			}
			else
			{
				throw new IllegalArgumentException();
			}
		}
		return typedValue;
	}

	public static Date toDate(Object value)
	{
		Date typedValue;
		if (value == null)
		{
			typedValue=null;
		}
		else
		{
			if (value instanceof Date)
			{
				typedValue=(Date)value;
			}
			else if (value instanceof String)
			{
				try
				{
					typedValue=new SimpleDateFormat("yyyyMMdd").parse((String)value);
				}
				catch (ParseException e)
				{
					throw new IllegalArgumentException(e);
				}
			}
			else
			{
				throw new IllegalArgumentException();
			}
		}
		return typedValue;
	}
}
