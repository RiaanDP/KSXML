package com.ksxml.source;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class KSXmlRecordResponse extends KSXmlResponse {

	private KSXmlRecord _record = null;
	
	public KSXmlRecordResponse() {
		
	}
	
	// ## get xml record
	public KSXmlRecord getRecord() {
		return this._record;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// ## element is available
		this._hasElement = true;
		
		// ## initialize record
		if (localName.equalsIgnoreCase("ksxmlrecord")) {
			this._record = new KSXmlRecord();
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// ## element is no longer available
		this._hasElement = false;
		
		// ## extract response code
		if (localName.equalsIgnoreCase("code")) {
			this._code = Integer.parseInt(this._elementValue);
			this._elementValue = "";
		}
		
		// ## extract response message
		if (localName.equalsIgnoreCase("message")) {
			this._message = this._elementValue;
			this._elementValue = "";
		}
		
		// ## extract record field value
		if (this._record != null)
			this._record.setValue(localName, this._elementValue);
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// ## extract element value
		if (this._hasElement) {
			this._elementValue = new String(ch, start, length);
			this._hasElement = false;
		}
	}
}
