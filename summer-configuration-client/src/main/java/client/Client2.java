package client;

import com.samajackun.summer.conf.PropertyUtils;

public class Client2
{
	private final int a=PropertyUtils.property("a");

	public int getA()
	{
		return this.a;
	}

}
