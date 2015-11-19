package com.interfaces;

public interface QuestionPopUpListner {

	public void onEditTaskStart(int type,String id);
	public void onUpdtaeTaskStart(int type,String id);
	public void onDeleteTaskStart(int type,String id);
	public void onVisible(int type,String id);
	public void onInVisible(int type,String id);
	
	
}
