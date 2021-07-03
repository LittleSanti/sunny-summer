package com.samajackun.sunnysummer.i18n.pathlocalize;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.text.StringSubstitutor;

/**
 * Template to specify a string transformation based on regular expressions. It is composed by:
 * <li>An input {@link java.lang.regex.Pattern regexp pattern}
 * <li>An output pattern, which may contain references to the groups specified in the input pattern: <code>$1, $2, $3... </code>
 * It also may contain references to external variables: <code>${locale}, ${language}, ${country}...</code>
 *
 * @see LocaleKeys
 * @author SKN
 */
public class RegexTemplate
{
	private final Pattern inputPattern;

	private final String outputPattern;

	public RegexTemplate(Pattern inputPattern, String outputPattern)
	{
		super();
		this.inputPattern=inputPattern;
		this.outputPattern=outputPattern;
	}

	public String transform(String input, Map<String, String> valuesMap)
	{
		Matcher matcher=this.inputPattern.matcher(input);
		String templateString=matcher.replaceAll(this.outputPattern);
		StringSubstitutor sub=new StringSubstitutor(new EmptyStringMap(valuesMap));
		return sub.replace(templateString);
	}

	@Override
	public int hashCode()
	{
		final int prime=31;
		int result=1;
		result=(prime * result) + ((this.inputPattern == null)
			? 0
			: this.inputPattern.hashCode());
		result=(prime * result) + ((this.outputPattern == null)
			? 0
			: this.outputPattern.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		RegexTemplate other=(RegexTemplate)obj;
		if (this.inputPattern == null)
		{
			if (other.inputPattern != null)
			{
				return false;
			}
		}
		else if (!this.inputPattern.pattern().equals(other.inputPattern.pattern()))
		{
			return false;
		}
		if (this.outputPattern == null)
		{
			if (other.outputPattern != null)
			{
				return false;
			}
		}
		else if (!this.outputPattern.equals(other.outputPattern))
		{
			return false;
		}
		return true;
	}
}
