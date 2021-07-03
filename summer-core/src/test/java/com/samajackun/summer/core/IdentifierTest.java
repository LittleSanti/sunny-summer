package com.samajackun.summer.core;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class IdentifierTest
{
	@Test
	public void oneIdentifier()
	{
		Identifier i1=new Identifier("enero");
		Map<Identifier, String> map=new HashMap<Identifier, String>();
		map.put(i1, "mes 01");
	}

}
