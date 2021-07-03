package com.samajackun.sunnysummer.i18n.pathlocalize;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Deque;
import java.util.Locale;
import java.util.Map;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Finds localized paths from a base path.
 *
 * @author SKN
 */
@NoArgsConstructor(access=AccessLevel.PRIVATE)
public final class PathLocalizer
{
	private static final PathLocalizer INSTANCE=new PathLocalizer();

	public static PathLocalizer getInstance()
	{
		return INSTANCE;
	}

	/**
	 * Finds the most specific localized path from a base path.
	 *
	 * @param root Root path from which the base as well as the localized versions are referenced.
	 * @param base Base path.
	 * @param regexTemplate Template to perform the transformation from the base name to a localized name.
	 * @param locale Input locale.
	 * @return The found localized path: At least the base path if no other localized versions were found.
	 * @throws IOException If any error occurs accessing the paths.
	 */
	public Path findLocalizedPath(Path root, Path base, RegexTemplate regexTemplate, Locale locale)
		throws IOException
	{
		Path selected;
		if (locale != null)
		{
			Deque<Map<String, String>> partsMapQueue=ResourceUtils.localeToMapQueue(locale);
			Path selectedPath=null;
			while ((selectedPath == null) && !partsMapQueue.isEmpty())
			{
				Map<String, String> partsMap=partsMapQueue.pollFirst();
				selectedPath=ResourceUtils.transformSubPathIfExists(root, base, regexTemplate, partsMap);
			}
			selected=(selectedPath == null)
				? base
				: selectedPath;
		}
		else
		{
			selected=base;
		}
		return selected;
	}
}
