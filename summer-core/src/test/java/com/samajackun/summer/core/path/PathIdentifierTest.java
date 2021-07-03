package com.samajackun.summer.core.path;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PathIdentifierTest
{
	@Test(expected=MinBoundTokenException.class)
	public void zeroSteps()
	{
		new PathIdentifier("");
	}

	@Test
	public void oneSteps()
	{
		PathIdentifier pi=new PathIdentifier("enero");
		assertEquals("enero", pi.getSteps().get(0).toString());
	}

	@Test
	public void twoSteps()
	{
		PathIdentifier pi=new PathIdentifier("enero/febrero");
		assertEquals("enero", pi.getSteps().get(0).toString());
		assertEquals("febrero", pi.getSteps().get(1).toString());
	}
}
