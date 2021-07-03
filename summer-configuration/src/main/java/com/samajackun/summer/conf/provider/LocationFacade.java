package com.samajackun.summer.conf.provider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class LocationFacade
{
	private final String src;

	private final File file;

	private final URL url;

	public LocationFacade(String src)
	{
		this.src=src;
		URL url;
		File file;
		try
		{
			url=new URL(src);
			file=null;
		}
		catch (MalformedURLException e)
		{
			url=null;
			file=new File(src);
		}
		this.url=url;
		this.file=file;
	}

	public String getSrc()
	{
		return this.src;
	}

	public boolean isFile()
	{
		return this.file != null;
	}

	public boolean isUrl()
	{
		return this.url != null;
	}

	public File getFile()
	{
		return this.file;
	}

	public URL getUrl()
	{
		return this.url;
	}

	public InputStream openStream()
		throws IOException
	{
		InputStream input;
		if (this.file != null)
		{
			input=new FileInputStream(this.file);
		}
		else if (this.url != null)
		{
			input=this.url.openStream();
		}
		else
		{
			throw new IllegalArgumentException();
		}
		return input;
	}
}
