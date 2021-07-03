package com.samajackun.summer.conf.provider.store;

import com.samajackun.summer.core.path.Identifier;

public class Token
{
	private final Identifier name;

	private final int index;

	public Token(Identifier name, int index)
	{
		super();
		this.name=name;
		this.index=index;
	}

	public int getIndex()
	{
		return this.index;
	}

	public Identifier getName()
	{
		return this.name;
	}

	@Override
	public String toString()
	{
		return "Token [name=" + this.name + ", index=" + this.index + "]";
	}
}