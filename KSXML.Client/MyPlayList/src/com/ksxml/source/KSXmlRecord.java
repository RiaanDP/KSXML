package com.ksxml.source;

import java.util.HashMap;

public class KSXmlRecord {

	private HashMap<String, Object> _dataMap = new HashMap<String, Object>();
	
	public KSXmlRecord() {
		
	}
	
	// ## get field value
	public void setValue(String field, Object value) {
		this._dataMap.put(field, value);
	}
	
	// ## set field value
	public Object getValue(String field) {
		return this._dataMap.get(field);
	}
}
