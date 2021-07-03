package com.samajackun.sunnysummer.i18n.pathlocalize;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.regex.Pattern;

import org.junit.Test;

public class PathLocalizerTest
{
	@Test
	public void findLocalizedPathForUniqueFileAndNullLocale()
	{
		Path root=Paths.get("target/test-classes/locales");
		Path base=Paths.get("resource00.txt");
		RegexTemplate regexTemplate=new RegexTemplate(Pattern.compile("(.*)(\\..*)"), "$1\\${underscore_locale}$2");
		Locale locale=null;
		try
		{
			Path result=PathLocalizer.getInstance().findLocalizedPath(root, base, regexTemplate, locale);
			assertEquals("resource00.txt", result.toString());
		}
		catch (IOException e)
		{
			fail(e.toString());
		}
	}

	@Test
	public void findLocalizedPathForUniqueFileAndLocaleWithLanguage()
	{
		Path root=Paths.get("target/test-classes/locales");
		Path base=Paths.get("resource00.txt");
		RegexTemplate regexTemplate=new RegexTemplate(Pattern.compile("(.*)(\\..*)"), "$1\\${underscore_locale}$2");
		Locale locale=new Locale("en");
		try
		{
			Path result=PathLocalizer.getInstance().findLocalizedPath(root, base, regexTemplate, locale);
			assertEquals("resource00.txt", result.toString());
		}
		catch (IOException e)
		{
			fail(e.toString());
		}
	}

	@Test
	public void findLocalizedPathForLanguageFileAndLocaleWithLanguage()
	{
		Path root=Paths.get("target/test-classes/locales");
		Path base=Paths.get("resource01.txt");
		RegexTemplate regexTemplate=new RegexTemplate(Pattern.compile("(.*)(\\..*)"), "$1\\${underscore_locale}$2");
		Locale locale=new Locale("en");
		try
		{
			Path result=PathLocalizer.getInstance().findLocalizedPath(root, base, regexTemplate, locale);
			assertEquals("resource01_en.txt", result.toString());
		}
		catch (IOException e)
		{
			fail(e.toString());
		}
	}

	@Test
	public void findLocalizedPathForLanguageFileAndLocaleWithLanguageAndCountry()
	{
		Path root=Paths.get("target/test-classes/locales");
		Path base=Paths.get("resource01.txt");
		RegexTemplate regexTemplate=new RegexTemplate(Pattern.compile("(.*)(\\..*)"), "$1\\${underscore_locale}$2");
		Locale locale=new Locale("en", "IR");
		try
		{
			Path result=PathLocalizer.getInstance().findLocalizedPath(root, base, regexTemplate, locale);
			assertEquals("resource01_en.txt", result.toString());
		}
		catch (IOException e)
		{
			fail(e.toString());
		}
	}

	@Test
	public void findLocalizedPathForLanguageFileAndLocaleWithLanguageAndCountryAndVariant()
	{
		Path root=Paths.get("target/test-classes/locales");
		Path base=Paths.get("resource01.txt");
		RegexTemplate regexTemplate=new RegexTemplate(Pattern.compile("(.*)(\\..*)"), "$1\\${underscore_locale}$2");
		Locale locale=new Locale("en", "IR", "GA");
		try
		{
			Path result=PathLocalizer.getInstance().findLocalizedPath(root, base, regexTemplate, locale);
			assertEquals("resource01_en.txt", result.toString());
		}
		catch (IOException e)
		{
			fail(e.toString());
		}
	}

	@Test
	public void findLocalizedPathForCountryFileAndLocaleWithLanguage()
	{
		Path root=Paths.get("target/test-classes/locales");
		Path base=Paths.get("resource02.txt");
		RegexTemplate regexTemplate=new RegexTemplate(Pattern.compile("(.*)(\\..*)"), "$1\\${underscore_locale}$2");
		Locale locale=new Locale("en");
		try
		{
			Path result=PathLocalizer.getInstance().findLocalizedPath(root, base, regexTemplate, locale);
			assertEquals("resource02_en.txt", result.toString());
		}
		catch (IOException e)
		{
			fail(e.toString());
		}
	}

