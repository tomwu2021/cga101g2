package com.emp.model;

import java.sql.Timestamp;

public class EmpVO implements java.io.Serializable {

	private Integer empNo;
	private String empName;
	private String account;
	private String password;
	private Timestamp createTime;
	private Integer status;

	public EmpVO() {
	}

	public Integer getEmpNo() {
		return empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "EmpVO [empNo=" + empNo + ", empName=" + empName + ", account=" + account + ", password=" + password
				+ ", createTime=" + createTime + ", status=" + status + "]";
	}
	
}
