package com.samajackun.summer.conf.provider.properties;

import static com.samajackun.summer.conf.provider.properties.UnicodeUtils.decode;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.samajackun.summer.conf.provider.properties.IncompleteEscapeSequenceException;
import com.samajackun.summer.conf.provider.properties.IncompleteUnicodeSequenceException;
import com.samajackun.summer.conf.provider.properties.InvalidUnicodeDigitException;
import com.samajackun.summer.conf.provider.properties.UnicodeFormatException;

public class UnicodeUtilsTest
{
	@Test
	public void decodeEmpty()
	{
		try
		{
			String s="";
			assertEquals(s, decode(s));
		}
		catch (UnicodeFormatException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void decodeOneUncodedChar()
	{
		try
		{
			String s="a";
			assertEquals(s, decode(s));
		}
		catch (UnicodeFormatException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void decodeSomeUncodedChars()
	{
		try
		{
			String s="enero";
			assertEquals(s, decode(s));
		}
		catch (UnicodeFormatException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void decodeOneCodedChar()
	{
		try
		{
			String s="\\u0020";
			assertEquals(" ", decode(s));
		}
		catch (UnicodeFormatException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void decodeOneWrongCodedChar()
	{
		try
		{
			String s="\\u00g0";
			decode(s);
			fail("Expected InvalidUnicodeDigitException");
		}
		catch (InvalidUnicodeDigitException e)
		{
			assertEquals('g', e.getDigit());
		}
		catch (UnicodeFormatException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void decodeSomeCodedChars()
	{
		try
		{
			String s="\\u0065\\u006E\\u0065\\u0072\\u006F";
			assertEquals("enero", decode(s));
		}
		catch (UnicodeFormatException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void decodeMixedCodedAndUncodedChars()
	{
		try
		{
			String s="mes de \\u0065\\u006E\\u0065\\u0072\\u006F es el \\u0070rimero";
			assertEquals("mes de enero es el primero", decode(s));
		}
		catch (UnicodeFormatException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void decodeSomeBackslashSequences()
	{
		try
		{
			String s="enero\\nfebrero\\rmarzo\\tabril\\bmayo\\xjunio";
			assertEquals("enero\nfebrero\rmarzo\tabril\bmayoxjunio", decode(s));
		}
		catch (UnicodeFormatException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void decodeIncompleteBackslashSequence()
	{
		String s="a\\";
		try
		{
			decode(s);
			fail("Expected IncompleteEscapeSequenceException");
		}
		catch (IncompleteEscapeSequenceException e)
		{
			assertEquals(s, e.getSource());
		}
		catch (UnicodeFormatException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void decodeEscappedBackslash()
	{
		try
		{
			String s="a\\\\b";
			assertEquals("a\\b", decode(s));
		}
		catch (UnicodeFormatException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void decodeIncompleteUnicodeSequence()
	{
		try
		{
			String s="enero\\u000";
			decode(s);
			fail("Expected IncompleteUnicodeSequenceException");
		}
		catch (IncompleteUnicodeSequenceException e)
		{
			assertEquals("000", e.getUnicodeSequence());
		}
		catch (UnicodeFormatException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

}
