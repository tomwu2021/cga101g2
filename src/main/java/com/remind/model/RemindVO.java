package com.remind.model;

import java.sql.Timestamp;

public class RemindVO {

	private Integer remindId;
	private Integer memberId;
	private String content;
	private Timestamp time;
	
	public Integer getRemindId() {
		return remindId;
	}
	public void setRemindId(Integer remindId) {
		this.remindId = remindId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "RemindVO[ "+ remindId + ", " + memberId + ", " + content + ", " + time + " ]";
	}
	
}
