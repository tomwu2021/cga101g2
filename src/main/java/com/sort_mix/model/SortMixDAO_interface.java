package com.sort_mix.model;

import java.util.List;

import com.common.model.JDBCDAO_Interface;
import com.sort1.model.Sort1VO;
import com.sort2.model.Sort2VO;
//定義接口類別
public interface SortMixDAO_interface extends JDBCDAO_Interface<SortMixVO>{
	
	//查詢某個主分類(包含對應的子分類)
	public Sort1VO findAllBySort1Id (Integer sort1Id);
	
	//查詢某個子分類(包含對應的主分類)
	public Sort2VO findAllBySort2Id (Integer sort2Id);
	
//	//用子分類ID sort1ID 找 Sort1VO
//	public Sort1VO findSort1VOBySort2Id (Integer sort2Id);
	
}