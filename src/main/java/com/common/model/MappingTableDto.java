package com.common.model;

public class MappingTableDto {
	private String tableName1;
	private String tablename2;
	private String column1;
	private String column2;
	private Integer id1;
	private Integer id2;
	
	public MappingTableDto() {
		
	}
	public MappingTableDto(String tableName1, String tablename2, String column1, String column2, Integer id1,
			Integer id2) {
		super();
		this.tableName1 = tableName1;
		this.tablename2 = tablename2;
		this.column1 = column1;
		this.column2 = column2;
		this.id1 = id1;
		this.id2 = id2;
	}
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
	public String getColumn1() {
		return column1;
	}
	public void setColumn1(String column1) {
		this.column1 = column1;
	}
	public String getColumn2() {
		return column2;
	}
	public void setColumn2(String column2) {
		this.column2 = column2;
	}
	public Integer getId1() {
		return id1;
	}
	public void setId1(Integer id1) {
		this.id1 = id1;
	}
	public Integer getId2() {
		return id2;
	}
	public void setId2(Integer id2) {
		this.id2 = id2;
	}
	@Override
	public String toString() {
		return "MappingTableDto:{tableName1:" + tableName1 + ", tablename2:" + tablename2 + ", column1:" + column1
				+ ", column2:" + column2 + ", id1:" + id1 + ", id2:" + id2 + "}";
	}

	
}
