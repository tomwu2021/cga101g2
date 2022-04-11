package com.chargeRecord.model;

import java.sql.Timestamp;

public class ChargeRecordVO implements java.io.Serializable {
	private Integer record_id;
	private Integer member_id;
	private Integer charge_amount;
	private Timestamp record_time;

	public ChargeRecordVO() {
	}

	public Integer getRecord_id() {
		return record_id;
	}

	public void setRecord_id(Integer record_id) {
		this.record_id = record_id;
	}

	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}

	public Integer getCharge_amount() {
		return charge_amount;
	}

	public void setCharge_amount(Integer charge_amount) {
		this.charge_amount = charge_amount;
	}

	public Timestamp getRecord_time() {
		return record_time;
	}

	public void setRecord_time(Timestamp record_time) {
		this.record_time = record_time;
	}

}
