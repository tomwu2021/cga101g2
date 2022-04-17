package com.prodouct.model;


import com.common.model.JDBCDAO_Interface;

public interface ProductDAO_interface extends  JDBCDAO_Interface<ProductVO>{
	
	//改變推薦狀態 回傳 1/-1代表成功或是失敗
	  int deleteByTopStatus(ProductVO productVO);
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map);
}