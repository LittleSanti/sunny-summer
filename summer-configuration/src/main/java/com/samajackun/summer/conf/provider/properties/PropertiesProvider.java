package com.samajackun.summer.conf.provider.properties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceConfigurationError;

import com.samajackun.summer.conf.provider.DefaultPropertyPath;
import com.samajackun.summer.conf.provider.PropertyPath;
import com.samajackun.summer.conf.provider.Provider;
import com.samajackun.summer.conf.provider.ProviderException;
import com.samajackun.summer.conf.provider.store.Token;
import com.samajackun.summer.conf.provider.store.TreePath;
import com.samajackun.summer.core.path.IdentifierFormatException;

public class PropertiesProvider implements Provider
{
	private final InputStream input;

	public PropertiesProvider(InputStream input)
	{
		this.input=input;
	}

	@Override
	public Map<PropertyPath, Object> getAll()
		throws ProviderException
	{
		Map<PropertyPath, Object> map=new HashMap<PropertyPath, Object>(100);
		try
		{
			BufferedReader reader=new BufferedReader(new InputStreamReader(this.input));
			String line;
			do
			{
				line=reader.readLine();
				if (line.trim().startsWith("#"))
				{
					// Es un comentario.
				}
				else
				{
					line=UnicodeUtils.decode(line);
					processLine(line, map);
				}
			}
			while (line != null);
			// for (String name : properties.stringPropertyNames())
			// {
			// map.put(name, properties.getProperty(name));
			// }
			return map;
		}
		catch (IOException e)
		{
			throw new ServiceConfigurationError(e.toString(), e);
		}
	}

	@SuppressWarnings("unchecked")
	private void processLine(String line, Map<PropertyPath, Object> map)
		throws IdentifierFormatException,
		EntryAlreadySetException
	{
		EntryLine entry=new EntryLine(line);
		Object value=entry.getValue();
		PropertyPath prefix=new DefaultPropertyPath();
		TreePath path=entry.getPath();
		while (path.hasNext())
		{
			Token token=path.next();
			PropertyPath key=prefix.createBranch(new DefaultPropertyPath(token.getName()));
			Object oldValue=map.get(key);
			if (oldValue == null)
			{
				// Entrada virgen:
				map.put(key, value);
			}
			else
			{
				// Entrada ya escrita:
				List<Object> list;
				if (oldValue instanceof List<?>)
				{
					// Ya había una lista de elementos:
					list=(List<Object>)oldValue;
				}
				else
				{
					// Había sólo un elemento:
					// Hay que construir y almacenar una lista.
					list=new ArrayList<Object>();
					list.add(oldValue);
					map.put(key, list);
				}
				// Cómo almacenar el nuevo elemento:
				if (!path.hasNext())
				{
					if (token.getIndex() < 0)
					{
						System.out.println("key=" + key + ", oldValue=" + oldValue + ", value=" + value);
						throw new EntryAlreadySetException(key, oldValue);
					}
					else if (token.getIndex() == 0)
					{
						list.add(value);
					}
					else
					{
						if (list.get(token.getIndex()) != null)
						{
							throw new EntryAlreadySetException(key, token.getIndex());
						}
						list.set(token.getIndex(), value);
					}
				}
			}
			// prefix=key + ".";
		}
	}
}
