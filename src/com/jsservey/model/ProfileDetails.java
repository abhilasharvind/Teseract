package com.jsservey.model;

import java.io.Serializable;

public class ProfileDetails implements Serializable{
	String profileName;
	boolean isValidUpto;
	boolean isChildVisible;
	boolean isChildSelection;
	String selectedDate;

	public boolean isValidUpto() {
		return isValidUpto;
	}

	public void setValidUpto(boolean isValidUpto) {
		this.isValidUpto = isValidUpto;
	}

	public boolean isChildVisible() {
		return isChildVisible;
	}

	public void setChildVisible(boolean isChildVisible) {
		this.isChildVisible = isChildVisible;
	}

	public boolean isChildSelection() {
		return isChildSelection;
	}

	public void setChildSelection(boolean isChildSelection) {
		this.isChildSelection = isChildSelection;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(String selectedDate) {
		this.selectedDate = selectedDate;
	}

}
