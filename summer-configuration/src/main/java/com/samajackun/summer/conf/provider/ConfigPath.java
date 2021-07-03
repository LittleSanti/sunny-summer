package com.samajackun.summer.conf.provider;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.samajackun.summer.conf.provider.properties.WrongFormatException;
import com.samajackun.summer.conf.provider.properties.WrongIndexException;
import com.samajackun.summer.core.path.AbstractPath;
import com.samajackun.summer.core.path.IndexedIdentifier;
import com.samajackun.summer.core.path.Path;

public class ConfigPath extends AbstractPath<IndexedIdentifier> implements Path<IndexedIdentifier>
{
	public ConfigPath(String name)
	{
		super(name, '.');
	}

	public ConfigPath(List<IndexedIdentifier> steps)
	{
		super(steps);
	}

	public ConfigPath()
	{
		super();
	}

	@Override
	protected IndexedIdentifier parseStep(String step)
	{
		Pattern pattern=Pattern.compile("([a-z0-9_\\-]+)(\\[.*\\])?", Pattern.CASE_INSENSITIVE);
		Matcher matcher=pattern.matcher(step);
		if (matcher.find())
		{
			if (matcher.end() != step.length())
			{
				throw new WrongFormatException(step);
			}
			String name=matcher.group(1);
			String index=matcher.group(2);
			int intIndex;
			if (index == null)
			{
				intIndex=-1;
			}
			else
			{
				if (index.length() >= 2)
				{
					index=index.substring(1, index.length() - 1);
				}
				if (index.isEmpty())
				{
					intIndex=0;
				}
				else
				{
					try
					{
						intIndex=Integer.parseInt(index);
					}
					catch (NumberFormatException e)
					{
						throw new WrongIndexException(index);
					}
				}
			}
			return new IndexedIdentifier(name, intIndex);
		}
		else
		{
			throw new WrongFormatException(step);
		}
	}

	@Override
	protected ConfigPath createClone()
	{
		return new ConfigPath(getSteps());
	}

	@Override
	public ConfigPath createBranch(IndexedIdentifier step)
	{
		ConfigPath newPath=createClone();
		newPath.addStep(step);
		return newPath;
	}

	@Override
	public ConfigPath createBranch(Path<IndexedIdentifier> path)
	{
		ConfigPath newPath=createClone();
		for (IndexedIdentifier step : path.getSteps())
		{
			newPath.addStep(step);
		}
		return newPath;
	}

	@Override
	public String toString()
	{
		return serialized();
	}

	@Override
	public boolean isEmpty()
	{
		return this.getSteps().isEmpty();
	}
}
