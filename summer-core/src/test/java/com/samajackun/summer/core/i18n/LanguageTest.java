package com.samajackun.summer.core.i18n;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.samajackun.summer.core.path.IllegalAlphabetFormatTokenException;
import com.samajackun.summer.core.path.MaxBoundTokenException;
import com.samajackun.summer.core.path.MinBoundTokenException;

public class LanguageTest
{
	@Test(expected=NullPointerException.class)
	public void nullValue()
	{
		String s=null;
		Language.getLanguage(s);
	}

	@Test(expected=MinBoundTokenException.class)
	public void empty()
	{
		String s="";
		Language.getLanguage(s);
	}

	@Test(expected=MinBoundTokenException.class)
	public void oneChars()
	{
		String s="E";
		Language.getLanguage(s);
	}

	@Test(expected=MaxBoundTokenException.class)
	public void threeChars()
	{
		String s="ESP";
		Language.getLanguage(s);
	}

	@Test(expected=IllegalAlphabetFormatTokenException.class)
	public void twoCharsUppercase()
	{
		String s="ES";
		Language.getLanguage(s);
	}

	@Test
	public void twoCharsLowercase()
	{
		String s="es";
		Language country=Language.getLanguage(s);
		assertEquals(s, country.toString());
	}

	@Test
	public void getLanguageTwice()
	{
		String s="es";
		assertSame(Language.getLanguage(s), Language.getLanguage("e" + "s"));
	}
}
