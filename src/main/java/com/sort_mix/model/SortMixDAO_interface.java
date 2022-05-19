package com.sort_mix.model;

import java.util.List;
import java.util.Map;

import com.common.model.JDBCDAO_Interface;
import com.product.model.ProductVO;
import com.sort1.model.Sort1VO;
import com.sort2.model.Sort2VO;
//定義接口類別
public interface SortMixDAO_interface extends JDBCDAO_Interface<SortMixVO>{
	
	//查詢某個主分類(包含對應的子分類)
	public Sort1VO findAllBySort1Id (Integer sort1Id);
	
	//查詢某個子分類(包含對應的主分類)
	public Sort2VO findSort2VOSort1VOsBySort2Id (Integer sort2Id);
	
	//用子分類ID sort2ID 找 Sort1VOList
	public List<Sort1VO> getSort1VOsBySort2Id (Integer sort2Id);
	
	//用主分類ID sort1ID 找 Sort2VOList
	public List<Sort2VO> getSort2VOListBySort1Id (Integer sort1Id);
	
	//輸入SortMixVO 尋找到對應的ProductVOList 只要一筆
	public List<ProductVO> getProductIdByMap (Map<String, String[]> map);
	
	public boolean deleteBySort1Id(Integer sort1Id);
	
	public boolean deleteBySort2Id(Integer sort2Id);
	
	public SortMixVO getOneBySortMixVO(SortMixVO sortMixVO);
	
}