package com.samajackun.sunnysummer.i18n.pathlocalize;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.regex.Pattern;

import org.junit.Test;

public class PathLocalizerCacheTest
{
	@Test
	public void getLocalizedPath()
	{
		Path root=Paths.get("target/test-classes/locales");
		RegexTemplate regexTemplate=new RegexTemplate(Pattern.compile("(.*)(\\..*)"), "$1\\${underscore_locale}$2");
		PathLocalizerCache cache=new PathLocalizerCache(root, regexTemplate);
		Path base=Paths.get("resource01.txt");
		try
		{
			Path transformed1=cache.getLocalizedPath(base, new Locale("en"));
			Path transformed2=cache.getLocalizedPath(base, new Locale("en"));
			assertSame(transformed1, transformed2);
		}
		catch (IOException e)
		{
			fail(e.toString());
		}
	}

}
