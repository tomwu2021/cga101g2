package com.prodouct.model;

import java.sql.Timestamp;

public class ProductVO implements java.io.Serializable{
  /**
	 * 定義ProductVO
	 */
 private static final long serialVersionUID = 1L;
 
// Date 要改Timestamp
 
 private Integer product_id;
 private String product_name;
 private Integer price;
 private Integer amount;
 private Timestamp update_time;
 private Integer group_amount1;
 private Integer group_amount2;
 private Integer group_amount3;
 private Integer group_price1;
 private Integer sort2_id;
 private String description;
 private Integer status;
 private Integer top_status;
public Integer getProduct_id() {
	return product_id;
}
public void setProduct_id(Integer product_id) {
	this.product_id = product_id;
}
public String getProduct_name() {
	return product_name;
}
public void setProduct_name(String product_name) {
	this.product_name = product_name;
}
public Integer getPrice() {
	return price;
}
public void setPrice(Integer price) {
	this.price = price;
}
public Integer getAmount() {
	return amount;
}
public void setAmount(Integer amount) {
	this.amount = amount;
}
public Timestamp getUpdate_time() {
	return update_time;
}
public void setUpdate_time(Timestamp update_time) {
	this.update_time = update_time;
}
public Integer getGroup_amount1() {
	return group_amount1;
}
public void setGroup_amount1(Integer group_amount1) {
	this.group_amount1 = group_amount1;
}
public Integer getGroup_amount2() {
	return group_amount2;
}
public void setGroup_amount2(Integer group_amount2) {
	this.group_amount2 = group_amount2;
}
public Integer getGroup_amount3() {
	return group_amount3;
}
public void setGroup_amount3(Integer group_amount3) {
	this.group_amount3 = group_amount3;
}
public Integer getGroup_price1() {
	return group_price1;
}
public void setGroup_price1(Integer group_price1) {
	this.group_price1 = group_price1;
}
public Integer getSort2_id() {
	return sort2_id;
}
public void setSort2_id(Integer sort2_id) {
	this.sort2_id = sort2_id;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public Integer getStatus() {
	return status;
}
public void setStatus(Integer status) {
	this.status = status;
}
public Integer getTop_status() {
	return top_status;
}
public void setTop_status(Integer top_status) {
	this.top_status = top_status;
}

 
 
}
