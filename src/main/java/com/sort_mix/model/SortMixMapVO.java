package com.sort_mix.model;

public class SortMixMapVO implements java.io.Serializable{

	/**
	 * 定義Sort2VO
	 */
	private static final long serialVersionUID = 1L;
	private String tableName1;
	private String tablename2;
	private String sort1Name;
	private String sort2Name;
	private Integer sort1Id;
	private Integer sort2Id;
	
	public String getTableName1() {
		return tableName1;
	}
	public void setTableName1(String tableName1) {
		this.tableName1 = tableName1;
	}
	public String getTablename2() {
		return tablename2;
	}
	public void setTablename2(String tablename2) {
		this.tablename2 = tablename2;
	}
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
