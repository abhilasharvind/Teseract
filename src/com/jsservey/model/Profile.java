package com.jsservey.model;

public class Profile {
	String profilr_name;
	String profile_id;
	boolean is_activated;
	public boolean isIs_activated() {
		return is_activated;
	}
	public void setIs_activated(boolean is_activated) {
		this.is_activated = is_activated;
	}
	public String getProfilr_name() {
		return profilr_name;
	}
	public void setProfilr_name(String profilr_name) {
		this.profilr_name = profilr_name;
	}
	public String getProfile_id() {
		return profile_id;
	}
	public void setProfile_id(String profile_id) {
		this.profile_id = profile_id;
	}

}
