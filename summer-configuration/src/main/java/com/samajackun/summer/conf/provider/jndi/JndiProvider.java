package com.samajackun.summer.conf.provider.jndi;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceConfigurationError;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;

import com.samajackun.summer.conf.provider.DefaultPropertyPath;
import com.samajackun.summer.conf.provider.PropertyPath;
import com.samajackun.summer.conf.provider.Provider;

public class JndiProvider implements Provider
{
	private final PropertyPath jndiPath;

	public JndiProvider(PropertyPath jndiPath)
	{
		super();
		this.jndiPath=jndiPath;
	}

	@Override
	public Map<PropertyPath, Object> getAll()
	{
		Map<PropertyPath, Object> map=new HashMap<PropertyPath, Object>(100);
		try
		{
			Context context=(Context)new InitialContext().lookup(this.jndiPath.serialized());
			browse(context, map, this.jndiPath);
			return map;
		}
		catch (NamingException e)
		{
			throw new ServiceConfigurationError(e.toString(), e);
		}
	}

	private void browse(Context context, Map<PropertyPath, Object> map, PropertyPath prefix)
		throws NamingException
	{
		Enumeration<NameClassPair> keys=context.list("");
		while (keys.hasMoreElements())
		{
			NameClassPair key=keys.nextElement();
			Object value=context.lookup(key.getName());
			PropertyPath childPath=prefix.createBranch(new DefaultPropertyPath(key.getName()));
			if (value instanceof Context)
			{
				browse((Context)value, map, childPath);
			}
			else
			{
				map.put(childPath, value);
			}
		}
	}
}
