package com.samajackun.summer.conf.property;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class LocalXmlPropertiesProvider extends XmlPropertiesProvider
{
	public LocalXmlPropertiesProvider() throws IOException, SAXException, ParserConfigurationException
	{
		super(createSources());
	}

	private static XmlPropertiesSource[] createSources()
		throws IOException,
		SAXException,
		ParserConfigurationException
	{
		String paths=System.getProperty("com.samajackun.summer.conf.property.LocalXmlPropertiesProvider.files");
		StringTokenizer stk=new StringTokenizer(paths, ";");
		int n=stk.countTokens();
		List<XmlPropertiesSource> sources=new ArrayList<XmlPropertiesSource>(n);
		while (stk.hasMoreTokens())
		{
			File file=new File(stk.nextToken());
			Document document=parse(new InputSource(new FileInputStream(file)));
			sources.add(new XmlPropertiesSource(file, document));
		}
		return sources.toArray(new XmlPropertiesSource[sources.size()]);
	}

	/**
	 * Parse an InputSource as a XML document.
	 * 
	 * @param input InputSource.
	 * @return DOM Document.
	 * @exception org.xml.sax.SAXException If an error occured while parsing.
	 * @exception javax.xml.parsers.ParserConfigurationException If the parser could not be instantiated.
	 * @exception java.io.IOException If an error occured while reading.
	 */
	private static org.w3c.dom.Document parse(org.xml.sax.InputSource input)
		throws org.xml.sax.SAXException,
		javax.xml.parsers.ParserConfigurationException,
		java.io.IOException
	{
		javax.xml.parsers.DocumentBuilderFactory FACTORY=javax.xml.parsers.DocumentBuilderFactory.newInstance();
		javax.xml.parsers.DocumentBuilder docBuilder=FACTORY.newDocumentBuilder();
		org.w3c.dom.Document doc=docBuilder.parse(input);
		return doc;
	}

}
