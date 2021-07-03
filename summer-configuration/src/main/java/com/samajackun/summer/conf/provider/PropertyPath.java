package com.samajackun.summer.conf.provider;

import com.samajackun.summer.core.path.Identifier;
import com.samajackun.summer.core.path.Path;

public interface PropertyPath extends Path<Identifier>
{
	@Override
	public PropertyPath createBranch(Identifier step);

	@Override
	public PropertyPath createBranch(Path<Identifier> path);
}
