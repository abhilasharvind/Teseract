package com.jsservey.model;

public class NewUser {
	String username;
	String fname;
	String lname;
	String phonenumb;
	String address;
	String passwrd;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getPhonenumb() {
		return phonenumb;
	}
	public void setPhonenumb(String phonenumb) {
		this.phonenumb = phonenumb;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPasswrd() {
		return passwrd;
	}
	public void setPasswrd(String passwrd) {
		this.passwrd = passwrd;
	}
	public String getPaswwrd_dup() {
		return paswwrd_dup;
	}
	public void setPaswwrd_dup(String paswwrd_dup) {
		this.paswwrd_dup = paswwrd_dup;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	String paswwrd_dup;
	String designation;
	String department;
}
