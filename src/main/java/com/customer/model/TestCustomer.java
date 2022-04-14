package com.customer.model;

import java.sql.Timestamp;
import java.util.List;


public class TestCustomer {

	public static void main(String[] args) {

		CustomerJDBCDAO dao = new CustomerJDBCDAO();
		
		// 新增
//		CustomerVO cVO1 = new CustomerVO();
//		cVO1.setMailAddress("winnie@gmail.com");
//		cVO1.setNickname("hateyou");
//		cVO1.setContent("我要客訴");
//		System.out.println(dao.insert(cVO1));
		
		// 刪除
//		CustomerVO cVO2 = new CustomerVO();
//		cVO2.setCaseId(2);
//		System.out.println(dao.delete(cVO2));
		
		// 修改
//		CustomerVO cVO3 = new CustomerVO();
//		cVO3.setReplyStatus(1);
//		cVO3.setEmpNo(1);
//		cVO3.setCaseId(3);
//		System.out.println(dao.update(cVO3));

		// 查一筆
//		System.out.println(dao.getOneById(3));

		// 查全部
		List<CustomerVO> clist = dao.getAll();
		
		for(CustomerVO cVO:clist) {
			System.out.println(cVO);
		}
	}

}
