package com.samajackun.summer.conf.utils;

public class PropertyUtils
{
	public static <T> T property(String name)
	{
		String x=System.getProperty(name);
		return (T)x;
	};
}
