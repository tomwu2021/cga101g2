package com.article.model;

import java.util.List;
import com.common.model.JDBCDAO_Interface;


public interface ArticleDAO_interface extends JDBCDAO_Interface<ArticleVO>{
	
	public ArticleVO insert(ArticleVO articleVO);
    public boolean delete(ArticleVO articleVO);
    public ArticleVO update(ArticleVO articleVO);
    public ArticleVO getOneById(Integer articleID);
    public List<ArticleVO> getAll();

}
