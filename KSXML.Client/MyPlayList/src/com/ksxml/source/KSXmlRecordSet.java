package com.ksxml.source;

import java.util.ArrayList;
import java.util.List;

public class KSXmlRecordSet {

	private List<KSXmlRecord> _recordList = new ArrayList<KSXmlRecord>();
	
	public KSXmlRecordSet() {
		
	}
	
	public void addRecord(KSXmlRecord xmlRecord) {
		this._recordList.add(xmlRecord);
	}
	
	public List<KSXmlRecord> getRecordList() {
		return this._recordList;
	}
}
