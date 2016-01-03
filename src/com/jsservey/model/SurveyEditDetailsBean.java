package com.jsservey.model;

import java.io.Serializable;

public class SurveyEditDetailsBean implements Serializable{

	private static final long serialVersionUID = 1L;
	String survey_name;
	String profile_id;
	String description;
	String schedule_from;
	String schedule_to;
	String schedule_days;
	String schedule_isactive;
	String thank_you_id;
	
	public String getSurvey_name() {
		return survey_name;
	}
	public void setSurvey_name(String survey_name) {
		this.survey_name = survey_name;
	}
	public String getProfile_id() {
		return profile_id;
	}
	public void setProfile_id(String profile_id) {
		this.profile_id = profile_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSchedule_from() {
		return schedule_from;
	}
	public void setSchedule_from(String schedule_from) {
		this.schedule_from = schedule_from;
	}
	public String getSchedule_to() {
		return schedule_to;
	}
	public void setSchedule_to(String schedule_to) {
		this.schedule_to = schedule_to;
	}
	public String getSchedule_isactive() {
		return schedule_isactive;
	}
	public void setSchedule_isactive(String schedule_isactive) {
		this.schedule_isactive = schedule_isactive;
	}
	public String getThank_you_id() {
		return thank_you_id;
	}
	public void setThank_you_id(String thank_you_id) {
		this.thank_you_id = thank_you_id;
	}

}
