package com.samajackun.summer.core.i18n;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.samajackun.summer.core.path.AbstractToken;

public class Charset extends AbstractToken
{
	private static final Pattern ALPHABET=Pattern.compile("[A-Z0-9_\\-]+", Pattern.CASE_INSENSITIVE);

	private Charset(String value)
	{
		super(value, 3, 20, ALPHABET);
	}

	private static final Map<String, Charset> map=createMap();

	private static Map<String, Charset> createMap()
	{
		Map<String, Charset> map=new HashMap<String, Charset>(300);
		return map;
	}

	public static Charset getCharset(String code)
	{
		Charset charset=map.get(code);
		if (charset == null)
		{
			synchronized (map)
			{
				charset=map.get(code);
				if (charset == null)
				{
					charset=new Charset(code);
					map.put(code, charset);
				}
			}
		}
		return charset;
	}
}
