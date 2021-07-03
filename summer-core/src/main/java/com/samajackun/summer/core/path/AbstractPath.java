package com.samajackun.summer.core.path;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPath<T extends Comparable<T>> implements Path<T>
{
	private final List<T> steps;

	public AbstractPath()
	{
		this.steps=new ArrayList<T>();
	}

	protected AbstractPath(String serial, char separator)
	{
		List<String> splits=StringSplitter.split(serial, separator);
		int n=splits.size();
		if (n == 0)
		{
			throw new IllegalArgumentException("Empty path not allowed");
		}
		this.steps=new ArrayList<T>(n);
		for (int i=0; i < n; i++)
		{
			String split=splits.get(i);
			if (split.length() == 0)
			{
				throw new IllegalArgumentException("Empty path step not allowed");
			}
			this.steps.add(parseStep(split));
		}
	}

	protected abstract T parseStep(String step);

	protected AbstractPath(List<T> steps)
	{
		this.steps=new ArrayList<T>(steps.size());
		for (T step : steps)
		{
			this.steps.add(step);
		}
	}

	@Override
	public boolean isEmpty()
	{
		return this.steps.isEmpty();
	}

	public void addStep(T step)
	{
		this.steps.add(step);
	}

	@Override
	public List<T> getSteps()
	{
		return this.steps;
	}

	@Override
	public Path<T> getParent()
	{
		// FIXME
		throw new UnsupportedOperationException();
	}

	@Override
	public Path<T> createBranch(T step)
	{
		AbstractPath<T> newPath=createClone();
		newPath.addStep(step);
		return newPath;
	}

	protected abstract AbstractPath<T> createClone();

	@Override
	public Path<T> createBranch(Path<T> path)
	{
		AbstractPath<T> newPath=createClone();
		for (T step : path.getSteps())
		{
			newPath.addStep(step);
		}
		return newPath;
	}

	public String serialized(char separator)
	{
		StringBuilder stb=new StringBuilder(20 * this.steps.size());
		for (T step : this.steps)
		{
			if (stb.length() > 0)
			{
				stb.append(separator);
			}
			stb.append(step.toString());
		}
		return stb.toString();
	}

	@Override
	public String serialized()
	{
		return serialized('.');
	}

	@Override
	public int hashCode()
	{
		final int prime=31;
		int result=1;
		result=prime * result + ((this.steps == null)
						? 0
							: this.steps.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof AbstractPath))
		{
			return false;
		}
		@SuppressWarnings("unchecked")
		AbstractPath<T> other=(AbstractPath<T>)obj;
		if (this.steps == null)
		{
			if (other.steps != null)
			{
				return false;
			}
		}
		else if (!this.steps.equals(other.steps))
		{
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		return serialized();
	}

	@Override
	public PathSplitter<T> createSplitter()
	{
		return new PathSplitter<T>(this);
	}
}
