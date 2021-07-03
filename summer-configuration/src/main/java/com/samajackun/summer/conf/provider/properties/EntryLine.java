package com.samajackun.summer.conf.provider.properties;

import com.samajackun.summer.conf.provider.store.TreePath;
import com.samajackun.summer.core.path.IdentifierFormatException;

class EntryLine
{
	private final TreePath path;

	private final String value;

	public EntryLine(String line) throws IdentifierFormatException
	{
		int p=line.indexOf('=');
		if (p < 0)
		{
			throw new MissingValueException(line);
		}
		this.path=new TreePath(line.substring(0, p).trim());
		this.value=line.substring(1 + p);
	}

	public TreePath getPath()
	{
		return this.path;
	}

	public String getValue()
	{
		return this.value;
	}
}
