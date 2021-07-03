package com.samajackun.summer.core.i18n;

import java.util.Locale;

public class Logo
{
	private final Language language;

	private final Country country;

	private final Variant variant;

	private Locale locale;

	public Logo(Language language)
	{
		this(language, null, null);
	}

	public Logo(Language language, Country country)
	{
		this(language, country, null);
	}

	public Logo(Language language, Country country, Variant variant)
	{
		super();
		if (language == null)
		{
			throw new NullPointerException("Language must not be null");
		}
		this.language=language;
		this.country=country;
		this.variant=variant;
	}

	public Language getLanguage()
	{
		return this.language;
	}

	public Country getCountry()
	{
		return this.country;
	}

	public Variant getVariant()
	{
		return this.variant;
	}

	public Locale toLocale()
	{
		if (this.locale == null)
		{
			if (this.country != null)
			{
				if (this.variant != null)
				{
					this.locale=new Locale(this.language.toString(), this.country.toString(), this.variant.toString());
				}
				else
				{
					this.locale=new Locale(this.language.toString(), this.country.toString());
				}
			}
			else
			{
				this.locale=new Locale(this.language.toString());
			}
		}
		return this.locale;
	}
}
