package com.jsservey.webservices;

public class RequestCreator implements URLConstants{
	
	public String loginRequest(String dbname,String uname,String pwd,String deviceId){
		String url=BASE_URL;	
		
		url=url+"SurveyAPI/LoginServlet?surveylogin=true&dbName="+dbname +"&userName="+uname+"&pwd="+pwd+"&svPwd=1&deviceId="+deviceId;
		return url;
	}
	public String initLoad(String deviceId){
		String url=BASE_URL;	
		
		url=url+"SurveyAPI/LoginServlet?initconn=true&deviceId=123456";
		return url;
	}
	
	
	public String userLoad(String dbname,String deviceId){
		String url=BASE_URL;	
		
		url=url+"SurveyAPI/LoginServlet?userload=true&dbName=csfeedback&deviceId=123456";
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
	
}
