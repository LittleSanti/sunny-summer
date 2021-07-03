package com.samajackun.summer.conf.property;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SystemPropertiesProvider implements PropertiesProvider
{
	@Override
	public String getProperty(PropertyPath PropertiesPath)
		throws PropertiesPathNotFoundException
	{
		String value=System.getProperty(PropertiesPath.serialized());
		if (value == null)
		{
			throw new PropertiesPathNotFoundException(PropertiesPath);
		}
		return value;
	}

	@Override
	public Properties getProperties(PropertyPath path)
		throws PropertiesPathNotFoundException
	{
		String serialPath=path.serialized() + ".";
		Properties properties=new Properties();
		for (String propertyName : System.getProperties().stringPropertyNames())
		{
			if (propertyName.startsWith(serialPath) && propertyName.indexOf('.', serialPath.length()) < 0)
			{
				properties.setProperty(propertyName.substring(serialPath.length()), System.getProperty(propertyName));
			}
		}
		return properties;
	}

	@Override
	public Map<PropertyPath, String> getPropertiesMap(PropertyPath PropertiesPath)
		throws PropertiesPathNotFoundException
	{
		String serialPath=PropertiesPath.serialized() + ".";
		Map<PropertyPath, String> map=new HashMap<PropertyPath, String>((int)(1.7d * System.getProperties().size()));
		for (String propertyName : System.getProperties().stringPropertyNames())
		{
			if (propertyName.startsWith(serialPath) && propertyName.indexOf('.', serialPath.length()) < 0)
			{
				map.put(new DefaultPropertyPath(propertyName), System.getProperty(propertyName));
			}
		}
		return map;
	}

	@Override
	public Map<PropertyPath, String> getAllPropertiesMap()
	{
		Map<PropertyPath, String> map=new HashMap<PropertyPath, String>((int)(1.7d * System.getProperties().size()));
		for (String propertyName : System.getProperties().stringPropertyNames())
		{
			map.put(new DefaultPropertyPath(propertyName), System.getProperty(propertyName));
		}
		return map;
	}
}
