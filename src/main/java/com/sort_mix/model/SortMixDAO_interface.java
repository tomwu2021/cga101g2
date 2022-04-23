package com.sort_mix.model;

import java.util.List;

import com.common.model.MappingTableDto;

public interface SortMixDAO_interface {
	
	int insert(SortMixMapVO sortMixMapVO);
	
	int delete(SortMixMapVO sortMixMapVO);

	int update(SortMixMapVO sortMixMapVO);

	List<SortMixMapVO> findAll();
	
	public List<SortMixMapVO> getBySet();
	
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map);
}
