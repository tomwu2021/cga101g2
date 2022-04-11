package com.article_picture.model;

import java.util.List;
import java.util.Map;

public interface ArticlePictureDAO_interface {
	
	public void insert(ArticlePictureVO articlePictureVO);
    public void update(ArticlePictureVO articlePictureVO);
    public List<ArticlePictureVO> getAll();
    public List<ArticlePictureVO> getAll(Map<String, Object[]> map); 

}
