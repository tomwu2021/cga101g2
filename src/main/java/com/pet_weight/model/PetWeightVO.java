package com.pet_weight.model;

import java.math.BigDecimal;
import java.sql.Date;

public class PetWeightVO {

	private Integer recordID;
	private Integer petID;
	private BigDecimal weightRecord;
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
	public BigDecimal getWeightRecord() {
		return weightRecord;
	}
	public void setWeightRecord(BigDecimal weightRecord) {
		this.weightRecord = weightRecord;
	}
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	
}
