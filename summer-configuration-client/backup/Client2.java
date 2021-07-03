package client;

import com.samajackun.summer.conf.utils.PropertyUtils;

public class Client2
{
	private final int a=PropertyUtils.property("a");

	public int getA()
	{
		return this.a;
	}
}
