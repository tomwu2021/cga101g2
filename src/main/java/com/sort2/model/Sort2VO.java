package com.sort2.model;

import java.util.List;

import com.sort1.model.Sort1VO;

public class Sort2VO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private  Integer sort2Id;
	private  String sort2Name;
	public List<Sort1VO> sort1VOList;

	public List<Sort1VO> getSort1VOList() {
		return sort1VOList;
	}

	public void setSort1VOList(List<Sort1VO> sort1voList) {
		sort1VOList = sort1voList;
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

	
	//for join dname from deptno
	//public com.dept.model.DeptVO getDeptVO() {
	//com.dept.model.DeptService deptSvc = new com.dept.model.DeptService();
	//com.dept.model.DeptVO deptVO = deptSvc.getOneDept(deptno);
	//return deptVO;
	//}
	//for join dname from deptno
	//在多方的VO放入一方的SERVICE的GETBYID
		
		
//		搜尋Sort2ID時 透過 sortmix 獲得相對應的Sort1VO
		public List<Sort1VO> getSort1VO() {
			com.sort_mix.model.SortMixService daoSvc = new com.sort_mix.model.SortMixService();
			 List<Sort1VO> sort1VO = daoSvc.getSort1VOsBySort2Id(sort2Id);
			return sort1VO;
		}
}
