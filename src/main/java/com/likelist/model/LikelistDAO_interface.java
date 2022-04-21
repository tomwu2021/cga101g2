package com.likelist.model;


import com.common.model.JDBCDAO_Interface;


public interface LikelistDAO_interface extends JDBCDAO_Interface<LikelistVO>{
	
	//刪除按讚（收回讚）
	public boolean delete(Integer postId, Integer memberId);
	
	
}
