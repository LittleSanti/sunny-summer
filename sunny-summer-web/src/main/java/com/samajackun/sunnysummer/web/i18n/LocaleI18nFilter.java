package com.samajackun.sunnysummer.web.i18n;

import java.util.Locale;

import org.apache.commons.lang3.LocaleUtils;

import jakarta.servlet.ServletRequest;

public class LocaleI18nFilter extends AbstractI18nFilter
{
	@Override
	protected Locale getLocale(ServletRequest request)
	{
		String localeStr=request.getParameter("locale");
		Locale locale=LocaleUtils.toLocale(localeStr);
		return locale;
	}
}
