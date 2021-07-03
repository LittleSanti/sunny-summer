package com.samajackun.summer.conf.provider.properties;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.samajackun.summer.conf.provider.DefaultPropertyPath;
import com.samajackun.summer.conf.provider.PropertyPath;
import com.samajackun.summer.conf.provider.Provider;

public class SystemPropertiesProvider implements Provider
{
	@Override
	public Map<PropertyPath, Object> getAll()
	{
		Map<PropertyPath, Object> map=new HashMap<PropertyPath, Object>(100);
		Properties properties=System.getProperties();
		for (String name : properties.stringPropertyNames())
		{
			map.put(new DefaultPropertyPath(name), properties.getProperty(name));
		}
		return map;
	}
}
