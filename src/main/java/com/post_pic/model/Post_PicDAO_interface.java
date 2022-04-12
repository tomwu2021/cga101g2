package com.post_pic.model;

import java.util.List;
	
public interface Post_PicDAO_interface {
	public void insert(Post_PicVO post_picVO);
	public void update(Post_PicVO post_picVO);
	public void delete(Integer pictureId);
	public Post_PicVO findByPrimaryKey(Integer pictureId);
	public List<Post_PicVO> getAll();
}