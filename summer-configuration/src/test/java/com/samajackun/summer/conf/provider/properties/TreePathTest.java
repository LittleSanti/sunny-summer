package com.samajackun.summer.conf.provider.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.samajackun.summer.conf.provider.properties.WrongFormatException;
import com.samajackun.summer.conf.provider.properties.WrongIndexException;
import com.samajackun.summer.conf.provider.store.Token;
import com.samajackun.summer.conf.provider.store.TreePath;
import com.samajackun.summer.core.path.IdentifierFormatException;

public class TreePathTest
{
	private List<Token> test(String src)
	{
		List<Token> list=new ArrayList<Token>();
		try
		{
			TreePath path=new TreePath(src);
			while (path.hasNext())
			{
				list.add(path.next());
			}
		}
		catch (IdentifierFormatException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
		return list;
	}

	@Test
	public void empty()
	{
		String src="";
		List<Token> tokens=test(src);
		assertEquals(0, tokens.size());
	}

	@Test
	public void oneSteps()
	{
		String src="mes";
		List<Token> tokens=test(src);
		assertEquals(1, tokens.size());
		Token token;
		token=tokens.get(0);
		assertEquals("mes", token.getName());
		assertEquals(-1, token.getIndex());
	}

	@Test
	public void oneStepsWithIndex()
	{
		String src="mes[1]";
		List<Token> tokens=test(src);
		assertEquals(1, tokens.size());
		Token token;
		token=tokens.get(0);
		assertEquals("mes", token.getName());
		assertEquals(1, token.getIndex());
	}

	@Test
	public void oneStepsWithEmptyIndex()
	{
		String src="mes[]";
		List<Token> tokens=test(src);
		assertEquals(1, tokens.size());
		Token token;
		token=tokens.get(0);
		assertEquals("mes", token.getName());
		assertEquals(0, token.getIndex());
	}

	@Test
	public void twoSteps()
	{
		String src="mes.enero";
		List<Token> tokens=test(src);
		assertEquals(2, tokens.size());
		Token token;
		token=tokens.get(0);
		assertEquals("mes", token.getName());
		assertEquals(-1, token.getIndex());
		token=tokens.get(1);
		assertEquals("enero", token.getName());
		assertEquals(-1, token.getIndex());
	}

	@Test
	public void twoStepsWithIndexAtOne()
	{
		String src="mes[2].enero";
		List<Token> tokens=test(src);
		assertEquals(2, tokens.size());
		Token token;
		token=tokens.get(0);
		assertEquals("mes", token.getName());
		assertEquals(2, token.getIndex());
		token=tokens.get(1);
		assertEquals("enero", token.getName());
		assertEquals(-1, token.getIndex());
	}

	@Test
	public void twoStepsWithIndexAtTwo()
	{
		String src="mes.enero[5]";
		List<Token> tokens=test(src);
		assertEquals(2, tokens.size());
		Token token;
		token=tokens.get(0);
		assertEquals("mes", token.getName());
		assertEquals(-1, token.getIndex());
		token=tokens.get(1);
		assertEquals("enero", token.getName());
		assertEquals(5, token.getIndex());
	}

	@Test
	public void threeStepsWithIndexes()
	{
		String src="mes.enero[5].semana[].dia";
		List<Token> tokens=test(src);
		assertEquals(4, tokens.size());
		Token token;

		token=tokens.get(0);
		assertEquals("mes", token.getName());
		assertEquals(-1, token.getIndex());

		token=tokens.get(1);
		assertEquals("enero", token.getName());
		assertEquals(5, token.getIndex());

		token=tokens.get(2);
		assertEquals("semana", token.getName());
		assertEquals(0, token.getIndex());

		token=tokens.get(3);
		assertEquals("dia", token.getName());
		assertEquals(-1, token.getIndex());
	}

	@Test
	public void wrongIndexFormat()
	{
		String src="mes[a]";
		try
		{
			new TreePath(src);
		}
		catch (WrongIndexException e)
		{
			assertEquals("a", e.getWrongIndex());
		}
		catch (IdentifierFormatException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void wrongNameFormat()
	{
		String src="m$s";
		try
		{
			new TreePath(src);
		}
		catch (WrongFormatException e)
		{
			assertEquals(src, e.getWrongName());
		}
		catch (IdentifierFormatException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void wrongNameFormatFromBeginning()
	{
		String src="$";
		try
		{
			new TreePath(src);
		}
		catch (WrongFormatException e)
		{
			assertEquals(src, e.getWrongName());
		}
		catch (IdentifierFormatException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void wrongIndexBoundaries()
	{
		String src="enero[20";
		try
		{
			new TreePath(src);
		}
		catch (WrongFormatException e)
		{
			assertEquals(src, e.getWrongName());
		}
		catch (IdentifierFormatException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}
}
