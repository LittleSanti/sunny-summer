package com.samajackun.summer.core.path;

import java.util.Map;

public interface Tree<K extends Comparable<K>, T>
{
	public Tree<K, T> getParent();

	public Map<K, Tree<K, T>> getChildren();

	public Path<K> getPath();

	public Tree<K, T> getTree(Path<K> path);

	public Visitor.Control traverse(Visitor<K, T> visitor);

	public Tree<K, T> addChild(K key);

	public Tree<K, T> addChild(K key, T value);

	public Tree<K, T> addChild(Tree<K, T> tree);

	public T getValue();

	public Map<Path<K>, PlainTree<K, T>> index();

	public void addedChild(Path<K> addedPath, T value);
}
