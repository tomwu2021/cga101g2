package com.post.model;

import java.util.List;

public class TestPost {
	
	public static void main(String[] args) {
		PostJDBCDAO dao = new PostJDBCDAO();
		
		//select 查詢全部
//		List<PostVO> list = dao.getAll();
//		for(PostVO vo: list) {
//			System.out.print(vo.getPostId()+ ",");
//			System.out.print(vo.getMemberId()+ ",");
//			System.out.print(vo.getContent()+ ",");
//			System.out.print(vo.getLikeCount()+ ",");
//			System.out.print(vo.getStatus()+ ",");
//			System.out.print(vo.getAuthority()+ ",");
//			System.out.print(vo.getCreateTime()+ ",");
//			System.out.print(vo.getUpdateTime());
//			System.out.println();
//		}
		
		//新增
//		PostVO postVO1 = new PostVO();
//		postVO1.setMemberId(6);
//		postVO1.setContent("貼文內容測試0428");
//		dao.insert(postVO1);
//		System.out.println(postVO1);
		
		//修改
//		PostVO postVO2 = new PostVO();
//		
//		postVO2.setContent("貼文內容測試2");
//		postVO2.setPostId(4);
//		
//		dao.update(postVO2);
		
		//只查詢其中一筆資料
//		PostVO postVO3 = dao.getOneById(5);
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
		
//		//查看個人頁面（個人貼文）
//		for(PostVO p1: dao.selectPost(1)) {
//			System.out.println(p1);
//		}
		
		//查看熱門貼文
//		List<PostVO> list = dao.selectHotPost();
//		for(PostVO vo1: list) {
//			System.out.print(vo1.getPostId()+ ",");
//			System.out.print(vo1.getMemberId()+ ",");
//			System.out.print(vo1.getContent()+ ",");
//			System.out.print(vo1.getLikeCount()+ ",");
//			System.out.print(vo1.getStatus()+ ",");
//			System.out.print(vo1.getAuthority()+ ",");
//			System.out.print(vo1.getCreateTime()+ ",");
//			System.out.print(vo1.getUpdateTime());
//			System.out.println();
//		}
	}

}
