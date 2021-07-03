package com.samajackun.summer.module;

import com.samajackun.summer.core.Identifier;

public class ModuleNotFoundException extends ModuleException
{
	private static final long serialVersionUID=-5025191117469389346L;

	public ModuleNotFoundException(Identifier module)
	{
		super(module, "Module " + module + " not found");
	}
}
