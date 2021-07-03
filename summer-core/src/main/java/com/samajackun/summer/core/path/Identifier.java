package com.samajackun.summer.core.path;

import java.util.regex.Pattern;

/**
 * An identifier is a subclass of Token with these restrictions:
 * <ul>
 * <li>It is not empty.
 * <li>Does not contain more than 1024 characters.
 * <li>The first character is always a letter.
 * <li>The rest of characters must be any combination of letters, digits, and symbols underline (_), hyphen (-), period (.), slash (/), open bracket ([) and closed bracket (]).
 * </ul>
 * 
 * @author Santi
 * @version 2014.00
 */
public class Identifier extends AbstractToken
{
	public static final int MIN_LENGTH=1;

	public static final int MAX_LENGTH=1024;

	private static final Pattern ALPHABET=Pattern.compile("[a-zA-Z0-9/_\\-\\.\\[\\]áéíóúàèìòùâêîôûäëïöüñçÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÄËÏÖÜÑÇ]+");

	public Identifier(char[] value) throws TokenException
	{
		super(value, MIN_LENGTH, MAX_LENGTH, ALPHABET);
	}

	public Identifier(String value) throws TokenException
	{
		super(value, MIN_LENGTH, MAX_LENGTH, ALPHABET);
	}
}
