package com.samajackun.summer.conf.property;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public abstract class AbstractPropertiesProvider<T> implements PropertiesProvider
{
	private final List<AbstractPropertiesSource<T>> sources=new ArrayList<AbstractPropertiesSource<T>>();

	protected AbstractPropertiesProvider(AbstractPropertiesSource<T>[] inputSources)
	{
		for (AbstractPropertiesSource<T> source : inputSources)
		{
			this.sources.add(source);
		}
	}

	protected List<AbstractPropertiesSource<T>> getSources()
	{
		return this.sources;
	}

	@Override
	public Properties getProperties(PropertyPath path)
		throws PropertiesPathNotFoundException
	{
		Properties properties=new Properties();
		Map<PropertyPath, String> map=getPropertiesMap(path);
		for (Map.Entry<PropertyPath, String> entry : map.entrySet())
		{
			properties.setProperty(entry.getKey().getSteps().get(entry.getKey().getSteps().size() - 1).toString(), entry.getValue());
		}
		return properties;
	}

}
