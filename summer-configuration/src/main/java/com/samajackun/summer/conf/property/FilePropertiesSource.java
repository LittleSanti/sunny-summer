package com.samajackun.summer.conf.property;

import java.io.File;
import java.util.Properties;

public class FilePropertiesSource extends AbstractPropertiesSource<Properties>
{
	public FilePropertiesSource(File file, Properties properties)
	{
		super(file, properties);
	}
}
