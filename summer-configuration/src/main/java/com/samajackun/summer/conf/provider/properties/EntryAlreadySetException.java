package com.samajackun.summer.conf.provider.properties;

import com.samajackun.summer.conf.provider.PropertyPath;
import com.samajackun.summer.conf.provider.ProviderException;

public class EntryAlreadySetException extends ProviderException
{
	private static final long serialVersionUID=-9107255928331954379L;

	private final PropertyPath key;

	private final Object oldValue;

	public EntryAlreadySetException(PropertyPath key, Object oldValue)
	{
		super("Entry '" + key.serialized() + "' already set to value '" + oldValue + "'");
		this.key=key;
		this.oldValue=oldValue;
	}

	public EntryAlreadySetException(PropertyPath key, int index, Object oldValue)
	{
		super("Entry '" + key.serialized() + "' already set at index " + index + " to value '" + oldValue + "'");
		this.key=key;
		this.oldValue=oldValue;
	}
}
