package com.authority.model;

import java.util.List;

public class TestAuthority {
	
	public static void main(String[] args) {
		AuthorityJDBCDAO dao = new AuthorityJDBCDAO();
		
		//查詢全部
		List<AuthorityVO> list = dao.getAll();
		for(AuthorityVO vo: list) {
			System.out.print(vo.getFunctionId()+ ",");
			System.out.print(vo.getFunctionName());
			System.out.println();
		}
		
		//新增
//		AuthorityVO authorityVO1 = new AuthorityVO();
//		authorityVO1.setFunctionName("會員管理");
//		dao.insert(authorityVO1);	
	}
}
