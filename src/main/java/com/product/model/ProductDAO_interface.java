package com.product.model;

import java.util.Set;

import com.common.model.JDBCDAO_Interface;
import com.emp.model.EmpVO;
import com.product_img.model.ProductImgVO;

public interface ProductDAO_interface extends JDBCDAO_Interface<ProductVO> {

	
	// 改變推薦狀態 回傳 1/-1代表成功或是失敗
	public boolean deleteByTopStatus(ProductVO productVO);

	 //查詢某部門的員工(一對多)(回傳 Set)
//	public Set<EmpVO> getEmpsByDeptno(Integer deptno);
	public Set<ProductImgVO> getPImgVOsByPdID(Integer pImgID);
	
	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map);
}