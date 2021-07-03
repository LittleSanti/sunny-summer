package com.samajackun.summer.conf.property;

import java.util.Map;
import java.util.Properties;

public interface PropertiesProvider
{
	public String getProperty(PropertyPath path)
		throws PropertiesPathNotFoundException;

	public Properties getProperties(PropertyPath path)
		throws PropertiesPathNotFoundException;

	public Map<PropertyPath, String> getPropertiesMap(PropertyPath path)
		throws PropertiesPathNotFoundException;

	public Map<PropertyPath, String> getAllPropertiesMap();
}
