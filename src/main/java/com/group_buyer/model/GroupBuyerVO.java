package com.group_buyer.model;

import com.group_order.model.GroupOrderVO;
import com.prodouct.model.ProductVO;

public class GroupBuyerVO  implements java.io.Serializable{
	 private Integer groupOrderId;
	 private Integer memberId;
	 private Integer productAmount;
	 private String phone;
	 private String address;
	 private String recipients;
	 private GroupOrderVO groupOrderVO;
	 private ProductVO productVO;

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
	
	

	public GroupOrderVO getGroupOrderVO() {
		return groupOrderVO;
	}

	public void setGroupOrderVO(GroupOrderVO groupOrderVO) {
		this.groupOrderVO = groupOrderVO;
	}

	public ProductVO getProductVO() {
		return productVO;
	}

	public void setProductVO(ProductVO productVO) {
		this.productVO = productVO;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String text = String.format(
				"groupOrderId:%d, memberId;:%d, productAmount:%s, phone:%s, address:%s, recipients:%s,groupOrderVO,productVO",
				groupOrderId, memberId, productAmount, phone, address, recipients);
		return text;
	}

}
