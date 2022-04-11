package com.remind.model;

import java.sql.Timestamp;

public class RemindVO {

	private Integer remindID;
	private Integer memberID;
	private String content;
	private Timestamp time;
	
	public Integer getRemindID() {
		return remindID;
	}
	public void setRemindID(Integer remindID) {
		this.remindID = remindID;
	}
	public Integer getMemberID() {
		return memberID;
	}
	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
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
		String remindID = this.remindID.toString();
		String memberID = this.memberID.toString();
		String content = this.content;
		String time = this.time.toString();
		return "["+ remindID+","+memberID+","+content+","+time+"]";
	}
	
}
