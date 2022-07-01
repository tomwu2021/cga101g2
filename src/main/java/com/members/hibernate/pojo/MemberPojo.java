package com.members.hibernate.pojo;

import java.sql.Timestamp;

import javax.persistence.*;

import core.pojo.Core;

@Entity
@Table(name = "members")
public class MemberPojo extends Core{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id", insertable = false)
	private Integer memberId;
	@Column(name = "account")
	private String account;
	@Column(name = "password")
	private String password;
	@Column(name = "name")
	private String name;
	@Column(name = "address", insertable = false)
	private String address;
	@Column(name = "phone", insertable = false)
	private String phone;
	@Column(name = "rank_id")
	private Integer rankId;
	@Column(name = "ewallet_amount")
	private Integer eWalletAmount;
	@Column(name = "ewallet_password", insertable = false)
	private String eWalletPassword;
	@Column(name = "bonus_amount")
	private Integer bonusAmount;
	@Column(name = "status")
	private Integer status;
	@Column(name = "create_time", insertable = false)
	private Timestamp createTime;

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

	public MemberPojo(Integer memberId, String account, String password, String name, String address, String phone,
			Integer rankId, Integer eWalletAmount, String eWalletPassword, Integer bonusAmount, Integer status,
			Timestamp createTime) {
		super();
		this.memberId = memberId;
		this.account = account;
		this.password = password;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.rankId = rankId;
		this.eWalletAmount = eWalletAmount;
		this.eWalletPassword = eWalletPassword;
		this.bonusAmount = bonusAmount;
		this.status = status;
		this.createTime = createTime;
	}

	public MemberPojo() {
		super();
	}
	
	
}
