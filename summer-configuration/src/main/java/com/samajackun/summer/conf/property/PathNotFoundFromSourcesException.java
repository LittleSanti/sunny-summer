package com.samajackun.summer.conf.property;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PathNotFoundFromSourcesException extends PropertiesPathNotFoundException
{
	private static final long serialVersionUID=3996439492419263364L;

	private final List<XmlPropertiesSource> sources;

	public PathNotFoundFromSourcesException(PropertyPath path, List<XmlPropertiesSource> sources)
	{
		super(path, "Property not found with path=" + path.serialized() + " within source files=" + toFiles(sources));
		this.sources=sources;
	}

	private static List<File> toFiles(List<XmlPropertiesSource> sources)
	{
		List<File> files=new ArrayList<File>(sources.size());
		for (XmlPropertiesSource source : sources)
		{
			if (source.getFile() != null)
			{
				files.add(source.getFile());
			}
		}
		return files;
	}

	public List<XmlPropertiesSource> getSources()
	{
		return this.sources;
	}

}
