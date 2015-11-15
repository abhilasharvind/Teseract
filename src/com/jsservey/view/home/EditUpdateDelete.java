package com.jsservey.view.home;

import com.jsservey.utils.AppPref;
import com.jsservey.utils.PrefConstant;
import com.jsservey.webservices.URLConstants;

import android.content.Context;

public class EditUpdateDelete implements URLConstants{
	String dbname;
		public EditUpdateDelete(Context context) {
			AppPref appPref = new AppPref(context);
			dbname= appPref.getString(PrefConstant.DB_NAME);
		}
	
	
	public String activateProfileTask(String prf_Id){
		String url=BASE_URL;	
		
		url=url+"SurveyAPI/LoginServlet?profileactivation=true&dbName="+dbname+"&deviceId=123456&profileId="+prf_Id;
		return url;	
		
	}
	public String deleteProfileTask(String prf_Id){
		String url=BASE_URL;	
		
		url=url+"SurveyAPI/LoginServlet?profiledeletion=true&dbName="+dbname+"&deviceId=123456&profileId="+prf_Id;
		return url;
	}
	//Survey........
	public String deleteSurveyTask(String sur_Id){
		String url=BASE_URL;	
		
		url=url+"SurveyAPI/LoginServlet?surveydeletion=true&dbName="+dbname+"&deviceId=123456&surveyId="+sur_Id;
		return url;
	}
	
	
}
