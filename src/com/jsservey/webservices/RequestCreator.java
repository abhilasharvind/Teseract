package com.jsservey.webservices;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.content.Context;
import android.util.Log;

import com.jsservey.model.NewUser;
import com.jsservey.model.QuestionBPojo;
import com.jsservey.utils.AppPref;
import com.jsservey.utils.PrefConstant;
import com.jsservey.utils.Utility;

public class RequestCreator implements URLConstants{
	//private Context context;
	String dbname;
	String deviceId;
	private RequestCreator(){}
	public RequestCreator(Context context){
		//this.context=context;
		AppPref appPref = new AppPref(context);
		this.dbname= appPref.getString(PrefConstant.DB_NAME);
		this.deviceId=Utility.getDeviceId(context);
	}
	
	
	
	public String loginRequest(String uname,String pwd,int svpwd){
		String url=BASE_URL;	
		try {
			url=url+"SurveyAPI/LoginServlet?surveylogin=true&dbName="+dbname +"&userName="+URLEncoder.encode(uname, "UTF-8")+"&pwd="+pwd+"&svPwd="+svpwd+"&deviceId="+deviceId;

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
	public String initLoad(){
		String url=BASE_URL;	
		
		url=url+"SurveyAPI/LoginServlet?initconn=true&deviceId="+deviceId;
		return url;
	}
	
	
	public String userLoad(){
		String url=BASE_URL;	
		
		url=url+"SurveyAPI/LoginServlet?userload=true&dbName=csfeedback&deviceId="+deviceId;
		return url;
	}
	
	public String createUser(NewUser user){
		String url=BASE_URL;	
		
		try {
			url=url+"SurveyAPI/LoginServlet?usercreation=true&dbName="+dbname+"&deviceId"+deviceId+"&firstName="+user.getFname()+"&lastName="+user.getLname()+"&emailId="+user.getUsername()+"&Pwd="+user.getPasswrd()+"&userAddress="+URLEncoder.encode(user.getAddress(),"UTF-8")+"&phNo="+user.getPhonenumb()+"&Department="+user.getDepartment()+"&Designation="+user.getDesignation()+"&Gender=male";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
	public String devicereg(String companykey,String device_name){
		String url=BASE_URL;	
		
		url=url+"SurveyAPI/LoginServlet?devicereg=true&deviceId="+deviceId+"&compKey="+companykey+"&device_name"+device_name;
		return url;
	}
	public String logout(){
		String url=BASE_URL;	
		
		url=url+"SurveyAPI/LoginServlet?userlogout=true&dbName=csfeedback&deviceId="+deviceId;
		return url;
	}
	public String profileFetch(){
		String url=BASE_URL;	
		//SurveyAPI/LoginServlet?profilelist=true&dbName=csfeedback&deviceId=123456&uId=4
		url=url+"SurveyAPI/LoginServlet?profilelist=true&deviceId="+deviceId+"&uId=3&dbName="+dbname;
		return url;
	}
	public String surveyFetch(String profile_id){
		String url=BASE_URL;	
		//SurveyAPI/LoginServlet?surveylist=true&dbName=csfeedback&deviceId=123456&profileId=1
		url=url+"SurveyAPI/LoginServlet?surveylist=true&deviceId="+deviceId+"&uId=3&dbName="+dbname+"&profileId="+profile_id;
		return url;
	}
	public String questionFetch(String survey_id){
		String url=BASE_URL;	
		//SurveyAPI/LoginServlet?questionslist=true&dbName=csfeedback&deviceId=123456&surveyId=1
		url=url+"SurveyAPI/LoginServlet?questionslist=true&dbName="+dbname+"&deviceId="+deviceId+"&surveyId="+survey_id;
		Log.d("abx", url);
		return url;
	}
	public String createProfile(String profile_name){
		String url=BASE_URL;	
		
		try {
			url=url+"SurveyAPI/LoginServlet?profilecreation=true&dbName="+dbname+"&deviceId="+deviceId+"&profileName="+URLEncoder.encode(profile_name, "UTF-8")+"&permissionId=1&validUptoactive=1&validUpto=2015-12-20%2000:00:00&uId=3&useMultiplesurvey=1";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
	public String createSurvey(String survey_name,String pf_id,String description){
		String url=BASE_URL;	
		
		try {
			url=url+"SurveyAPI/LoginServlet?surveycreation=true&dbName="+dbname+"&deviceId="+deviceId+"&surveyName="+URLEncoder.encode(survey_name, "UTF-8")+"&profileId="+pf_id+"&description="+URLEncoder.encode(description, "UTF-8")+"&activateSchedule=1&scheduleTimefrom=02:35:25&scheduleTimeto=03:35:25&scheduleDays=1011010&thankyouId=2";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
	
	public String createQuesOther(String srv_id,String questionName,String questiontext,String surveyTypeid,String halfRating,String value){
		String url=BASE_URL;	
		//LoginServlet?questioncreation=true&dbName=csfeedback&deviceId=123456&questionName=ithuname&profileSurveyid=1&question=ithu%20queston&isVisisble=1&surveyTypeid=3&halfRating=1&Value=10
		try {
			url=url+"SurveyAPI/LoginServlet?questioncreation=true&dbName="+dbname+"&deviceId="+deviceId+"&questionName="+URLEncoder.encode(questionName, "UTF-8")+"&profileSurveyid="+srv_id+"&question="+URLEncoder.encode(questiontext, "UTF-8")+"&isVisisble=1&surveyTypeid="+surveyTypeid+"&halfRating="+halfRating+"&Value="+value;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
	public String createQuesText(QuestionBPojo questionBPojo,String srv_id,String questionName,String questiontext,String surveyTypeid){
		String url=BASE_URL;	
		//LoginServlet?questioncreation=true&dbName=csfeedback&deviceId=123456&questionName=ithuname&profileSurveyid=1&question=ithu%20queston&isVisisble=1&surveyTypeid=3&halfRating=1&Value=10
		try {
			url=url+"SurveyAPI/LoginServlet?questioncreationtext=true&dbName="+dbname+"&deviceId="+deviceId+"&questionName="+URLEncoder.encode(questionName, "UTF-8")+"&profileSurveyid="+srv_id+"&question="+URLEncoder.encode(questiontext, "UTF-8")+"&isVisisble=1&surveyTypeid="+surveyTypeid+"&answer1="+questionBPojo.getOption1()+"&answer2="+questionBPojo.getOption2()+"&answer3="+questionBPojo.getOption3()+"&answer4="+questionBPojo.getOption4()+"&answer5="+questionBPojo.getOption5()+"&answer6="+questionBPojo.getOption6()+"&answer7="+questionBPojo.getOption7()+"&answer8="+questionBPojo.getOption8()+"&answer9="+questionBPojo.getOption9()+"&answer10="+questionBPojo.getOption10();
		Log.e("p",">>>"+ url);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
	public String createCustomerInfo( String customerName,String phoneNo,String emailId,String address,String personalDesc){
		String url=BASE_URL;	
		//LoginServlet?questioncreation=true&dbName=csfeedback&deviceId=123456&questionName=ithuname&profileSurveyid=1&question=ithu%20queston&isVisisble=1&surveyTypeid=3&halfRating=1&Value=10
		try {
			url=url+"SurveyAPI/LoginServlet?customercreation=true&dbName="+dbname+"&deviceId="+deviceId+"&customerName="+URLEncoder.encode(customerName, "UTF-8")+"&phoneNo="+URLEncoder.encode(phoneNo, "UTF-8")+"&emailId="+URLEncoder.encode(emailId, "UTF-8")+"&Address="+URLEncoder.encode(address, "UTF-8")+"&personalDesc="+URLEncoder.encode(personalDesc, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}	
}
