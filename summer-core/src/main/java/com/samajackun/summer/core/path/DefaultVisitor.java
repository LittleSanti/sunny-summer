package com.samajackun.summer.core.path;

public class DefaultVisitor<K extends Comparable<K>, T> implements Visitor<K, T>
{
	@Override
	public com.samajackun.summer.core.path.Visitor.Control before(Tree<K, T> tree)
	{
		return Control.NEXT_LEVEL;
	}

	@Override
	public com.samajackun.summer.core.path.Visitor.Control after(Tree<K, T> tree)
	{
		return Control.NEXT_CHILD;
	}
}
