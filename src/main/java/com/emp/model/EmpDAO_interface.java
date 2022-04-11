package com.emp.model;

import java.util.List;
import com.emp.model.EmpVO;

public interface EmpDAO_interface {

	public void insert(EmpVO empVO);

	public void update(EmpVO empVO);

	public void delete(Integer emp_no);

	public EmpVO findByPrimaryKey(Integer emp_no);

	public List<EmpVO> getAll();

}
