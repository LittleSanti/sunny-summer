package com.samajackun.summer.conf.property;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceConfigurationError;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;

import com.samajackun.summer.core.Identifier;

public class JndiSource implements Source
{
	private final String jndiPath;

	public JndiSource(String jndiPath)
	{
		super();
		this.jndiPath=jndiPath;
	}

	@Override
	public Map<PropertyPath, String> getAll()
	{
		Map<PropertyPath, String> map=new HashMap<PropertyPath, String>(100);
		try
		{
			Context context=(Context)new InitialContext().lookup(this.jndiPath);
			browse(context, map, new DefaultPropertyPath());
			return map;
		}
		catch (NamingException e)
		{
			throw new ServiceConfigurationError(e.toString(), e);
		}
	}

	private void browse(Context context, Map<PropertyPath, String> map, PropertyPath prefix)
		throws NamingException
	{
		Enumeration<?> keys=context.list("");
		while (keys.hasMoreElements())
		{
			NameClassPair key=(NameClassPair)keys.nextElement();
			map.put(prefix.createBranch(new Identifier(key.getName())), context.lookup(prefix + "/" + key.getName()).toString());
		}
	}
}
