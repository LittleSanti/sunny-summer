package com.samajackun.summer.conf.property;


public class SystemPropertiesProviderTest extends AbstractPropertiesProviderTest
{
	@Override
	protected PropertiesProvider createProvider()
	{
		System.setProperty("month", "january");
		System.setProperty("year", "2000");
		System.setProperty("birthday", "2000-02-01");
		System.setProperty("system.planet", "venus");

		System.setProperty("music.album", "Revolver");
		System.setProperty("music.song", "Eleanor Rigby");
		System.setProperty("poem", "The Raven");

		SystemPropertiesProvider provider=new SystemPropertiesProvider();
		return provider;
	}
}
