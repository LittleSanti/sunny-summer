package com.samajackun.summer.conf.property;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

public class LocalFilePropertiesProvider extends FilePropertiesProvider
{
	public LocalFilePropertiesProvider() throws IOException
	{
		super(createSources());
	}

	private static FilePropertiesSource[] createSources()
		throws IOException
	{
		String paths=System.getProperty("com.samajackun.summer.conf.property.LocalFilePropertiesProvider.files");
		StringTokenizer stk=new StringTokenizer(paths, ";");
		int n=stk.countTokens();
		List<FilePropertiesSource> sources=new ArrayList<FilePropertiesSource>(n);
		while (stk.hasMoreTokens())
		{
			File file=new File(stk.nextToken());
			Properties properties=new Properties();
			properties.load(new FileInputStream(file));
			sources.add(new FilePropertiesSource(file, properties));
		}
		return sources.toArray(new FilePropertiesSource[sources.size()]);
	}
}
