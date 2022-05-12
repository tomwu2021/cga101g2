package com.chargeRecord.model;

import java.sql.Timestamp;

public class ChargeRecordVO implements java.io.Serializable {

	private Integer recordId;
	private Integer memberId;
	private Integer chargeAmount;
	private String recordTime;

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

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	@Override
	public String toString() {
		return "ChargeRecordVO [recordId=" + recordId + ", memberId=" + memberId + ", chargeAmount=" + chargeAmount
				+ ", recordTime=" + recordTime + "]";
	}

}
