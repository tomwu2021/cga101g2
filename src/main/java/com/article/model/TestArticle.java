package com.article.model;

import java.util.List;

public class TestArticle {
	
	public static void main(String[] args) {
		
		ArticleJDBCDAO dao = new ArticleJDBCDAO();
		
		// 新增
		ArticleVO aVO1 = new ArticleVO();
		aVO1.setType(0);
		aVO1.setTitle("2022/5/2 伺服器暫停服務");
		aVO1.setContent("嘿嘿嘿不給你買");
		aVO1.setEmpNo(1);
		System.out.println(dao.insert(aVO1));
		
		// 刪除
//		ArticleVO aVO2 = new ArticleVO();
//		aVO2.setArticleId(2);
//		System.out.println(dao.delete(aVO2));
	
		// 修改
//		ArticleVO aVO3 = new ArticleVO();
//		aVO3.setType(1);
//		aVO3.setTitle("newaaa");
//		aVO3.setContent(null);
//		aVO3.setEmpNo(1);
//		aVO3.setArticleId(3);
//		System.out.println(dao.update(aVO3));
	
		// 查一筆
//		System.out.println(dao.getOneById(4));

		// 查全部
//		List<ArticleVO> aList = dao.getAll();
//		
//		for(ArticleVO aVO:aList) {
//			System.out.println(aVO);
//		}
		
		// 查全部
		List<ArticleVO> tList = dao.getAllByType(0);
		
		for(ArticleVO aVO:tList) {
			System.out.println(aVO);
		}
	}
	
}
