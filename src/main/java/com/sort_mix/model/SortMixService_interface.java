package com.sort_mix.model;

import java.util.List;
import java.util.Map;

import com.product.model.ProductVO;
import com.sort1.model.Sort1VO;
import com.sort2.model.Sort2VO;


public interface SortMixService_interface {
	
	//新增成功/失敗,新增前檢查是否有重複
	boolean save();
	
	boolean update();
	
	List<Sort1VO> getAll();
	
	List<SortMixVO> getAllSortMixVO();
	
	//新增獲得所有Sort2VO包含相對應的Sort1List
	List<Sort2VO> getAllSort2VOsandSort1List();
	
	//查詢某個主分類(包含對應的子分類)
	public Sort1VO findAllBySort1Id (Integer sort1Id);
	
	//查詢某個子分類(包含對應的主分類)
	public Sort2VO findSort2VOSort1VOsBySort2Id (Integer sort2Id);
	
	//用子分類ID sort2ID 找 Sort1VOList
	public List<Sort1VO> getSort1VOsBySort2Id (Integer sort2Id);
	
	//用主分類ID sort1ID 找 Sort2VOList
	public List<Sort2VO> getSort2VOsBySort1Id (Integer sort1Id);
	
	//輸入SortMixVO 尋找到對應的ProductVOList 只要一筆
	public List<ProductVO> getProductIdByMap(Map<String, String[]> map);
	

}