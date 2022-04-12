package com.prodouct.model;

import java.util.List;

public interface ProductDAO_interface {
	public void insert(ProductVO productVO);
	//更改商品與團購價格
    public void update(ProductVO productVO);
	//更改一般商品的推薦狀態
//    '0:非推薦狀態\n1:推薦狀態',
    public void updatetBytop_status(Integer top_status ,Integer product_id);
    //注意商品的delete為更改為商品上下架狀態
//    0:下架  1:一般商品上架 2:一般商品+團購上架
    public void delete(Integer status ,Integer product_id);
    
    
    public ProductVO findByPrimaryKey(Integer product_id);
    public List<ProductVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
