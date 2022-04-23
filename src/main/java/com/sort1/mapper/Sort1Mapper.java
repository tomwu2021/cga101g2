package com.sort1.mapper;

import java.util.List;

import com.sort1.model.Sort1VO;

public interface Sort1Mapper {
	
	int insert(Sort1VO sort1VO);
	
	int deleteById(Integer id);

	int updateById(Sort1VO sort1VO);

	Sort1VO getOneById(Integer id);

	List<Sort1VO> selectAll();
	
	Sort1VO selectBySort1Name(String sort1Name);
	
}
