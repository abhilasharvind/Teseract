  package com.jsservey.view.home.survey.questions;

import com.jsservey.utils.AppPref;
import com.jsservey.utils.PrefConstant;
import com.jsservey.utils.Utility;
import com.jsservey.webservices.URLConstants;

import android.content.Context;
import android.util.Log;

public class QuestionRequestCreator implements URLConstants{
	private Context context;
	String dbname;
	String deviceId;
	private QuestionRequestCreator(){}
	public QuestionRequestCreator(Context context){
		this.context=context;
		AppPref appPref = new AppPref(context);
		this.dbname= appPref.getString(PrefConstant.DB_NAME);
		this.deviceId=Utility.getDeviceId(context);
	}
	public String questionActivate(String questionId){
		String url=BASE_URL;	
		//http://106.51.126.62:8080/SurveyAPI/LoginServlet?questionanswers=true&dbName=csfeedback&deviceId=123456&surveyId=1&profileId=1
		url=url+"SurveyAPI/LoginServlet?questionvisible=true&dbName="+dbname+"&deviceId="+deviceId+"&questionId="+questionId+"&visibleQuestion=1";
		Log.d("abx", url);
		return url;
	}
}
