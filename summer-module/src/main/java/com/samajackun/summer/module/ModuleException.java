package com.samajackun.summer.module;

import com.samajackun.summer.core.Identifier;

public class ModuleException extends Exception
{
	private static final long serialVersionUID=-5627942710783189578L;

	private final Identifier module;

	// public ModuleException(String module)
	// {
	// super();
	// this.module=module;
	// }
	//
	public ModuleException(Identifier module, String message, Throwable cause)
	{
		super(message, cause);
		this.module=module;
	}

	public ModuleException(Identifier module, String message)
	{
		super(message);
		this.module=module;
	}

	public Identifier getModule()
	{
		return this.module;
	}
}
