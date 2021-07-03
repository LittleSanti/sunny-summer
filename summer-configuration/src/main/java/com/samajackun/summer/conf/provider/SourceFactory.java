package com.samajackun.summer.conf.provider;

import java.io.IOException;
import java.io.InputStream;
import java.util.ServiceConfigurationError;

import org.xml.sax.InputSource;

import com.samajackun.summer.conf.provider.jndi.JndiProvider;
import com.samajackun.summer.conf.provider.properties.PropertiesProvider;
import com.samajackun.summer.conf.provider.properties.SystemPropertiesProvider;
import com.samajackun.summer.conf.provider.xml.XmlProvider;

public class SourceFactory
{
	private static final SourceFactory INSTANCE=new SourceFactory();

	public static SourceFactory getInstance()
	{
		return INSTANCE;
	}

	private SourceFactory()
	{
	}

	public Source getSource(String serial)
		throws IOException
	{
		Source source;
		if (serial.startsWith("system:"))
		{
			source=new Source(new SystemPropertiesProvider());
		}
		else if (serial.startsWith("properties:"))
		{
			String path=serial.substring("properties:".length());
			LocationFacade locationFacade=new LocationFacade(path);
			InputStream input=locationFacade.openStream();
			try
			{
				source=new Source(new PropertiesProvider(input), locationFacade);
			}
			finally
			{
				input.close();
			}
		}
		else if (serial.startsWith("xml:"))
		{
			String path=serial.substring("xml:".length());
			LocationFacade locationFacade=new LocationFacade(path);
			InputStream input=locationFacade.openStream();
			try
			{
				source=new Source(new XmlProvider(new InputSource(input)), locationFacade);
			}
			finally
			{
				input.close();
			}
		}
		else if (serial.startsWith("jndi:"))
		{
			ConfigPath jndi=new ConfigPath(serial.substring("jndi:".length()));
			source=new Source(new JndiProvider(jndi));
		}
		else if (serial.startsWith("class:"))
		{
			String className=serial.substring("class:".length());
			try
			{
				source=(Source)Class.forName(className).newInstance();
			}
			catch (InstantiationException | IllegalAccessException | ClassNotFoundException e)
			{
				throw new ServiceConfigurationError(e.toString(), e);
			}
		}
		else
		{
			throw new IllegalArgumentException("Not a valid properties provider: Only allowed: 'properties:', 'xml:', 'jndi:', 'class:' and 'system:'.");
		}
		return source;
	}
}
