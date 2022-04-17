package com.customer.model;

import java.util.List;


public class TestCustomer {

	public static void main(String[] args) {

		CustomerJDBCDAO dao = new CustomerJDBCDAO();
		
		// 新增
		CustomerVO cVO1 = new CustomerVO();
		cVO1.setMailAddress("winnie1002375@gmail.com");
		cVO1.setNickname("King");
		cVO1.setContent("森777777");
		System.out.println(dao.insert(cVO1));
		
		// 刪除
//		CustomerVO cVO2 = new CustomerVO();
//		cVO2.setCaseId(2);
//		System.out.println(dao.delete(cVO2));
		
		// 修改回覆狀態為已回覆
//		CustomerVO cVO3 = new CustomerVO();
//		cVO3.setEmpNo(1);
//		cVO3.setCaseId(4);
//		System.out.println(dao.update(cVO3));

		// 查一筆
//		System.out.println(dao.getOneById(1));

		// 查一email
//		List<CustomerVO> eList =dao.getOneByEmail("winnie@gmail.com");
//		for(CustomerVO cVO:eList) {
//			System.out.println(cVO);
//		}
		
		// 查全部
//		List<CustomerVO> cList = dao.getAll();
//		for(CustomerVO cVO:cList) {
//			System.out.println(cVO);
//		}
		
		// 以回覆狀態查
//		List<CustomerVO> rList = dao.getAllByStatus(0);
//		for(CustomerVO cVO:rList) {
//			System.out.println(cVO);
//		}
		
		// 以回覆狀態查
		List<CustomerVO> kList = dao.getAllByKeyword("late");
		for(CustomerVO cVO:kList) {
			System.out.println(cVO);
		}
	}

}
