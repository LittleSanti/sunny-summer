package com.samajackun.sunnysummer.i18n.pathlocalize;

import java.util.HashMap;
import java.util.Map;

/**
 * Encapsulates a Strings map and makes sure that any null value is returned as empty string by the {@link #get} method.
 *
 * @author SKN
 */
class EmptyStringMap extends HashMap<String, String>
{
	private static final long serialVersionUID=-449087189096317198L;

	public EmptyStringMap(Map<? extends String, ? extends String> m)
	{
		super(m);
	}

	/**
	 * @return The empty string instead of null.
	 */
	@Override
	public String get(Object key)
	{
		String value=super.get(key);
		return (value == null)
			? ""
			: value;
	}

}
