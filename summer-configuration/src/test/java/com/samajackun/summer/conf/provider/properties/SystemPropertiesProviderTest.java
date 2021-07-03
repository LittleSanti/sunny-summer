package com.samajackun.summer.conf.provider.properties;

import java.io.IOException;

import com.samajackun.summer.conf.provider.AbstractProviderTest;
import com.samajackun.summer.conf.provider.Provider;
import com.samajackun.summer.conf.provider.properties.SystemPropertiesProvider;

public class SystemPropertiesProviderTest extends AbstractProviderTest
{
	@Override
	protected Provider createProvider()
		throws IOException

	{
		System.setProperty("author", "Paul McCartney");
		System.setProperty("album", "Tug of war");
		System.setProperty("planet.name", "Venus");
		System.setProperty("planet.mass", "1290");
		System.setProperty("planet.country.name", "Spain");
		return new SystemPropertiesProvider();
	}
}
