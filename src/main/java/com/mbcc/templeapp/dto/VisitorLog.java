package com.mbcc.templeapp.dto;

import java.util.Date;

public class VisitorLog {
	
	private String firstName;
	private String lastName;
	private Date dateofvisit;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateofvisit() {
		return dateofvisit;
	}
	public void setDateofvisit(Date dateofvisit) {
		this.dateofvisit = dateofvisit;
	}
	

}
