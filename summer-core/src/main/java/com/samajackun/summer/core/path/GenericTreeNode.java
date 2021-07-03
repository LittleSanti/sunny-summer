package com.samajackun.summer.core.path;

import java.util.ArrayList;
import java.util.List;

public class GenericTreeNode<K extends Comparable<K>, T>
{
	private final K key;

	private final T value;

	private final List<Tree<K, T>> children=new ArrayList<Tree<K, T>>();

	public GenericTreeNode(K key)
	{
		this(key, null);
	}

	public GenericTreeNode(K key, T value)
	{
		super();
		this.key=key;
		this.value=value;
	}

	public K getKey()
	{
		return this.key;
	}

	public T getValue()
	{
		return this.value;
	}

	public List<Tree<K, T>> getChildren()
	{
		return this.children;
	}
}
