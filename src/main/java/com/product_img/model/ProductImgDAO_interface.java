package com.product_img.model;

import java.util.List;
import java.util.Set;

import com.common.model.JDBCDAO_Interface;

public interface ProductImgDAO_interface extends JDBCDAO_Interface<ProductImgVO>{

	public List<ProductImgVO> getImgsByProductId(Integer productId);
}
