package com.samajackun.summer.conf.property;

import java.util.HashMap;
import java.util.Map;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.samajackun.summer.core.Identifier;

public class XmlPropertiesProvider extends AbstractPropertiesProvider<Document>
{
	private final XPathFactory xpathFactory=XPathFactory.newInstance();

	public XmlPropertiesProvider(XmlPropertiesSource... sources)
	{
		super(sources);
	}

	@Override
	public String getProperty(PropertyPath path)
		throws PropertiesPathNotFoundException
	{
		String value=null;
		XPath xpath=this.xpathFactory.newXPath();
		String xpathExpr="/*/" + path.serialized().replace('.', '/');
		for (AbstractPropertiesSource<Document> source : getSources())
		{
			try
			{
				Element element=(Element)xpath.evaluate(xpathExpr, source.getProperties(), XPathConstants.NODE);
				if (element != null)
				{
					value=element.getTextContent();
					break;
				}
			}
			catch (XPathExpressionException e)
			{
				// Debería quedar estructuralmente evitada en el constructor de DefaultPath.
				throw new PropertiesPathNotFoundException(path);
			}
		}
		if (value == null)
		{
			throw new PropertiesPathNotFoundInFilesException(path, getSources());
		}
		return value;
	}

	private Map<PropertyPath, String> toMap(PropertyPath base, NodeList list)
	{
		Map<PropertyPath, String> map=new HashMap<PropertyPath, String>();
		int n=list.getLength();
		for (int i=0; i < n; i++)
		{
			Element element=(Element)list.item(i);
			Identifier id=new Identifier(element.getNodeName());
			PropertyPath propertyKey=base.createBranch(id);
			String value=element.getTextContent();
			map.put(propertyKey, value);
		}
		return map;
	}

	@Override
	public Map<PropertyPath, String> getPropertiesMap(PropertyPath path)
		throws PropertiesPathNotFoundException
	{
		Map<PropertyPath, String> map=null;
		XPath xpath=this.xpathFactory.newXPath();
		NodeList list;
		String xpathExpr="/*/" + path.serialized().replace('.', '/') + "/*";
		for (AbstractPropertiesSource<Document> source : getSources())
		{
			try
			{
				list=(NodeList)xpath.evaluate(xpathExpr, source.getProperties(), XPathConstants.NODESET);
				if (list.getLength() > 0)
				{
					map=toMap(path, list);
					break;
				}
			}
			catch (XPathExpressionException e)
			{
				// Debería quedar estructuralmente evitada en el constructor de DefaultPath.
				throw new PropertiesPathNotFoundException(path);
			}
		}
		if (map == null)
		{
			throw new PropertiesPathNotFoundInFilesException(path, getSources());
		}
		return map;
	}

	@Override
	public Map<PropertyPath, String> getAllPropertiesMap()

	{
		Map<PropertyPath, String> map=new HashMap<PropertyPath, String>();
		NodeList list;
		PropertyPath path=new DefaultPropertyPath();
		for (AbstractPropertiesSource<Document> source : getSources())
		{
			list=source.getProperties().getDocumentElement().getChildNodes();
			browse(path, list, map);
		}
		return map;
	}

	private void browse(PropertyPath base, NodeList list, Map<PropertyPath, String> map)
	{
		int n=list.getLength();
		for (int i=0; i < n; i++)
		{
			Node item=list.item(i);
			if (item instanceof Element)
			{
				Element element=(Element)item;
				Identifier id=new Identifier(element.getNodeName());
				PropertyPath propertyKey=base.createBranch(id);
				if (element.getChildNodes().getLength() == 1 && element.getFirstChild().getNodeType() == Node.TEXT_NODE)
				{
					String value=element.getTextContent();
					map.put(propertyKey, value);
				}
				else
				{
					browse(propertyKey, element.getChildNodes(), map);
				}
			}
		}
	}
}
