package com.samajackun.sunnysummer.i18n.pathlocalize;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Deque;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import org.junit.Test;

public class ResourceUtilsTest
{

	// @Test
	// public void test()
	// {
	// Pattern pattern=Pattern.compile("(.*)(\\..*)");
	// Matcher matcher=pattern.matcher("myfle.txt");
	// String s=matcher.replaceAll("$1_es$2");
	// System.out.println(s);
	// }
	//
	// @Test
	// public void test2()
	// {
	// Pattern pattern=Pattern.compile("(.*)(\\..*)");
	// Matcher matcher=pattern.matcher("myfle");
	// String templateString=matcher.replaceAll("$1\\${locale}$2");
	//
	// Map<String, String> valuesMap=new HashMap<>();
	// valuesMap.put("locale", "_es");
	// StringSubstitutor sub=new StringSubstitutor(valuesMap);
	// String resolvedString=sub.replace(templateString);
	// System.out.println(resolvedString);
	// }
	//
	// @Test
	// public void serializeLocale()
	// {
	// Locale locale=new Locale(null, "ES");
	// System.out.println(locale);
	// }
	//
	// @Test
	// public void paths()
	// throws IOException
	// {
	// Path zip=Paths.get("S:/proyectos/sunny-summer/sunny-summer-i18n/src/test/resources/resources.zip");
	// System.out.println("exists pom.xml in zip=" + existsSubPath(zip, "pom.xml"));
	// System.out.println("exists pollas.xml in zip=" + existsSubPath(zip, "pollas.xml"));
	// Path dir=Paths.get("S:/proyectos/sunny-summer/sunny-summer-i18n");
	// System.out.println("exists pom.xml in dir=" + existsSubPath(dir, "pom.xml"));
	// System.out.println("exists pollas.xml in dir=" + existsSubPath(dir, "pollas.xml"));
	// }
	//
	// private static boolean existsSubPath(Path root, String child)
	// {
	// boolean x;
	// try
	// {
	// Path checkable;
	// if (root.toFile().isDirectory())
	// {
	// checkable=root.resolve(child);
	// }
	// else
	// {
	// FileSystem fs;
	// fs=FileSystems.newFileSystem(root);
	// checkable=fs.getPath(child);
	// }
	// x=Files.exists(checkable);
	// }
	// catch (IOException e)
	// {
	// x=false;
	// }
	// return x;
	// }

	@Test
	public void existsSubPathForExistingFileInDirectory()
	{
		Path dir=Paths.get(".");
		try
		{
			assertTrue(ResourceUtils.existsSubPath(dir, "pom.xml"));
		}
		catch (IOException e)
		{
			fail(e.toString());
		}
	}

	@Test
	public void existsSubPathForNonExistingFileInDirectory()
	{
		Path dir=Paths.get(".");
		try
		{
			assertFalse(ResourceUtils.existsSubPath(dir, "not-existing-file"));
		}
		catch (IOException e)
		{
			fail(e.toString());
		}
	}

	@Test
	public void existsSubPathForExistingFileInZip()
	{
		Path dir=Paths.get("target/test-classes/resources.zip");
		try
		{
			assertTrue(ResourceUtils.existsSubPath(dir, "pom.xml"));
		}
		catch (IOException e)
		{
			fail(e.toString());
		}
	}

	@Test
	public void existsSubPathForNonExistingFileInZip()
	{
		Path dir=Paths.get("target/test-classes/resources.zip");
		try
		{
			assertFalse(ResourceUtils.existsSubPath(dir, "not-existing-file"));
		}
		catch (IOException e)
		{
			fail(e.toString());
		}
	}

	@Test
	public void localeToMapQueueForLocaleWithEmptyLanguage()
	{
		Locale locale=new Locale("");
		Deque<Map<String, String>> deque=ResourceUtils.localeToMapQueue(locale);
		assertEquals(1, deque.size());
		Map<String, String> map;

		// global map:
		map=deque.pollFirst();
		assertNull(map.get(LocaleKeys.SERIAL));
		assertNull(map.get(LocaleKeys.SLASH_SERIAL));
		assertNull(map.get(LocaleKeys.UNDERSCORE_SERIAL));
		assertNull(map.get(LocaleKeys.LANGUAGE));
		assertNull(map.get(LocaleKeys.SLASH_LANGUAGE));
		assertNull(map.get(LocaleKeys.UNDERSCORE_LANGUAGE));
		assertNull(map.get(LocaleKeys.COUNTRY));
		assertNull(map.get(LocaleKeys.SLASH_COUNTRY));
		assertNull(map.get(LocaleKeys.UNDERSCORE_COUNTRY));
		assertNull(map.get(LocaleKeys.VARIANT));
		assertNull(map.get(LocaleKeys.SLASH_VARIANT));
		assertNull(map.get(LocaleKeys.UNDERSCORE_VARIANT));
	}

