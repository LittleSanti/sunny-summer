package com.samajackun.summer.core.i18n;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.samajackun.summer.core.path.AbstractToken;

public class Country extends AbstractToken
{
	private static final Pattern ALPHABET=Pattern.compile("[A-Z][A-Z]");

	private Country(String value)
	{
		super(value, 2, 2, ALPHABET);
	}

	private static final Map<String, Country> map=createMap();

	private static Map<String, Country> createMap()
	{
		Map<String, Country> map=new HashMap<String, Country>(300);
		// String[] values= { "ES", "UK", "US", "FR", "IT", "PT", "BR" };
		// for (String value : values)
		// {
		// map.put(value, new Country(value));
		// }
		return map;
	}

	public static Country getCountry(String code)
	{
		Country country=map.get(code);
		if (country == null)
		{
			synchronized (map)
			{
				country=map.get(code);
				if (country == null)
				{
					country=new Country(code);
					map.put(code, country);
				}
			}
		}
		return country;
	}

}
