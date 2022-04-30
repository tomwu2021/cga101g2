package com.product.model;

import java.util.List;

import com.p_sort1.model.PSort1Service;
import com.p_sort1.model.PSort1VO;
import com.sort1.model.Sort1Service;

public class ProductService {
	
	
	private ProductDAO_interface dao;

	public  ProductService() {
	 dao = new ProductJDBCDAO();
	}
	
	public ProductVO insertProduct(String productName, Integer price, Integer amount, Integer sort2Id, 
			Integer groupPrice1, Integer groupAmount1, Integer groupAmount2, Integer groupAmount3,
			String description, String sort1Id[]) {
//通過servlet驗證後製作商品DAO的材料
//1.打包pdVO
		ProductVO pdVO = new ProductVO();
		pdVO.setProductName(productName);
		pdVO.setPrice(price);
		pdVO.setAmount(amount);
		pdVO.setSort2Id(sort2Id);
		pdVO.setGroupPrice1(groupPrice1);
		pdVO.setGroupAmount1(groupAmount1);
		pdVO.setGroupAmount2(groupAmount2);
		pdVO.setGroupAmount3(groupAmount3);
		pdVO.setDescription(description);
//2.DAO新增pdVO
		ProductVO productVO = new ProductVO();
		productVO = dao.insert(pdVO);
//3.拿取DAO回傳的newProductVO的PKID
		int newProductId = productVO.getProductId();
		System.out.println("新增的newProductId" +newProductId);
//呼叫Psort1Service
		PSort1Service pSort1Svc = new PSort1Service();
		for (int i = 0; i < sort1Id.length; i++) {
			System.out.println("從addproduct.jsp獲得sort1id"+sort1Id[i]);
			PSort1VO pSort1VO = new PSort1VO();
			pSort1VO.setProductId(newProductId);
			pSort1VO.setSort1Id(Integer.valueOf(sort1Id[i]));
			pSort1Svc.insert(pSort1VO);
			System.out.println("pSort1Svc 新增成功");
		}
		return productVO;
	}
	
	
	public ProductVO getOneProductByid(Integer prodouctId) {
		return dao.getOneById(prodouctId);
	}

	public List<ProductVO> getAll() {
		return dao.getAll();
	}

}
