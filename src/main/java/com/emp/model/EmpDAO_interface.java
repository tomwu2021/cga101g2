package com.emp.model;

import com.common.model.JDBCDAO_Interface;

public interface EmpDAO_interface extends JDBCDAO_Interface<EmpVO> {

	// 情境五 select：使用 account 查詢某一筆員工資料
	public EmpVO getOneByAccount(String account);

	// 情境六 update：修改員工的狀態
	public boolean changeStatus(EmpVO empVO);
}
