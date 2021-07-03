package com.samajackun.summer.conf.provider;

public final class Source
{
	private final Provider provider;

	private final LocationFacade location;

	public Source(Provider provider, LocationFacade location)
	{
		super();
		this.provider=provider;
		this.location=location;
	}

	public Source(Provider provider)
	{
		this(provider, null);
	}

	public LocationFacade getLocation()
	{
		return this.location;
	}

	public Provider getProvider()
	{
		return this.provider;
	}
}
