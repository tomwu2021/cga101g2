package com.post.model;

import java.util.List;

public interface PostDAO_interface {
	public void insert(PostVO postVO);
	public void update(PostVO postVO);
	public void delete(Integer postId);
	public PostVO findByPrimaryKey(Integer postId);
	public List<PostVO> getAll();
}
