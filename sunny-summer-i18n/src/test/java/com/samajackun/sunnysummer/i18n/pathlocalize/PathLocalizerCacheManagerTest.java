package com.samajackun.sunnysummer.i18n.pathlocalize;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import org.junit.Test;

public class PathLocalizerCacheManagerTest
{
	@Test
	public void getInstance()
	{
		assertNotNull(PathLocalizerCacheManager.getInstance());
	}

	@Test
	public void getCacheForSamePathAndTemplate()
	{
		Path root1=Paths.get("target/test-classes/locales");
		RegexTemplate regexTemplate1=new RegexTemplate(Pattern.compile("(.*)(\\..*)"), "$1\\${underscore_locale}$2");
		PathLocalizerCache cache1a=PathLocalizerCacheManager.getInstance().getCache(root1, regexTemplate1);
		PathLocalizerCache cache1b=PathLocalizerCacheManager.getInstance().getCache(root1, regexTemplate1);
		assertSame(cache1a, cache1b);
	}

	@Test
	public void getCacheForSamePathAndDifferentTemplate()
	{
		Path root1=Paths.get("target/test-classes/locales");
		RegexTemplate regexTemplate1=new RegexTemplate(Pattern.compile("(.*)(\\..*)"), "$1\\${underscore_locale}$2");
		PathLocalizerCache cache1=PathLocalizerCacheManager.getInstance().getCache(root1, regexTemplate1);
		RegexTemplate regexTemplate2=new RegexTemplate(Pattern.compile("(.*)(\\..*)"), "$1\\${locale}$2");
		PathLocalizerCache cache2=PathLocalizerCacheManager.getInstance().getCache(root1, regexTemplate2);
		assertNotSame(cache1, cache2);
	}
}
