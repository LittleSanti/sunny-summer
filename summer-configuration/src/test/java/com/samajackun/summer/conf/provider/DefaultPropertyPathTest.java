package com.samajackun.summer.conf.provider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.samajackun.summer.conf.provider.DefaultPropertyPath;
import com.samajackun.summer.conf.provider.PropertyPath;
import com.samajackun.summer.core.path.Identifier;

public class DefaultPropertyPathTest
{

	@Test
	public void empty()
	{
		try
		{
			new DefaultPropertyPath("");
			fail("Expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e)
		{
			// OK.
		}
	}

	@Test
	public void twoEmptySteps()
	{
		try
		{
			new DefaultPropertyPath(".");
			fail("Expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e)
		{
			// OK.
		}
	}

	@Test
	public void blankStep()
	{
		try
		{
			new DefaultPropertyPath(".febrero");
			fail("Expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e)
		{
			// OK.
		}
	}

	@Test
	public void stepWithBlanks()
	{
		try
		{
			new DefaultPropertyPath("enero ");
			fail("Expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e)
		{
			// OK.
		}
	}

	@Test
	public void oneSteps()
	{
		PropertyPath path=new DefaultPropertyPath("enero");
		List<Identifier> steps=path.getSteps();
		assertEquals(1, steps.size());
		assertEquals("enero", steps.get(0).toString());
	}

	@Test
	public void twoSteps()
	{
		PropertyPath path=new DefaultPropertyPath("enero.febrero");
		List<Identifier> steps=path.getSteps();
		assertEquals(2, steps.size());
		assertEquals("enero", steps.get(0).toString());
		assertEquals("febrero", steps.get(1).toString());
	}
}
