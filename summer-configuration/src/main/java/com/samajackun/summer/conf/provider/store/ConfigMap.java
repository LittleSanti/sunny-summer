package com.samajackun.summer.conf.provider.store;

import java.util.ArrayList;
import java.util.List;

public class ConfigMap
{
	private final ConfigNode root=new ConfigNode();

	public ConfigNode getNode(TreePath path)
	{
		ConfigNode current=this.root;
		while (path.hasNext())
		{
			Token token=path.next();
			List<ConfigNode> nodes=current.getChildren().get(token.getName());
			if (nodes == null)
			{
				nodes=new ArrayList<ConfigNode>();
				current.getChildren().put(token.getName(), nodes);
			}
			if (token.getIndex() >= 0)
			{
				current=nodes.get(token.getIndex());
				if (current == null)
				{
					current=new ConfigNode(token.getName());
					nodes.set(token.getIndex(), current);
				}
			}
			else if (token.getIndex() < 0)
			{
				current=new ConfigNode(token.getName());
				nodes.add(token.getIndex(), current);
			}
		}
		return current;
	}
}
