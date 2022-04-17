package com.sort1.model;

public class Sort1VO implements java.io.Serializable{
	/**
	 *定義Sort1VO
	 */
	private static final long serialVersionUID = 1L;
	private Integer sort1Id;
	private String sort1Name;
	
	public Sort1VO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Sort1VO(Integer sort1Id, String sort1Name) {
		super();
		this.sort1Id = sort1Id;
		this.sort1Name = sort1Name;
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
	

}
