package com.customer.model;

import java.sql.Timestamp;

public class CustomerVO {

	private Integer caseID;
	private String mailAddress;
	private String nickname;
	private String content;
	private Timestamp sendTime;
	private Byte replyStatus;
	private Integer empNo;
	
	public Integer getCaseID() {
		return caseID;
	}
	public void setCaseID(Integer caseID) {
		this.caseID = caseID;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getSendTime() {
		return sendTime;
	}
	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}
	public Byte getReplyStatus() {
		return replyStatus;
	}
	public void setReplyStatus(Byte replyStatus) {
		this.replyStatus = replyStatus;
	}
	public Integer getEmpNo() {
		return empNo;
	}
	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}
	
}
