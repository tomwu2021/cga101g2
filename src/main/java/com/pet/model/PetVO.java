package com.pet.model;

import java.sql.Date;

public class PetVO {

	private Integer petID;
	private Integer memberID;
	private String petName;
	private Integer type;
	private Byte gender;
	private String introduction;
	private Integer pictureID;
	private Date birthday;
	private Byte status;
	
	public Integer getPetID() {
		return petID;
	}
	public void setPetID(Integer petID) {
		this.petID = petID;
	}
	public Integer getMemberID() {
		return memberID;
	}
	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}
	public String getPetName() {
		return petName;
	}
	public void setPetName(String petName) {
		this.petName = petName;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Byte getGender() {
		return gender;
	}
	public void setGender(Byte gender) {
		this.gender = gender;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public Integer getPictureID() {
		return pictureID;
	}
	public void setPictureID(Integer pictureID) {
		this.pictureID = pictureID;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
		
}
