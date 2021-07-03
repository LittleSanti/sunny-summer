package com.samajackun.summer.conf.provider;

import static org.junit.Assert.fail;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class JndiTest
{
	@Test
	public void test()
		throws IOException
	{
		try
		{
			Context ctx=new InitialContext();
			NamingEnumeration<NameClassPair> enu=ctx.list("");
			while (enu.hasMore())
			{
				NameClassPair p=enu.next();
				System.out.println("p=" + p.getName() + ", classname=" + p.getClassName() + ", value=" + ctx.lookup(p.getName()));
			}
		}
		catch (NamingException e1)
		{
			e1.printStackTrace();
			fail(e1.toString());
		}
	}
}
