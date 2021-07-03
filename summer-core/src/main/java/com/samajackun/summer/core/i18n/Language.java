package com.samajackun.summer.core.i18n;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.samajackun.summer.core.path.AbstractToken;

public final class Language extends AbstractToken
{
	private static final Pattern ALPHABET=Pattern.compile("[a-z][a-z]");

	private Language(String value)
	{
		super(value, 2, 2, ALPHABET);
	}

	private static final Map<String, Language> map=createMap();

	private static Map<String, Language> createMap()
	{
		Map<String, Language> map=new HashMap<String, Language>(300);
		// String[] values= { "es", "en", "fr", "it", "pt" };
		// for (String value : values)
		// {
		// map.put(value, new Language(value));
		// }
		return map;
	}

	public static Language getLanguage(String code)
	{
		Language language=map.get(code);
		if (language == null)
		{
			synchronized (map)
			{
				language=map.get(code);
				if (language == null)
				{
					language=new Language(code);
					map.put(code, language);
				}
			}
		}
		return language;
	}
}
