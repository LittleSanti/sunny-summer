package com.samajackun.summer.conf.property;

import java.util.ServiceConfigurationError;

public class PropertiesProviderManager
{
	private static final PropertiesProviderManager INSTANCE=new PropertiesProviderManager();

	private PropertiesProvider propertiesProvider;

	public static PropertiesProviderManager getInstance()
	{
		return INSTANCE;
	}

	private PropertiesProviderManager()
	{
	}

	public void setPropertiesProvider(PropertiesProvider propertiesProvider)
	{
		if (this.propertiesProvider != null)
		{
			throw new IllegalStateException();
		}
		this.propertiesProvider=propertiesProvider;
	}

	public PropertiesProvider getPropertiesProvider()
	{
		if (this.propertiesProvider == null)
		{
			this.propertiesProvider=createPropertiesProvider();
		}
		return this.propertiesProvider;
	}

	private PropertiesProvider createPropertiesProvider()
	{
		String className=System.getProperty("demo.property.PropertiesProvider");
		if (className == null)
		{
			throw new ServiceConfigurationError("Missing system property 'demo.property.PropertiesProvider'");
		}
		try
		{
			Object o=Class.forName(className).newInstance();
			if (!(o instanceof PropertiesProvider))
			{
				throw new ServiceConfigurationError("Class '" + className + "' must be subclass of " + PropertiesProvider.class.getName());
			}
			return (PropertiesProvider)o;
		}
		catch (InstantiationException e)
		{
			throw new ServiceConfigurationError(e.toString(), e);
		}
		catch (IllegalAccessException e)
		{
			throw new ServiceConfigurationError(e.toString(), e);
		}
		catch (ClassNotFoundException e)
		{
			throw new ServiceConfigurationError(e.toString(), e);
		}
	}
}
