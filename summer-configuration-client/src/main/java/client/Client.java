package client;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import com.samajackun.summer.conf.annotations.PropertiesSource;
import com.samajackun.summer.conf.annotations.PropertiesSource.Position;
import com.samajackun.summer.conf.annotations.Property;

//import demo.annotations.Properties;
@PropertiesSource(position=Position.ABSOLUTE)
public class Client
{
	@Property
	private Map<String, MyBean> beanMap;

	@Property
	private Collection<MyBean> beanList;

	@Property(name="otherPackage.OtherClass.other_property")
	private final String myString="x";

	@Property
	private final int myInt=11;

	@Property
	private final long myLong=12L;

	@Property
	private final double myDouble=99d;

	@Property(format="yyyy-MM-dd HH:mm:ss")
	private final Date myDate=null;

	public String getMyString()
	{
		return this.myString;
	}

	// public void setMyString(String myString)
	// {
	// this.myString=myString;
	// }

	public int getMyInt()
	{
		return this.myInt;
	}

	// public void setMyInt(int myInt)
	// {
	// this.myInt=myInt;
	// }

	public long getMyLong()
	{
		return this.myLong;
	}

	// public void setMyLong(long myLong)
	// {
	// this.myLong=myLong;
	// }

	public double getMyDouble()
	{
		return this.myDouble;
	}

	// public void setMyDouble(double myDouble)
	// {
	// this.myDouble=myDouble;
	// }

	public Date getMyDate()
	{
		return this.myDate;
	}

	// public void setMyDate(Date myDate)
	// {
	// this.myDate=myDate;
	// }
}
