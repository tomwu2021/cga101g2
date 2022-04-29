package com.product.model;

import java.util.List;
import java.util.Set;

import com.emp.model.EmpVO;
import com.mysql.cj.x.protobuf.MysqlxCrud.Insert;
import com.picture.model.PictureDAO_Interface;

public class ProductService {
	
	
	private ProductDAO_interface dao;

	public  ProductService() {
	 dao = new ProductJDBCDAO();
	}
	
	public ProductVO insertProduct(String productName, Integer price, Integer amount, Integer sort2Id, 
			Integer groupPrice1, Integer groupAmount1, Integer groupAmount2, Integer groupAmount3,
			String description, String sort1Name[]) {

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
		dao.insert(pdVO);

		return pdVO;
	}
	
	
	public ProductVO getOneProductByid(Integer prodouctId) {
		return dao.getOneById(prodouctId);
	}

	public List<ProductVO> getAll() {
		return dao.getAll();
	}

}
