package com.sort1.model;

import com.common.model.JDBCDAO_Interface;
import com.sort2.model.Sort2VO;

public interface Sort1DAO_interface extends JDBCDAO_Interface<Sort1VO> {
	
//	模仿偉銘老師的帳號重複 
	Sort1VO selectBySort1Name(String sort1Name);
	//萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 

}
