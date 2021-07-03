package com.samajackun.summer.conf.provider.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.InputSource;

import com.samajackun.summer.conf.provider.AbstractProviderTest;
import com.samajackun.summer.conf.provider.Provider;
import com.samajackun.summer.conf.provider.xml.XmlProvider;

public class XmlProviderTest extends AbstractProviderTest
{
	@Override
	protected Provider createProvider()
		throws IOException
	{
		InputStream input=new FileInputStream(new File(this.dir, "music.xml"));
		return new XmlProvider(new InputSource(input));
	}
}
