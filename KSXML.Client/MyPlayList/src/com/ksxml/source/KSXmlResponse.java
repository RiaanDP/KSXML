package com.ksxml.source;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class KSXmlResponse extends DefaultHandler {
	
	protected int _code;
	protected String _message = "";
	protected String _elementValue = null;
	protected boolean _hasElement = false;
	
	public KSXmlResponse() {
		
	}
	
	// ## get response code
	public int getCode() {
		return this._code;
	}
	
	// ## get response message
	public String getMessage() {
		return this._message;
	}
	
	// ## parse serialized response object
	public void Parse(String xml) throws Exception {
		try
		{
			// ## get xml parser factory
			SAXParserFactory factory = SAXParserFactory.newInstance();
			
			// ## init xml parser
			SAXParser parser = factory.newSAXParser();
			
			// ## init xml reader from parser
			XMLReader reader = parser.getXMLReader();
			
			// ## parse xml data
			reader.setContentHandler(this);
			reader.parse(new InputSource(new ByteArrayInputStream(xml.getBytes())));
		}
		catch (SAXException e) {
			Log.w("_ERROR", e.getMessage());
			throw new Exception("Caught SAXException");
		}
		catch (ParserConfigurationException e) {
			Log.w("_ERROR", e.getMessage());
			throw new Exception("Caught ParserConfigurationException");
		}
		catch (IOException e) {
			Log.w("_ERROR", e.getMessage());
			throw new Exception("Caught IOException");
		}
	}
}
