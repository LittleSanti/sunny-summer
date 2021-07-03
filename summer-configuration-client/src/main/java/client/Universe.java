package client;

import com.samajackun.summer.conf.annotations.Property;

public class Universe
{
	@Property
	public final long age=10000;

	public Universe()
	{
		System.out.println(1);
	}

	public Universe(boolean x)
	{
		System.out.println(2);
	}
}
