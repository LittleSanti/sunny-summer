package com.samajackun.sunnysummer.i18n.pathlocalize;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Pattern;

import org.junit.Test;

public class RegexTemplateTest
{
	@Test
	public void equalsWithSameInputAndOutput()
	{
		RegexTemplate regexTemplate1=new RegexTemplate(Pattern.compile("(.*)(\\..*)"), "$1\\${underscore_locale}$2");
		RegexTemplate regexTemplate2=new RegexTemplate(Pattern.compile("(.*)(\\..*)"), "$1\\${underscore_locale}$2");
		assertTrue(regexTemplate1.equals(regexTemplate2));
	}

	@Test
	public void equalsWithSameInputAndDifferentOutput()
	{
		RegexTemplate regexTemplate1=new RegexTemplate(Pattern.compile("(.*)(\\..*)"), "$1\\${underscore_locale}$2");
		RegexTemplate regexTemplate2=new RegexTemplate(Pattern.compile("(.*)(\\..*)"), "$1\\${locale}$2");
		assertFalse(regexTemplate1.equals(regexTemplate2));
	}

}
