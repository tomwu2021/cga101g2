package com.post.model;

import java.util.List;

public class PostTestDAO {
	
	public static void main(String[] args) {
		PostJDBCDAO dao = new PostJDBCDAO();
		
		//查詢全部
		List<PostVO> list = dao.getAll();
		for(PostVO vo: list) {
			System.out.print(vo.getPostId()+ ",");
			System.out.print(vo.getMemberId()+ ",");
			System.out.print(vo.getContent()+ ",");
			System.out.print(vo.getLikeCount()+ ",");
			System.out.print(vo.getStatus()+ ",");
			System.out.print(vo.getAuthority()+ ",");
			System.out.print(vo.getCreateTime()+ ",");
			System.out.print(vo.getUpdateTime());
			System.out.println();
		}
		
		//新增
		//PostVO postVO1 = new PostVO();
//		postVO1.setMemberId(2);
//		postVO1.setContent("The Wolf and the Seven Little Kids");
//		postVO1.setLikeCount(100);
//		postVO1.setStatus(0);
//		postVO1.setAuthority(0);
		
		//再新增一筆
//		postVO1.setMemberId(3);
//		postVO1.setContent("Little Red-cap");
//		postVO1.setLikeCount(50);
//		postVO1.setStatus(0);
//		postVO1.setAuthority(1);
		
//		dao.insert(postVO1);
		
	
		//修改
//		PostVO postVO2 = new PostVO();
//		
//		postVO2.setMemberId(4);
//		postVO2.setContent("Snow-white");
//		postVO2.setLikeCount(30);
//		postVO2.setAuthority(1);
//		postVO2.setPostId(4);
//		
//		dao.update(postVO2);
		
		//查詢一筆
//		PostVO postVO3 = dao.findByPrimaryKey(3);
//		
//		System.out.println("-------------------------------------------");
//		System.out.print(postVO3.getPostId()+ ",");
//		System.out.print(postVO3.getMemberId()+ ",");
//		System.out.print(postVO3.getContent()+ ",");
//		System.out.print(postVO3.getLikeCount()+ ",");
//		System.out.print(postVO3.getStatus()+ ",");
//		System.out.print(postVO3.getAuthority()+ ",");
//		System.out.print(postVO3.getCreateTime()+ ",");
//		System.out.print(postVO3.getUpdateTime());	
	}

}
