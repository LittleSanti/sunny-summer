package com.samajackun.summer.core.path;

public class LevelIdentifier extends Identifier implements Comparable<LevelIdentifier>
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
			throw new IllegalArgumentException("Level identifier chars must be between ascii 32 and must not contain the SLASH");
		}
	}

	@Override
	public int compareTo(LevelIdentifier o)
	{
		// FIXME Se puede optimizar.
		return toString().compareTo(o.toString());
	}
}
