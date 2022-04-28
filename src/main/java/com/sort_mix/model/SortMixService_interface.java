package com.sort_mix.model;

import java.util.List;

import com.common.model.MappingTableDto;
import com.sort1.model.Sort1VO;
import com.sort2.model.Sort2VO;


public interface SortMixService_interface {
	
	//新增成功/失敗,新增前檢查是否有重複
	boolean save();
	
	boolean update();
	
	List<Sort1VO> getAll();
	
	//查詢某個主分類(包含對應的子分類)
	public Sort1VO findAllBySort1Id (Integer sort1Id);
	
	//查詢某個子分類(包含對應的主分類)
	public Sort2VO findAllBySort2Id (Integer sort2Id);

}