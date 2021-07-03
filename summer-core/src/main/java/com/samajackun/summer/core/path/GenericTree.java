package com.samajackun.summer.core.path;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.samajackun.summer.core.path.Visitor.Control;

public class GenericTree<K extends Comparable<K>, T> implements Tree<K, T>
{
	private final Tree<K, T> parent;

	private final Path<K> path;

	private final Map<K, Tree<K, T>> children=new HashMap<K, Tree<K, T>>();

	private final Map<Path<K>, PlainTree<K, T>> index=new HashMap<Path<K>, PlainTree<K, T>>();

	private final T value;

	public GenericTree(Tree<K, T> parent, Path<K> path, T value)
	{
		super();
		this.parent=parent;
		this.path=path;
		this.value=value;
		// addedChild(path, value);
	}

	@Override
	public T getValue()
	{
		return this.value;
	}

	@Override
	public Tree<K, T> getParent()
	{
		return this.parent;
	}

	@Override
	public Map<K, Tree<K, T>> getChildren()
	{
		return this.children;
	}

	@Override
	public Path<K> getPath()
	{
		return this.path;
	}

	@Override
	public Tree<K, T> getTree(Path<K> path)
	{
		Tree<K, T> tree=this;
		for (Iterator<K> iterator=path.getSteps().iterator(); iterator.hasNext() && tree != null;)
		{
			K step=iterator.next();
			tree=tree.getChildren().get(step);
		}
		return tree;
	}

	@Override
	public Visitor.Control traverse(Visitor<K, T> visitor)
	{
		Visitor.Control control=visitor.before(this);
		switch (control)
		{
			case NEXT_CHILD:
				break;
			case NEXT_LEVEL:
				for (Tree<K, T> child : this.children.values())
				{
					control=child.traverse(visitor);
					if (control != Control.NEXT_CHILD)
					{
						break;
					}
				}
				break;
			case STOP:
				break;
		}
		if (control != Control.STOP)
		{
			control=visitor.after(this);
		}
		return control;
	}

	@Override
	public Tree<K, T> addChild(K key)
	{
		return addChild(key, null);
	}

	@Override
	public Tree<K, T> addChild(K key, T value)
	{
		Path<K> childPath=this.path.createBranch(key);
		Tree<K, T> child=new GenericTree<K, T>(this, childPath, value);
		this.children.put(key, child);
		addedChild(childPath.getTail(), value);
		// if (this.parent != null)
		// {
		// this.parent.addedChild(new PathSplitter<K>(childPath), value);
		// }
		return child;
	}

	@Override
	public Tree<K, T> addChild(Tree<K, T> tree)
	{
		if (tree.getParent() != null)
		{
			throw new IllegalArgumentException("Input tree must be a root node");
		}
		Path<K> childPath=this.path.createBranch(tree.getPath());
		tree.setParent(this.path);
		System.out.println("childPath=" + childPath + ", tree.getPath().getTailStep()=" + tree.getPath().getTailStep());
		this.children.put(tree.getPath().getTailStep(), tree);
		// addedChildren(tree);
		// if (this.parent != null)
		// {
		// this.parent.addedChild(new PathSplitter<K>(childPath), value);
		// }
		return tree;
	}

	@Override
	public Map<Path<K>, PlainTree<K, T>> index()
	{
		// final Map<Path<K>, PlainTree<K, T>> map=new HashMap<Path<K>, PlainTree<K, T>>();
		// Visitor<K, T> visitor=new DefaultVisitor<K, T>()
		// {
		// @Override
		// public com.samajackun.summer.core.path.Visitor.Control before(Tree<K, T> tree)
		// {
		// PlainTree<K, T> plainTree=new PlainTree<K, T>(tree.getValue());
		// for (K subpath : tree.getChildren().keySet())
		// {
		// plainTree.getEntries().add(tree.getPath().createBranch(subpath));
		// }
		// map.put(tree.getPath(), plainTree);
		// return super.before(tree);
		// }
		// };
		// traverse(visitor);
		// return map;
		return this.index;
	}

	public void addedChildren(Tree<K, T> addedTree)
	{
		Path<K> main=addedTree.getPath();
		synchronized (this.index)
		{
			for (Path<K> addedPath : addedTree.index().keySet())
			{
				Path<K> p=main.createBranch(addedPath);
				addedChild(p, addedTree.getTree(addedPath).getValue());
			}
		}
	}

	@Override
	public void addedChild(Path<K> addedPath, T value)
	{
		PlainTree<K, T> plainTree=this.index.get(addedPath);
		if (plainTree == null)
		{
			synchronized (this.index)
			{
				plainTree=this.index.get(addedPath);
				if (plainTree == null)
				{
					plainTree=new PlainTree<K, T>();
					this.index.put(addedPath, plainTree);
				}
			}
		}
		if (this.parent != null)
		{
			this.parent.addedChild(addedPath.createParentBranch(this.path.getTailStep()), value);
		}
	}
	// @Override
	// public void addedChild(PathSplitter<K> splitter, T value)
	// {
	// PlainTree<K, T> plainTree=this.index.get(splitter.getChildPart());
	// if (plainTree == null)
	// {
	// synchronized (this.index)
	// {
	// plainTree=this.index.get(splitter.getChildPart());
	// if (plainTree == null)
	// {
	// plainTree=new PlainTree<K, T>();
	// this.index.put(splitter.getChildPart(), plainTree);
	// }
	// }
	// }
	// synchronized (plainTree)
	// {
	// plainTree.getEntryNames().add(splitter.getChildPart());
	// }
	// if (this.parent != null)
	// {
	// splitter.next();
	// this.parent.addedChild(splitter, value);
	// }
	// }
}
