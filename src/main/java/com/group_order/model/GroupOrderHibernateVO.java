package com.group_order.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "group_order")
public class GroupOrderHibernateVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_order_id")
	private Integer groupOrderId;
	@Column(name = "product_id")
	private Integer productId;
	@Column(name = "create_time",updatable = false,insertable = false)
	private Timestamp createTime;
	@Column(name = "end_time")
	private Timestamp endTime;
	@Column(name = "end_type")
	private Integer endType;
	@Column(name = "final_price")
	private Integer finalPrice;
	@Column
	private Integer status;
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
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String text = String.format(
				"groupOrderId:%d, productId:%d, createTime:%s, endTime:%s, endType:%d, finalPrice:%d, status:%d",
				groupOrderId, productId, createTime, endTime, endType, finalPrice, status);
		return text;
	}
	
	
}
