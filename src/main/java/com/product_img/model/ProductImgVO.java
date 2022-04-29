package com.product_img.model;

public class ProductImgVO {

	private Integer productImgId;
	private Integer productId;

	public Integer getProductImgId() {
		return productImgId;
	}

	public void setProductImgId(Integer productImgId) {
		this.productImgId = productImgId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "ProductImgVO [productImgId=" + productImgId + ", productId=" + productId + "]";
	}

}
