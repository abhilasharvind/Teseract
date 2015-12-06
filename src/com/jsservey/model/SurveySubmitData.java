package com.jsservey.model;

public class SurveySubmitData {
	
	String  ques_id;
	public String getQues_id() {
		return ques_id;
	}
	public void setQues_id(String ques_id) {
		this.ques_id = ques_id;
	}
	public String getAns_id() {
		return ans_id;
	}
	public void setAns_id(String ans_id) {
		this.ans_id = ans_id;
	}
	public String getAns_value() {
		return ans_value;
	}
	public void setAns_value(String ans_value) {
		this.ans_value = ans_value;
	}
	String ans_id;
	String ans_value;

}
