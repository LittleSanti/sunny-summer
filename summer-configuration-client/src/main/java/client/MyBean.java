package client;

import java.util.Date;

import com.samajackun.summer.conf.annotations.PropertiesSource;
import com.samajackun.summer.conf.annotations.Property;

@PropertiesSource(position=PropertiesSource.Position.RELATIVE)
public class MyBean
{
	private int id;

	private String name;

	private Date birth;

	private boolean single;

	public MyBean(@Property int id, @Property String name, @Property Date birth, @Property boolean single)
	{
		super();
		this.id=id;
		this.name=name;
		this.birth=birth;
		this.single=single;
	}

	public int getId()
	{
		return this.id;
	}

	public void setId(int id)
	{
		this.id=id;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name=name;
	}

	public Date getBirth()
	{
		return this.birth;
	}

	public void setBirth(Date birth)
	{
		this.birth=birth;
	}

	public boolean isSingle()
	{
		return this.single;
	}

	public void setSingle(boolean single)
	{
		this.single=single;
	}
}
