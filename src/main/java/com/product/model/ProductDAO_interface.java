package com.product.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.common.model.JDBCDAO_Interface;
import com.product_img.model.ProductImgVO;

public interface ProductDAO_interface extends JDBCDAO_Interface<ProductVO> {

	// 確認商品名稱是否重複
	public ProductVO checkProdcutName(String prodcutName);
		
	// 改變推薦狀態
	public boolean deleteByTopStatus(Integer topStatus,Integer productId);
	
	 //查詢某部門的員工(一對多)(回傳 Set)
//	public Set<EmpVO> getEmpsByDeptno(Integer deptno);
	public Set<ProductImgVO> getPImgVOsByPdID(Integer pImgID);
	
//	 萬用複合查詢(傳入參數型態Map)(回傳 List)
	public List<ProductVO> getAll(Map<String, String[]> map, int pageSize, int pageNo);

	public int getAllTotalCount(Map<String, String[]> map);
	
//	Shop用的公開頁面
	public List<ProductVO> getForShopFront(Map<String, String[]> map, int pageSize, int pageNo);
	
	public int getForShopFrontTotalCount(Map<String, String[]> map);
	
//	GroupSho用的公開頁面
	public List<ProductVO> getForGroupShopFront(Map<String, String[]> map);	
	
}