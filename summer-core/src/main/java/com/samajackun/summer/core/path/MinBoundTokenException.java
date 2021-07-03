package com.samajackun.summer.core.path;

public class MinBoundTokenException extends TokenException
{
	private static final long serialVersionUID=-7056550447274163477L;

	private final int minBound;

	public MinBoundTokenException(String tokenType, int minBound)
	{
		super("Any of type " + tokenType + " must have at least " + minBound + " characters");
		this.minBound=minBound;
	}

	public int getMinBound()
	{
		return this.minBound;
	}
}
