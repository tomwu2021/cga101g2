package com.remind.model;

import java.sql.Timestamp;

public class TestRemind {
	
	public static void main(String[] args) {

		RemindJDBCDAO dao = new RemindJDBCDAO();
		
		// 新增
		RemindVO rVO1 = new RemindVO();
		rVO1.setMemberId(2);
		rVO1.setContent("去洗澡");
		rVO1.setTime(Timestamp.valueOf("2022-04-17 02:00:00"));
		System.out.println(dao.insert(rVO1));
		
		// 刪除
//		RemindVO rVO2 = new RemindVO();
//		rVO2.setRemindId(1);
//		System.out.println(dao.delete(rVO2));
		
		// 修改
//		RemindVO rVO3 = new RemindVO();
//		rVO3.setContent("公園散步");
//		rVO3.setTime(Timestamp.valueOf("2022-04-17 02:00:00"));
//		rVO3.setRemindId(2);
//		System.out.println(dao.update(rVO3));

		// 查一筆
//		System.out.println(dao.getOneById(2));
		
		// 查一會員
//		System.out.println(dao.getOneByMemberId(2));

//		System.out.println(dao.getUndueByMemberId(2));
		
		// 查全部
//		System.out.println(dao.getAll());		

		System.out.println(dao.getAllNow());
	}

}
