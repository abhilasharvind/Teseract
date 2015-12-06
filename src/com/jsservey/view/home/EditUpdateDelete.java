package com.jsservey.view.home;

import android.content.Context;
import android.util.Log;

import com.jsservey.utils.AppPref;
import com.jsservey.utils.PrefConstant;
import com.jsservey.utils.Utility;
import com.jsservey.webservices.URLConstants;

public class EditUpdateDelete implements URLConstants{
	String dbname;
	String deviceId;
		public EditUpdateDelete(Context context) {
			AppPref appPref = new AppPref(context);
			dbname= appPref.getString(PrefConstant.DB_NAME);
			this.deviceId=Utility.getDeviceId(context);
		}
	
	
	public String activateProfileTask(String prf_Id){
		String url=BASE_URL;	
		
		url=url+"SurveyAPI/LoginServlet?profileactivation=true&dbName="+dbname+"&deviceId="+deviceId+"&profileId="+prf_Id;
		return url;	
		
	}
	public String deleteProfileTask(String prf_Id){
		String url=BASE_URL;	
		
		url=url+"SurveyAPI/LoginServlet?profiledeletion=true&dbName="+dbname+"&deviceId="+deviceId+"&profileId="+prf_Id;
		return url;
	}
	//Survey........
	public String surveyActivate(String sr_id){
		String url=BASE_URL;	
		//http://106.51.126.62:8080/SurveyAPI/LoginServlet?questionanswers=true&dbName=csfeedback&deviceId=123456&surveyId=1&profileId=1
		url=url+"SurveyAPI/LoginServlet?surveyactivation=true&dbName="+dbname+"&deviceId="+deviceId+"&surveyId="+sr_id;
		Log.d("abx", url);
		return url;
	}
	public String surveyDelete(String sr_id){
		String url=BASE_URL;	
		//http://106.51.126.62:8080/SurveyAPI/LoginServlet?questionanswers=true&dbName=csfeedback&deviceId=123456&surveyId=1&profileId=1
		url=url+"SurveyAPI/LoginServlet?surveydeletion=true&dbName="+dbname+"&deviceId="+deviceId+"&surveyId="+sr_id;
		Log.d("abx", url);
		return url;
	}
	//Question
	public String questionVisibility(String qu_id,int visibilityFlag){
		String url=BASE_URL;	
		//http://106.51.126.62:8080/SurveyAPI/LoginServlet?questionanswers=true&dbName=csfeedback&deviceId=123456&surveyId=1&profileId=1
		url=url+"SurveyAPI/LoginServlet?questionvisible=true&dbName="+dbname+"&deviceId="+deviceId+"&questionId="+qu_id+"&visibleQuestion="+visibilityFlag;
		Log.d("abx", url);
		return url;
	}
	public String questionDelete(String qu_id){
		String url=BASE_URL;	
		//http://106.51.126.62:8080/SurveyAPI/LoginServlet?questionanswers=true&dbName=csfeedback&deviceId=123456&surveyId=1&profileId=1
		url=url+"SurveyAPI/LoginServlet?questiondeletion=true&dbName="+dbname+"&deviceId="+deviceId+"&questionId="+qu_id;
		Log.d("abx", url);
		return url;
	}
	
}
