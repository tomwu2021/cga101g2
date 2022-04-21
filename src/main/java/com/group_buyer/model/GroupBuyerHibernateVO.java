package com.group_buyer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
@Entity
@Table(name = "group_buyer")
@IdClass(GroupBuyerPK.class)
public class GroupBuyerHibernateVO {
	@Id
	@Column(name = "group_order_id")
	private Integer groupOrderId;
	@Id
	@Column(name = "member_id")
	 private Integer memberId;
	@Column(name = "product_amount")
	 private Integer productAmount;
	@Column(name = "phone")
	 private String phone;
	@Column(name = "address")
	 private String address;
	@Column(name = "recipient")
	 private String recipients;
	public Integer getGroupOrderId() {
		return groupOrderId;
	}
	public void setGroupOrderId(Integer groupOrderId) {
		this.groupOrderId = groupOrderId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(Integer productAmount) {
		this.productAmount = productAmount;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRecipients() {
		return recipients;
	}
	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String text = String.format(
				"groupOrderId:%d, memberId;:%d, productAmount:%s, phone:%s, address:%s, recipients:%s",
				groupOrderId, memberId, productAmount, phone, address, recipients);
		return text;
	}
}
