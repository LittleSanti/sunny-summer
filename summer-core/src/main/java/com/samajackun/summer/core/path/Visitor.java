package com.samajackun.summer.core.path;

public interface Visitor<K extends Comparable<K>, T>
{
	public enum Control {
		NEXT_LEVEL, NEXT_CHILD, STOP
	};

	public Control before(Tree<K, T> tree);

	public Control after(Tree<K, T> tree);
}
