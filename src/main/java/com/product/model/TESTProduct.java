package com.product.model;

import java.util.List;

import com.product_img.model.ProductImgVO;

public class TESTProduct {

	public static void main(String[] args) {

		ProductJDBCDAO dao = new ProductJDBCDAO();

		// 新增
		ProductVO ProductVO1 = new ProductVO();
		ProductVO1.setProductName("2111");
		ProductVO1.setPrice(600);
		ProductVO1.setAmount(30);
		ProductVO1.setGroupAmount1(50);
		ProductVO1.setGroupAmount2(60);
		ProductVO1.setGroupAmount3(70);
		ProductVO1.setGroupPrice1(580);
		ProductVO1.setSort2Id(3);
		ProductVO1.setDescription("666");
		dao.insert(ProductVO1);

		// 更新 測試名字是空值時會阻擋
//		ProductVO ProductVO2 = new ProductVO();
//		ProductVO2.setProductName("ss ");
//		ProductVO2.setPrice(1);
//		ProductVO2.setAmount(33);
//		ProductVO2.setGroupAmount1(40);
//		ProductVO2.setGroupAmount2(50);
//		ProductVO2.setGroupAmount3(70);
//		ProductVO2.setGroupPrice1(70);
//		ProductVO2.setSort2Id(7);
//		ProductVO2.setDescription(" d");
//		ProductVO2.setProductId(2);
//		dao.update(ProductVO2);

		// 改變一般商品與團購商品的上下架狀態
//		ProductVO productVO3 = new ProductVO();
//		productVO3.setStatus(3);
//		productVO3.setProductId(2);
//		dao.delete(productVO3);

		// 改變一般商品的推薦狀態
//		ProductVO productVO4 = new ProductVO();
//		productVO4.setTopStatus(1);
//		productVO4.setProductId(999);
//		dao.deleteByTopStatus(productVO4);

		// 用id查詢單一商品
//		ProductVO ProductVO3 = dao.getOneById(3);
//		System.out.print(ProductVO3.getProductId() + ",");
//		System.out.print(ProductVO3.getProductName() + ",");
//		System.out.print(ProductVO3.getPrice() + ",");
//		System.out.print(ProductVO3.getAmount() + ",");
//		System.out.print(ProductVO3.getUpdateTime() + ",");
//		System.out.print(ProductVO3.getGroupAmount1() + ",");
//		System.out.print(ProductVO3.getGroupAmount2() + ",");
//		System.out.print(ProductVO3.getGroupAmount3() + ",");
//		System.out.print(ProductVO3.getGroupPrice1() + ",");
//		System.out.print(ProductVO3.getSort2Id() + ",");
//		System.out.print(ProductVO3.getDescription() + ",");
//		System.out.print(ProductVO3.getStatus() + ",");
//		System.out.println(ProductVO3.getTopStatus());
//		System.out.println("---------------------");

//		List<ProductVO> list = dao.getAll();
//		for (ProductVO aProductVO : list) {
//			System.out.print(aProductVO.getProductId() + ",");
//			System.out.print(aProductVO.getProductName() + ",");
//			System.out.print(aProductVO.getPrice() + ",");
//			System.out.print(aProductVO.getAmount() + ",");
//			System.out.print(aProductVO.getUpdateTime() + ",");
//			System.out.print(aProductVO.getGroupAmount1() + ",");
//			System.out.print(aProductVO.getGroupAmount2() + ",");
//			System.out.print(aProductVO.getGroupAmount3() + ",");
//			System.out.print(aProductVO.getGroupPrice1() + ",");
//			System.out.print(aProductVO.getSort2Id() + ",");
//			System.out.print(aProductVO.getDescription() + ",");
//			System.out.print(aProductVO.getStatus() + ",");
//			System.out.println(aProductVO.getTopStatus());
//			System.out.println("---------------------");
//		}

//		for (ProductVO m1 : dao.getAll()) {
//			System.out.println(m1);
//		}
		
//		List<ProductVO> list = dao.getAll();
//		for (ProductVO aProductVO : list) {
//			System.out.print(aProductVO.getProductId() + ",");
//			System.out.print(aProductVO.getProductName() + ",");
//			System.out.print(aProductVO.getPrice() + ",");
//			System.out.print(aProductVO.getAmount() + ",");
//			System.out.print(aProductVO.getUpdateTime() + ",");
//			System.out.print(aProductVO.getGroupAmount1() + ",");
//			System.out.print(aProductVO.getGroupAmount2() + ",");
//			System.out.print(aProductVO.getGroupAmount3() + ",");
//			System.out.print(aProductVO.getGroupPrice1() + ",");
//			System.out.print(aProductVO.getSort2Id() + ",");
//			System.out.print(aProductVO.getDescription() + ",");
//			System.out.print(aProductVO.getStatus() + ",");
//			System.out.println(aProductVO.getTopStatus());
//			System.out.println("---------------------");
//		}
//		
	}
}