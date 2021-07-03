package com.samajackun.summer.core.path;

public class MaxBoundTokenException extends TokenException
{
	private static final long serialVersionUID=-7476422618567747610L;

	private final int maxBound;

	public MaxBoundTokenException(String tokenType, int maxBound)
	{
		super("Any token of type " + tokenType + " must have at max " + maxBound + " characters");
		this.maxBound=maxBound;
	}

	public int getMaxBound()
	{
		return this.maxBound;
	}

}
