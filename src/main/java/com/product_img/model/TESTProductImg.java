package com.product_img.model;

import java.util.List;

import com.picture.model.PictureVO;

public class TESTProductImg {
	
	public static void main(String[] args) {
		ProductImgJDBCDAO dao = new ProductImgJDBCDAO();
		
//		List<ProductImgVO> list = dao.getAll();
//		for (ProductImgVO aProductImgVO : list) {
//			System.out.print(aProductImgVO.getProductImgId()+ ",");
//			System.out.print(aProductImgVO.getProductId() + ",");
//			System.out.println("---------------------");
//		}
		
		List<PictureVO> list2 = dao.getPicVOsByProductId(6);
		for (PictureVO picVO : list2) {
			System.out.print(picVO.getPictureId() +",");
			System.out.print(picVO.getUrl() +",");
			System.out.print(picVO.getCreateTime() +",");
			System.out.print(picVO.getFileKey() +",");
			System.out.print(picVO.getFileName() +",");
			System.out.print(picVO.getSize() +",");
			System.out.print(picVO.getPreviewUrl() +",");
			System.out.print(picVO.getPreviewKey() +",");
			System.out.println("---------------------");
		}
		
	}

}
