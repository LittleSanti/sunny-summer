package client;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.samajackun.summer.conf.annotations.PropertiesSource;
import com.samajackun.summer.conf.annotations.PropertiesSource.Position;
import com.samajackun.summer.conf.annotations.Property;

@PropertiesSource(position=Position.ABSOLUTE)
public class MyAbsoluteBean
{
	@Property
	private final Class<? extends CharSequence> implementation;

	@Property
	private final int max;

	@Property
	private final Set<MyReusableBean> mySetOfBeans;

	@Property
	private final List<MyReusableBean> myListOfBeans;

	@Property
	private final Map<String, MyReusableBean> myMapOfBeans;

	public MyAbsoluteBean(Class<? extends CharSequence> implementation, int max, Set<MyReusableBean> mySetOfBeans, List<MyReusableBean> myListOfBeans, Map<String, MyReusableBean> myMapOfBeans)
	{
		super();
		this.implementation=implementation;
		this.max=max;
		this.mySetOfBeans=mySetOfBeans;
		this.myListOfBeans=myListOfBeans;
		this.myMapOfBeans=myMapOfBeans;
	}

	public Class<? extends CharSequence> getImplementation()
	{
		return this.implementation;
	}

	public int getMax()
	{
		return this.max;
	}

	public Set<MyReusableBean> getMySetOfBeans()
	{
		return this.mySetOfBeans;
	}

	public List<MyReusableBean> getMyListOfBeans()
	{
		return this.myListOfBeans;
	}

	public Map<String, MyReusableBean> getMyMapOfBeans()
	{
		return this.myMapOfBeans;
	}

}
