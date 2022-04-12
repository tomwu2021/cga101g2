package com.sort1.model;
import java.util.*;

	public interface Sort1DAO_interface {
	          public void insert(Sort1VO Sort1);
	          public void update(Sort1VO Sort1);
//	          public void delete(Integer Sort1);
	          public List<Sort1VO> getAll();
	          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//	        public List<EmpVO> getAll(Map<String, String[]> map); 
	}

