package com.remind.model;

import java.sql.Timestamp;
import java.util.List;

public class TestRemind {
	
	public static void main(String[] args) {

		RemindJDBCDAO dao = new RemindJDBCDAO();
		
		// 新增
		RemindVO rVO1 = new RemindVO();
		rVO1.setMemberId(1);
		rVO1.setContent("周日去公園");
		rVO1.setTime(Timestamp.valueOf("2022-04-10 19:00:00"));
		System.out.println(dao.insert(rVO1));
		
		// 刪除
		RemindVO rVO2 = new RemindVO();
		rVO2.setRemindId(17);
		System.out.println(dao.delete(rVO2));
		
		// 修改
		RemindVO rVO3 = new RemindVO();
		rVO3.setContent("去AAA公園");
		rVO3.setTime(Timestamp.valueOf("2022-04-16 17:00:00"));
		rVO3.setRemindId(14);
		System.out.println(dao.update(rVO3));

		// 查一筆
		System.out.println(dao.getOneById(3));

		// 查全部
		List<RemindVO> rlist = dao.getAll();
		
		for(RemindVO rVO:rlist) {
			System.out.println(rVO);
		}
	}

}
