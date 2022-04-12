package com.members.model;

import java.sql.Timestamp;

public class MembersVO implements java.io.Serializable {
	private Integer memberId;
	private String account;
	private String password;
	private String name;
	private String address;
	private String phone;
	private Integer rankId;
	private Integer eWalletAmount;
	private String eWalletPassword;
	private Integer bonusAmount;
	private Integer status;
	private Timestamp createTime;

	public MembersVO() {
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getRankId() {
		return rankId;
	}

	public void setRankId(Integer rankId) {
		this.rankId = rankId;
	}

	public Integer geteWalletAmount() {
		return eWalletAmount;
	}

	public void seteWalletAmount(Integer eWalletAmount) {
		this.eWalletAmount = eWalletAmount;
	}

	public String geteWalletPassword() {
		return eWalletPassword;
	}

	public void seteWalletPassword(String eWalletPassword) {
		this.eWalletPassword = eWalletPassword;
	}

	public Integer getBonusAmount() {
		return bonusAmount;
	}

	public void setBonusAmount(Integer bonusAmount) {
		this.bonusAmount = bonusAmount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}