	@Test
	public void localeToMapQueueForLocaleWithLanguage()
	{
		Locale locale=new Locale("en");
		Deque<Map<String, String>> deque=ResourceUtils.localeToMapQueue(locale);
		assertEquals(2, deque.size());
		Map<String, String> map;

		// language map:
		map=deque.pollFirst();
		assertEquals("en", map.get(LocaleKeys.SERIAL));
		assertEquals("/en", map.get(LocaleKeys.SLASH_SERIAL));
		assertEquals("_en", map.get(LocaleKeys.UNDERSCORE_SERIAL));
		assertEquals("en", map.get(LocaleKeys.LANGUAGE));
		assertEquals("/en", map.get(LocaleKeys.SLASH_LANGUAGE));
		assertEquals("_en", map.get(LocaleKeys.UNDERSCORE_LANGUAGE));
		assertNull(map.get(LocaleKeys.COUNTRY));
		assertNull(map.get(LocaleKeys.SLASH_COUNTRY));
		assertNull(map.get(LocaleKeys.UNDERSCORE_COUNTRY));
		assertNull(map.get(LocaleKeys.VARIANT));
		assertNull(map.get(LocaleKeys.SLASH_VARIANT));
		assertNull(map.get(LocaleKeys.UNDERSCORE_VARIANT));

		// global map:
		map=deque.pollFirst();
		assertNull(map.get(LocaleKeys.SERIAL));
		assertNull(map.get(LocaleKeys.SLASH_SERIAL));
		assertNull(map.get(LocaleKeys.UNDERSCORE_SERIAL));
		assertNull(map.get(LocaleKeys.LANGUAGE));
		assertNull(map.get(LocaleKeys.SLASH_LANGUAGE));
		assertNull(map.get(LocaleKeys.UNDERSCORE_LANGUAGE));
		assertNull(map.get(LocaleKeys.COUNTRY));
		assertNull(map.get(LocaleKeys.SLASH_COUNTRY));
		assertNull(map.get(LocaleKeys.UNDERSCORE_COUNTRY));
		assertNull(map.get(LocaleKeys.VARIANT));
		assertNull(map.get(LocaleKeys.SLASH_VARIANT));
		assertNull(map.get(LocaleKeys.UNDERSCORE_VARIANT));
	}

	@Test
	public void localeToMapQueueForLocaleWithLanguageAndCountry()
	{
		Locale locale=new Locale("en", "IR");
		Deque<Map<String, String>> deque=ResourceUtils.localeToMapQueue(locale);
		assertEquals(3, deque.size());
		Map<String, String> map;

		// country map:
		map=deque.pollFirst();
		assertEquals("en_IR", map.get(LocaleKeys.SERIAL));
		assertEquals("/en_IR", map.get(LocaleKeys.SLASH_SERIAL));
		assertEquals("_en_IR", map.get(LocaleKeys.UNDERSCORE_SERIAL));
		assertEquals("en", map.get(LocaleKeys.LANGUAGE));
		assertEquals("/en", map.get(LocaleKeys.SLASH_LANGUAGE));
		assertEquals("_en", map.get(LocaleKeys.UNDERSCORE_LANGUAGE));
		assertEquals("IR", map.get(LocaleKeys.COUNTRY));
		assertEquals("/IR", map.get(LocaleKeys.SLASH_COUNTRY));
		assertEquals("_IR", map.get(LocaleKeys.UNDERSCORE_COUNTRY));
		assertNull(map.get(LocaleKeys.VARIANT));
		assertNull(map.get(LocaleKeys.SLASH_VARIANT));
		assertNull(map.get(LocaleKeys.UNDERSCORE_VARIANT));

		// language map:
		map=deque.pollFirst();
		assertEquals("en", map.get(LocaleKeys.SERIAL));
		assertEquals("/en", map.get(LocaleKeys.SLASH_SERIAL));
		assertEquals("_en", map.get(LocaleKeys.UNDERSCORE_SERIAL));
		assertEquals("en", map.get(LocaleKeys.LANGUAGE));
		assertEquals("/en", map.get(LocaleKeys.SLASH_LANGUAGE));
		assertEquals("_en", map.get(LocaleKeys.UNDERSCORE_LANGUAGE));
		assertNull(map.get(LocaleKeys.COUNTRY));
		assertNull(map.get(LocaleKeys.SLASH_COUNTRY));
		assertNull(map.get(LocaleKeys.UNDERSCORE_COUNTRY));
		assertNull(map.get(LocaleKeys.VARIANT));
		assertNull(map.get(LocaleKeys.SLASH_VARIANT));
		assertNull(map.get(LocaleKeys.UNDERSCORE_VARIANT));

		// global map:
		map=deque.pollFirst();
		assertNull(map.get(LocaleKeys.SERIAL));
		assertNull(map.get(LocaleKeys.SLASH_SERIAL));
		assertNull(map.get(LocaleKeys.UNDERSCORE_SERIAL));
		assertNull(map.get(LocaleKeys.LANGUAGE));
		assertNull(map.get(LocaleKeys.SLASH_LANGUAGE));
		assertNull(map.get(LocaleKeys.UNDERSCORE_LANGUAGE));
		assertNull(map.get(LocaleKeys.COUNTRY));
		assertNull(map.get(LocaleKeys.SLASH_COUNTRY));
		assertNull(map.get(LocaleKeys.UNDERSCORE_COUNTRY));
		assertNull(map.get(LocaleKeys.VARIANT));
		assertNull(map.get(LocaleKeys.SLASH_VARIANT));
		assertNull(map.get(LocaleKeys.UNDERSCORE_VARIANT));
	}

