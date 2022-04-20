package com.sort1.mapper;

import com.sort1.model.Sort1VO;
import java.util.List;

public interface Sort1Mapper {

	int insert(Sort1VO sort1VO);
	
	int deleteById(Integer id);

	int updateById(Sort1VO sort1VO);

	Sort1VO getOneById(Integer id);

	List<Sort1VO> selectAll();
	
//	參照帳號重複 
//	if (dao.selectByUsername(member.getUsername()) != null)
	Sort1VO selectBySort1Name(String sort1Name);
	
}
