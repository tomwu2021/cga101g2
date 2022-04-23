package com.sort2.mapper;

import java.util.List;

import com.sort2.model.Sort2VO;

public interface Sort2Mapper {
	int insert(Sort2VO sort2VO);

	int deleteById(Integer id);

	int updateById(Sort2VO sort2VO);

	Sort2VO getOneById(Integer id);

	List<Sort2VO> selectAll();
	
	Sort2VO selectBySort2Name(String sort2Name);
}
