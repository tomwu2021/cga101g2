package com.common.model;

public class MappingTableDto {
	private String tableName;
	private String column1;
	private String column2;
	private Integer id1;
	private Integer id2;

	public MappingTableDto() {
	
	}
	
	public MappingTableDto(String tableName, String column1, String column2, Integer id1, Integer id2) {
		super();
		this.tableName = tableName;
		this.column1 = column1;
		this.column2 = column2;
		this.id1 = id1;
		this.id2 = id2;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
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

}
