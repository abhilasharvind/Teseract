package com.interfaces;


public interface EditDeleteUpdate_listner {
	
	
	public void onEditTaskStart(int type,String id);
	public void onUpdtaeTaskStart(int type,String id);
	public void onDeleteTaskStart(int type,String id);
	public void onActivateTaskStart(int type,String id);

}