	@Test
	public void localeToMapQueueForLocaleWithLanguageAndCountryAndVariant()
	{
		Locale locale=new Locale("en", "IR", "GA");
		Deque<Map<String, String>> deque=ResourceUtils.localeToMapQueue(locale);
		assertEquals(4, deque.size());
		Map<String, String> map;

		// variant map:
		map=deque.pollFirst();
		assertEquals("en_IR_GA", map.get(LocaleKeys.SERIAL));
		assertEquals("/en_IR_GA", map.get(LocaleKeys.SLASH_SERIAL));
		assertEquals("_en_IR_GA", map.get(LocaleKeys.UNDERSCORE_SERIAL));
		assertEquals("en", map.get(LocaleKeys.LANGUAGE));
		assertEquals("/en", map.get(LocaleKeys.SLASH_LANGUAGE));
		assertEquals("_en", map.get(LocaleKeys.UNDERSCORE_LANGUAGE));
		assertEquals("IR", map.get(LocaleKeys.COUNTRY));
		assertEquals("/IR", map.get(LocaleKeys.SLASH_COUNTRY));
		assertEquals("_IR", map.get(LocaleKeys.UNDERSCORE_COUNTRY));
		assertEquals("GA", map.get(LocaleKeys.VARIANT));
		assertEquals("/GA", map.get(LocaleKeys.SLASH_VARIANT));
		assertEquals("_GA", map.get(LocaleKeys.UNDERSCORE_VARIANT));

		// country map:
		map=deque.pollFirst();
		assertEquals("en_IR", map.get(LocaleKeys.SERIAL));
		assertEquals("/en_IR", map.get(LocaleKeys.SLASH_SERIAL));
		assertEquals("_en_IR", map.get(LocaleKeys.UNDERSCORE_SERIAL));
		assertEquals("en", map.get(LocaleKeys.LANGUAGE));
		assertEquals("/en", map.get(LocaleKeys.SLASH_LANGUAGE));
		assertEquals("_en", map.get(LocaleKeys.UNDERSCORE_LANGUAGE));
		assertEquals("IR", map.get(LocaleKeys.COUNTRY));
		assertEquals("/IR", map.get(LocaleKeys.SLASH_COUNTRY));
		assertEquals("_IR", map.get(LocaleKeys.UNDERSCORE_COUNTRY));
		assertNull(map.get(LocaleKeys.VARIANT));
		assertNull(map.get(LocaleKeys.SLASH_VARIANT));
		assertNull(map.get(LocaleKeys.UNDERSCORE_VARIANT));

		// language map:
		map=deque.pollFirst();
		assertEquals("en", map.get(LocaleKeys.SERIAL));
		assertEquals("/en", map.get(LocaleKeys.SLASH_SERIAL));
		assertEquals("_en", map.get(LocaleKeys.UNDERSCORE_SERIAL));
		assertEquals("en", map.get(LocaleKeys.LANGUAGE));
		assertEquals("/en", map.get(LocaleKeys.SLASH_LANGUAGE));
		assertEquals("_en", map.get(LocaleKeys.UNDERSCORE_LANGUAGE));
		assertNull(map.get(LocaleKeys.COUNTRY));
		assertNull(map.get(LocaleKeys.SLASH_COUNTRY));
		assertNull(map.get(LocaleKeys.UNDERSCORE_COUNTRY));
		assertNull(map.get(LocaleKeys.VARIANT));
		assertNull(map.get(LocaleKeys.SLASH_VARIANT));
		assertNull(map.get(LocaleKeys.UNDERSCORE_VARIANT));

		// global map:
		map=deque.pollFirst();
		assertNull(map.get(LocaleKeys.SERIAL));
		assertNull(map.get(LocaleKeys.SLASH_SERIAL));
		assertNull(map.get(LocaleKeys.UNDERSCORE_SERIAL));
		assertNull(map.get(LocaleKeys.LANGUAGE));
		assertNull(map.get(LocaleKeys.SLASH_LANGUAGE));
		assertNull(map.get(LocaleKeys.UNDERSCORE_LANGUAGE));
		assertNull(map.get(LocaleKeys.COUNTRY));
		assertNull(map.get(LocaleKeys.SLASH_COUNTRY));
		assertNull(map.get(LocaleKeys.UNDERSCORE_COUNTRY));
		assertNull(map.get(LocaleKeys.VARIANT));
		assertNull(map.get(LocaleKeys.SLASH_VARIANT));
		assertNull(map.get(LocaleKeys.UNDERSCORE_VARIANT));
	}

