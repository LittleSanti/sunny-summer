package com.samajackun.summer.conf.property;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ServiceConfigurationError;

public class PropertiesSource implements Source
{
	private final InputStream input;

	public PropertiesSource(InputStream input)
	{
		this.input=input;
	}

	@Override
	public Map<PropertyPath, String> getAll()
	{
		Map<PropertyPath, String> map=new HashMap<PropertyPath, String>(100);
		try
		{
			Properties properties=new Properties();
			properties.load(this.input);
			for (String name : properties.stringPropertyNames())
			{
				map.put(new DefaultPropertyPath(name), properties.getProperty(name));
			}
			return map;
		}
		catch (IOException e)
		{
			throw new ServiceConfigurationError(e.toString(), e);
		}
	}
}
