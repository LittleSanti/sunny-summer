package com.samajackun.summer.core.path;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class PathIdentifier extends Identifier implements Path<LevelIdentifier>
{
	public static final char SEPARATOR='/';

	private final Path<LevelIdentifier> parent;

	private final LevelIdentifier currentStep;

	private final List<LevelIdentifier> steps;

	public PathIdentifier(String s) throws TokenException
	{
		super(s);
		if (s.isEmpty())
		{
			throw new IllegalEmptyPathException();
		}
		int p=s.lastIndexOf(SEPARATOR);
		String lastStep;
		Path<LevelIdentifier> myParent;
		if (p >= 0)
		{
			lastStep=s.substring(1 + p);
			myParent=new PathIdentifier(s.substring(0, p));
		}
		else
		{
			lastStep=s;
			myParent=null;
		}
		this.parent=myParent;
		this.currentStep=new LevelIdentifier(lastStep);
		this.steps=Collections.unmodifiableList(collectSteps(this.parent, this.currentStep));
	}

	public PathIdentifier(Path<LevelIdentifier> parent, LevelIdentifier currentStep) throws TokenException
	{
		super(parent.toString() + SEPARATOR + currentStep.toString());
		this.parent=parent;
		this.currentStep=currentStep;
		this.steps=Collections.unmodifiableList(collectSteps(parent, currentStep));
	}

	private static List<LevelIdentifier> collectSteps(Path<LevelIdentifier> parent, LevelIdentifier currentStep)
	{
		Deque<LevelIdentifier> stack=new ArrayDeque<LevelIdentifier>(100);
		if (currentStep != null)
		{
			stack.push(currentStep);
		}
		Path<LevelIdentifier> t=parent;
		while (t != null)
		{
			stack.push(t.getTailStep());
			t=t.getParent();
		}
		List<LevelIdentifier> list=new ArrayList<LevelIdentifier>(stack.size());
		for (LevelIdentifier step : stack)
		{
			list.add(step);
		}
		return list;
	}

	@Override
	public List<LevelIdentifier> getSteps()
	{
		return this.steps;
	}

	@Override
	public Path<LevelIdentifier> createBranch(LevelIdentifier step)
	{
		return new PathIdentifier(this, step);
	}

	@Override
	public Path<LevelIdentifier> createParentBranch(LevelIdentifier step)
	{
		PathIdentifier newParent=new PathIdentifier(null, step);
		return newParent.createBranch(this);
	}

	@Override
	public Path<LevelIdentifier> createBranch(Path<LevelIdentifier> path)
	{
		Path<LevelIdentifier> newPath=this;
		for (LevelIdentifier step : path.getSteps())
		{
			newPath=newPath.createBranch(step);
		}
		return newPath;
	}

	@Override
	public String serialized()
	{
		return toString();
		// StringBuilder stb=new StringBuilder(20 * this.levels.size());
		// for (LevelIdentifier level : this.levels)
		// {
		// stb.append(level.toString());
		// }
		// return stb.toString();
	}

	@Override
	public boolean isEmpty()
	{
		return this.steps.isEmpty();
	}

	@Override
	public Path<LevelIdentifier> getParent()
	{
		return this.parent;
	}

	@Override
	public LevelIdentifier getTailStep()
	{
		return this.currentStep;
	}

	@Override
	public Path<LevelIdentifier> getTail()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PathSplitter<LevelIdentifier> createSplitter()
	{
		return new PathSplitter<LevelIdentifier>(this);
	}
}
