package com.product_img.model;

import java.util.Set;

public class ProductImgVO {

	private Integer productImgId;
	private Integer productId;
	private String productImgUrl;
	private String fileKey;
	private String fileName;
	private String size;
	private String previewUrl;
	

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
	
//    // for join dname from deptno
//    public com.dept.model.DeptVO getDeptVO() {
//	    com.dept.model.DeptService deptSvc = new com.dept.model.DeptService();
//	    com.dept.model.DeptVO deptVO = deptSvc.getOneDept(deptno);
//	    return deptVO;
//    }
	//for join dname from deptno
//	在多方的VO放入一方的SERVICE的GETBYID
	public com.product.model.ProductVO getProductVO() {
		com.product.model.ProductService daoSvc = new com.product.model.ProductService();
		com.product.model.ProductVO productVO = daoSvc.getOneProductByid(productId);
		return productVO;
	}
	
//	public com.product_img.model.ProductImgVO getProductImgVOs() {
//		com.product_img.model.ProductImgService daoSvc = new com.product_img.model.ProductImgService();
//		Set<ProductImgVO> ProductImgVO = daoSvc.getImgsByProductId(productId);
//		return (com.product_img.model.ProductImgVO) ProductImgVO;
//	}
	
}
