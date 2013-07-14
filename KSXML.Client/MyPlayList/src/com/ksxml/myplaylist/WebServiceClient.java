package com.ksxml.myplaylist;

import java.io.IOException;
import java.util.HashMap;
import java.util.ListIterator;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;

import com.ksxml.source.KSXmlRecord;
import com.ksxml.source.KSXmlRecordResponse;
import com.ksxml.source.KSXmlRecordSetResponse;


public class WebServiceClient {

	private static String wsNameSpace = "http://ksxml.org/";

	// ## generate web service URI
	private static String GenerateWebServiceURI() {
		return "http://192.168.1.90/PlayListService/PlayListService.asmx";
	}
	
	public static KSXmlRecord GetTrackInformation(int id) throws Exception {
		try {
			// ## init web service request
			SoapObject request = new SoapObject(wsNameSpace, "GetTrackInformation");
			
			// ## init parameters
			PropertyInfo pi = new PropertyInfo();
			pi.setName("id");
			pi.setValue(id);
			pi.setType(int.class);
			request.addProperty(pi);

			// ## init response envelope
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);
			
			// ## init transport and perform web service call
			HttpTransportSE httpTransport = new HttpTransportSE(GenerateWebServiceURI());
			httpTransport.call(wsNameSpace + "GetTrackInformation", envelope);
			
			// ## extract xml data from response
			String data = envelope.getResponse().toString();

			// ## parse xml response
			KSXmlRecordResponse response = new KSXmlRecordResponse();
			response.Parse(data);
			
			// ## check response code
			if (response.getCode() != 1) {
				throw new Exception("WS returned error response");
			}
			
			return response.getRecord();
		}
		catch (XmlPullParserException e) {
			Log.w("_ERROR", e.getMessage());
			throw new Exception("Caught XmlPullParserException");
		}
		catch (IOException e) {
			Log.w("_ERROR", e.getMessage());
			throw new Exception("Caught IOException");
		}
		catch (Exception e) {
			Log.w("_ERROR", e.getMessage());
			throw new Exception("Caught Exception");
		}
	}

	public static HashMap<String, String> GetAllTrackNames() throws Exception {
		try {
			// ## init web service request
			SoapObject request = new SoapObject(wsNameSpace, "GetAllTrackNames");
			
			// ## init response envelope
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);
			
			// ## init transport and perform web service call
			HttpTransportSE httpTransport = new HttpTransportSE(GenerateWebServiceURI());
			httpTransport.call(wsNameSpace + "GetAllTrackNames", envelope);
			
			// ## extract xml data from response
			String data = envelope.getResponse().toString();
			
			// ## parse xml response
			KSXmlRecordSetResponse response = new KSXmlRecordSetResponse();
			response.Parse(data);
			
			// ## check response code
			if (response.getCode() != 1) {
				throw new Exception("WS returned error response");
			}
			
			HashMap<String, String> mapData = new HashMap<String, String>();
			
			ListIterator<KSXmlRecord> iterator = response.getRecordSet().getRecordList().listIterator();
			while (iterator.hasNext()) {
				KSXmlRecord record = iterator.next();
				mapData.put(record.getValue("id").toString(), record.getValue("Name").toString());
			}
			
			return mapData;
		}
		catch (XmlPullParserException e) {
			Log.w("_ERROR", e.getMessage());
			throw new Exception("Caught XmlPullParserException");
		}
		catch (IOException e) {
			Log.w("_ERROR", e.getMessage());
			throw new Exception("Caught IOException");
		}
		catch (Exception e) {
			Log.w("_ERROR", e.getMessage());
			throw new Exception("Caught Exception");
		}
	}
}
