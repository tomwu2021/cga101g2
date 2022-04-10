package com.orders.model;

import java.sql.Timestamp;

public class OrdersVO implements java.io.Serializable{
	private Integer order_id;
	private Integer member_id;
	 private String recipients;
	 private String phone;
	 private String address;
	 private Integer sum_price;
	 private Integer bonus;
	 private Integer discount;
	 private Integer pay_price;
	 private Integer status;
	 private Timestamp create_time;
	 
	 public OrdersVO() {

		}
	 
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
	public String getRecipients() {
		return recipients;
	}
	public void setRecipients(String recipients) {
		this.recipients = recipients;
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
	public Integer getSum_price() {
		return sum_price;
	}
	public void setSum_price(Integer sum_price) {
		this.sum_price = sum_price;
	}
	public Integer getBonus() {
		return bonus;
	}
	public void setBonus(Integer bonus) {
		this.bonus = bonus;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	public Integer getPay_price() {
		return pay_price;
	}
	public void setPay_price(Integer pay_price) {
		this.pay_price = pay_price;
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
