package com.p_sort1.model;

import java.util.List;

import org.hibernate.hql.internal.ast.tree.BooleanLiteralNode;

import com.common.model.JDBCDAO_Interface;
import com.sort1.model.Sort1VO;

public interface PSort1DAO_interface extends JDBCDAO_Interface<PSort1VO>{

//查詢單種的商品的主分類"集合"要有名字的 (productId > sort1VOlist)
	public List<Sort1VO>  findSort1VOByproductId (Integer productId);
	
	public boolean deletePSort1sByProductId(Integer productId);
	
}
