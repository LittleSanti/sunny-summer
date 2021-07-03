package com.samajackun.summer.core;

import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class FactoryTest
{
	@Test
	public void factory01()
	{
		MyBean01 bean01=Factory.<MyBean01> instance();
		assertNotNull(bean01);
	}

	@Test
	public void factory02()
	{
		MyBean01 bean01=Factory.<MyBean01> instance(123);
		assertNotNull(bean01);
	}
}
