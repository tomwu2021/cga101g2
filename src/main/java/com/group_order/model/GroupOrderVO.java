package com.group_order.model;

import java.sql.Timestamp;

import javax.tools.JavaCompiler;

import com.picture.model.PictureVO;
import com.product.model.ProductVO;

public class GroupOrderVO implements java.io.Serializable {
	private Integer groupOrderId;
	private Integer productId;
	private Timestamp createTime;
	private Timestamp endTime;
	private Integer endType;
	private Integer finalPrice;
	private Integer status;
	private Integer minAmount;
	private ProductVO productVO;

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

	public void setFinalPrice(Integer d) {
		this.finalPrice = d;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

	public Integer getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(Integer minAmount) {
		this.minAmount = minAmount;
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
				"groupOrderId:%d, productId:%d, createTime:%s, endTime:%s, endType:%d, finalPrice:%d, status:%d",
				groupOrderId, productId, createTime, endTime, endType, finalPrice, status);
		return text;
	}

}