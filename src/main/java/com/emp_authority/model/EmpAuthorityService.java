package com.emp_authority.model;

import java.util.*;

public class EmpAuthorityService {

	private EmpAuthorityDAO_interface dao;

	public EmpAuthorityService() {
		dao = new EmpAuthorityJDBCDAO();
	}

	// 情境一 insert：新增員工權限
	public EmpAuthorityVO insert(EmpAuthorityVO empAuthorityVO) {
		return dao.insert(empAuthorityVO);
	}

	// 情境二 delete：刪除一筆員工權限
	public boolean delete(EmpAuthorityVO empAuthorityVO) {
		return dao.delete(empAuthorityVO);
	}

	// 情境三：查詢某員工所有權限編號
	public List<EmpAuthorityVO> getAllByEmpNo(Integer empNO) {
		return dao.getAllByEmpNo(empNO);
	}

	// 情境四：查詢某權限所有員工編號
	public List<EmpAuthorityVO> getAllByFunctionId(Integer functionId) {
		return dao.getAllByFunctionId(functionId);
	}
}
