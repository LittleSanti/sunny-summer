package com.samajackun.summer.conf.property;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.samajackun.summer.core.Identifier;

public class FilePropertiesProvider extends AbstractPropertiesProvider<Properties>
{
	public FilePropertiesProvider(FilePropertiesSource... sources)
	{
		super(sources);
	}

	@Override
	public String getProperty(PropertyPath path)
		throws PropertiesPathNotFoundException
	{
		String value=null;
		for (AbstractPropertiesSource<Properties> source : getSources())
		{
			value=source.getProperties().getProperty(path.serialized());
			if (value != null)
			{
				break;
			}
		}
		if (value == null)
		{
			throw new PropertiesPathNotFoundInFilesException(path, getSources());
		}
		return value;
	}

	// private Map<PropertyPath, String> toMap(PropertyPath base, NodeList list)
	// {
	// Map<PropertyPath, String> map=new HashMap<PropertyPath, String>();
	// int n=list.getLength();
	// for (int i=0; i < n; i++)
	// {
	// Element element=(Element)list.item(i);
	// map.put(base.createBranch(new Identifier(element.getNodeName())), element.getTextContent());
	// }
	// return map;
	// }

	@Override
	public Map<PropertyPath, String> getPropertiesMap(PropertyPath path)
		throws PropertiesPathNotFoundException
	{
		Map<PropertyPath, String> map=new HashMap<PropertyPath, String>();
		String key=path.serialized();
		String prefix=key + ".";
		for (AbstractPropertiesSource<Properties> source : getSources())
		{
			Properties p=source.getProperties();
			for (String propertyName : p.stringPropertyNames())
			{
				if (key.equals(propertyName) || propertyName.startsWith(prefix))
				{
					map.put(path.createBranch(new Identifier(propertyName.substring(1 + key.length()))), p.getProperty(propertyName));
				}
			}
		}
		if (map.isEmpty())
		{
			throw new PropertiesPathNotFoundInFilesException(path, getSources());
		}
		return map;
	}

	@Override
	public Map<PropertyPath, String> getAllPropertiesMap()
	{
		PropertyPath path=new DefaultPropertyPath();
		Map<PropertyPath, String> map=new HashMap<PropertyPath, String>();
		for (AbstractPropertiesSource<Properties> source : getSources())
		{
			Properties p=source.getProperties();
			for (String propertyName : p.stringPropertyNames())
			{
				map.put(path.createBranch(new DefaultPropertyPath(propertyName)), p.getProperty(propertyName));
			}
		}
		return map;
	}
}
