package com.product_img.model;

import java.util.List;
import java.util.Set;

import com.emp.model.EmpVO;

public class ProductImgService {
	
	
	private ProductImgJDBCDAO dao;

	public  ProductImgService() {
	 dao = new ProductImgJDBCDAO();
	}
	
	public List<ProductImgVO> getAll() {
		return dao.getAll();
	}

	public List<ProductImgVO> getImgsByProductId(Integer productid) {
		return  dao.getImgsByProductId(productid);
	}

	public void deleteProductImgById(ProductImgVO productImgid) {
		dao.delete(productImgid);
	}
	
}
		
