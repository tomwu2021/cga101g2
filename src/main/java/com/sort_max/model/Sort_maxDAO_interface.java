package com.sort_max.model;

import java.util.List;

public interface Sort_maxDAO_interface {
	
	public void insert(Sort_maxVO sort_max);
//    public void update(Sort_maxVO sort_max);
    public void delete(Sort_maxVO sort_max);
    public List<Sort_maxVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
