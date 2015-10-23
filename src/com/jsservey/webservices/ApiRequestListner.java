package com.jsservey.webservices;

import org.json.JSONObject;

public interface ApiRequestListner {
	
	public String onSuccess(JSONObject result);
	public String onFailed();
	public String onStarted();

}
