package com.samajackun.summer.conf.provider;

import java.util.Map;

public interface Provider
{
	public Map<PropertyPath, Object> getAll()
		throws ProviderException;
}
