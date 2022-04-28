package com.p_sort1.model;

import com.sort1.model.Sort1VO;

public interface PSort1Service_interface {

	//查詢該商品的主分類"集合"
	public Sort1VO findSort1VOByproductId (Integer sort1Id);
}
