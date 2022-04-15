package com.emp.model;

public class TestEmpDAO {

	public static void main(String[] args) {

		EmpJDBCDAO dao = new EmpJDBCDAO();

		// 情境一 insert：新增一筆員工資料
//		EmpVO empVO01 = new EmpVO();
//		empVO01.setAccount("wusihuke@altmails.com"); // 動態
//		empVO01.setPassword("!QAZ2wsx"); // 動態 dao.insert(empVO01);
//		System.out.println(empVO01.getEmpNo());
//		System.out.println(empVO01.getAccount());
//		System.out.println(empVO01.getPassword());

		// 情境二 update：員工修改資料
//		EmpVO empVO2 = new EmpVO();
//		empVO2.setEmpName("吉尤斐");
//		empVO2.setAccount("puxof233112@altmails.com");
//		empVO2.setPassword("7777777");
//		empVO2.setEmpNo(3);
//		System.out.println(dao.update(empVO2));

		// 情境三 select：使用 empNo 查詢某一筆員工資料
//		System.out.println(dao.getOneById(10));

		// 情境四 select：查詢全部員工資料
//		for (EmpVO emp : dao.getAll()) {
//			System.out.println(emp);
//		}

		// 情境五 select：使用 account 查詢某一筆員工資料
//		System.out.println(dao.getOneByAccount("tyb38785@zcrcd.com"));

		// 情境六 update：修改員工的狀態
//		EmpVO empVO3 = new EmpVO();
//		empVO3.setEmpNo(6);
//		empVO3.setStatus(0);
//		System.out.println(dao.changeStatus(empVO3));
		
	}

}
