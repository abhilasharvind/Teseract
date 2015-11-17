package com.jsservey.webservices;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import com.jsservey.model.QuestionBPojo;
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
		try {
			url=url+"SurveyAPI/LoginServlet?surveylogin=true&dbName="+dbname +"&userName="+URLEncoder.encode(uname, "UTF-8")+"&pwd="+pwd+"&svPwd=1&deviceId="+deviceId;

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		
		try {
			url=url+"SurveyAPI/LoginServlet?profilecreation=true&dbName="+dbname +"&deviceId="+deviceId+"&profileName="+URLEncoder.encode(uname, "UTF-8")+"&permissionId=1&activateSchedule=1&scheduleTimefrom=02:35:25&scheduleTimeto=03:35:25&scheduleDays=1011010&scheduleUpto=2015-12-20 00:00:00&uId=3";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		url=url+"SurveyAPI/LoginServlet?questionslist=true&dbName="+dbname+"&deviceId=123456&surveyId="+survey_id;
		Log.d("abx", url);
		return url;
	}
	public String createProfile(String deviceId,String profile_name){
		String url=BASE_URL;	
		
		try {
			url=url+"SurveyAPI/LoginServlet?profilecreation=true&dbName="+dbname+"&deviceId="+deviceId+"&profileName="+URLEncoder.encode(profile_name, "UTF-8")+"&permissionId=1&validUptoactive=1&validUpto=2015-12-20%2000:00:00&uId=3&useMultiplesurvey=1";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
	public String createSurvey(String deviceId,String survey_name,String pf_id,String description){
		String url=BASE_URL;	
		
		try {
			url=url+"SurveyAPI/LoginServlet?surveycreation=true&dbName="+dbname+"&deviceId="+deviceId+"&surveyName="+URLEncoder.encode(survey_name, "UTF-8")+"&profileId="+pf_id+"&description="+URLEncoder.encode(description, "UTF-8")+"&activateSchedule=1&scheduleTimefrom=02:35:25&scheduleTimeto=03:35:25&scheduleDays=1011010&thankyouId=2";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
	
	public String createQuestionA(String deviceId,String srv_id,String questionName,String questiontext,String surveyTypeid,String halfRating,String value){
		String url=BASE_URL;	
		//LoginServlet?questioncreation=true&dbName=csfeedback&deviceId=123456&questionName=ithuname&profileSurveyid=1&question=ithu%20queston&isVisisble=1&surveyTypeid=3&halfRating=1&Value=10
		try {
			url=url+"SurveyAPI/LoginServlet?questioncreation=true&dbName="+dbname+"&deviceId=123456&questionName="+URLEncoder.encode(questionName, "UTF-8")+"&profileSurveyid="+srv_id+"&question="+URLEncoder.encode(questiontext, "UTF-8")+"&isVisisble=1&surveyTypeid="+surveyTypeid+"&halfRating="+halfRating+"&Value="+value;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
	public String createQuestionB(String deviceId,QuestionBPojo questionBPojo,String srv_id,String questionName,String questiontext,String surveyTypeid){
		String url=BASE_URL;	
		//LoginServlet?questioncreation=true&dbName=csfeedback&deviceId=123456&questionName=ithuname&profileSurveyid=1&question=ithu%20queston&isVisisble=1&surveyTypeid=3&halfRating=1&Value=10
		try {
			url=url+"SurveyAPI/LoginServlet?questioncreationtext=true&dbName=csfeedback&deviceId=123456&questionName="+URLEncoder.encode(questionName, "UTF-8")+"&profileSurveyid="+srv_id+"&question="+URLEncoder.encode(questiontext, "UTF-8")+"&isVisisble=1&surveyTypeid="+surveyTypeid+"&answer1="+questionBPojo.getOption1()+"&answer2="+questionBPojo.getOption2()+"&answer3="+questionBPojo.getOption3()+"&answer4="+questionBPojo.getOption4()+"&answer5="+questionBPojo.getOption5()+"&answer6="+questionBPojo.getOption6()+"&answer7="+questionBPojo.getOption7()+"&answer8="+questionBPojo.getOption8()+"&answer9="+questionBPojo.getOption9()+"&answer10="+questionBPojo.getOption10();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
	
}
