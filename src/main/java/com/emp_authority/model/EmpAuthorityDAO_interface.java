package com.emp_authority.model;

import java.util.List;
import com.emp_authority.model.Emp_authorityVO;

public interface Emp_authorityDAO_interface {
	// Ω∆¶X•D¡‰
	public void insert(Emp_authorityVO emp_authorityVO);

	public void update(Emp_authorityVO emp_authorityVO);

	public void delete(Integer emp_no, Integer function_id);

	public Emp_authorityVO findByPrimaryKey(Integer emp_no, Integer function_id);

	public List<Emp_authorityVO> getAll();

}
