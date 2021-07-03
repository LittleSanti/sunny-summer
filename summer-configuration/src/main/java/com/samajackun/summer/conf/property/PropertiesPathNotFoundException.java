package com.samajackun.summer.conf.property;

public class PropertiesPathNotFoundException extends PropertiesProviderException
{
	/**  */
	private static final long serialVersionUID=-2214339364800664967L;

	private final PropertyPath path;

	public PropertiesPathNotFoundException(PropertyPath path)
	{
		super("Property not found with path=" + path.serialized());
		this.path=path;
	}

	public PropertiesPathNotFoundException(PropertyPath path, String message)
	{
		super(message);
		this.path=path;
	}

	public PropertyPath getPath()
	{
		return this.path;
	}

}
