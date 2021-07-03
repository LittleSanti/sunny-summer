package com.samajackun.summer.core.path;

class MyBean
{
	private final int id;

	private final String name;

	public MyBean(int id, String name)
	{
		super();
		this.id=id;
		this.name=name;
	}

	public int getId()
	{
		return this.id;
	}

	public String getName()
	{
		return this.name;
	}

	@Override
	public String toString()
	{
		return "MyBean [id=" + this.id + ", name=" + this.name + "]";
	}

}