package com.sort1.model;

import java.util.List;

import com.sort2.model.Sort2VO;

public class Sort1VO implements java.io.Serializable {
	/**
	 * 定義Sort1VO
	 */
	private static final long serialVersionUID = 1L;
	private Integer sort1Id;
	private String sort1Name;
	private List<Sort2VO> Sort2VOList;
	
	public List<Sort2VO> getSort2VOList() {
		return Sort2VOList;
	}

	public void setSort2VOList(List<Sort2VO> sort2voList) {
		Sort2VOList = sort2voList;
	}

	public Integer getSort1Id() {
		return sort1Id;
	}

	public void setSort1Id(Integer sort1Id) {
		this.sort1Id = sort1Id;
	}

	public String getSort1Name() {
		return sort1Name;
	}

	public void setSort1Name(String sort1Name) {
		this.sort1Name = sort1Name;
	}

	@Override
	public String toString() {
		return "Sort1VO [sort1Id=" + sort1Id + ", sort1Name=" + sort1Name + "]";
	}
}