	@Test
	public void transformSubPathIfExistsForNonExistentFile()
	{
		Path root=Paths.get("target/test-classes/locales");
		Path base=Paths.get("non-existent.txt");
		RegexTemplate regexTemplate=new RegexTemplate(Pattern.compile("(.*)(\\..*)"), "$1\\${underscore_locale}$2");
		Map<String, String> valuesMap=new HashMap<>();
		try
		{
			Path result=ResourceUtils.transformSubPathIfExists(root, base, regexTemplate, valuesMap);
			assertNull(result);
		}
		catch (IOException e)
		{
			fail(e.toString());
		}
	}

	@Test
	public void transformSubPathIfExistsForUniqueFileAndEmptyLocale()
	{
		Path root=Paths.get("target/test-classes/locales");
		Path base=Paths.get("resource00.txt");
		RegexTemplate regexTemplate=new RegexTemplate(Pattern.compile("(.*)(\\..*)"), "$1\\${underscore_locale}$2");
		Map<String, String> valuesMap=new HashMap<>();
		try
		{
			Path result=ResourceUtils.transformSubPathIfExists(root, base, regexTemplate, valuesMap);
			assertNotNull(result);
			assertEquals("resource00.txt", result.toString());
		}
		catch (IOException e)
		{
			fail(e.toString());
		}
	}

	@Test
	public void transformSubPathIfExistsForUniqueFileAndLocaleWithLanguage()
	{
		Path root=Paths.get("target/test-classes/locales");
		Path base=Paths.get("resource00.txt");
		RegexTemplate regexTemplate=new RegexTemplate(Pattern.compile("(.*)(\\..*)"), "$1\\${underscore_locale}$2");
		Map<String, String> valuesMap=createMap("en");
		try
		{
			Path result=ResourceUtils.transformSubPathIfExists(root, base, regexTemplate, valuesMap);
			assertNull(result);
		}
		catch (IOException e)
		{
			fail(e.toString());
		}
	}

	@Test
	public void transformSubPathIfExistsForLanguageFileAndLocaleWithLanguage()
	{
		Path root=Paths.get("target/test-classes/locales");
		Path base=Paths.get("resource01.txt");
		RegexTemplate regexTemplate=new RegexTemplate(Pattern.compile("(.*)(\\..*)"), "$1\\${underscore_locale}$2");
		Map<String, String> valuesMap=createMap("en");
		try
		{
			Path result=ResourceUtils.transformSubPathIfExists(root, base, regexTemplate, valuesMap);
			assertNotNull(result);
			assertEquals("resource01_en.txt", result.toString());
		}
		catch (IOException e)
		{
			fail(e.toString());
		}
	}

	@Test
	public void transformSubPathIfExistsForLanguageFileAndLocaleWithLanguageAndCountry()
	{
		Path root=Paths.get("target/test-classes/locales");
		Path base=Paths.get("resource01.txt");
		RegexTemplate regexTemplate=new RegexTemplate(Pattern.compile("(.*)(\\..*)"), "$1\\${underscore_locale}$2");
		Map<String, String> valuesMap=createMap("en_IR");
		try
		{
			Path result=ResourceUtils.transformSubPathIfExists(root, base, regexTemplate, valuesMap);
			assertNull(result);
		}
		catch (IOException e)
		{
			fail(e.toString());
		}
	}

	private static Map<String, String> createMap(String value)
	{
		Map<String, String> map=new HashMap<>();
		map.put("underscore_locale", "_" + value);
		return map;
	}
}
