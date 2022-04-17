package com.article.model;

import java.util.List;
import com.common.model.JDBCDAO_Interface;


public interface ArticleDAO_interface extends JDBCDAO_Interface<ArticleVO>{

/** 發布最新消息；
 * (必)type[0-公告,1-商城], title, content, empNo */
	public ArticleVO insert(ArticleVO articleVO);
/** 下架最新消息：
 * (必)articleId */
    public boolean delete(ArticleVO articleVO);
/** 修改最新消息：
 * (必)articleId, type[0-公告,1-商城], title, content, empNo */
    public ArticleVO update(ArticleVO articleVO);
/** 查詢一則最新消息：
 * (必)articleId */
    public ArticleVO getOneById(Integer articleID);
/** 查詢所有最新消息 */
    public List<ArticleVO> getAll();
/** 查詢一類最新消息：
 * (必)type[0-公告,1-商城] */
    public List<ArticleVO> getAllByType(Integer type);
    
}
