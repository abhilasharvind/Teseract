package com.jsservey.view.login;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.abx.jsservey.R;
import com.jsservey.database.SQLiteHelper;
import com.jsservey.model.Settings;
import com.jsservey.utils.AppPref;
import com.jsservey.utils.PrefConstant;
import com.jsservey.utils.Utility;
import com.jsservey.view.BaseActivity;
import com.jsservey.view.home.HomeActivity;
import com.jsservey.view.home.survey.questions.QuestionsDisplayActivity;
import com.jsservey.webservices.ApiRequestListner;
import com.jsservey.webservices.ApiRequester;
import com.jsservey.webservices.RequestCreator;
import com.jsservey.webservices.SurveyRequestCreator;

public class SplashActivity extends BaseActivity implements ApiRequestListner{	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash_layout);
	
		RequestCreator requestCreator = new RequestCreator(getApplicationContext());
		Log.d("abx", "SplashActivity");
		new ApiRequester(this,requestCreator.initLoad(),this).execute("");
		
		
	}

	@Override
	public String onSuccess(JSONObject result) {
		if (result!=null && result.has("db_name")) {
			try {
				String dbname=result.getString("db_name");
				if(dbname.equals("0")){
					Utility.startActivity(SplashActivity.this, DeviceCreationActivity.class);
					finish();
				}else{
					Log.d("abx", "dbname= "+dbname);
					RequestCreator requestCreator = new RequestCreator(getApplicationContext());
					AppPref appPref = new AppPref(getApplicationContext());
					appPref.putString(PrefConstant.DB_NAME, dbname);
					new ApiRequester(getApplicationContext(), requestCreator.settings("4"), new ApiRequestListner() {
						
						@Override
						public String onSuccess(JSONObject result) {
							
							Settings settings = new Settings(getApplicationContext());
							try{
							settings.setSettingsData(result.getString("phno_is_active"), result.getString("address_is_active"), result.getString("questions_left_isactive"), result.getString("bg_colour"), result.getString("layout_heading"),result.getString(" email_is_active"), result.getString("lock_is_active"), result.getString("layout_colour"), result.getString("customerinfo_is_active"), result.getString("thankyou_timeout"), result.getString("personaldesc_is_active"), result.getString("name_is_active"));
							}catch(Exception e){
								e.printStackTrace();
							}
							
							return null;
						}
						
						@Override
						public String onStarted() {
							// TODO Auto-generated method stub
							return null;
						}
						
						@Override
						public String onFailed() {
							// TODO Auto-generated method stub
							return null;
						}
					}).execute("");
					
					new ApiRequester(this,requestCreator.userLoad(),new ApiRequestListner() {
						
						@Override
						public String onSuccess(JSONObject result) {
							try {
								if (result.has("user_load")) {
									String userload_id=result.getString("user_load");
									String userAdvanced=result.getString("user_advanced");
									String survey_perform_only_id=result.getString("survey_perform_only");
									if(userload_id.equals("0")){
										Utility.startActivity(SplashActivity.this, LoginActivity.class);
									}
									else if(survey_perform_only_id.equals("1")&&userAdvanced.equals("1")){
										SurveyRequestCreator requestCreator = new SurveyRequestCreator(getApplicationContext());
										new ApiRequester(SplashActivity.this,requestCreator.surveyQuesFetch("1"), new ApiRequestListner() {
											
											@Override
											public String onSuccess(JSONObject result) {
												SQLiteHelper db = 	SQLiteHelper.getInstance(getApplicationContext());
												db.insertSurveyQuestions(result.toString());
												String d=db.getSurveyQuestions();
												Log.d("abx", d);
												Utility.startActivity(SplashActivity.this, QuestionsDisplayActivity.class);
									
												return null;
											}
											
											@Override
											public String onStarted() {
												// TODO Auto-generated method stub
												return null;
											}
											
											@Override
											public String onFailed() {
												// TODO Auto-generated method stub
												return null;
											}
										}).execute("");
									}else{
										Utility.startActivity(SplashActivity.this, HomeActivity.class);
										
									}
													
									finish();	
								}else{
									Log.d("abx", "json error in splash");
								}
								
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							return null;
						}
						
						@Override
						public String onStarted() {
							// TODO Auto-generated method stub
							return null;
						}
						
						@Override
						public String onFailed() {
							
							Utility.startActivity(SplashActivity.this, LoginActivity.class);
							return null;
						}
					}).execute("");
					
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			Log.d("abx", "json null in splash");
			Toast.makeText(getApplicationContext(), "Internal error", 2000).show();
		}
		
		
		return null;
	}

	@Override
	public String onFailed() {
		// TODO Auto-generated method stub
		
		Toast.makeText(getApplicationContext(), "Unable to connect to the server.Check your internet connectivity", 2000).show();
		return null;
	}

	@Override
	public String onStarted() {
		// TODO Auto-generated method stub
		return null;
	}

}
