package com.jsservey.model;

public class Survey {
	String survey_name;
	String survey_id;
	String activated_survey_id;
	public String getActivated_survey_id() {
		return activated_survey_id;
	}
	public void setActivated_survey_id(String activated_survey_id) {
		this.activated_survey_id = activated_survey_id;
	}
	public String getSurvey_name() {
		return survey_name;
	}
	public void setSurvey_name(String survey_name) {
		this.survey_name = survey_name;
	}
	public String getSurvey_id() {
		return survey_id;
	}
	public void setSurvey_id(String survey_id) {
		this.survey_id = survey_id;
	}
}
