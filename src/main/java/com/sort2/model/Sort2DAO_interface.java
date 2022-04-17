package com.sort2.model;


import com.common.model.JDBCDAO_Interface;

public interface Sort2DAO_interface extends  JDBCDAO_Interface<Sort2VO>{

//	模仿偉銘老師的帳號重複 
	Sort2VO selectBySort2Name(String sort2Name);
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
