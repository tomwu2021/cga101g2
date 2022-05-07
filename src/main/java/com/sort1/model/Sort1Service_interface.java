package com.sort1.model;

import java.util.List;


public interface Sort1Service_interface {
	
	//新增成功/失敗,新增前檢查是否有重複
	boolean save(Sort1VO sort1VO);
	
	List<Sort1VO> getAll();
	
	//更新成功/失敗,更新前檢查是否有重複
	boolean updateB(Sort1VO sort1VO);
	
	Sort1VO getOneById(Integer sort1Id);
	
}
