package com.samajackun.summer.core.path;

import java.util.NoSuchElementException;

public class PathSplitter<K extends Comparable<K>>
{
	private final Path<K> src;

	private Path<K> parentPart;

	private Path<K> childPart;

	private int pos;

	public PathSplitter(Path<K> src)
	{
		super();
		this.src=src;
		this.parentPart=this.src;
		this.pos=this.src.getSteps().size();
		fetch();
	}

	public boolean hasNext()
	{
		return this.pos >= 0;
	}

	public PathSplitter<K> next()
	{
		if (this.pos < 0)
		{
			throw new NoSuchElementException();
		}
		fetch();
		return this;
	}

	private void fetch()
	{
		this.pos--;
		if (this.parentPart != null)
		{
			this.childPart=this.childPart == null
				? this.parentPart.getTail()
				: this.childPart.createParentBranch(this.parentPart.getTailStep());
							this.parentPart=this.parentPart.getParent();
		}
	}

	public Path<K> getParentPart()
	{
		return this.parentPart;
	}

	public Path<K> getChildPart()
	{
		return this.childPart;
	}
}
