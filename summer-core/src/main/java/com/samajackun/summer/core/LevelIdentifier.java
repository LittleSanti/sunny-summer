package com.samajackun.summer.core;

public class LevelIdentifier extends Identifier
{
	public LevelIdentifier(char[] value)
	{
		super(checkLexic(value));
	}

	public LevelIdentifier(String s)
	{
		super(checkLexic(s));
	}

	private static char[] checkLexic(char[] s)
	{
		for (char c : s)
		{
			checkChar(c);
		}
		return s;
	}

	private static String checkLexic(String s)
	{
		for (int i=0; i < s.length(); i++)
		{
			char c=s.charAt(i);
			checkChar(c);
		}
		return s;
	}

	private static void checkChar(char c)
	{
		if (c < ' ' || c == '/')
		{
			throw new IllegalArgumentException("Level identifier chars must be between ascii 32 and 255, and must not contain the SLASH character '/'");
		}
	}
}
