package com.sort1.service;

import java.util.List;

import com.sort1.model.Sort1VO;


public interface Sort1Service_interface {
	
	//新增成功/失敗,新增前檢查是否有重複
	boolean save(Sort1VO sort1VO);
	
	List<Sort1VO> findAll();
	
	//更新成功/失敗,更新前檢查是否有重複
	boolean updateB(Sort1VO sort1VO);
}
