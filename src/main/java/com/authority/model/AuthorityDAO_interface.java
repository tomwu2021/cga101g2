package com.authority.model;

import java.util.List;
import com.authority.model.AuthorityVO;

public interface AuthorityDAO_interface {

	public void insert(AuthorityVO authorityVO);

	public void update(AuthorityVO authorityVO);

	public void delete(Integer function_id);

	public AuthorityVO findByPrimaryKey(Integer function_id);

	public List<AuthorityVO> getAll();

}
