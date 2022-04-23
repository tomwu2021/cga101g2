package com.product.mapper;

import java.util.List;

import com.product.model.ProductVO;


	public interface ProductMapper {
		
		int insert(ProductVO productVO);
		
		int deleteById(Integer id);

		int updateById(ProductVO productVO);

		ProductVO getOneById(Integer id);

		List<ProductVO> getAll();
		
//		ProductVO selectBySort1Name(String sort1Name);
		
	}
