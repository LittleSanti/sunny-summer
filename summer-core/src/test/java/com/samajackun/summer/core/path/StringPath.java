package com.samajackun.summer.core.path;

import java.util.StringTokenizer;

class StringPath extends AbstractPath2<String>
{
	public StringPath(Path<String> parent, String lastStep)
	{
		super(parent, lastStep);
	}

	public StringPath(String lastStep)
	{
		super(lastStep);
	}

	public StringPath()
	{
		super();
	}

	public static Path<String> parse(String serial, char separator)
	{
		Path<String> stringPath=null;
		StringTokenizer stk=new StringTokenizer(serial, String.valueOf(separator));
		while (stk.hasMoreTokens())
		{
			String step=stk.nextToken();
			if (stringPath == null)
			{
				stringPath=new StringPath(step);
			}
			else
			{
				stringPath=stringPath.createBranch(step);
			}
		}
		if (stringPath == null)
		{
			stringPath=new StringPath();
		}
		return stringPath;
	}

	@Override
	public Path<String> createBranch(String step)
	{
		return new StringPath(this, step);
	}

	@Override
	public Path<String> createParentBranch(String step)
	{
		StringPath newParent=new StringPath(null, step);
		return newParent.createBranch(this);
	}

	@Override
	protected Path<String> createFromSingleStep(String step)
	{
		return new StringPath(null, step);
	}
}