package com.jsservey.model;

import java.io.Serializable;

public class ProfileEditDetailsBean implements Serializable {

	private static final long serialVersionUID = 1L;
	String profile_name;
	String permission_id;
	String valid_upto;
	String valid_upto_isactive;
	String user_id;
	String multiple_survey;

	public String getProfile_name() {
		return profile_name;
	}

	public void setProfile_name(String profile_name) {
		this.profile_name = profile_name;
	}

	public String getPermission_id() {
		return permission_id;
	}

	public void setPermission_id(String permission_id) {
		this.permission_id = permission_id;
	}

	public String getValid_upto() {
		return valid_upto;
	}

	public void setValid_upto(String valid_upto) {
		this.valid_upto = valid_upto;
	}

	public String getValid_upto_isactive() {
		return valid_upto_isactive;
	}

	public void setValid_upto_isactive(String valid_upto_isactive) {
		this.valid_upto_isactive = valid_upto_isactive;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getMultiple_survey() {
		return multiple_survey;
	}

	public void setMultiple_survey(String multiple_survey) {
		this.multiple_survey = multiple_survey;
	}
}
