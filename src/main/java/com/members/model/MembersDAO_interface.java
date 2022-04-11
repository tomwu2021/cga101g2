package com.members.model;

import java.util.List;
import com.emp.model.EmpVO;

public interface MembersDAO_interface {
	
	public void insert(MembersVO memberVO);

	public void update(MembersVO memberVO);

	public void delete(Integer member_id);

	public MembersVO findByPrimaryKey(Integer member_id);

	public List<MembersVO> getAll();
	
}