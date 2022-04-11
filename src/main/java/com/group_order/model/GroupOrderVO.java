package com.group_order.model;

import java.sql.Timestamp;

import javax.tools.JavaCompiler;

public class GroupOrderVO implements java.io.Serializable {
 private Integer groupOrderId;
 private Integer productId;
 private Timestamp createTime;
 private Timestamp endTime;
 private Integer endType;
 private Integer finalPrice;
 private Integer status;

 public GroupOrderVO() {
 }

public Integer getGroupOrderId() {
	return groupOrderId;
}

public void setGroupOrderId(Integer groupOrderId) {
	this.groupOrderId = groupOrderId;
}

public Integer getProductId() {
	return productId;
}

public void setProductId(Integer productId) {
	this.productId = productId;
}

public Timestamp getCreateTime() {
	return createTime;
}

public void setCreateTime(Timestamp createTime) {
	this.createTime = createTime;
}

public Timestamp getEndTime() {
	return endTime;
}

public void setEndTime(Timestamp endTime) {
	this.endTime = endTime;
}

public Integer getEndType() {
	return endType;
}

public void setEndType(Integer endType) {
	this.endType = endType;
}

public Integer getFinalPrice() {
	return finalPrice;
}

public void setFinalPrice(Integer finalPrice) {
	this.finalPrice = finalPrice;
}

public Integer getStatus() {
	return status;
}

public void setStatus(Integer status) {
	this.status = status;
}

 

}