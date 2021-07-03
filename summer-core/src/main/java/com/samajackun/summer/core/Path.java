package com.samajackun.summer.core;

import java.util.List;

public interface Path<T>
{
	public List<T> getSteps();

	public Path<T> createBranch(T step);

	public Path<T> createBranch(Path<T> path);

	public String serialized();
}
