package com.customer.model;

import java.sql.Timestamp;

public class CustomerVO {

	private Integer caseId;
	private String mailAddress;
	private String nickname;
	private String content;
	private Timestamp sendTime;
	private Integer replyStatus;
	private Integer empNo;
	
	public Integer getCaseId() {
		return caseId;
	}
	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
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
	public Integer getReplyStatus() {
		return replyStatus;
	}
	public void setReplyStatus(Integer replyStatus) {
		this.replyStatus = replyStatus;
	}
	public Integer getEmpNo() {
		return empNo;
	}
	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}
	public com.emp.model.EmpVO getEmpVO(){
		com.emp.model.EmpService empSvc =new com.emp.model.EmpService();
		com.emp.model.EmpVO empVO = empSvc.getOneById(empNo);
		return empVO;
	}
	@Override
	public String toString() {
		return "CustomerVO[ "+ caseId + ", " + mailAddress + ", " + nickname + ", " + content + ", " + sendTime + ", " + replyStatus + ", " + empNo + " ]";
	}
	
}
