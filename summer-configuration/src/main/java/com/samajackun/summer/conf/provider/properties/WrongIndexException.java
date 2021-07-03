package com.samajackun.summer.conf.provider.properties;

import com.samajackun.summer.core.path.IdentifierFormatException;

public class WrongIndexException extends IdentifierFormatException
{
	private static final long serialVersionUID=3497393768569091756L;

	private final String wrongIndex;

	public WrongIndexException(String wrongIndex)
	{
		super("Wrong index '" + wrongIndex + "'");
		this.wrongIndex=wrongIndex;
	}

	public String getWrongIndex()
	{
		return this.wrongIndex;
	}

}
