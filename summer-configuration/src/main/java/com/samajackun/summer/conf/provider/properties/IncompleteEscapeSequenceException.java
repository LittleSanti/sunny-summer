package com.samajackun.summer.conf.provider.properties;

public class IncompleteEscapeSequenceException extends UnicodeFormatException
{
	private static final long serialVersionUID=5092576722730566613L;

	private final CharSequence src;

	public IncompleteEscapeSequenceException(CharSequence src)
	{
		super("Incomplete escape sequence '" + src + "'");
		this.src=src;
	}

	public CharSequence getSource()
	{
		return this.src;
	}

}
