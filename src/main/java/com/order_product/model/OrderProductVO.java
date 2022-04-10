package com.order_product.model;

public class OrderProductVO implements java.io.Serializable{
	 private Integer order_id;
	 private Integer product_id;
	 private Integer product_amount;
	 private Integer order_price;
	 
	 public OrderProductVO() {
	 }

	public Integer getOrder_id() {
		return order_id;
	}
	
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	
	public Integer getProduct_id() {
		return product_id;
	}
	
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	
	public Integer getProduct_amount() {
		return product_amount;
	}
	
	public void setProduct_amount(Integer product_amount) {
		this.product_amount = product_amount;
	}
	
	public Integer getOrder_price() {
		return order_price;
	}
	
	public void setOrder_price(Integer order_price) {
		this.order_price = order_price;
	}

}
