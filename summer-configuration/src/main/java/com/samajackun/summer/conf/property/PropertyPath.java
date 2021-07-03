package com.samajackun.summer.conf.property;

import com.samajackun.summer.core.Identifier;
import com.samajackun.summer.core.Path;

public interface PropertyPath extends Path<Identifier>
{
	@Override
	public PropertyPath createBranch(Identifier step);

	@Override
	public PropertyPath createBranch(Path<Identifier> path);
}
