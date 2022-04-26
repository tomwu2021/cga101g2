package com.product.model;

import java.util.List;
import java.util.Set;

import com.picture.model.PictureDAO_Interface;

public class ProductService {
	
	
	private ProductDAO_interface dao;

	public  ProductService() {
	 dao = new ProductJDBCDAO();
	}
	
	public ProductVO getOneProductByid(Integer prodouctId) {
		return dao.getOneById(prodouctId);
	}

	public List<ProductVO> getAll() {
		return dao.getAll();
	}

}
