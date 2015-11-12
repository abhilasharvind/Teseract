package com.jsservey.webservices;

import com.jsservey.utils.AppPref;
import com.jsservey.utils.PrefConstant;

import android.content.Context;
import android.util.Log;

public class RequestCreator implements URLConstants{
	private Context context;
	String dbname;
	private RequestCreator(){}
	public RequestCreator(Context context){
		this.context=context;
		AppPref appPref = new AppPref(context);
		this.dbname= appPref.getString(PrefConstant.DB_NAME);
	}
	
	
	
	public String loginRequest(String uname,String pwd,String deviceId){
		String url=BASE_URL;	
		
		url=url+"SurveyAPI/LoginServlet?surveylogin=true&dbName="+dbname +"&userName="+uname+"&pwd="+pwd+"&svPwd=1&deviceId="+deviceId;
		return url;
	}
	public String initLoad(String deviceId){
		String url=BASE_URL;	
		
		url=url+"SurveyAPI/LoginServlet?initconn=true&deviceId=12345645";
		return url;
	}
	
	
	public String userLoad(String deviceId){
		String url=BASE_URL;	
		
		url=url+"SurveyAPI/LoginServlet?userload=true&dbName=csfeedback&deviceId=12345645";
		return url;
	}
	
	public String createUser(String uname,String deviceId){
		String url=BASE_URL;	
		
		url=url+"SurveyAPI/LoginServlet?profilecreation=true&dbName="+dbname +"&deviceId="+deviceId+"&profileName="+uname+"&permissionId=1&activateSchedule=1&scheduleTimefrom=02:35:25&scheduleTimeto=03:35:25&scheduleDays=1011010&scheduleUpto=2015-12-20 00:00:00&uId=3";
		return url;
	}
	public String devicereg(String companykey,String deviceId){
		String url=BASE_URL;	
		
		url=url+"SurveyAPI/LoginServlet?devicereg=true&deviceId="+deviceId+"&compKey="+companykey;
		return url;
	}
	
	public String profileFetch(String deviceId){
		String url=BASE_URL;	
		//SurveyAPI/LoginServlet?profilelist=true&dbName=csfeedback&deviceId=123456&uId=4
		url=url+"SurveyAPI/LoginServlet?profilelist=true&deviceId="+deviceId+"&uId=3&dbName="+dbname;
		return url;
	}
	public String surveyFetch(String deviceId,String profile_id){
		String url=BASE_URL;	
		//SurveyAPI/LoginServlet?surveylist=true&dbName=csfeedback&deviceId=123456&profileId=1
		url=url+"SurveyAPI/LoginServlet?surveylist=true&deviceId="+deviceId+"&uId=3&dbName="+dbname+"&profileId="+profile_id;
		return url;
	}
	public String questionFetch(String deviceId,String survey_id){
		String url=BASE_URL;	
		//SurveyAPI/LoginServlet?questionslist=true&dbName=csfeedback&deviceId=123456&surveyId=1
		url=url+"SurveyAPI/LoginServlet?surveylist=true&deviceId="+deviceId+"&uId=3&dbName="+dbname+"&surveyId="+survey_id;
		Log.d("abx", url);
		return url;
	}
	public String createProfile(String deviceId,String profile_name){
		String url=BASE_URL;	
		
		url=url+"SurveyAPI/LoginServlet?profilecreation=true&dbName="+dbname+"&deviceId="+deviceId+"&profileName="+profile_name+"&permissionId=1&validUptoactive=1&validUpto=2015-12-20%2000:00:00&uId=3&useMultiplesurvey=1";
		return url;
	}
	public String createSurvey(String deviceId,String survey_name,String pf_id,String description){
		String url=BASE_URL;	
		
		url=url+"SurveyAPI/LoginServlet?surveycreation=true&dbName="+dbname+"&deviceId="+deviceId+"&surveyName="+survey_name+"&profileId="+pf_id+"&description="+description+"&activateSchedule=1&scheduleTimefrom=02:35:25&scheduleTimeto=03:35:25&scheduleDays=1011010&thankyouId=2";
		return url;
	}
	
	
}
