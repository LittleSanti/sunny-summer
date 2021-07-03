package com.samajackun.summer.conf.provider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.junit.Test;

import com.samajackun.summer.conf.provider.PropertyPath;
import com.samajackun.summer.conf.provider.Provider;
import com.samajackun.summer.conf.provider.ProviderException;

public abstract class AbstractProviderTest
{
	protected final File dir=new File(getSystemProperty("test.dir"));

	private static String getSystemProperty(String propertyName)
	{
		String value=System.getProperty(propertyName);
		if (value == null)
		{
			throw new IllegalArgumentException("Missing system property '" + propertyName + "'");
		}
		return value;
	}

	@Test
	public void oneLevel()
		throws IOException
	{
		try
		{
			Provider provider=createProvider();
			Map<PropertyPath, Object> map=provider.getAll();
			assertEquals("Paul McCartney", map.get("author"));
			assertEquals("Tug of war", map.get("album"));
		}
		catch (ProviderException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void twoLevels()
		throws IOException
	{
		try
		{
			Provider provider=createProvider();
			Map<PropertyPath, Object> map=provider.getAll();
			assertEquals("Venus", map.get("planet.name"));
			assertEquals("1290", String.valueOf(map.get("planet.mass")));
		}
		catch (ProviderException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void threeLevels()
		throws IOException
	{
		try
		{
			Provider provider=createProvider();
			Map<PropertyPath, Object> map=provider.getAll();
			assertEquals("Spain", map.get("planet.country.name"));
		}
		catch (ProviderException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void iterableItemsAsString()
		throws IOException
	{
		try
		{
			Provider provider=createProvider();
			Map<PropertyPath, Object> map=provider.getAll();
			assertEquals(Arrays.asList(new String[] { "enero", "febrero", "marzo" }), map.get("month"));
		}
		catch (ProviderException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	protected abstract Provider createProvider()
		throws IOException;
}
