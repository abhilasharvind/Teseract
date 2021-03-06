package com.jsservey.webservices;

import android.content.Context;
import android.util.Log;

import com.jsservey.utils.AppPref;
import com.jsservey.utils.PrefConstant;
import com.jsservey.utils.Utility;

public class SurveyRequestCreator implements URLConstants{
	//private Context context;
	String dbname;
	String deviceId;
	private SurveyRequestCreator(){}
	public SurveyRequestCreator(Context context){
		//this.context=context;
		AppPref appPref = new AppPref(context);
		this.dbname= appPref.getString(PrefConstant.DB_NAME);
		this.deviceId=Utility.getDeviceId(context);
	}
	public String surveyQuesFetch(String pf_id){
		String url=BASE_URL;	
		//http://106.51.126.62:8080/SurveyAPI/LoginServlet?questionanswers=true&dbName=csfeedback&deviceId=123456&surveyId=1&profileId=1
		url=url+"SurveyAPI/LoginServlet?questionanswers=true&dbName="+dbname+"&deviceId="+deviceId+"&surveyId=1&profileId="+pf_id;
		Log.d("abx", url);
		return url;
	}
	
	
}
