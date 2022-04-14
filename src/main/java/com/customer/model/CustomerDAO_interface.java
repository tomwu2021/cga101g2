package com.customer.model;

import java.util.List;
import java.util.Map;

import com.article.model.ArticleVO;
import com.common.model.JDBCDAO_Interface;


public interface CustomerDAO_interface extends JDBCDAO_Interface<CustomerVO>{

	public CustomerVO insert(CustomerVO customerVO);
	public boolean delete(CustomerVO customerVO);
    public CustomerVO update(CustomerVO customerVO);
    public CustomerVO getOneById(Integer id);
    public List<CustomerVO> getAll();
}
