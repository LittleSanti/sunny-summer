package com.samajackun.summer.conf.property;

import java.util.List;

public interface Path
{
	public List<String> getSteps();

	public Path createBranch(String step);

	public Path createBranch(Path path);

	public String serialized();
}
