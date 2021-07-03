package com.samajackun.summer.conf.provider.jndi;

import java.io.IOException;

import com.samajackun.summer.conf.provider.AbstractProviderTest;
import com.samajackun.summer.conf.provider.DefaultPropertyPath;
import com.samajackun.summer.conf.provider.PropertyPath;
import com.samajackun.summer.conf.provider.Provider;
import com.samajackun.summer.conf.provider.jndi.JndiProvider;

public class JndiProviderTest extends AbstractProviderTest
{
	@Override
	protected Provider createProvider()
		throws IOException
	{
		PropertyPath jndi=new DefaultPropertyPath();
		return new JndiProvider(jndi);
	}
}
