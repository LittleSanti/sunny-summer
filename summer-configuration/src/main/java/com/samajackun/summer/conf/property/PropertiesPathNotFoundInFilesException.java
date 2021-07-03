package com.samajackun.summer.conf.property;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PropertiesPathNotFoundInFilesException extends PropertiesPathNotFoundException
{
	private static final long serialVersionUID=3996439492419263364L;

	private final List<? extends FileContainer> sources;

	public PropertiesPathNotFoundInFilesException(PropertyPath path, List<? extends FileContainer> sources)
	{
		super(path, "Property not found with path=" + path.serialized() + " within source files=" + toFiles(sources));
		this.sources=sources;
	}

	private static List<File> toFiles(List<? extends FileContainer> sources)
	{
		List<File> files=new ArrayList<File>(sources.size());
		for (FileContainer source : sources)
		{
			if (source.getFile() != null)
			{
				files.add(source.getFile());
			}
		}
		return files;
	}

	public List<? extends FileContainer> getSources()
	{
		return this.sources;
	}

}
