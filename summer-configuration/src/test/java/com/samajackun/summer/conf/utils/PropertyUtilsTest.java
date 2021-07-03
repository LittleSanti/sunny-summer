package com.samajackun.summer.conf.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class PropertyUtilsTest
{
	private static final String myMonth=PropertyUtils.property("test.month");

	@Test
	public void stringValue()
	{
		assertEquals("enero", myMonth);
	}

	// private static final int myDay=PropertyUtils.property("test.day");
	//
	// @Test
	// public void intValue()
	// {
	// assertEquals(20, myDay);
	// }
}
