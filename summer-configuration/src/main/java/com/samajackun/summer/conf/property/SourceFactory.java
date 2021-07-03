package com.samajackun.summer.conf.property;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ServiceConfigurationError;

import org.xml.sax.InputSource;

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
			source=new SystemPropertiesSource();
		}
		else if (serial.startsWith("properties:"))
		{
			File file=new File(serial.substring("properties:".length()));
			InputStream input=new FileInputStream(file);
			try
			{
				source=new PropertiesSource(input);
			}
			finally
			{
				input.close();
			}
		}
		else if (serial.startsWith("xml:"))
		{
			File file=new File(serial.substring("xml:".length()));
			InputStream input=new FileInputStream(file);
			try
			{
				source=new XmlSource(new InputSource(input));
			}
			finally
			{
				input.close();
			}
		}
		else if (serial.startsWith("jndi:"))
		{
			String jndi=serial.substring("jndi:".length());
			source=new JndiSource(jndi);
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
			throw new IllegalArgumentException("Not a valid properties source: Only allowed 'properties', 'xml', 'jndi', 'class' and 'system'.");
		}
		return source;
	}

}
