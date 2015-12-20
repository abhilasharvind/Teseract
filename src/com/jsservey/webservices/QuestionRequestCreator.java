  package com.jsservey.webservices;

import android.content.Context;
import android.util.Log;

import com.jsservey.model.SurveySubmitData;
import com.jsservey.utils.AppPref;
import com.jsservey.utils.PrefConstant;
import com.jsservey.utils.Utility;

public class QuestionRequestCreator implements URLConstants{
	String dbname;
	String deviceId;
	private QuestionRequestCreator(){}
	public QuestionRequestCreator(Context context){
		AppPref appPref = new AppPref(context);
		this.dbname= appPref.getString(PrefConstant.DB_NAME);
		this.deviceId=Utility.getDeviceId(context);
	}
	public String questionActivate(String questionId){
		String url=BASE_URL;	
		url=url+"SurveyAPI/LoginServlet?questionvisible=true&dbName="+dbname+"&deviceId="+deviceId+"&questionId="+questionId+"&visibleQuestion=1";
		Log.d("abx", url);
		return url;
	}
	public String surveyData(SurveySubmitData surveySubmitData,String customerinfoId){
		String url=BASE_URL;	
		url=url+"SurveyAPI/LoginServlet?detailcreation=true&dbName="+dbname+"&deviceId="+deviceId+"&surveyanswerId="+surveySubmitData.getAns_id()+"&surveyquestionId="+surveySubmitData.getQues_id()+"&profilesurveyId=6&userprofileId=4&userId=3&customerinfoId="+customerinfoId+"&billinfoId=1&deviceinfoId=1&answerValue="+surveySubmitData.getAns_value();
		Log.d("abx",">>"+ url);
		return url;
	}	
	

			
	
	
}
