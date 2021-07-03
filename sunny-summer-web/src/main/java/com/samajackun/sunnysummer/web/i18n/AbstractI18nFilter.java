package com.samajackun.sunnysummer.web.i18n;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.regex.Pattern;

import com.samajackun.sunnysummer.i18n.pathlocalize.PathLocalizerCache;
import com.samajackun.sunnysummer.i18n.pathlocalize.PathLocalizerCacheManager;
import com.samajackun.sunnysummer.i18n.pathlocalize.RegexTemplate;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public abstract class AbstractI18nFilter implements Filter
{
	private PathLocalizerCache cache;

	private ServletContext servletContext;

	@Override
	public void init(FilterConfig filterConfig)
		throws ServletException
	{
		this.servletContext=filterConfig.getServletContext();
		Path root=Paths.get(filterConfig.getServletContext().getRealPath(filterConfig.getInitParameter("root_path")));
		RegexTemplate regexTemplate=new RegexTemplate(Pattern.compile(filterConfig.getInitParameter("input_pattern")), filterConfig.getInitParameter("output_pattern"));
		this.cache=PathLocalizerCacheManager.getInstance().getCache(root, regexTemplate);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException,
		ServletException
	{
		Locale locale=getLocale(request);
		HttpServletRequest httpRequest=(HttpServletRequest)request;
		String servletPath=getServletPath(httpRequest);
		Path output=this.cache.getLocalizedPath(Paths.get(servletPath), locale);
		String newPath="/" + output.toString().replace(File.separatorChar, '/');
		this.servletContext.log("Translated path for locale " + locale + ": " + servletPath + " to " + newPath);
		request.getServletContext().getRequestDispatcher(newPath).forward(request, response);
	}

	private String getServletPath(HttpServletRequest httpRequest)
	{
		String servletPath=httpRequest.getServletPath();
		if (servletPath.startsWith("/"))
		{
			servletPath=servletPath.substring(1);
		}
		return servletPath;
	}

	protected abstract Locale getLocale(ServletRequest request);
}
