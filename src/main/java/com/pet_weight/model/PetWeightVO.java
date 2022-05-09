package com.pet_weight.model;

import java.math.BigDecimal;
import java.sql.Date;

public class PetWeightVO {

	private Integer recordId;
	private Integer petId;
	private BigDecimal weightRecord;
	private Date recordTime;
	
	public Integer getRecordId() {
		return recordId;
	}
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	public Integer getPetId() {
		return petId;
	}
	public void setPetId(Integer petId) {
		this.petId = petId;
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
	public String toString() {
		return "PetWeightVO[ "+ recordId + ", " + petId + ", " + weightRecord + ", " + recordTime + " ]";
	}
	
}
