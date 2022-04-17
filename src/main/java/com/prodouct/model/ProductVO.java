package com.prodouct.model;

import java.sql.Timestamp;

public class ProductVO implements java.io.Serializable{
  /**
	 * 定義ProductVO
	 */
 private static final long serialVersionUID = 1L;
 
// Date 型態改為Timestamp
 private Integer productId;
 private String productName;
 private Integer price;
 private Integer amount;
 private Timestamp updateTime;
 private Integer groupAmount1;
 private Integer groupAmount2;
 private Integer groupAmount3;
 private Integer groupPrice1;
 private Integer sort2Id;
 private String description;
 private Integer status;
 private Integer topStatus;
 
 
 
public ProductVO() {
	super();
	// TODO Auto-generated constructor stub
}
public ProductVO(Integer productId, String productName, Integer price, Integer amount, Timestamp updateTime,
		Integer groupAmount1, Integer groupAmount2, Integer groupAmount3, Integer groupPrice1, Integer sort2Id,
		String description, Integer status, Integer topStatus) {
	super();
	this.productId = productId;
	this.productName = productName;
	this.price = price;
	this.amount = amount;
	this.updateTime = updateTime;
	this.groupAmount1 = groupAmount1;
	this.groupAmount2 = groupAmount2;
	this.groupAmount3 = groupAmount3;
	this.groupPrice1 = groupPrice1;
	this.sort2Id = sort2Id;
	this.description = description;
	this.status = status;
	this.topStatus = topStatus;
}
public Integer getProductId() {
	return productId;
}
public void setProductId(Integer productId) {
	this.productId = productId;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
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
public Timestamp getUpdateTime() {
	return updateTime;
}
public void setUpdateTime(Timestamp updateTime) {
	this.updateTime = updateTime;
}
public Integer getGroupAmount1() {
	return groupAmount1;
}
public void setGroupAmount1(Integer groupAmount1) {
	this.groupAmount1 = groupAmount1;
}
public Integer getGroupAmount2() {
	return groupAmount2;
}
public void setGroupAmount2(Integer groupAmount2) {
	this.groupAmount2 = groupAmount2;
}
public Integer getGroupAmount3() {
	return groupAmount3;
}
public void setGroupAmount3(Integer groupAmount3) {
	this.groupAmount3 = groupAmount3;
}
public Integer getGroupPrice1() {
	return groupPrice1;
}
public void setGroupPrice1(Integer groupPrice1) {
	this.groupPrice1 = groupPrice1;
}
public Integer getSort2Id() {
	return sort2Id;
}
public void setSort2Id(Integer sort2Id) {
	this.sort2Id = sort2Id;
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
public Integer getTopStatus() {
	return topStatus;
}
public void setTopStatus(Integer topStatus) {
	this.topStatus = topStatus;
}

 
 
}
