package com.ksxml.source;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class KSXmlRecordSetResponse extends KSXmlResponse {

	private KSXmlRecordSet _recordSet = null;
	private KSXmlRecord _currentRecord = null;
	
	public KSXmlRecordSetResponse() {
		
	}
	
	// ## get xml record set
	public KSXmlRecordSet getRecordSet() {
		return this._recordSet;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// ## element is available
		this._hasElement = true;
		
		// ## initialize record set
		if (localName.equalsIgnoreCase("ksxmlrecordset")) {
			this._recordSet = new KSXmlRecordSet();
		}
		
		// ## initialize current record
		if (localName.equalsIgnoreCase("ksxmlrecord")) {
			this._currentRecord = new KSXmlRecord();
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
		
		// ## store current record
		if (localName.equalsIgnoreCase("ksxmlrecord")) {
			this._recordSet.addRecord(this._currentRecord);
			this._currentRecord = null;
		}
		
		// ## extract record field value
		if (this._currentRecord != null) {
			this._currentRecord.setValue(localName, this._elementValue);
		}
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
