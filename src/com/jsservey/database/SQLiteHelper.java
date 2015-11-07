
package com.jsservey.database;



import java.util.ArrayList;

import com.jsservey.model.ProfileHugePojo;
import com.jsservey.model.ProfileName;
import com.jsservey.model.RegistrationDetailsBean;
import com.jsservey.model.Survey;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {

	private static SQLiteHelper sInstance;
	private static final String DATABASE_NAME = "datastore.db";
	private static final int DATABASE_VERSION = 6;

	private static final String TABLE_USER_DETAILS = "USER_DETAILS";
	public static final String USER_NAME = "user_name";
	public static final String USER_FIRST_NAME = "user_firstName";
	public static final String USER_LAST_NAME = "user_LastName";
	public static final String USER_PASSWORD = "user_password";
	public static final String USER_CONFIRM_PASSWORD = "user_confirm_password";
	public static final String UERS_PROFILES = "user_profiles";

	private static final String TABLE_PROFILE_DETAILS = "PROFILE_DETAILS";
	public static final String USER_ID = "user_id";
	public static final String PROFILE_USER_NAME = "user_name";
	public static final String PROFILE_ID = "profile_id";
	public static final String PROFILE_NAME = "profile_name";
	public static final String SURVEY_ID = "survey_id";
	public static final String PROFILE_SURVEY_NAME = "survey_name";
	public static final String QUESTION_ID = "question_id";
	public static final String QUESTION_NAME = "question_name";
	public static final String ANSWER_ID = "answer_id";
	public static final String ANSWER_NAME = "answer_name";
	public static final String IS_ACTIVATED = "activated";

	// Database creation sql statement
	private static final String TABLE_USER_DETAILS_CREATE = "create table "
			+ TABLE_USER_DETAILS + "(" + USER_NAME + " text, "
			+ USER_FIRST_NAME + " text, " + USER_LAST_NAME + " text, "
			+ USER_PASSWORD + " text, " + USER_CONFIRM_PASSWORD + " text);";

	private static final String TABLE_PROFILE_DETAILS_CREATE = "create table "
			+ TABLE_PROFILE_DETAILS + "(" + USER_ID + " text, "
			+ PROFILE_USER_NAME + " text, " + PROFILE_ID + " text, "
			+ PROFILE_NAME + " text, " + SURVEY_ID + " text, "
			+ PROFILE_SURVEY_NAME + " text, " + QUESTION_ID + " text, "
			+ QUESTION_NAME + " text, " + ANSWER_ID + " text, " + ANSWER_NAME
			+ " text , " + IS_ACTIVATED+ " integer);";
	private static final String TABLE_USER_PROFILE_CREATE = "create table "
			+ UERS_PROFILES + "( profile_id  text,  Profile_name text ,is_activated boolean );";
	private SQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public static SQLiteHelper getInstance(Context context) {
		// Use the application context, which will ensure that you
		// don't accidentally leak an Activity's context.
		if (sInstance == null) {
			sInstance = new SQLiteHelper(context.getApplicationContext());
		}
		return sInstance;
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(TABLE_USER_DETAILS_CREATE);
		database.execSQL(TABLE_PROFILE_DETAILS_CREATE);
		database.execSQL(TABLE_USER_PROFILE_CREATE);
	}

	public boolean insertDetails(RegistrationDetailsBean registrationDetailsBean) {

		boolean createSuccessful = false;

		ContentValues values = new ContentValues();

		values.put(USER_NAME, registrationDetailsBean.getUserName());
		values.put(USER_FIRST_NAME, registrationDetailsBean.getFirstName());
		values.put(USER_LAST_NAME, registrationDetailsBean.getLastName());
		values.put(USER_PASSWORD, registrationDetailsBean.getPassword());
		SQLiteDatabase db = this.getWritableDatabase();

		createSuccessful = db.insert(TABLE_USER_DETAILS, null, values) > 0;
		db.close();

		return createSuccessful;
	}

	public boolean insertProfileDetails(ProfileHugePojo profile) {

		boolean createSuccessful = false;

		ContentValues values = new ContentValues();

		values.put(USER_ID, profile.getUserId());
		values.put(PROFILE_USER_NAME, profile.getUserName());
		values.put(PROFILE_ID, profile.getProfileId());
		values.put(PROFILE_NAME, profile.getProfileName());
		values.put(SURVEY_ID, profile.getSurveyId());
		values.put(PROFILE_SURVEY_NAME, profile.getSurveyName());
		values.put(QUESTION_ID, profile.getQuestionId());
		values.put(QUESTION_NAME, profile.getQuestionName());
		values.put(ANSWER_ID, profile.getAnswerId());
		values.put(ANSWER_NAME, profile.getAnswerName());
		values.put(IS_ACTIVATED, profile.getIs_activated());
		SQLiteDatabase db = this.getWritableDatabase();

		createSuccessful = db.insert(TABLE_PROFILE_DETAILS, null, values) > 0;
		db.close();

		return createSuccessful;
	}
	
	public boolean insertProfileDetails_bulk(ArrayList<ProfileHugePojo> profileArray) {
		
		Log.d("abx", "in insertProfileDetails_bulk profileArray size= "+profileArray.size());
		boolean createSuccessful = false;
		 SQLiteDatabase db = this.getWritableDatabase();
		 try {
			// String query = "TRUNCATE  TABLE "+TABLE_PROFILE_DETAILS;
			 db.delete(TABLE_PROFILE_DETAILS, null, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		 
		for (ProfileHugePojo profile : profileArray) {
			ContentValues values = new ContentValues();
			ContentValues values2 = new ContentValues();

			values.put(USER_ID, profile.getUserId());
			values.put(PROFILE_USER_NAME, profile.getUserName());
			values.put(PROFILE_ID, profile.getProfileId());
			values.put(PROFILE_NAME, profile.getProfileName());
			values.put(SURVEY_ID, profile.getSurveyId());
			values.put(PROFILE_SURVEY_NAME, profile.getSurveyName());
			values.put(QUESTION_ID, profile.getQuestionId());
			values.put(QUESTION_NAME, profile.getQuestionName());
			values.put(ANSWER_ID, profile.getAnswerId());
			values.put(ANSWER_NAME, profile.getAnswerName());
			values.put(IS_ACTIVATED, 0);
			values2.put(PROFILE_ID, profile.getProfileId());
			values2.put(PROFILE_NAME, profile.getProfileName());
			createSuccessful = db.insert(TABLE_PROFILE_DETAILS, null, values) > 0;
			db.insert(UERS_PROFILES, null, values2) ;//inserting data into user_profiles
		}

		db.close();

		return createSuccessful;
	}

	public boolean insertProfileNames(ProfileName profile) {
		boolean createSuccessful = false;

		ContentValues values = new ContentValues();

		
		values.put(PROFILE_ID, profile.getProfile_id());
		values.put(PROFILE_NAME, profile.getProfilr_name());
		
		SQLiteDatabase db = this.getWritableDatabase();

		createSuccessful = db.insert(UERS_PROFILES, null, values) > 0;
		db.close();

		return createSuccessful;}
	public ProfileHugePojo getProfileDetails() {
		String query = "SELECT * FROM "+TABLE_PROFILE_DETAILS;
		ProfileHugePojo profile = new ProfileHugePojo();
		SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
		Cursor cursor = sqLiteDatabase.rawQuery(query, null);
		cursor.moveToFirst();
		profile.setUserId(cursor.getString(cursor.getColumnIndex("user_id")));
		profile.setUserName(cursor.getString(cursor.getColumnIndex("user_name")));
		profile.setProfileId(cursor.getString(cursor.getColumnIndex("profile_id")));
		profile.setProfileName(cursor.getString(cursor.getColumnIndex("profile_name")));
		profile.setSurveyId(cursor.getString(cursor.getColumnIndex("survey_id")));
		profile.setSurveyName(cursor.getString(cursor.getColumnIndex("survey_name")));
		profile.setQuestionId(cursor.getString(cursor.getColumnIndex("question_id")));
		profile.setQuestionName(cursor.getString(cursor.getColumnIndex("question_name")));
		profile.setAnswerId(cursor.getString(cursor.getColumnIndex("answer_id")));
		profile.setAnswerName(cursor.getString(cursor.getColumnIndex("answer_name")));
		profile.setIs_activated(cursor.getInt(cursor.getColumnIndex(IS_ACTIVATED)));
		return profile;

	}
	public ProfileName getProfileDetailsNames() {
		String query = "SELECT * FROM "+UERS_PROFILES;
		ProfileName profile = new ProfileName();
		SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
		Cursor cursor = sqLiteDatabase.rawQuery(query, null);
		cursor.moveToFirst();
		
		profile.setProfile_id(cursor.getString(cursor.getColumnIndex("profile_id")));
		profile.setProfilr_name(cursor.getString(cursor.getColumnIndex("profile_name")));
		
		return profile;

	}
	public ArrayList<ProfileName>  getProfileNames_bulk() {
		Log.d("abx", "in getProfileNames_bulk");
		ArrayList<ProfileName> profileArray= new ArrayList<ProfileName>();	
		String query = "SELECT DISTINCT * FROM "+UERS_PROFILES;
		
		SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
		Cursor cursor = sqLiteDatabase.rawQuery(query, null);
		//cursor.moveToFirst();
		if (cursor.moveToFirst()) {
			Log.d("abx", "in getProfileNames_bulk11"+cursor.getCount()+"");
			do {
				ProfileName profile = new ProfileName();
				
				Log.d("abx", "in getProfileNames_bulk"+cursor.getString(cursor.getColumnIndex("profile_id")));
				profile.setProfile_id(cursor.getString(cursor.getColumnIndex("profile_id")));
				profile.setProfilr_name(cursor.getString(cursor.getColumnIndex("Profile_name")));
				profileArray.add(profile);
			} while (cursor.moveToNext());
		}
		
		
		return profileArray;

	}
	public ArrayList<ProfileHugePojo>  getProfileDetails_bulk() {
		ArrayList<ProfileHugePojo> profileArray= new ArrayList<ProfileHugePojo>();	
		String query = "SELECT DISTINCT * FROM "+TABLE_PROFILE_DETAILS;
		
		SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
		Cursor cursor = sqLiteDatabase.rawQuery(query, null);
		//cursor.moveToFirst();
		if (cursor.moveToFirst()) {
			do {
				ProfileHugePojo profile = new ProfileHugePojo();
			
				profile.setUserId(cursor.getString(cursor.getColumnIndex("user_id")));
				profile.setUserName(cursor.getString(cursor.getColumnIndex("user_name")));
				profile.setProfileId(cursor.getString(cursor.getColumnIndex("profile_id")));
				profile.setProfileName(cursor.getString(cursor.getColumnIndex("profile_name")));
				profile.setSurveyId(cursor.getString(cursor.getColumnIndex("survey_id")));
				profile.setSurveyName(cursor.getString(cursor.getColumnIndex("survey_name")));
				profile.setQuestionId(cursor.getString(cursor.getColumnIndex("question_id")));
				profile.setQuestionName(cursor.getString(cursor.getColumnIndex("question_name")));
				profile.setAnswerId(cursor.getString(cursor.getColumnIndex("answer_id")));
				profile.setAnswerName(cursor.getString(cursor.getColumnIndex("answer_name")));
				profileArray.add(profile);
			} while (cursor.moveToNext());
		}
		
		
		return profileArray;

	}
	public ArrayList<Survey>  getSurveyList_bulk(String p_id) {
		ArrayList<Survey> profileArray= new ArrayList<Survey>();	
		String query = "SELECT DISTINCT survey_id,survey_name FROM "+TABLE_PROFILE_DETAILS + " where profile_id ="+p_id;
		
		SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
		Cursor cursor = sqLiteDatabase.rawQuery(query, null);
		//cursor.moveToFirst();
		if (cursor.moveToFirst()) {
			do {
				Survey profile = new Survey();
			
				
				profile.setSurvey_id(cursor.getString(cursor.getColumnIndex("survey_id")));
				profile.setSurvey_name(cursor.getString(cursor.getColumnIndex("survey_name")));
				
				
				profileArray.add(profile);
			} while (cursor.moveToNext());
		}
		
		
		return profileArray;

	}
	
	public ArrayList<ProfileName>  getProfileNames() {
		ArrayList<ProfileName> profileArray= new ArrayList<ProfileName>();	
		String query = "SELECT DISTINCT profile_id,profile_name FROM "+TABLE_PROFILE_DETAILS;
		
		SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
		Cursor cursor = sqLiteDatabase.rawQuery(query, null);
		//cursor.moveToFirst();
		if (cursor.moveToFirst()) {
			do {
				ProfileName profile = new ProfileName();
			
				profile.setProfile_id(cursor.getString(cursor.getColumnIndex("profile_id")));
				profile.setProfilr_name(cursor.getString(cursor.getColumnIndex("profile_name")));
				
				profileArray.add(profile);
			} while (cursor.moveToNext());
		}
		
		
		return profileArray;

	}

	public RegistrationDetailsBean readUserDetails() {

		String query = "SELECT * FROM USER_DETAILS";
		SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
		Cursor cursor = sqLiteDatabase.rawQuery(query, null);
		cursor.moveToFirst();
		String userName = cursor.getString(cursor.getColumnIndex("user_name"));
		String firstName = cursor.getString(cursor.getColumnIndex(USER_FIRST_NAME));
		String lastName = cursor.getString(cursor.getColumnIndex(USER_LAST_NAME));
		String password = cursor.getString(cursor.getColumnIndex(USER_PASSWORD));
		String confirmPassword = cursor.getString(cursor
				.getColumnIndex(USER_CONFIRM_PASSWORD));
		RegistrationDetailsBean detailsBean = new RegistrationDetailsBean();
		detailsBean.setUserName(userName);
		detailsBean.setFirstName(firstName);
		detailsBean.setLastName(lastName);
		detailsBean.setPassword(password);
		return detailsBean;

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_DETAILS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE_DETAILS);
		onCreate(db);
	}

	public void clearAllTables() {
		SQLiteDatabase db = getWritableDatabase();
		db.delete(TABLE_USER_DETAILS, null, null);
		db.delete(TABLE_PROFILE_DETAILS, null, null);

	}
}
