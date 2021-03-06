package com.pet.model;

import java.sql.Date;

public class PetVO {

	private Integer petId;
	private Integer memberId;
	private String petName;
	private Integer sort1Id;
	private Integer gender;
	private String introduction;
	private Integer pictureId;
	private Date birthday;
	private Integer status;
	
	public Integer getPetId() {
		return petId;
	}
	public void setPetId(Integer petId) {
		this.petId = petId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getPetName() {
		return petName;
	}
	public void setPetName(String petName) {
		this.petName = petName;
	}
	public Integer getSort1Id() {
		return sort1Id;
	}
	public void setSort1Id(Integer sort1Id) {
		this.sort1Id = sort1Id;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public Integer getPictureId() {
		return pictureId;
	}
	public void setPictureId(Integer pictureId) {
		this.pictureId = pictureId;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public com.picture.model.PictureVO getPicVO(){
		com.picture.service.PictureService picSvc = new com.picture.service.PictureService();
		com.picture.model.PictureVO picVO = picSvc.getOne(pictureId);
		return picVO;
	}
	public String toString() {
		return "PetVO[ "+ petId + ", " + memberId + ", " + petName + ", " + sort1Id+ ", " + gender+ ", " + introduction+ ", " + pictureId+ ", " + birthday+ ", " + status + " ]";
	}
}
