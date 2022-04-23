package com.sort2.model;

public class Sort2VO implements java.io.Serializable{

	/**
	 * 定義Sort2VO
	 */
	private static final long serialVersionUID = 1L;
	private  Integer sort2Id;
	private  String sort2Name;
//	private List<Sort1VO> Sort1VOs;
//
//	public List<Sort1VO> getSort2VOs() {
//		return Sort1VOs;
//	}
//
//	public void setSort2VOs(List<Sort1VO> sort1VOs) {
//		Sort1VOs = sort1VOs;
//	}

	public Sort2VO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Sort2VO(Integer sort2Id, String sort2Name) {
		super();
		this.sort2Id = sort2Id;
		this.sort2Name = sort2Name;
	}

	public Integer getSort2Id() {
		return sort2Id;
	}
	public void setSort2Id(Integer sort2Id) {
		this.sort2Id = sort2Id;
	}
	public String getSort2Name() {
		return sort2Name;
	}
	public void setSort2Name(String sort2Name) {
		this.sort2Name = sort2Name;
	}


	
}
