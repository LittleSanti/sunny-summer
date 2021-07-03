package com.samajackun.summer.conf.provider.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.junit.Test;

import com.samajackun.summer.conf.provider.AbstractProviderTest;
import com.samajackun.summer.conf.provider.PropertyPath;
import com.samajackun.summer.conf.provider.Provider;
import com.samajackun.summer.conf.provider.ProviderException;
import com.samajackun.summer.conf.provider.properties.PropertiesProvider;

public class PropertiesProviderTest extends AbstractProviderTest
{
	@Override
	protected Provider createProvider()
		throws IOException

	{
		InputStream input=new FileInputStream(new File(this.dir, "music.properties"));
		return new PropertiesProvider(input);
	}

	@Override
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

}
