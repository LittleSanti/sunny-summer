package com.samajackun.summer.core.path;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class PlainTree<K extends Comparable<K>, T>
{
	private final T value;

	private final SortedSet<Path<K>> entries=new TreeSet<Path<K>>(new PathComparator());

	public PlainTree(T value)
	{
		super();
		this.value=value;
	}

	public PlainTree()
	{
		this(null);
	}

	public T getValue()
	{
		return this.value;
	}

	public Set<Path<K>> getEntries()
	{
		return this.entries;
	}

	public List<Path<K>> getEntryNames()
	{
		List<Path<K>> names=new ArrayList<Path<K>>(this.entries.size());
		for (Path<K> key : this.entries)
		{
			names.add(key.getTail());
		}
		return names;
	}

	private class PathComparator implements Comparator<Path<K>>
	{
		@Override
		public int compare(Path<K> o1, Path<K> o2)
		{
			int x=0;
			Iterator<K> iterator1=o1.getSteps().iterator();
			Iterator<K> iterator2=o2.getSteps().iterator();
			while (iterator1.hasNext() && iterator2.hasNext() && x == 0)
			{
				K k1=iterator1.next();
				K k2=iterator2.next();
				x=k1.compareTo(k2);
			}
			if (x == 0)
			{
				if (iterator1.hasNext())
				{
					x=1;
				}
				else if (iterator2.hasNext())
				{
					x=-1;
				}
			}
			return x;
		}
	}

	@Override
	public String toString()
	{
		return "PlainTree [value=" + this.value + ", entries=" + this.entries + "]";
	}

}
