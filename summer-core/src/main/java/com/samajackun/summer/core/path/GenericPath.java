package com.samajackun.summer.core.path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class GenericPath<T extends Comparable<T>> implements Path<T>
{
	private final Path<T> parent;

	private final T tailStep;

	private Path<T> tail;

	private final List<T> steps;

	protected GenericPath()
	{
		this(null, null);
	}

	protected GenericPath(T lastStep)
	{
		this(null, lastStep);
	}

	protected GenericPath(Path<T> parent, T lastStep)
	{
		super();
		this.parent=parent;
		this.tailStep=lastStep;
		this.steps=createSteps();
	}

	@Override
	public boolean isEmpty()
	{
		return this.parent == null && this.tailStep == null;
	}

	private List<T> createSteps()
	{
		List<T> list;
		if (this.parent == null)
		{
			if (this.tailStep == null)
			{
				list=Collections.emptyList();
			}
			else
			{
				list=new ArrayList<T>(1);
				list.add(this.tailStep);
			}
		}
		else
		{
			list=new ArrayList<T>();
			list.addAll(this.parent.getSteps());
			list.add(this.tailStep);
		}
		return list;
	}

	@Override
	public List<T> getSteps()
	{
		return this.steps;
	}

	@Override
	public Path<T> getParent()
	{
		return this.parent;
	}

	@Override
	public T getTailStep()
	{
		return this.tailStep;
	}

	@Override
	public String serialized(char separator)
	{
		return (this.parent == null
			? ""
			: this.parent.serialized(separator)) + separator + (this.tailStep == null
			? ""
			: this.tailStep);
	}

	@Override
	public String serialized()
	{
		return serialized('.');
	}

	@Override
	public String toString()
	{
		return serialized();
	}

	@Override
	public int hashCode()
	{
		final int prime=31;
		int result=1;
		result=prime * result + ((this.tailStep == null)
			? 0
			: this.tailStep.hashCode());
		result=prime * result + ((this.parent == null)
			? 0
			: this.parent.hashCode());
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
		if (!(obj instanceof GenericPath))
		{
			return false;
		}
		@SuppressWarnings("unchecked")
		GenericPath<T> other=(GenericPath<T>)obj;
		if (this.tailStep == null)
		{
			if (other.tailStep != null)
			{
				return false;
			}
		}
		else if (!this.tailStep.equals(other.tailStep))
		{
			return false;
		}
		if (this.parent == null)
		{
			if (other.parent != null)
			{
				return false;
			}
		}
		else if (!this.parent.equals(other.parent))
		{
			return false;
		}
		return true;
	}

	@Override
	public Path<T> createBranch(Path<T> path)
	{
		Path<T> parent=this;
		for (T step : path.getSteps())
		{
			parent=parent.createBranch(step);
		}
		return parent;
	}

	protected Path<T> createFromSingleStep(T step)
	{
		return new GenericPath<T>(null, step);
	}

	@Override
	public Path<T> createBranch(T step)
	{
		return new GenericPath<T>(this, step);
	}

	@Override
	public Path<T> createParentBranch(T step)
	{
		Path<T> newParent=new GenericPath<T>(null, step);
		return newParent.createBranch(this);
	}

	@Override
	public Path<T> getTail()
	{
		// Debo realizar inicialización perezosa,
		// porque si lo inicializara en el constructor,
		// se produciría un embuclamiento recurisvo.
		if (this.tail == null)
		{
			this.tail=createFromSingleStep(getTailStep());
		}
		return this.tail;
	}

	@Override
	public PathSplitter<T> createSplitter()
	{
		return new PathSplitter<T>(this);
	}

	@Override
	public Path<T> getPathRelativeTo(Path<T> base)
	{
		List<T> steps=getSteps();
		List<T> baseSteps=base.getSteps();
		// Comparar base con steps, desde el principio:
		boolean equal=baseSteps.size() <= steps.size();
		for (int i=0; i < baseSteps.size() && equal; i++)
		{
			T step=steps.get(i);
			T baseStep=baseSteps.get(i);
			equal=(step.equals(baseStep));
		}
		return equal
			? createFromSteps(steps.subList(baseSteps.size(), steps.size()))
			: null;
	}

	protected Path<T> createFromSteps(List<T> steps)
	{
		Path<T> path=null;
		for (T step : steps)
		{
			if (path == null)
			{
				path=createFromSingleStep(step);
			}
			else
			{
				path=path.createBranch(step);
			}
		}
		return path;
	}

	public static <T extends Comparable<T>> Path<T> createFromList(List<T> steps)
	{
		Path<T> path=null;
		for (T step : steps)
		{
			if (path == null)
			{
				path=new GenericPath<T>(step);
			}
			else
			{
				path=path.createBranch(step);
			}
		}
		if (path == null)
		{
			path=new GenericPath<T>();
		}
		return path;
	}

	public static <T extends Comparable<T>> Path<T> parse(String serial, char separator, Converter<T> converter)
		throws ConverterException
	{
		Path<T> newPath=null;
		StringTokenizer stk=new StringTokenizer(serial, String.valueOf(separator));
		while (stk.hasMoreTokens())
		{
			T step=converter.parse(stk.nextToken());
			if (newPath == null)
			{
				newPath=new GenericPath<T>(step);
			}
			else
			{
				newPath=newPath.createBranch(step);
			}
		}
		if (newPath == null)
		{
			newPath=new GenericPath<T>();
		}
		return newPath;
	}
}
