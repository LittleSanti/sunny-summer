package com.samajackun.summer.conf.property;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DefaultPath implements Path
{
	private final List<String> steps;

	public DefaultPath()
	{
		this.steps=new ArrayList<String>();
	}

	private DefaultPath(List<String> steps)
	{
		this.steps=new ArrayList<String>(steps.size());
		for (String step : steps)
		{
			this.steps.add(step);
		}
	}

	public DefaultPath(String serial)
	{
		StringTokenizer stk=new StringTokenizer(serial, ".");
		int n=stk.countTokens();
		this.steps=new ArrayList<String>(n);
		for (int i=0; i < n; i++)
		{
			this.steps.add(stk.nextToken());
		}
	}

	public void addStep(String step)
	{
		this.steps.add(step);
	}

	@Override
	public List<String> getSteps()
	{
		return this.steps;
	}

	@Override
	public String serialized()
	{
		StringBuilder stb=new StringBuilder(20 * this.steps.size());
		for (String step : this.steps)
		{
			if (stb.length() > 0)
			{
				stb.append('.');
			}
			stb.append(step);
		}
		return stb.toString();
	}

	@Override
	public Path createBranch(String step)
	{
		DefaultPath newPath=new DefaultPath(this.steps);
		newPath.addStep(step);
		return newPath;
	}

	@Override
	public Path createBranch(Path path)
	{
		DefaultPath newPath=new DefaultPath(this.steps);
		for (String step : path.getSteps())
		{
			newPath.addStep(step);
		}
		return newPath;
	}

}
