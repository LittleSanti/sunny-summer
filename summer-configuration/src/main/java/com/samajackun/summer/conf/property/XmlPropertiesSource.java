package com.samajackun.summer.conf.property;

import java.io.File;

import org.w3c.dom.Document;

public class XmlPropertiesSource extends AbstractPropertiesSource<Document>
{
	public XmlPropertiesSource(File file, Document document)
	{
		super(file, document);
	}
}
