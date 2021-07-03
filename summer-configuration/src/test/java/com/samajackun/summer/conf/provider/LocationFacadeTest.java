package com.samajackun.summer.conf.provider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.samajackun.summer.conf.provider.LocationFacade;

public class LocationFacadeTest
{
	private final File dir=new File(getSystemProperty("test.dir"));

	private static String getSystemProperty(String propertyName)
	{
		String value=System.getProperty(propertyName);
		if (value == null)
		{
			throw new IllegalArgumentException("Missing system property '" + propertyName + "'");
		}
		return value;
	}

	/**
	 * Reads fully a inputStream.
	 * 
	 * @param input InputStream.
	 * @return Content as String.
	 * @exception java.io.IOException If an error occured while reading.
	 */
	private static java.lang.String readInputStream(java.io.InputStream input)
		throws java.io.IOException
	{
		byte[] buffer=new byte[4096];
		java.lang.String str="";
		int n;
		do
		{
			n=input.read(buffer);
			if (n > 0)
			{
				str+=new java.lang.String(buffer, 0, n);
			}
		}
		while (n >= 0);
		return str;
	}

	@Test
	public void file()
		throws IOException
	{
		String src=new File(this.dir, "meses.txt").getAbsolutePath();
		LocationFacade locationFacade=new LocationFacade(src);
		assertTrue(locationFacade.isFile());
		assertFalse(locationFacade.isUrl());
		assertEquals(src, locationFacade.getFile().getAbsolutePath());
		assertNull(locationFacade.getUrl());
		assertEquals("enero", readInputStream(locationFacade.openStream()));
	}

	@Test
	public void url()
		throws IOException
	{
		String src=new File(this.dir, "semana.txt").toURI().toURL().toString();
		LocationFacade locationFacade=new LocationFacade(src);
		assertFalse(locationFacade.isFile());
		assertTrue(locationFacade.isUrl());
		assertNull(locationFacade.getFile());
		assertEquals(src, locationFacade.getUrl().toString());
		assertEquals("lunes", readInputStream(locationFacade.openStream()));
	}
}
