package com.samajackun.summer.conf.provider.store;

import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.samajackun.summer.conf.provider.properties.WrongFormatException;
import com.samajackun.summer.conf.provider.properties.WrongIndexException;
import com.samajackun.summer.core.path.Identifier;
import com.samajackun.summer.core.path.IdentifierFormatException;

public class TreePath
{
	private final String src;

	private Token fetched;

	private int currentPos;

	public TreePath(String src) throws IdentifierFormatException
	{
		super();
		this.src=src;
		fetch();
	}

	public boolean hasNext()
	{
		return this.fetched != null;
	}

	public Token next()
		throws IdentifierFormatException
	{
		if (this.fetched == null)
		{
			throw new NoSuchElementException();
		}
		Token next=this.fetched;
		fetch();
		return next;
	}

	private void fetch()
		throws IdentifierFormatException
	{
		if (this.currentPos == this.src.length())
		{
			this.fetched=null;
		}
		else
		{
			int p=this.src.indexOf('.', this.currentPos);
			String s;
			if (p >= 0)
			{
				s=this.src.substring(this.currentPos, p);
				this.currentPos=1 + p;
			}
			else
			{
				s=this.src.substring(this.currentPos);
				this.currentPos=this.src.length();
			}
			Pattern pattern=Pattern.compile("([a-z0-9_\\-]+)(\\[.*\\])?", Pattern.CASE_INSENSITIVE);
			Matcher matcher=pattern.matcher(s);
			if (matcher.find())
			{
				if (matcher.end() != s.length())
				{
					throw new WrongFormatException(s);
				}
				String name=matcher.group(1);
				String index=matcher.group(2);
				int intIndex;
				if (index == null)
				{
					intIndex=-1;
				}
				else
				{
					if (index.length() >= 2)
					{
						index=index.substring(1, index.length() - 1);
					}
					if (index.isEmpty())
					{
						intIndex=0;
					}
					else
					{
						try
						{
							intIndex=Integer.parseInt(index);
						}
						catch (NumberFormatException e)
						{
							throw new WrongIndexException(index);
						}
					}
				}
				this.fetched=new Token(new Identifier(name), intIndex);
			}
			else
			{
				throw new WrongFormatException(s);
			}
		}
	}

	@Override
	public String toString()
	{
		return "TreePath [src=" + this.src + "]";
	}

}
