package com.p_sort1.model;

import java.util.List;

import com.sort1.model.Sort1VO;

public class PSort1VO implements java.io.Serializable{

	/**
	 * 定義Psort1VO
	 */
	private static final long serialVersionUID = 1L;
	private Integer sort1Id;
	private Integer productId;
	private List<Sort1VO> sort1VOList;
	
	public Integer getSort1Id() {
		return sort1Id;
	}
	public void setSort1Id(Integer sort1Id) {
		this.sort1Id = sort1Id;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public List<Sort1VO> getSort1VOList() {
		return sort1VOList;
	}
	public void setSort1VOList(List<Sort1VO> sort1voList) {
		sort1VOList = sort1voList;
	}
	@Override
	public String toString() {
		return "Psort1VO [sort1Id=" + sort1Id + ", productId=" + productId + ", sort1VOList=" + sort1VOList + "]";
	}


}
