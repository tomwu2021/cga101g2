package com.sort1.model;

import java.util.List;
import java.util.Set;

import com.common.model.JDBCDAO_Interface;
import com.emp.model.EmpVO;
import com.sort2.model.Sort2VO;

public interface Sort1DAO_interface extends JDBCDAO_Interface<Sort1VO> {
	
	//防止分類名重複,UQ
	Sort1VO selectBySort1Name(String sort1Name);
	
	int getSort1Count ();
	
}
