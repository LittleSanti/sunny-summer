package com.samajackun.summer.conf.utils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class ParameterUtils
{
	private ParameterUtils()
	{
	}

	public static String getSystemProperty(String propertyName)
	{
		String value=System.getProperty(propertyName);
		if (value == null)
		{
			throw new MissingPropertyException(propertyName);
		}
		return value;
	}

	public static String getSystemProperty(String propertyName, String defaultValue)
	{
		String value=System.getProperty(propertyName);
		if (value == null)
		{
			value=defaultValue;
		}
		return value;
	}

	public static String getSystemPropertyAsString(String propertyName)
	{
		return getSystemProperty(propertyName);
	}

	public static String getSystemPropertyAsString(String propertyName, String defaultValue)
	{
		return getSystemProperty(propertyName, defaultValue);
	}

	public static int getSystemPropertyAsInt(String propertyName, int defaultValue)
	{
		String value=System.getProperty(propertyName);
		return (value == null)
			? defaultValue
			: Integer.parseInt(value);
	}

	public static int getSystemPropertyAsInt(String propertyName)
	{
		return Integer.parseInt(getSystemProperty(propertyName));
	}

	public static long getSystemPropertyAsLong(String propertyName, long defaultValue)
	{
		String value=System.getProperty(propertyName);
		return (value == null)
			? defaultValue
			: Long.parseLong(value);
	}

	public static long getSystemPropertyAsLong(String propertyName)
	{
		return Long.parseLong(getSystemProperty(propertyName));
	}

	public static byte getSystemPropertyAsByte(String propertyName, byte defaultValue)
	{
		String value=System.getProperty(propertyName);
		return (value == null)
			? defaultValue
			: Byte.parseByte(value);
	}

	public static byte getSystemPropertyAsByte(String propertyName)
	{
		return Byte.parseByte(getSystemProperty(propertyName));
	}

	public static boolean getSystemPropertyAsBoolean(String propertyName, boolean defaultValue)
	{
		String value=System.getProperty(propertyName);
		return (value == null)
			? defaultValue
			: Boolean.parseBoolean(value);
	}

	public static boolean getSystemPropertyAsBoolean(String propertyName)
	{
		return Boolean.parseBoolean(getSystemProperty(propertyName));
	}

	public static float getSystemPropertyAsFloat(String propertyName, float defaultValue)
	{
		String value=System.getProperty(propertyName);
		return (value == null)
			? defaultValue
			: Float.parseFloat(value);
	}

	public static float getSystemPropertyAsFloat(String propertyName)
	{
		return Float.parseFloat(getSystemProperty(propertyName));
	}

	public static double getSystemPropertyAsDouble(String propertyName, double defaultValue)
	{
		String value=System.getProperty(propertyName);
		return (value == null)
			? defaultValue
			: Double.parseDouble(value);
	}

	public static double getSystemPropertyAsDouble(String propertyName)
	{
		return Double.parseDouble(getSystemProperty(propertyName));
	}

	public static Date getSystemPropertyAsDate(String propertyName, String format, Date defaultValue)
	{
		String value=System.getProperty(propertyName);
		Date valueDate;
		if (value == null)
		{
			valueDate=defaultValue;
		}
		else
		{
			try
			{
				valueDate=new SimpleDateFormat(format, Locale.getDefault()).parse(value);
			}
			catch (ParseException e)
			{
				throw new ValueFormatException(propertyName, format, value);
			}
		}
		return valueDate;
	}

	public static Date getSystemPropertyAsDate(String propertyName, String format)
	{
		String value=getSystemProperty(propertyName);
		try
		{
			return new SimpleDateFormat(format, Locale.getDefault()).parse(value);
		}
		catch (ParseException e)
		{
			throw new ValueFormatException(propertyName, format, value);
		}
	}

	public static Date getSystemPropertyAsDate(String propertyName)
	{
		return getSystemPropertyAsDate(propertyName, "yyyyMMdd");
	}

	public static File getSystemPropertyAsFile(String propertyName)
	{
		return new File(getSystemProperty(propertyName));
	}

	public static File getSystemPropertyAsFile(String propertyName, File defaultValue)
	{
		String value=System.getProperty(propertyName);
		return (value == null)
			? defaultValue
			: new File(value);
	}
}
