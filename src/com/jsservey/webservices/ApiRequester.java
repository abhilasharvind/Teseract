package com.jsservey.webservices;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class ApiRequester extends AsyncTask<String, Void, JSONObject> implements URLConstants {

	//private Context context;
	ApiRequestListner apiRequestListner;
	private boolean timeOut = false;
	private String url;
	

	public ApiRequester(Context context,String url,ApiRequestListner apiRequestListner) {

		//this.context = context;
		this.apiRequestListner=apiRequestListner;
		this.url=url;
		
	}
	

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		apiRequestListner.onStarted();

	}

	@Override
	protected JSONObject doInBackground(String... params) {

		//String auth = null;

		final int TIMEOUT_MILLISEC = 30000;

		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, TIMEOUT_MILLISEC);
		HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MILLISEC);
		HttpClient httpclient = new DefaultHttpClient(httpParams);

		
		

		Log.e("abx", "url"+url);

		

		HttpGet httppost = new HttpGet(url);
		JSONObject inputJson = null;

		try {
			

			HttpResponse response = httpclient.execute(httppost);

			Log.e("abx", ":resp:" + response);

			HttpEntity entity = response.getEntity();

			if (entity != null) {

				Log.e("abx", "::" + entity);
				InputStream instream = entity.getContent();

				String result = RestClient.convertStreamToString(instream);

				Log.e("abx", result);

				try {
					inputJson = new JSONObject(result);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				Log.e("abx", "::" + inputJson);
			} else
				Log.e("Entity", "null");

			return inputJson;

		} catch (ClientProtocolException e) {
			Log.e("abx", e.toString());
			return null;
		} catch (IOException e) {
			Log.e("abx", e.toString());
			return null;
		} catch (Throwable t) {

			if (t instanceof org.apache.http.conn.ConnectTimeoutException)
				timeOut = true;

			Log.e("abx", t.getMessage());
			return null;
		}
	}

	@Override
	protected void onPostExecute(JSONObject result) {
		super.onPostExecute(result);

		if(result!=null){
			Log.e("abx", "responce="+result.toString());
			apiRequestListner.onSuccess(result);
		}else{
			apiRequestListner.onFailed();
		}

		
	}
}
