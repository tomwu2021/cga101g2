package com.product_img.model;

import java.util.List;
import java.util.Set;

import com.emp.model.EmpVO;
import com.picture.model.PictureVO;

public class ProductImgService implements ProductImgDAO_interface{
	
	
	private ProductImgJDBCDAO dao;


	public  ProductImgService() {
	 dao = new ProductImgJDBCDAO();
	}
	
	public List<ProductImgVO> getAll() {
		return dao.getAll();
	}
	

	//用商品id找到PictureVO的"集合"們
	@Override
	public List<PictureVO> getPicVOsByProductId(Integer productId) {
		System.out.println("ProductImgService執行");
		return dao.getPicVOsByProductId(productId);
	}
	//用商品id找到ProductImgVO的"集合"們
	@Override
	public List<ProductImgVO> getProductImgVOsByProductId(Integer productId) {
		return dao.getProductImgVOsByProductId(productId);
	}
	
	public void deleteProductImgById(ProductImgVO productImgid) {
		dao.delete(productImgid);
	}

	@Override
	public ProductImgVO insert(ProductImgVO productImgVO) {
		return dao.insert(productImgVO);
	}

	@Override
	public boolean delete(ProductImgVO t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ProductImgVO update(ProductImgVO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductImgVO getOneById(Integer id) {
		return dao.getOneById(id);
	}


	
}
		
