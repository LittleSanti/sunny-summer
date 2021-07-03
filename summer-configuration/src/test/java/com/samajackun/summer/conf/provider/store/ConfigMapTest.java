package com.samajackun.summer.conf.provider.store;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import com.samajackun.summer.conf.provider.DefaultPropertyPath;
import com.samajackun.summer.conf.provider.PropertyPath;
import com.samajackun.summer.conf.provider.store.ConfigMap;
import com.samajackun.summer.conf.provider.store.ConfigNode;

public class ConfigMapTest
{
	@Test
	public void test()
	{
		ConfigMap map=new ConfigMap();
		ConfigNode node=map.createNode(new DefaultPropertyPath("universo.planeta"));
		node.put(new DefaultPropertyPath("name"), "Venus");
		node.put(new DefaultPropertyPath("mass"), 1290);
		Set<PropertyPath> set=node.listNames();
		assertTrue(set.contains(new DefaultPropertyPath("universo.planeta.name")));
		assertTrue(set.contains(new DefaultPropertyPath("universo.planeta.mass")));

	}
}
