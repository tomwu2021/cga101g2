package com.chargeRecord.model;

import java.sql.Timestamp;

public class ChargeRecordVO implements java.io.Serializable {

	private Integer recordId;
	private Integer memberId;
	private Integer chargeAmount;
	private Timestamp recordTime;
	private String recordTimeString;// yyyy-MM-dd

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

	public String getRecordTimeString() {
		return recordTimeString;
	}

	public void setRecordTimeString(String recordTimeString) {
		this.recordTimeString = recordTimeString;
	}

	@Override
	public String toString() {
		return "ChargeRecordVO [recordId=" + recordId + ", memberId=" + memberId + ", chargeAmount=" + chargeAmount
				+ ", recordTime=" + recordTime + "]";
	}

}
