package com.samajackun.summer.core.path;

public interface Converter<T>
{
	public T parse(String s)
		throws ConverterException;

	public String serialize(T token)
		throws ConverterException;
}
