package com.samajackun.summer.core.path;

public class IndexedIdentifier extends Identifier
{
	public static final int ANY=-1;

	private final int index;

	public IndexedIdentifier(char[] value, int index)
	{
		super(value);
		this.index=index;
	}

	public IndexedIdentifier(String s, int index)
	{
		super(s);
		this.index=index;
	}

	public int getIndex()
	{
		return this.index;
	}
}
