package com.samajackun.summer.core.i18n;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class LogoTest
{
	@Test(expected=NullPointerException.class)
	public void nullLanguage()
	{
		Language language=null;
		new Logo(language);
	}

	@Test
	public void language()
	{
		Language language=Language.getLanguage("es");
		Logo logo=new Logo(language);
		assertSame(language, logo.getLanguage());
		assertNull(logo.getCountry());
		assertNull(logo.getVariant());
		assertEquals("es", logo.toLocale().toString());
	}

	@Test
	public void languageAndCountry()
	{
		Language language=Language.getLanguage("es");
		Country country=Country.getCountry("PT");
		Logo logo=new Logo(language, country);
		assertSame(language, logo.getLanguage());
		assertSame(country, logo.getCountry());
		assertNull(logo.getVariant());
		assertEquals("es_PT", logo.toLocale().toString());
	}

	@Test
	public void languageAndCountryAndVariant()
	{
		Language language=Language.getLanguage("es");
		Country country=Country.getCountry("PT");
		Variant variant=new Variant("que");
		Logo logo=new Logo(language, country, variant);
		assertSame(language, logo.getLanguage());
		assertSame(country, logo.getCountry());
		assertSame(variant, logo.getVariant());
		assertEquals("es_PT_que", logo.toLocale().toString());
	}

	@Test
	public void toLocaleTwice()
	{
		Language language=Language.getLanguage("es");
		Logo logo=new Logo(language);
		assertSame(logo.toLocale(), logo.toLocale());
	}
}
