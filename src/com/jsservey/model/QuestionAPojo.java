package com.jsservey.model;

public class QuestionAPojo {

	private String questionName;
	private String questionText;
	private int questionType;

	public int getQuestionType() {
		return questionType;
	}

	private int maxValue;
	private int otherValue;

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public int getOtherValue() {
		return otherValue;
	}

	public void setOtherValue(int otherValue) {
		this.otherValue = otherValue;
	}

}
