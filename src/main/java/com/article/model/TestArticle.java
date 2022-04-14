package com.article.model;

import java.util.List;

public class TestArticle {
	
	public static void main(String[] args) {
		
		ArticleJDBCDAO dao = new ArticleJDBCDAO();
		
		// 新增
		ArticleVO aVO1 = new ArticleVO();
		aVO1.setType(1);
		aVO1.setTitle("DOREMI");
		aVO1.setContent("周日公園趣");
		aVO1.setEmpNo(1);
		System.out.println(dao.insert(aVO1));
		
		// 刪除
		ArticleVO aVO2 = new ArticleVO();
		aVO2.setArticleId(1);
		System.out.println(dao.delete(aVO2));
		
		// 修改
		ArticleVO aVO3 = new ArticleVO();
		aVO3.setType(1);
		aVO3.setTitle("news");
		aVO3.setContent("content here");
		aVO3.setEmpNo(1);
		aVO3.setArticleId(2);
		System.out.println(dao.update(aVO3));
	
		// 查一筆
		System.out.println(dao.getOneById(3));
	
		// 查全部
		List<ArticleVO> alist = dao.getAll();
		
		for(ArticleVO aVO:alist) {
			System.out.println(aVO);
		}
	}
	
}
