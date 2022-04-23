package com.sort_mix.service;

import java.util.List;

import com.common.model.MappingTableDto;
import com.sort_mix.model.SortMixMapVO;


public interface SortMixService_interface {
	
	//新增成功/失敗,新增前檢查是否有重複
	boolean save(MappingTableDto mtd);
	
	boolean update(MappingTableDto mtd);
	
	List<SortMixMapVO> getAll();

}