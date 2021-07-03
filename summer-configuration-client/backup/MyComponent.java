package client;

import static com.samajackun.summer.conf.utils.PropertyUtils.property;

import com.samajackun.summer.conf.annotations.Property;

public class MyComponent
{
	@Property
	private final String name=property(null);

	@Property
	private final int id=property(null);
}
