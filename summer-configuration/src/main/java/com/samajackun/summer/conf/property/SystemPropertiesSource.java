package com.samajackun.summer.conf.property;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SystemPropertiesSource implements Source
{
	@Override
	public Map<PropertyPath, String> getAll()
	{
		Map<PropertyPath, String> map=new HashMap<PropertyPath, String>(100);
		Properties properties=System.getProperties();
		for (String name : properties.stringPropertyNames())
		{
			map.put(new DefaultPropertyPath(name), properties.getProperty(name));
		}
		return map;
	}
}
