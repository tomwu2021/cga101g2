package com.product.model;

import java.sql.Timestamp;
import java.util.List;

import com.p_sort1.model.PSort1Service;
import com.picture.model.PictureVO;
import com.sort1.model.Sort1VO;
import com.sort2.model.Sort2Service;
import com.sort2.model.Sort2VO;

public class ProductVO implements java.io.Serializable {
	/**
	 * 定義ProductVO
	 */
	private static final long serialVersionUID = 1L;

// Date 型態改為Timestamp
	private Integer productId;
	private String productName;
	private Integer price;
	private Integer amount;
	private Integer sort2Id;
	private Timestamp updateTime;
	private Integer groupPrice1;
	private Integer groupAmount1;
	private Integer groupAmount2;
	private Integer groupAmount3;
	private String description;
	private Integer status;
	private Integer topStatus;
	private Integer cartAmount;
	

	public Integer getCartAmount() {
		return cartAmount;
	}

	public void setCartAmount(Integer cartAmount) {
		this.cartAmount = cartAmount;
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

//for join dname from deptno
//public com.dept.model.DeptVO getDeptVO() {
//com.dept.model.DeptService deptSvc = new com.dept.model.DeptService();
//com.dept.model.DeptVO deptVO = deptSvc.getOneDept(deptno);
//return deptVO;
//}
//for join dname from deptno
//在多方的VO放入一方的SERVICE的GETBYID
	
	
////	找到ProductImgVOList btye[]
//	public List<ProductImgVO> getProductImgVOList() {
//		com.product_img.model.ProductImgService daoSvc = new com.product_img.model.ProductImgService();
//		List<ProductImgVO> productImgVOList= daoSvc.getProductImgVOsByProductId(productId);
//		return productImgVOList;
//	}
//	找到Sort2VO
	public Sort2VO getSort2VO() {
		Sort2Service daoSvc = new Sort2Service();
		Sort2VO sort2VO = daoSvc.getOneById(sort2Id);
		return sort2VO;
	}
	
//	找到Sort1VOList
	public List<Sort1VO> getSort1VOList() {
		PSort1Service daoSvc = new PSort1Service();
		List<Sort1VO> sort1VOList = daoSvc.findSort1VOByproductId(productId);
		return sort1VOList;
	}
	
//	找到picV	O
	public List<PictureVO> getPictureVOList() {
		com.product_img.model.ProductImgService daoSvc = new com.product_img.model.ProductImgService();
		List<PictureVO> pictureVOList = daoSvc.getPicVOsByProductId(productId);
		return pictureVOList;
	}

}