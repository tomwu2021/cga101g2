package com.chargeRecord.model;

import java.sql.Timestamp;

public class ChargeRecordVO implements java.io.Serializable {

	private Integer recordId;
	private Integer memberId;
	private Integer chargeAmount;
	private Timestamp recordTime;

	public ChargeRecordVO() {
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(Integer chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public Timestamp getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Timestamp recordTime) {
		this.recordTime = recordTime;
	}

}
