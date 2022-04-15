package com.group_buyer.model;

public class GroupBuyerVO  implements java.io.Serializable{
	private Integer groupOrderId;
	 private Integer memberId;
	 private Integer productAmount;
	 private String phone;
	 private String address;
	 private String recipients;

	 public GroupBuyerVO() {
	 }

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
