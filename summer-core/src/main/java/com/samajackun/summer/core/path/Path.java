package com.samajackun.summer.core.path;

import java.util.List;

public interface Path<T extends Comparable<T>>
{
	public List<T> getSteps();

	public Path<T> getParent();

	public Path<T> createBranch(T step);

	public Path<T> createBranch(Path<T> path);

	public Path<T> createParentBranch(T step);

	public String serialized();

	public String serialized(char separator);

	public boolean isEmpty();

	public Path<T> getTail();

	public T getTailStep();

	public PathSplitter<T> createSplitter();

	public Path<T> getPathRelativeTo(Path<T> base);
}
