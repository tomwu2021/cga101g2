package com.product_img.model;

import java.util.Arrays;

public class ProductImgVO {

	private Integer productImgId;
	private Integer productId;
	private byte[] image;
	
	
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

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
		return "ProductImgVO [productImgId=" + productImgId + ", productId=" + productId + ", image="
				+ Arrays.toString(image) + "]";
	}

	
}
