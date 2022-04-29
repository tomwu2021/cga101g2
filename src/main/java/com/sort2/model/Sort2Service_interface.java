package com.sort2.model;

import java.util.List;


public interface Sort2Service_interface {
		
		//新增成功/失敗,新增前檢查是否有重複
		boolean save(Sort2VO sort2VO);
		
		List<Sort2VO> findAll();
		
		//更新成功/失敗,更新前檢查是否有重複
		boolean updateB(Sort2VO sort2VO);
		
		public Sort2VO getOneById(Integer sort2Id);
	
}
