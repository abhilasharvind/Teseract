package com.jsservey.model;

import java.util.ArrayList;

public class Question {
	
	String question_id;
	String question;
	String type_id;
	String type_name;
	String value;
	ArrayList<Answer> answerlist = new ArrayList<Answer>();
	public String getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(String question_id) {
		this.question_id = question_id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getType_id() {
		return type_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public ArrayList<Answer> getAnswerlist() {
		return answerlist;
	}
	public void setAnswerlist(ArrayList<Answer> answerlist) {
		this.answerlist = answerlist;
	}



	
	
	

}
