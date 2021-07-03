package com.samajackun.summer.core.path;

import java.util.ArrayList;
import java.util.List;

public final class StringSplitter
{
	private StringSplitter()
	{
	}

	public static List<String> split(String input, char separator)
	{
		int len=input.length();
		List<String> splits=new ArrayList<String>((int)(0.1d * len));
		int p0=-1;
		for (int i=0; i < len; i++)
		{
			char c=input.charAt(i);
			if (c == separator)
			{
				splits.add(input.substring(1 + p0, i));
				p0=i;
			}
		}
		if (p0 < len)
		{
			splits.add(input.substring(1 + p0));
		}
		return splits;
	}
}
