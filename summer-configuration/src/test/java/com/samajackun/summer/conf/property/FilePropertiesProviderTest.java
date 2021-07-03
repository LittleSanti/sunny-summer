package com.samajackun.summer.conf.property;

import java.io.File;
import java.util.Properties;

public class FilePropertiesProviderTest extends AbstractPropertiesProviderTest
{
	protected PropertiesProvider createProvider()
	{
		Properties properties1=new Properties();
		properties1.setProperty("month", "january");
		properties1.setProperty("year", "2000");
		properties1.setProperty("birthday", "2000-02-01");
		properties1.setProperty("system.planet", "venus");
		FilePropertiesSource source1=new FilePropertiesSource(new File("file1"), properties1);

		Properties properties2=new Properties();
		properties2.setProperty("music.album", "Revolver");
		properties2.setProperty("music.song", "Eleanor Rigby");
		properties2.setProperty("poem", "The Raven");
		FilePropertiesSource source2=new FilePropertiesSource(new File("file2"), properties2);

		FilePropertiesProvider provider=new FilePropertiesProvider(source1, source2);
		return provider;
	}
}
