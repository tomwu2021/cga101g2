package com.post_pic.model;

import java.util.List;

public class Post_PicTestDAO {
	public static void main(String[] args) {
		
		Post_PicJDBCDAO dao = new Post_PicJDBCDAO();
		
		//�s�W
		Post_PicVO post_picvo1 = new Post_PicVO();
		
//		post_picvo1.setPostId(3);
//		post_picvo1.setPostId(4);
//		post_picvo1.setPostId(5);
		
		//dao.insert(post_picvo1);
		
		//�d�ߥ���
		List<Post_PicVO> list = dao.getAll();
		for(Post_PicVO vo: list) {
			System.out.print(vo.getPictureId()+ ",");
			System.out.print(vo.getPostId());
			System.out.println();	
		}
	}
}
