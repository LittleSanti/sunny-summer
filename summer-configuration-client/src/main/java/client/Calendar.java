package client;

import com.samajackun.summer.conf.annotations.Property;
import com.samajackun.summer.conf.utils.ParameterUtils;

public class Calendar
{
	@Property
	private final String month="january";

	private final int year;

	public Calendar()
	{
		this.year=ParameterUtils.getSystemPropertyAsInt("myyear");
	}

}
