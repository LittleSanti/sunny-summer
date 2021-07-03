package com.samajackun.summer.conf.provider.properties;

public class IncompleteUnicodeSequenceException extends UnicodeFormatException
{
	private static final long serialVersionUID=222283374498924462L;

	private final String unicodeSequence;

	public IncompleteUnicodeSequenceException(String unicodeSequence)
	{
		super("Incomplete unicode sequence '" + unicodeSequence + "'");
		this.unicodeSequence=unicodeSequence;
	}

	public String getUnicodeSequence()
	{
		return this.unicodeSequence;
	}
}
