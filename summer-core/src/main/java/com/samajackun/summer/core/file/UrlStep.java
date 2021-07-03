package com.samajackun.summer.core.file;

public class UrlStep implements Comparable<UrlStep>
{
	private final String name;

	public UrlStep(String name)
	{
		super();
		this.name=name;
	}

	public String getName()
	{
		return this.name;
	}

	@Override
	public String toString()
	{
		return this.name;
	}

	@Override
	public int compareTo(UrlStep o)
	{
		return this.name.compareTo(o.name);
	}
}
