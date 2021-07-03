package com.samajackun.summer.module;

import com.samajackun.summer.core.Identifier;

public class ModuleParseException extends ModuleException
{
	private static final long serialVersionUID=8044656092258468618L;

	public ModuleParseException(Identifier module, String message)
	{
		super(module, message);
	}

}
