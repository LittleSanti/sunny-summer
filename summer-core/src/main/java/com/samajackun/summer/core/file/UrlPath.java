package com.samajackun.summer.core.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.samajackun.summer.core.path.GenericPath;
import com.samajackun.summer.core.path.Path;
import com.samajackun.summer.core.path.StreamSource;

public class UrlPath extends GenericPath<UrlStep> implements StreamSource
{
	private final URL url;

	public UrlPath()
	{
		super();
		this.url=initUrl();
	}

	public UrlPath(Path<UrlStep> parent, UrlStep lastStep)
	{
		super(parent, lastStep);
		this.url=initUrl();
	}

	public UrlPath(UrlStep lastStep)
	{
		super(lastStep);
		this.url=initUrl();
	}

	private URL initUrl()
	{
		try
		{
			return new URL(serialized());
		}
		catch (MalformedURLException e)
		{
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public InputStream openInputStream()
					throws IOException
	{
		return new URL(serialized()).openStream();
	}

	@Override
	public OutputStream openOutputStream()
					throws IOException
	{
		URLConnection connection=this.url.openConnection();
		return connection.getOutputStream();
	}
}
