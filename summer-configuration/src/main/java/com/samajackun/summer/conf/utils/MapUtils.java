package com.samajackun.summer.conf.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

final class MapUtils
{
	private MapUtils()
	{
	}

	@SuppressWarnings("unchecked")
	public static void put(Map<String, Object> map, String key, Object value)
	{
		Object oldValue=map.get(key);
		if (oldValue != null)
		{
			List<Object> values;
			if (oldValue instanceof List<?>)
			{
				values=(List<Object>)oldValue;
			}
			else
			{
				values=new ArrayList<Object>(10);
				values.add(oldValue);
			}
			values.add(value);
			value=values;
		}
		map.put(key, value);
	}
}
