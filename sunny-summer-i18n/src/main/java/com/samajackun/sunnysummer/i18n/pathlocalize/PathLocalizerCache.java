package com.samajackun.sunnysummer.i18n.pathlocalize;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Plays as a cache facade for {@link PathLocalizer}
 *
 * @author SKN
 */
public final class PathLocalizerCache
{
	private final Path root;

	private final RegexTemplate regexTemplate;

	private final Map<Locale, Map<Path, Path>> map=new HashMap<>();

	/**
	 * Initializes variables.
	 *
	 * @param root Root path from which the base as well as the localized versions are referenced.
	 * @param regexTemplate Template to perform the transformation from the base name to a localized name.
	 */
	public PathLocalizerCache(Path root, RegexTemplate regexTemplate)
	{
		super();
		this.root=root;
		this.regexTemplate=regexTemplate;
	}

	/**
	 * Calls {@link PathLocalizer#findLocalizedPath(Path, Path, RegexTemplate, Locale)} and caches the results.
	 *
	 * @param base Base path.
	 * @param locale Input locale.
	 * @return Localized path (at least, the base).
	 * @throws IOException If any error occurs accessing the paths.
	 * @see {@link PathLocalizer#findLocalizedPath(Path, Path, RegexTemplate, Locale)}
	 */
	public Path getLocalizedPath(Path base, Locale locale)
		throws IOException
	{
		return this.map.computeIfAbsent(locale, x -> new HashMap<>()).computeIfAbsent(base, y -> {
			try
			{
				return PathLocalizer.getInstance().findLocalizedPath(this.root, base, this.regexTemplate, locale);
			}
			catch (IOException e)
			{
				throw new RuntimeIOException(e);
			}
		});
	}

	private class RuntimeIOException extends RuntimeException
	{
		private static final long serialVersionUID=-1929479482692381534L;

		public RuntimeIOException(Throwable cause)
		{
			super(cause);
		}

	}
}
