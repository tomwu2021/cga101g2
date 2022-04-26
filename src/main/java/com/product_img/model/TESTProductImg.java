package com.product_img.model;

import java.util.List;

public class TESTProductImg {
	
	public static void main(String[] args) {
		ProductImgJDBCDAO dao = new ProductImgJDBCDAO();
		
		List<ProductImgVO> list = dao.getAll();
		for (ProductImgVO aProductImgVO : list) {
			System.out.print(aProductImgVO.getProductImgId()+ ",");
			System.out.print(aProductImgVO.getProductId() + ",");
			System.out.print(aProductImgVO.getProductImgUrl() + ",");
			System.out.print(aProductImgVO.getFileKey()+ ",");
			System.out.print(aProductImgVO.getFileName() + ",");
			System.out.print(aProductImgVO.getSize() + ",");
			System.out.print(aProductImgVO.getPreviewUrl() + ",");
			System.out.println("---------------------");
		}
	}

}
