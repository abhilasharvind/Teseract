package com.jsservey.model;

import com.jsservey.utils.AppPref;
import com.jsservey.utils.PrefConstant;

import android.content.Context;

public class Settings {

	String phno_is_active;

	String address_is_active;
	String questions_left_isactive;
	String bg_colour;
	String layout_heading;
	String email_is_active;
	String lock_is_active;
	String layout_colour;
	String customerinfo_is_active;
	String name_is_active;
	String personaldesc_is_active;
	String thankyou_timeout;
	AppPref appPref;
	public Settings(Context context) {
		 appPref = new AppPref(context);
	}
	
	public String getPhno_is_active() {
		return phno_is_active;
	}
	private void setPhno_is_active(String phno_is_active) {
		this.phno_is_active = phno_is_active;
		
		appPref.putString(PrefConstant.phno_is_active, phno_is_active);
	}
	public String getAddress_is_active() {
		return address_is_active;
	}
	private void setAddress_is_active(String address_is_active) {
		this.address_is_active = address_is_active;
		appPref.putString(PrefConstant.address_is_active, address_is_active);
	}
	public String getQuestions_left_isactive() {
		return questions_left_isactive;
	}
	private void setQuestions_left_isactive(String questions_left_isactive) {
		this.questions_left_isactive = questions_left_isactive;
		appPref.putString(PrefConstant.questions_left_isactive, questions_left_isactive);
	}
	public String getBg_colour() {
		return bg_colour;
	}
	private void setBg_colour(String bg_colour) {
		this.bg_colour = bg_colour;
		appPref.putString(PrefConstant.bg_colour, bg_colour);
	}
	public String getLayout_heading() {
		return layout_heading;
	}
	private void setLayout_heading(String layout_heading) {
		this.layout_heading = layout_heading;
		appPref.putString(PrefConstant.layout_heading, layout_heading);
	}
	public String getEmail_is_active() {
		return email_is_active;
	}
	private void setEmail_is_active(String email_is_active) {
		this.email_is_active = email_is_active;
		appPref.putString(PrefConstant.email_is_active, email_is_active);
	}
	public String getLock_is_active() {
		return lock_is_active;
	}
	private void setLock_is_active(String lock_is_active) {
		this.lock_is_active = lock_is_active;
		appPref.putString(PrefConstant.lock_is_active, lock_is_active);
	}
	public String getLayout_colour() {
		return layout_colour;
	}
	private void setLayout_colour(String layout_colour) {
		this.layout_colour = layout_colour;
		appPref.putString(PrefConstant.layout_colour, layout_colour);
	}
	public String getCustomerinfo_is_active() {
		return customerinfo_is_active;
	}
	private void setCustomerinfo_is_active(String customerinfo_is_active) {
		this.customerinfo_is_active = customerinfo_is_active;
		appPref.putString(PrefConstant.customerinfo_is_active, customerinfo_is_active);
	}
	public String getName_is_active() {
		return name_is_active;
	}
	private void setName_is_active(String name_is_active) {
		this.name_is_active = name_is_active;
	}
	public String getPersonaldesc_is_active() {
		return personaldesc_is_active;
	}
	private void setPersonaldesc_is_active(String personaldesc_is_active) {
		this.personaldesc_is_active = personaldesc_is_active;
		appPref.putString(PrefConstant.personaldesc_is_active, personaldesc_is_active);
	}
	public String getThankyou_timeout() {
		return thankyou_timeout;
	}
	private void setThankyou_timeout(String thankyou_timeout) {
		this.thankyou_timeout = thankyou_timeout;
		appPref.putString(PrefConstant.thankyou_timeout, thankyou_timeout);
	}

	public void setSettingsData(String phno_is_active, String address_is_active, String questions_left_isactive,
			String bg_colour, String layout_heading, String email_is_active, String lock_is_active,
			String layout_colour, String customerinfo_is_active, String thankyou_timeout, String personaldesc_is_active,
			String name_is_active) {
		setThankyou_timeout(thankyou_timeout);
		setPersonaldesc_is_active(personaldesc_is_active);
		setLock_is_active(lock_is_active);
		setName_is_active(name_is_active);
		setCustomerinfo_is_active(customerinfo_is_active);
		setLayout_colour(layout_colour);
		setEmail_is_active(email_is_active);
		setQuestions_left_isactive(questions_left_isactive);
		setBg_colour(bg_colour);
		setAddress_is_active(address_is_active);
		setPhno_is_active(phno_is_active);
		setLayout_heading(layout_heading);
	}
}
