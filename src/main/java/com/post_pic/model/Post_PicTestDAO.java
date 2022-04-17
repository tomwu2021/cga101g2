package com.post_pic.model;

import java.util.List;

public class Post_PicTestDAO {
	public static void main(String[] args) {
		
		Post_PicJDBCDAO dao = new Post_PicJDBCDAO();
		
		//新增
		Post_PicVO post_picvo1 = new Post_PicVO();
		
//		post_picvo1.setPostId(1);
//		post_picvo1.setPostId(2);
//		post_picvo1.setPostId(3);
//		post_picvo1.setPostId(4);
//		post_picvo1.setPostId(5);
		
		dao.insert(post_picvo1);
		
		//查詢全部
		List<Post_PicVO> list = dao.getAll();
		for(Post_PicVO vo: list) {
			System.out.print(vo.getPictureId()+ ",");
			System.out.print(vo.getPostId());
			System.out.println();	
		}
	}
}
