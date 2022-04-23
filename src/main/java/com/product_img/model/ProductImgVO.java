package com.product_img.model;

public class ProductImgVO {

	private Integer productImgId;
	private Integer productId;
	private String productImgUrl;
	private String fileKey;
	private String fileName;
	private String size;
	private String previewUrl;
	
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getPreviewUrl() {
		return previewUrl;
	}
	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
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
	public String getProductImgUrl() {
		return productImgUrl;
	}
	public void setProductImgUrl(String productImgUrl) {
		this.productImgUrl = productImgUrl;
	}
	public String getFileKey() {
		return fileKey;
	}
	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}
	@Override
	public String toString() {
		return "ProductImgVO [productImgId=" + productImgId + ", productId=" + productId + ", productImgUrl="
				+ productImgUrl + ", fileKey=" + fileKey + "]";
	}
	
	
}
