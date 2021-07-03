package client;

import com.samajackun.summer.conf.annotations.PropertiesSource;
import com.samajackun.summer.conf.annotations.PropertiesSource.Position;
import com.samajackun.summer.conf.annotations.Property;

@PropertiesSource(position=Position.RELATIVE)
public class MyReusableBean
{
	@Property
	private final String name;

	@Property
	private final int age;

	public MyReusableBean(@Property String name, @Property int age)
	{
		super();
		this.name=name;
		this.age=age;
	}

	public String getName()
	{
		return this.name;
	}

	public int getAge()
	{
		return this.age;
	}
}
