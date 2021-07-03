package com.samajackun.summer.core.i18n;

import java.util.regex.Pattern;

import com.samajackun.summer.core.path.AbstractToken;

public class Variant extends AbstractToken
{
	private static final Pattern ALPHABET=Pattern.compile("[a-zA-Z0-9]+");

	public Variant(String value)
	{
		super(value, 0, 20, ALPHABET);
	}
}
