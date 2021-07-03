package com.samajackun.summer.conf.provider.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.samajackun.summer.core.path.Identifier;

public class ConfigNode
{
	private final Identifier identifier;

	private final List<Object> values=new ArrayList<Object>();

	private final Map<Identifier, List<ConfigNode>> children=new HashMap<Identifier, List<ConfigNode>>();

	public ConfigNode(Identifier identifier)
	{
		super();
		this.identifier=identifier;
	}

	public List<Object> getValues()
	{
		return this.values;
	}

	public Map<Identifier, List<ConfigNode>> getChildren()
	{
		return this.children;
	}

	public Identifier getIdentifier()
	{
		return this.identifier;
	}
}
