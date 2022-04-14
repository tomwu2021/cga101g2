package com.article_picture.model;

import java.util.List;
import java.util.Map;

import com.common.model.JDBCDAO_Interface;

public interface ArticlePictureDAO_interface extends JDBCDAO_Interface<ArticlePictureVO>{
	
	public ArticlePictureVO insert(ArticlePictureVO articlePictureVO);
	public boolean delete(ArticlePictureVO t);
    public ArticlePictureVO update(ArticlePictureVO articlePictureVO);
	public ArticlePictureVO getOneById(Integer id);
	public List<ArticlePictureVO> getAll();


}
