package com.samajackun.summer.conf.property;

import java.io.File;

public class AbstractPropertiesSource<T> implements FileContainer
{
	private final File file;

	private final T properties;

	protected AbstractPropertiesSource(File file, T properties)
	{
		super();
		this.file=file;
		this.properties=properties;
	}

	@Override
	public File getFile()
	{
		return this.file;
	}

	public T getProperties()
	{
		return this.properties;
	}
}
