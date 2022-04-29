package com.sort_mix.model;

import java.util.List;

import com.sort1.model.Sort1VO;
import com.sort2.model.Sort2VO;


public interface SortMixService_interface {
	
	//新增成功/失敗,新增前檢查是否有重複
	boolean save();
	
	boolean update();
	
	List<Sort1VO> getAll();
	
	//新增獲得所有Sort2VO包含相對應的Sort1List
	List<Sort2VO> getAllSort2VOsandSort1List();
	
	//查詢某個主分類(包含對應的子分類)
	public Sort1VO findAllBySort1Id (Integer sort1Id);
	
	//查詢某個子分類(包含對應的主分類)
	public Sort2VO findSort2VOSort1VOsBySort2Id (Integer sort2Id);
	
	//用子分類ID sort2ID 找 Sort1VO
	public List<Sort1VO> getSort1VOsBySort2Id (Integer sort2Id);

}