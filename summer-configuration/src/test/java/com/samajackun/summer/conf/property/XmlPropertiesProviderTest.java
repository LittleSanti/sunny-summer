package com.samajackun.summer.conf.property;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ServiceConfigurationError;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XmlPropertiesProviderTest extends AbstractPropertiesProviderTest
{
	@Override
	protected PropertiesProvider createProvider()
	{
		Document document1=parse("<root><month>january</month><year>2000</year><birthday>2000-02-01</birthday><system><planet>venus</planet></system></root>");
		XmlPropertiesSource source1=new XmlPropertiesSource(new File("file1"), document1);

		Document document2=parse("<root><music><album>Revolver</album><song>Eleanor Rigby</song></music><poem>The Raven</poem></root>");
		XmlPropertiesSource source2=new XmlPropertiesSource(new File("file2"), document2);

		XmlPropertiesProvider provider=new XmlPropertiesProvider(source1, source2);
		return provider;
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
	private static org.w3c.dom.Document parse(String xml)
	{
		org.xml.sax.InputSource input=new org.xml.sax.InputSource(new StringReader(xml));
		javax.xml.parsers.DocumentBuilderFactory FACTORY=javax.xml.parsers.DocumentBuilderFactory.newInstance();
		javax.xml.parsers.DocumentBuilder docBuilder;
		try
		{
			docBuilder=FACTORY.newDocumentBuilder();
			org.w3c.dom.Document doc=docBuilder.parse(input);
			return doc;
		}
		catch (ParserConfigurationException e)
		{
			throw new ServiceConfigurationError(e.toString(), e);
		}
		catch (SAXException e)
		{
			throw new IllegalArgumentException(e);
		}
		catch (IOException e)
		{
			// Absurdo
			throw new ServiceConfigurationError(e.toString(), e);
		}
	}
}
