package com.samajackun.summer.core.i18n;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.samajackun.summer.core.path.IllegalAlphabetFormatTokenException;
import com.samajackun.summer.core.path.MaxBoundTokenException;
import com.samajackun.summer.core.path.MinBoundTokenException;

public class CountryTest
{
	@Test(expected=NullPointerException.class)
	public void nullValue()
	{
		String s=null;
		Country.getCountry(s);
	}

	@Test(expected=MinBoundTokenException.class)
	public void empty()
	{
		String s="";
		Country.getCountry(s);
	}

	@Test(expected=MinBoundTokenException.class)
	public void oneChars()
	{
		String s="e";
		Country.getCountry(s);
	}

	@Test(expected=MaxBoundTokenException.class)
	public void threeChars()
	{
		String s="esp";
		Country.getCountry(s);
	}

	@Test(expected=IllegalAlphabetFormatTokenException.class)
	public void twoCharsLowercase()
	{
		String s="es";
		Country.getCountry(s);
	}

	@Test
	public void twoCharsUppercase()
	{
		String s="ES";
		Country country=Country.getCountry(s);
		assertEquals(s, country.toString());
	}

	@Test
	public void getCountryTwice()
	{
		String s="ES";
		assertSame(Country.getCountry(s), Country.getCountry("E" + "S"));
	}
}
