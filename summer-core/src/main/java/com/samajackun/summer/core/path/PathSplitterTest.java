package com.samajackun.summer.core.path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PathSplitterTest
{
	@Test
	public void empty()
	{
		Path<String> path=new StringPath("tierra");
		PathSplitter<String> splitter=path.createSplitter();

		assertTrue(splitter.hasNext());
		assertNull(splitter.getParentPart());
		assertEquals(".tierra", splitter.getChildPart().toString());
		splitter.next();

		assertFalse(splitter.hasNext());
	}

	@Test
	public void oneSteps()
	{
		Path<String> path=new StringPath("tierra");
		path=path.createBranch("europa");
		PathSplitter<String> splitter=path.createSplitter();

		assertTrue(splitter.hasNext());
		assertEquals(".tierra", splitter.getParentPart().toString());
		assertEquals(".europa", splitter.getChildPart().toString());
		splitter.next();

		assertTrue(splitter.hasNext());
		assertNull(splitter.getParentPart());
		assertEquals(".tierra.europa", splitter.getChildPart().toString());
		splitter.next();

		assertFalse(splitter.hasNext());
	}

	@Test
	public void twoSteps()
	{
		Path<String> path=new StringPath("tierra");
		path=path.createBranch("europa");
		path=path.createBranch("españa");
		PathSplitter<String> splitter=path.createSplitter();

		assertTrue(splitter.hasNext());
		assertEquals(".tierra.europa", splitter.getParentPart().toString());
		assertEquals(".españa", splitter.getChildPart().toString());
		splitter.next();

		assertTrue(splitter.hasNext());
		assertEquals(".tierra", splitter.getParentPart().toString());
		assertEquals(".europa.españa", splitter.getChildPart().toString());
		splitter.next();

		assertTrue(splitter.hasNext());
		assertNull(splitter.getParentPart());
		assertEquals(".tierra.europa.españa", splitter.getChildPart().toString());
		splitter.next();

		assertFalse(splitter.hasNext());
	}
}