	@Test
	public void findLocalizedPathForCountryFileAndLocaleWithLanguageAndCountry()
	{
		Path root=Paths.get("target/test-classes/locales");
		Path base=Paths.get("resource02.txt");
		RegexTemplate regexTemplate=new RegexTemplate(Pattern.compile("(.*)(\\..*)"), "$1\\${underscore_locale}$2");
		Locale locale=new Locale("en", "IR");
		try
		{
			Path result=PathLocalizer.getInstance().findLocalizedPath(root, base, regexTemplate, locale);
			assertEquals("resource02_en_IR.txt", result.toString());
		}
		catch (IOException e)
		{
			fail(e.toString());
		}
	}

	@Test
	public void findLocalizedPathForCountryFileAndLocaleWithLanguageAndCountryAndVariant()
	{
		Path root=Paths.get("target/test-classes/locales");
		Path base=Paths.get("resource02.txt");
		RegexTemplate regexTemplate=new RegexTemplate(Pattern.compile("(.*)(\\..*)"), "$1\\${underscore_locale}$2");
		Locale locale=new Locale("en", "IR", "GA");
		try
		{
			Path result=PathLocalizer.getInstance().findLocalizedPath(root, base, regexTemplate, locale);
			assertEquals("resource02_en_IR.txt", result.toString());
		}
		catch (IOException e)
		{
			fail(e.toString());
		}
	}

	@Test
	public void findLocalizedPathForVariantFileAndLocaleWithLanguage()
	{
		Path root=Paths.get("target/test-classes/locales");
		Path base=Paths.get("resource03.txt");
		RegexTemplate regexTemplate=new RegexTemplate(Pattern.compile("(.*)(\\..*)"), "$1\\${underscore_locale}$2");
		Locale locale=new Locale("en");
		try
		{
			Path result=PathLocalizer.getInstance().findLocalizedPath(root, base, regexTemplate, locale);
			assertEquals("resource03_en.txt", result.toString());
		}
		catch (IOException e)
		{
			fail(e.toString());
		}
	}

	@Test
	public void findLocalizedPathForVariantFileAndLocaleWithLanguageAndCountry()
	{
		Path root=Paths.get("target/test-classes/locales");
		Path base=Paths.get("resource03.txt");
		RegexTemplate regexTemplate=new RegexTemplate(Pattern.compile("(.*)(\\..*)"), "$1\\${underscore_locale}$2");
		Locale locale=new Locale("en", "IR");
		try
		{
			Path result=PathLocalizer.getInstance().findLocalizedPath(root, base, regexTemplate, locale);
			assertEquals("resource03_en_IR.txt", result.toString());
		}
		catch (IOException e)
		{
			fail(e.toString());
		}
	}

	@Test
	public void findLocalizedPathForVariantFileAndLocaleWithLanguageAndCountryAndVariant()
	{
		Path root=Paths.get("target/test-classes/locales");
		Path base=Paths.get("resource03.txt");
		RegexTemplate regexTemplate=new RegexTemplate(Pattern.compile("(.*)(\\..*)"), "$1\\${underscore_locale}$2");
		Locale locale=new Locale("en", "IR", "GA");
		try
		{
			Path result=PathLocalizer.getInstance().findLocalizedPath(root, base, regexTemplate, locale);
			assertEquals("resource03_en_IR_GA.txt", result.toString());
		}
		catch (IOException e)
		{
			fail(e.toString());
		}
	}

	@Test
	public void findLocalizedPathForUniqueFileAndNonMatchingTransformation()
	{
		Path root=Paths.get("target/test-classes/locales");
		Path base=Paths.get("resource01.txt");
		RegexTemplate regexTemplate=new RegexTemplate(Pattern.compile("(.*)(\\..*)"), "non-existing");
		Locale locale=new Locale("en");
		try
		{
			Path result=PathLocalizer.getInstance().findLocalizedPath(root, base, regexTemplate, locale);
			assertEquals("resource01.txt", result.toString());
		}
		catch (IOException e)
		{
			fail(e.toString());
		}
	}
}
