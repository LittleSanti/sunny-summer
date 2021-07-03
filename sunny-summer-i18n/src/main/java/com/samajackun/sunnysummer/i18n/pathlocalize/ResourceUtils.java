package com.samajackun.sunnysummer.i18n.pathlocalize;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import lombok.experimental.UtilityClass;

/**
 * Utility methods to deal with paths and locales.
 *
 * @author SKN
 */
@UtilityClass
class ResourceUtils
{
	/**
	 * Creates a queue of maps. Each map consists of all the possible values of {@link LocaleKeys} keys, extracted from the input locale processed by levels:
	 * <ol>
	 * <li>The first map consideers the whole locale: language, country and variant (if any).
	 * <li>The second map consideers just the language and country.
	 * <li>The third map consideers just the language.
	 * <li>The fourth map is empty.
	 * </ol>
	 *
	 * @param locale
	 * @return
	 */
	public Deque<Map<String, String>> localeToMapQueue(Locale locale)
	{
		// Hay que retornar una cola para extaer maps en orden:
		Deque<Map<String, String>> deque=new ArrayDeque<>(4);
		String serial="";
		Map<String, String> mapGlobal=new HashMap<>();
		deque.addFirst(mapGlobal);
		if (!locale.getLanguage().isEmpty())
		{
			Map<String, String> mapLanguage=new HashMap<>(mapGlobal);
			mapLanguage.put(LocaleKeys.LANGUAGE, locale.getLanguage());
			mapLanguage.put(LocaleKeys.SLASH_LANGUAGE, "/" + locale.getLanguage());
			mapLanguage.put(LocaleKeys.UNDERSCORE_LANGUAGE, "_" + locale.getLanguage());
			serial=locale.getLanguage();
			mapLanguage.put(LocaleKeys.SERIAL, serial);
			mapLanguage.put(LocaleKeys.SLASH_SERIAL, "/" + serial);
			mapLanguage.put(LocaleKeys.UNDERSCORE_SERIAL, "_" + serial);
			deque.addFirst(mapLanguage);
			if (!locale.getCountry().isEmpty())
			{
				Map<String, String> mapCountry=new HashMap<>(mapLanguage);
				mapCountry.put(LocaleKeys.COUNTRY, locale.getCountry());
				mapCountry.put(LocaleKeys.SLASH_COUNTRY, "/" + locale.getCountry());
				mapCountry.put(LocaleKeys.UNDERSCORE_COUNTRY, "_" + locale.getCountry());
				serial+="_" + locale.getCountry();
				mapCountry.put(LocaleKeys.SERIAL, serial);
				mapCountry.put(LocaleKeys.SLASH_SERIAL, "/" + serial);
				mapCountry.put(LocaleKeys.UNDERSCORE_SERIAL, "_" + serial);
				deque.addFirst(mapCountry);
				if (!locale.getVariant().isEmpty())
				{
					Map<String, String> mapVariant=new HashMap<>(mapCountry);
					mapVariant.put(LocaleKeys.VARIANT, locale.getVariant());
					mapVariant.put(LocaleKeys.SLASH_VARIANT, "/" + locale.getVariant());
					mapVariant.put(LocaleKeys.UNDERSCORE_VARIANT, "_" + locale.getVariant());
					serial+="_" + locale.getVariant();
					mapVariant.put(LocaleKeys.SERIAL, serial);
					mapVariant.put(LocaleKeys.SLASH_SERIAL, "/" + serial);
					mapVariant.put(LocaleKeys.UNDERSCORE_SERIAL, "_" + serial);
					deque.addFirst(mapVariant);
				}
			}
		}
		return deque;
	}

	/**
	 * Transform a base path through a RegexTemplate with a map of values, and returns the produced path, only if it actually exists within the root path.
	 *
	 * @param root Root path.
	 * @param base Base path.
	 * @param regexTemplate Transforming template.
	 * @param valuesMap Map of values to expand the references in the template's output pattern.
	 * @return Transformed subpath if exists, null otherwise.
	 * @throws IOException If some error happens when accessing the paths.
	 */
	public static Path transformSubPathIfExists(Path root, Path base, RegexTemplate regexTemplate, Map<String, String> valuesMap)
		throws IOException
	{
		String candidate=regexTemplate.transform(base.toString(), valuesMap);
		Path selected=(existsSubPath(root, candidate))
			? Path.of(candidate)
			: null;
		return selected;
	}

	/**
	 * Checks if a child path exists under a root path. It supports filesystem paths and ZIP paths.
	 *
	 * @param root Root path.
	 * @param child Child path.
	 * @return true if exists, false otherwise.
	 * @throws IOException If some error happens when accessing the paths.
	 */
	public static boolean existsSubPath(Path root, String child)
		throws IOException
	{
		boolean x;
		Path checkable;
		if (root.toFile().isDirectory())
		{
			checkable=root.resolve(child);
		}
		else
		{
			FileSystem fs;
			fs=FileSystems.newFileSystem(root);
			checkable=fs.getPath(child);
		}
		x=Files.exists(checkable);
		return x;
	}
}
