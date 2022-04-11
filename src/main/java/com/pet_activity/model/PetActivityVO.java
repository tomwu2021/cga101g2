package com.pet_activity.model;

import java.sql.Date;

public class PetActivityVO {
	
	private Integer recordID;
	private Integer petID;
	private String activity;
	private Date recordTime;
	
	public Integer getRecordID() {
		return recordID;
	}
	public void setRecordID(Integer recordID) {
		this.recordID = recordID;
	}
	public Integer getPetID() {
		return petID;
	}
	public void setPetID(Integer petID) {
		this.petID = petID;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	
}
