package com.likelist.model;

import java.util.List;

public interface LikelistDAO_interface {
	public List<LikelistVO> getAll();
	public void insert(LikelistVO likelistVo);
	public void update(LikelistVO likelistVo);
	public void delete(Integer postid);
	public void findByPrimaryLKey(Integer postid);	
}
