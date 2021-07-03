package com.samajackun.summer.conf.property;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.junit.Test;

public abstract class AbstractPropertiesProviderTest
{
	protected abstract PropertiesProvider createProvider();

	@Test
	public void getPropertyOneLevels()
	{
		PropertiesProvider provider=createProvider();
		try
		{
			assertEquals("january", provider.getProperty(new DefaultPropertyPath("month")));
		}
		catch (PropertiesPathNotFoundException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void getPropertyTwoLevels()
	{
		PropertiesProvider provider=createProvider();
		try
		{
			assertEquals("venus", provider.getProperty(new DefaultPropertyPath("system.planet")));
		}
		catch (PropertiesPathNotFoundException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void getPropertyNonExisting()
	{
		PropertiesProvider provider=createProvider();
		DefaultPropertyPath path=new DefaultPropertyPath("non-existing");
		try
		{
			provider.getProperty(path);
		}
		catch (PropertiesPathNotFoundException e)
		{
			assertEquals(path, e.getPath());
		}
	}

	@Test
	public void getPropertyInSecondFile()
	{
		PropertiesProvider provider=createProvider();
		try
		{
			assertEquals("Revolver", provider.getProperty(new DefaultPropertyPath("music.album")));
		}
		catch (PropertiesPathNotFoundException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void getProperties()
	{
		PropertiesProvider provider=createProvider();
		try
		{
			PropertyPath path=new DefaultPropertyPath("music");
			Properties properties=provider.getProperties(path);
			assertNotNull(properties);
			assertNotNull(properties.getProperty("album"));
			assertNotNull(properties.getProperty("song"));
		}
		catch (PropertiesPathNotFoundException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void getPropertiesMap()
	{
		PropertiesProvider provider=createProvider();
		try
		{
			PropertyPath path=new DefaultPropertyPath("music");
			Map<PropertyPath, String> map=provider.getPropertiesMap(path);
			Set<PropertyPath> set=map.keySet();
			assertEquals(2, set.size());
			assertTrue(set.contains(new DefaultPropertyPath("music.album")));
			assertTrue(set.contains(new DefaultPropertyPath("music.song")));
		}
		catch (PropertiesPathNotFoundException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void getAllPropertiesMap()
	{
		PropertiesProvider provider=createProvider();
		Map<PropertyPath, String> map=provider.getAllPropertiesMap();
		Set<PropertyPath> set=map.keySet();
		// assertEquals(7, set.size());
		System.out.println("getAllPropertiesMap: map=" + map);
		assertTrue(set.contains(new DefaultPropertyPath("month")));
		assertTrue(set.contains(new DefaultPropertyPath("year")));
		assertTrue(set.contains(new DefaultPropertyPath("birthday")));
		assertTrue(set.contains(new DefaultPropertyPath("system.planet")));
		assertTrue(set.contains(new DefaultPropertyPath("music.album")));
		assertTrue(set.contains(new DefaultPropertyPath("music.song")));
		assertTrue(set.contains(new DefaultPropertyPath("poem")));
	}
}