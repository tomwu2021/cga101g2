package com.sort2.model;

import java.util.List;

public interface Sort2DAO_interface {
    public void insert(Sort2VO Sort2);
    public void update(Sort2VO Sort2);
//    public void delete(Integer Sort1);
    public List<Sort2VO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
