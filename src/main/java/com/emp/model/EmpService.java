package com.emp.model;

import java.util.*;

public class EmpService {
	
	private EmpDAO_interface dao;

	public EmpService() {
		dao = new EmpJDBCDAO();
	}

	// 情境一 insert：新增一筆員工資料
	public EmpVO insert(EmpVO empVO) {
		return dao.insert(empVO);
	}

	// 情境二 update：員工修改資料
	public EmpVO update(EmpVO empVO) {
		return dao.update(empVO);

	}

	// 情境三 select：使用 empNo 查詢某一筆員工資料
	public EmpVO getOneById(Integer id) {
		return dao.getOneById(id);
	}

	// 情境四 select：查詢全部員工資料
	public List<EmpVO> getAll() {
		return dao.getAll();
	}

	// 情境五 select：使用 account 查詢某一筆員工資料
	public EmpVO getOneByAccount(String account) {
		return dao.getOneByAccount(account);
	}

	// 情境六 update：修改員工的狀態
	public boolean changeStatus(EmpVO empVO) {
		return dao.changeStatus(empVO);
	}

}
