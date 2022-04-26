package com.sort2.model;


import java.util.List;

import com.common.model.JDBCDAO_Interface;
import com.sort1.model.Sort1VO;

public interface Sort2DAO_interface extends  JDBCDAO_Interface<Sort2VO>{

//	防止重複
	Sort2VO selectBySort2Name(String sort2Name);
	
	public List<Sort1VO> sort1VOList();
	
}
