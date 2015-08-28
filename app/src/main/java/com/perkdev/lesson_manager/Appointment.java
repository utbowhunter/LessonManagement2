package com.perkdev.lesson_manager;

import java.util.Date;


public class Appointment {

	enum STATUS {COMPLETE, CANCELLED, RESCHEDULED};
	
	int id;
	
	String date;
	String startTime;
	String endTime;

	Double rate;
	
	STATUS status;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}
	
	
	

	
}
