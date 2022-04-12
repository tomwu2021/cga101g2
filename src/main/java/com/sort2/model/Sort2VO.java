package com.sort2.model;

public class Sort2VO implements java.io.Serializable{

	/**
	 * 定義Sort2VO
	 */
	private static final long serialVersionUID = 1L;
	private  Integer sort2_id;
	private  String sort2_name;
	
	
	
	public Sort2VO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Sort2VO(Integer sort2_id, String sort2_name) {
		super();
		this.sort2_id = sort2_id;
		this.sort2_name = sort2_name;
	}


	public Integer getSort2_id() {
		return sort2_id;
	}


	public void setSort2_id(Integer sort2_id) {
		this.sort2_id = sort2_id;
	}


	public String getSort2_name() {
		return sort2_name;
	}


	public void setSort2_name(String sort2_name) {
		this.sort2_name = sort2_name;
	}



	
	
}
