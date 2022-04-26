package com.sort_mix.model;

public class SortMixVO implements java.io.Serializable{

	/**
	 * Sort_maxVO
	 */
	private static final long serialVersionUID = 1L;
	private  Integer sort1Id;
	private  Integer sort2Id;
	private  String sort1Name;
	private  String sort2Name;
	
	public String getSort1Name() {
		return sort1Name;
	}
	public void setSort1Name(String sort1Name) {
		this.sort1Name = sort1Name;
	}
	public String getSort2Name() {
		return sort2Name;
	}
	public void setSort2Name(String sort2Name) {
		this.sort2Name = sort2Name;
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
