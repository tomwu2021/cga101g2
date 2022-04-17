package com.sort_mix.model;

public class SortMixVO implements java.io.Serializable{

	/**
	 * Sort_maxVO
	 */
	private static final long serialVersionUID = 1L;
	private  Integer sort1Id;
	private  Integer sort2Id;
	
	public SortMixVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SortMixVO(Integer sort1Id, Integer sort2Id) {
		super();
		this.sort1Id = sort1Id;
		this.sort2Id = sort2Id;
	}

	public Integer getSort1Id() {
		return sort1Id;
	}
	public void setSort1Id(Integer sort1Id) {
		this.sort1Id = sort1Id;
	}
	public Integer getSort2Id() {
		return sort2Id;
	}
	public void setSort2Id(Integer sort2Id) {
		this.sort2Id = sort2Id;
	}



}
