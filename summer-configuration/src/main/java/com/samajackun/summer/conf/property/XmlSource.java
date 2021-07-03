package com.samajackun.summer.conf.property;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceConfigurationError;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlSource implements Source
{
	private final InputSource input;

	public XmlSource(InputSource input)
	{
		this.input=input;
	}

	@Override
	public Map<PropertyPath, String> getAll()
	{
		Map<PropertyPath, String> map=new HashMap<PropertyPath, String>(100);
		try
		{
			Document doc=parse(this.input);
			Element root=doc.getDocumentElement();
			browse(root, map, new DefaultPropertyPath());
			return map;
		}
		catch (SAXException | ParserConfigurationException | IOException e)
		{
			throw new ServiceConfigurationError(e.toString(), e);
		}
	}

	private void browse(Element root, Map<PropertyPath, String> map, PropertyPath prefix)
	{
		NodeList list=root.getChildNodes();
		for (int i=0; i < list.getLength(); i++)
		{
			Node node=list.item(i);
			if (node instanceof Element)
			{
				Element element=(Element)node;
				int numberOfChildren=element.getChildNodes().getLength();
				if (numberOfChildren == 1 && element.getFirstChild().getNodeType() == Node.TEXT_NODE)
				{
					map.put(prefix + element.getNodeName(), element.getTextContent());
				}
				else if (numberOfChildren > 0)
				{
					browse(element, map, prefix + element.getNodeName() + ".");
				}
			}
		}
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
