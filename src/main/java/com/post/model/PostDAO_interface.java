package com.post.model;

import java.util.List;

import com.common.model.JDBCDAO_Interface;

public interface PostDAO_interface extends JDBCDAO_Interface<PostVO> {
	
	//查看個人頁面（個人貼文）
	public List<PostVO> selectPost(Integer id);
		
	//查看熱門貼文
	public List<PostVO> selectHotPost();
	
	
	//查詢貼文，顯示 status狀態0:正常1:審核中2:刪除
	public List<PostVO> selectChangePost();
	
	//刪除貼文
	public boolean updatedelete(Integer postId);

	
	public PostVO getOneById(Integer postId, Integer memberId);
	
	
	
}
