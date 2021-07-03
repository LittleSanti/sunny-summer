package com.samajackun.summer.conf.property;

import java.util.ServiceConfigurationError;

public class ConfigurationManager
{
	private static final ConfigurationManager INSTANCE=new ConfigurationManager();

	private final PropertiesProvider propertiesProvider=createPropertiesProvider();

	public static ConfigurationManager getInstance()
	{
		return INSTANCE;
	}

	private PropertiesProvider createPropertiesProvider()
	{
		PropertiesProvider propertiesProvider;
		String className=System.getProperty("com.samajackun.summer.conf.property.ConfigurationManager.propertiesProvider");
		if (className == null)
		{
			propertiesProvider=new SystemPropertiesProvider();
		}
		else
		{
			try
			{
				Class<?> cl=Class.forName(className);
				Object o=cl.newInstance();
				if (!(o instanceof PropertiesProvider))
				{
					throw new ServiceConfigurationError("Class " + className + " must be subclass of " + PropertiesProvider.class.getName());
				}
				propertiesProvider=(PropertiesProvider)o;
			}
			catch (ClassNotFoundException e)
			{
				throw new ServiceConfigurationError(e.toString(), e);
			}
			catch (InstantiationException e)
			{
				throw new ServiceConfigurationError(e.toString(), e);
			}
			catch (IllegalAccessException e)
			{
				throw new ServiceConfigurationError(e.toString(), e);
			}
		}
		return propertiesProvider;
	}

	private ConfigurationManager()
	{
	}

	public PropertiesProvider getPropertiesProvider()
	{
		return this.propertiesProvider;
	}

}
