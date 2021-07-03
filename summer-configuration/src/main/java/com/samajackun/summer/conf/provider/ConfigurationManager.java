package com.samajackun.summer.conf.provider;

import java.util.Map;
import java.util.TreeMap;

public class ConfigurationManager
{
	private final Map<PropertyPath, Object> configuration=new TreeMap<PropertyPath, Object>();

	public ConfigurationManager(Source... sources) throws ProviderException
	{
		for (Source source : sources)
		{
			this.configuration.putAll(source.getProvider().getAll());
		}
	}

	public Object getProperty(String key)
	{
		return this.configuration.get(key);
	}

	public Map<PropertyPath, Object> getProperties()
	{
		return this.configuration;
	}
}
