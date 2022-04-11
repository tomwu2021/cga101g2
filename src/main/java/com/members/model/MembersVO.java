package com.members.model;

import java.sql.Timestamp;

public class MembersVO implements java.io.Serializable {
	private Integer member_id;
	private String account;
	private String password;
	private String name;
	private String address;
	private String phone;
	private Integer rank_id;
	private Integer e_wallet_amount;
	private String e_wallet_password;
	private Integer bonus_amount;
	private Integer status;
	private Timestamp create_time;

	public MembersVO() {
	}

	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
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

	public Integer getRank_id() {
		return rank_id;
	}

	public void setRank_id(Integer rank_id) {
		this.rank_id = rank_id;
	}

	public Integer getE_wallet_amount() {
		return e_wallet_amount;
	}

	public void setE_wallet_amount(Integer e_wallet_amount) {
		this.e_wallet_amount = e_wallet_amount;
	}

	public String getE_wallet_password() {
		return e_wallet_password;
	}

	public void setE_wallet_password(String e_wallet_password) {
		this.e_wallet_password = e_wallet_password;
	}

	public Integer getBonus_amount() {
		return bonus_amount;
	}

	public void setBonus_amount(Integer bonus_amount) {
		this.bonus_amount = bonus_amount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

}