package com.post.model;

import java.util.List;

import com.common.model.JDBCDAO_Interface;

public interface PostDAO_interface extends JDBCDAO_Interface<PostVO> {
	
	//查看個人頁面（個人貼文）
		public List<PostVO> selectPost(Integer id);
		
		//查看熱門貼文
		public List<PostVO> selectHotPost();
	
}
