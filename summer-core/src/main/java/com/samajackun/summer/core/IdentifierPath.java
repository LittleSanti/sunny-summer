package com.samajackun.summer.core;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class IdentifierPath extends Identifier implements Path<LevelIdentifier>
{
	private final List<LevelIdentifier> levels;

	public IdentifierPath(String s)
	{
		super(s);
		this.levels=parse(s);
	}

	private static List<LevelIdentifier> parse(String s)
	{
		StringTokenizer stk=new StringTokenizer(s, "/");
		int n=stk.countTokens();
		List<LevelIdentifier> levels=new ArrayList<LevelIdentifier>(n);
		for (int i=0; i < n; i++)
		{
			levels.add(new LevelIdentifier(stk.nextToken()));
		}
		return levels;
	}

	@Override
	public List<LevelIdentifier> getSteps()
	{
		return this.levels;
	}

	@Override
	public Path<LevelIdentifier> createBranch(LevelIdentifier step)
	{
		return new IdentifierPath(serialized() + "/" + step.toString());
	}

	@Override
	public Path<LevelIdentifier> createBranch(Path<LevelIdentifier> path)
	{
		return new IdentifierPath(serialized() + "/" + path.serialized());
	}

	@Override
	public String serialized()
	{
		StringBuilder stb=new StringBuilder(20 * this.levels.size());
		for (LevelIdentifier level : this.levels)
		{
			stb.append(level.toString());
		}
		return stb.toString();
	}
}
