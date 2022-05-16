package com.likelist.model;


import com.common.model.JDBCDAO_Interface;


public interface LikelistDAO_interface extends JDBCDAO_Interface<LikelistVO>{
	
	//新增按讚
	public boolean insertAndBoo(LikelistVO likelistVO);
	
	//刪除按讚（收回讚）
	public boolean delete(Integer postId, Integer memberId);
	
	
}
