package com.product_img.model;

import java.util.List;

import com.common.model.JDBCDAO_Interface;
import com.picture.model.PictureVO;

public interface ProductImgDAO_interface extends JDBCDAO_Interface<ProductImgVO>{

	
	//用商品id找到照片的"集合"們
	public List<PictureVO> getPicVOsByProductId(Integer productId);
}
