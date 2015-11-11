package com.jsservey.webservices;

public class RequestCreator implements URLConstants{
	
	public String loginRequest(String dbname,String uname,String pwd,String deviceId){
		String url=BASE_URL;	
		
		url=url+"SurveyAPI/LoginServlet?surveylogin=true&dbName="+dbname +"&userName="+uname+"&pwd="+pwd+"&svPwd=1&deviceId="+deviceId;
		return url;
	}
	public String initLoad(String deviceId){
		String url=BASE_URL;	
		
		url=url+"SurveyAPI/LoginServlet?initconn=true&deviceId=12345645";
		return url;
	}
	
	
	public String userLoad(String dbname,String deviceId){
		String url=BASE_URL;	
		
		url=url+"SurveyAPI/LoginServlet?userload=true&dbName=csfeedback&deviceId=12345645";
		return url;
	}
	
	public String createUser(String dbname,String uname,String deviceId){
		String url=BASE_URL;	
		
		url=url+"SurveyAPI/LoginServlet?profilecreation=true&dbName="+dbname +"&deviceId="+deviceId+"&profileName="+uname+"&permissionId=1&activateSchedule=1&scheduleTimefrom=02:35:25&scheduleTimeto=03:35:25&scheduleDays=1011010&scheduleUpto=2015-12-20 00:00:00&uId=3";
		return url;
	}
	public String devicereg(String companykey,String deviceId){
		String url=BASE_URL;	
		
		url=url+"SurveyAPI/LoginServlet?devicereg=true&deviceId="+deviceId+"&compKey="+companykey;
		return url;
	}
	
	public String profileFetch(String dbName,String deviceId){
		String url=BASE_URL;	
		
		url=url+"SurveyAPI/LoginServlet?menulist=true&deviceId="+deviceId+"&uId=3&dbName="+dbName;
		return url;
	}
	public String createProfile(String dbName,String deviceId,String profile_name){
		String url=BASE_URL;	
		
		url=url+"SurveyAPI/LoginServlet?profilecreation=true&dbName="+dbName+"&deviceId="+deviceId+"&profileName="+profile_name+"&permissionId=1&validUptoactive=1&validUpto=2015-12-20%2000:00:00&uId=3&useMultiplesurvey=1";
		return url;
	}
	public String createSurvey(String dbName,String deviceId,String survey_name,String pf_id,String description){
		String url=BASE_URL;	
		
		url=url+"SurveyAPI/LoginServlet?surveycreation=true&dbName="+dbName+"&deviceId="+deviceId+"&surveyName="+survey_name+"&profileId="+pf_id+"&description="+description+"&activateSchedule=1&scheduleTimefrom=02:35:25&scheduleTimeto=03:35:25&scheduleDays=1011010&thankyouId=2";
		return url;
	}
	
}
