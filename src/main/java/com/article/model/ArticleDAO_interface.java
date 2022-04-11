package com.article.model;

import java.util.List;
import java.util.Map;


public interface ArticleDAO_interface {
	
	public void insert(ArticleVO articleVO);
    public void update(ArticleVO articleVO);
    public void delete(Integer articleID);
    public ArticleVO findByPrimaryKey(Integer articleID);
    public List<ArticleVO> getAll();
    public List<ArticleVO> getAll(Map<String, Object[]> map); 

}
