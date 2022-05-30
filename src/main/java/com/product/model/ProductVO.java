package com.product.model;

import java.sql.Timestamp;
import java.util.List;

import com.p_sort1.model.PSort1Service;
import com.p_sort1.model.PSort1VO;
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
	//為了後台多拉一張一對一的sort2Name
	private String sort2Name;
	private Timestamp updateTime;
	private Integer groupPrice1;
	private Integer groupAmount1;
	private Integer groupAmount2;
	private Integer groupAmount3;
	private String description;
	private Integer status;
	private Integer topStatus;
	private Integer cartAmount;
	private PictureVO pictureVO;
	private List<PictureVO> pictureVOList;
	private List<Sort1VO> PSort1VOList;
	
	private Integer totalView;
	
	public Integer getTotalView() {
		return totalView;
	}

	public void setTotalView(Integer totalView) {
		this.totalView = totalView;
	}

	public List<Sort1VO> getPSort1VOList() {
		return PSort1VOList;
	}

	public void setPSort1VOList(List<Sort1VO> pSort1VOList) {
		PSort1VOList = pSort1VOList;
	}

	public String getSort2Name() {
		return sort2Name;
	}

	public void setSort2Name(String sort2Name) {
		this.sort2Name = sort2Name;
	}

	public PictureVO getPictureVO() {
		return pictureVO;
	}

	public void setPictureVO(PictureVO pictureVO) {
		this.pictureVO = pictureVO;
	}

	public List<PictureVO> getPictureVOList() {
		return pictureVOList;
	}

	public void setPictureVOList(List<PictureVO> pictureVOList) {
		this.pictureVOList = pictureVOList;
	}

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
	@Override
	public String toString() {
		return "ProductVO [productId=" + productId + ", productName=" + productName + ", price=" + price + ", amount="
				+ amount + ", sort2Id=" + sort2Id + ", updateTime=" + updateTime + ", groupPrice1=" + groupPrice1
				+ ", groupAmount1=" + groupAmount1 + ", groupAmount2=" + groupAmount2 + ", groupAmount3=" + groupAmount3
				+ ", description=" + description + ", status=" + status + ", topStatus=" + topStatus + ", cartAmount="
				+ cartAmount + "]";
	}
	
	
}