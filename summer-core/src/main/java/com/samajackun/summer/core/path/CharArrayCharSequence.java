package com.samajackun.summer.core.path;


public class CharArrayCharSequence implements CharSequence
{
	private final char[] array;

	public CharArrayCharSequence(char[] array)
	{
		super();
		this.array=array;
	}

	@Override
	public int length()
	{
		return this.array.length;
	}

	@Override
	public char charAt(int index)
	{
		return this.array[index];
	}

	@Override
	public CharSequence subSequence(int start, int end)
	{
		char[] newArray=new char[end - start];
		System.arraycopy(this.array, start, newArray, 0, newArray.length);
		return new CharArrayCharSequence(newArray);
	}

	@Override
	public String toString()
	{
		return new String(this.array);
	}
}
