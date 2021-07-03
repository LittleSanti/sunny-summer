package com.samajackun.summer.conf.provider;

import java.io.IOException;
import java.util.NoSuchElementException;

public class SourceParser
{
	private final String src;

	private Source fetched;

	private int pos;

	public SourceParser(String src) throws IOException
	{
		super();
		this.src=src;
		fetch();
	}

	public boolean hasNext()
	{
		return this.fetched != null;
	}

	public Source next()
		throws IOException
	{
		if (this.fetched == null)
		{
			throw new NoSuchElementException();
		}
		Source last=this.fetched;
		fetch();
		return last;
	}

	private void fetch()
		throws IOException
	{
		int p=this.src.indexOf(';', this.pos);
		String serial=((p < 0)
			? this.src.substring(this.pos)
			: this.src.substring(this.pos, p)).trim();
		this.fetched=SourceFactory.getInstance().getSource(serial);
		this.pos=1 + p;
	}
}
