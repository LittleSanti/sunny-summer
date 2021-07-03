package com.samajackun.summer.core.file;

public class FileStep implements Comparable<FileStep>
{
	private final String name;

	public FileStep(String name)
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
	public int compareTo(FileStep o)
	{
		return this.name.compareTo(o.name);
	}

}
