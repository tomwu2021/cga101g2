package com.emp_authority.model;

import java.util.List;

import com.common.model.JDBCDAO_Interface;
import com.emp.model.EmpVO;

public interface EmpAuthorityDAO_interface extends JDBCDAO_Interface<EmpAuthorityVO> {
	
	// 情境三：查詢某員工所有權限編號
	List<EmpAuthorityVO> getAllByEmpNo(Integer empNO);
	
	// 情境四：查詢某權限所有員工編號
	List<EmpAuthorityVO> getAllByFunctionId(Integer functionId);
	
}
