package client;

import java.util.Map;

import com.samajackun.summer.conf.annotations.PropertiesSource;
import com.samajackun.summer.conf.annotations.PropertiesSource.Position;
import com.samajackun.summer.conf.annotations.Property;
import com.samajackun.summer.core.Identifier;

@PropertiesSource(position=Position.ABSOLUTE)
public class MyFactory
{
	@Property
	private final MyInterface myDefaultImplementation;

	@Property
	private final Map<Identifier, MyInterface> implementations;

	public MyFactory(MyInterface myDefaultImplementation, Map<Identifier, MyInterface> implementations)
	{
		super();
		this.myDefaultImplementation=myDefaultImplementation;
		this.implementations=implementations;
	}

}
