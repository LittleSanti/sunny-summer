package com.samajackun.summer.core.path;

import org.junit.Test;

public class IdentifierTest
{
	@Test(expected=MinBoundTokenException.class)
	public void empty()
	{
		new Identifier("");
	}

	@Test
	public void oneIdentifier()
	{
		new Identifier("enero");
	}

	@Test
	public void identifierWithBrackets()
	{
		new Identifier("enero[1]");
	}

	@Test
	public void identifierWithTildes()
	{
		new Identifier("áéíóúñüçÁÉÍÓÚÑÇÜ");
	}
}
