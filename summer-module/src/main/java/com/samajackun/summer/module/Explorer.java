package com.samajackun.summer.module;

import java.util.Set;

import com.samajackun.summer.core.IdentifierPath;

public interface Explorer
{
	public abstract Set<IdentifierPath> getModulePaths();

	public abstract Module getModule(IdentifierPath path)
		throws ModuleNotFoundException;
}
