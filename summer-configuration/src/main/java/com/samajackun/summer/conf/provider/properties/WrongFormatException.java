package com.samajackun.summer.conf.provider.properties;

import com.samajackun.summer.core.path.IdentifierFormatException;

public class WrongFormatException extends IdentifierFormatException
{
	private static final long serialVersionUID=3497393768569091756L;

	private final String wrongName;

	public WrongFormatException(String wrongName)
	{
		super("Wrong format for name '" + wrongName + "'");
		this.wrongName=wrongName;
	}

	public String getWrongName()
	{
		return this.wrongName;
	}

}
