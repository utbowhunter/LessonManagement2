package com.perkdev.lesson_manager;

import java.util.Date;

public class Student {

	int id;
	
	String fullName;
	String fullAddress;
	String contactPhone;
	String contactEmail;
	String parentNames;
	String notes;
	Double defaultRate;
	
	String birthDate;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

    public String getFullAddress() {
		return fullAddress;
	}
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getParentNames() {
		return parentNames;
	}
	public void setParentNames(String parentNames) {
		this.parentNames = parentNames;
	}

	public String getBirthDate() { return birthDate; }
	public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

	public Double getDefaultRate() {
		return defaultRate;
	}
	public void setDefaultRate(Double defaultRate) {
		this.defaultRate = defaultRate;
	}

	public String getNotes() {	return notes; }
	public void setNotes(String notes) { this.notes = notes; }

	public String getContact_email() { return contactEmail; }
	public void setContact_email(String contact_email) { this.contactEmail = contactEmail; }
}
