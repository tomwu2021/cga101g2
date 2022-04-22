package com.emp_authority.model;

import com.emp.model.EmpJDBCDAO;

public class TestEmpAuthorityDAO {

	public static void main(String[] args) {

		EmpAuthorityJDBCDAO dao = new EmpAuthorityJDBCDAO();

		// 情境一 insert：新增員工權限
//		EmpAuthorityVO empAuthorityVO1 = new EmpAuthorityVO();
//		empAuthorityVO1.setEmpNo(4);
//		empAuthorityVO1.setFunctionId(3);
//		System.out.println(dao.insert(empAuthorityVO1));

		// 情境二 delete：刪除一筆員工權限
//		EmpAuthorityVO empAuthorityVO2 = new EmpAuthorityVO();
//		empAuthorityVO2.setEmpNo(2);
//		empAuthorityVO2.setFunctionId(1);
//		System.out.println(dao.delete(empAuthorityVO2));

		// 情境三：查詢某員工所有權限
//		for (EmpAuthorityVO empAuthorityVO3 : dao.getAllByEmpNo(2)) {
//			System.out.println(empAuthorityVO3);
//		}

		// 情境四：查詢某權限所有員工編號
//		for (EmpAuthorityVO empAuthorityVO4 : dao.getAllByFunctionId(3)) {
//			System.out.println(empAuthorityVO4);
//		}
	}

}
