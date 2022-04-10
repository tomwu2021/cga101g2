package com.group_buyer.model;

public class GroupBuyerVO  implements java.io.Serializable{
	private Integer group_order_id;
	 private Integer member_id;
	 private Integer product_amount;
	 private String phone;
	 private String address;
	 private String recipients;

	 public GroupBuyerVO() {
	 }

	 public Integer getGroup_order_id() {
	  return group_order_id;
	 }

	 public void setGroup_order_id(Integer group_order_id) {
	  this.group_order_id = group_order_id;
	 }

	 public Integer getMember_id() {
	  return member_id;
	 }

	 public void setMember_id(Integer member_id) {
	  this.member_id = member_id;
	 }

	 public Integer getProduct_amount() {
	  return product_amount;
	 }

	 public void setProduct_amount(Integer product_amount) {
	  this.product_amount = product_amount;
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


}
