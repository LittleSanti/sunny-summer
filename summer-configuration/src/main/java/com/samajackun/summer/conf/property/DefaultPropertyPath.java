package com.samajackun.summer.conf.property;

import java.util.List;

import com.samajackun.summer.core.AbstractPath;
import com.samajackun.summer.core.Identifier;
import com.samajackun.summer.core.Path;

public class DefaultPropertyPath extends AbstractPath<Identifier> implements PropertyPath
{
	public DefaultPropertyPath(String name)
	{
		super(name, '.');
	}

	public DefaultPropertyPath(List<Identifier> steps)
	{
		super(steps);
	}

	public DefaultPropertyPath()
	{
		super();
	}

	@Override
	protected Identifier parseStep(String step)
	{
		return new Identifier(step);
	}

	@Override
	protected DefaultPropertyPath createClone()
	{
		return new DefaultPropertyPath(getSteps());
	}

	@Override
	public PropertyPath createBranch(Identifier step)
	{
		DefaultPropertyPath newPath=createClone();
		newPath.addStep(step);
		return newPath;
	}

	@Override
	public PropertyPath createBranch(Path<Identifier> path)
	{
		DefaultPropertyPath newPath=createClone();
		for (Identifier step : path.getSteps())
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
}
