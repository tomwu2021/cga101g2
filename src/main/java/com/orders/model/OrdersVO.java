package com.orders.model;

import java.sql.Timestamp;

import com.picture.model.PictureVO;
import com.product.model.ProductVO;
import com.product_img.model.ProductImgVO;

public class OrdersVO implements java.io.Serializable{
	 private Integer orderId;
	 private Integer memberId;
	 private String recipient;
	 private String phone;
	 private String address;
	 private Integer sumPrice;
	 private Integer bonus;
	 private Integer discount;
	 private Integer payPrice;
	 private Integer status;
	 private Timestamp createTime;
	 //在mapping表欄位，直接宣告在這裡做
	 private Integer productAmount;
	 private Integer orderPrice;
	 private ProductVO productVO;
	 private PictureVO pictureVO;
	 
	 
	 public OrdersVO() {

		}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
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

	public Integer getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(Integer sumPrice) {
		this.sumPrice = sumPrice;
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

	public Integer getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(Integer payPrice) {
		this.payPrice = payPrice;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	

	public ProductVO getProductVO() {
		return productVO;
	}

	public void setProductVO(ProductVO productVO) {
		this.productVO = productVO;
	}
	
	
	public PictureVO getPictureVO() {
		return pictureVO;
	}

	public void setPictureVO(PictureVO pictureVO) {
		this.pictureVO = pictureVO;
	}
	
	

	public Integer getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(Integer productAmount) {
		this.productAmount = productAmount;
	}

	public Integer getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Integer orderPrice) {
		this.orderPrice = orderPrice;
	}

	@Override
	public String toString() {
		  String text = String.format(
		    "orderId:%d, memberId:%d, recipient:%s, phone:%s, address:%s, sumPrice:%d, bonus:%d, discount:%d, payPrice:%d, status:%d, createTime:%s",
		    orderId, memberId, recipient, phone, address, sumPrice, bonus, discount, payPrice, status, createTime);
		   return text;
		 }
	 
	 
}
