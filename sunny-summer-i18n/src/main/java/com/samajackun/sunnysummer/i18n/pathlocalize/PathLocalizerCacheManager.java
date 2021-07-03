package com.samajackun.sunnysummer.i18n.pathlocalize;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Manager of {@link PathLocalizerCache}s. Indexed by tuples Path+RegexPattern.
 *
 * @author SKN
 */
public class PathLocalizerCacheManager
{
	private static final PathLocalizerCacheManager INSTANCE=new PathLocalizerCacheManager();

	private final Map<Path, Map<RegexTemplate, PathLocalizerCache>> map=new HashMap<>();

	public static PathLocalizerCacheManager getInstance()
	{
		return INSTANCE;
	}

	private PathLocalizerCacheManager()
	{
	}

	/**
	 * Get a cache or create a new one.
	 *
	 * @param root Root path.
	 * @param regexTemplate RegexTemplate.
	 * @return The requested cache.
	 */
	public PathLocalizerCache getCache(Path root, RegexTemplate regexTemplate)
	{
		Map<RegexTemplate, PathLocalizerCache> map2=this.map.computeIfAbsent(root, x -> new HashMap<>());
		return map2.computeIfAbsent(regexTemplate, y -> new PathLocalizerCache(root, regexTemplate));
	}
}
