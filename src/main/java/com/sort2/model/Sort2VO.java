package com.sort2.model;

import java.util.List;

import com.sort1.model.Sort1VO;

public class Sort2VO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private  Integer sort2Id;
	private  String sort2Name;
	
	private List<Sort1VO> Sort1VOList;


	public List<Sort1VO> getSort1VOList() {
		return Sort1VOList;
	}

	public void setSort1VOList(List<Sort1VO> sort1voList) {
		Sort1VOList = sort1voList;
	}

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
