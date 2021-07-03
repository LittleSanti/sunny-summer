package com.samajackun.summer.conf.provider.properties;

import com.samajackun.summer.core.path.IdentifierFormatException;

public class MissingValueException extends IdentifierFormatException
{
	private static final long serialVersionUID=5148838525428683628L;

	private final String line;

	public MissingValueException(String line)
	{
		super("Missing equals sign (=) in line: " + line);
		this.line=line;
	}

	public String getLine()
	{
		return this.line;
	}

}
