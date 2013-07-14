package com.ksxml.myplaylist;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ksxml.source.KSXmlRecord;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onGetTrackInformation(View view) {
		try {
			new GetTrackInformationAsyncTask().execute(1);
		}
		catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
	
	public void onGetAllTrackNames(View view) {
		try {
			new GetAllTrackNamesAsyncTask().execute();
		}
		catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
	
	private class GetTrackInformationAsyncTask extends AsyncTask<Integer, Integer, KSXmlRecord> {
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		
		@Override
		protected KSXmlRecord doInBackground(Integer... params) {
			try {
				return WebServiceClient.GetTrackInformation(params[0]);
			}
			catch (Exception ex) {
				return null;
			}
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
		}
		
		protected void onPostExecute(KSXmlRecord result) {
			super.onPostExecute(result);
			
			TextView tv_output = ((TextView)findViewById(R.id.tv_output));
			tv_output.setText("");
			tv_output.append("id: ");
			tv_output.append((String)result.getValue("id"));
			tv_output.append(" Name: ");
			tv_output.append((String)result.getValue("Name"));
			tv_output.append(" Album: ");
			tv_output.append((String)result.getValue("Album"));
			tv_output.append(" Artist: ");
			tv_output.append((String)result.getValue("Artist"));
			tv_output.append(" Length: ");
			tv_output.append((String)result.getValue("Length"));
		}
	}
	
	private class GetAllTrackNamesAsyncTask extends AsyncTask<Void, Integer, HashMap<String,String>> {
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		
		@Override
		protected HashMap<String,String> doInBackground(Void... params) {
			try {
				return WebServiceClient.GetAllTrackNames();
			}
			catch (Exception ex) {
				return null;
			}
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
		}
		
		protected void onPostExecute(HashMap<String,String> result) {
			super.onPostExecute(result);
			
			TextView tv_output = ((TextView)findViewById(R.id.tv_output));
			tv_output.setText("");
			
			for (Map.Entry<String, String> mapEntry : result.entrySet()) {
				tv_output.append("id: ");
				tv_output.append(mapEntry.getKey());
				tv_output.append("Name: ");
				tv_output.append(mapEntry.getValue());
			}
		}
	}
}
