package com.likelist.model;

import java.util.List;

public class LikelistTestDAO {
	
	public static void main(String[] args) {
		LikelistJDBCDAO dao = new LikelistJDBCDAO();
		
		
		//�d�ߥ���
		List<LikelistVO> list = dao.getAll();
		for(LikelistVO vo: list) {
			System.out.print(vo.getPostId()+ ",");
			System.out.print(vo.getMemberId());
			System.out.println();
		}
		
		//�s�W
		LikelistVO likelistVO1 = new LikelistVO();
//		likelistVO1.setPost_id(2);
//		likelistVO1.setMember_id(1);
		
		//�A�s�W��L��
//		likelistVO1.setPost_id(3);
//		likelistVO1.setMember_id(2);
		
//		likelistVO1.setPost_id(4);
//		likelistVO1.setMember_id(4);
		
//		likelistVO1.setPost_id(5);
//		likelistVO1.setMember_id(3);
	
//		dao.insert(likelistVO1);
	}
		
		//�R��

}
