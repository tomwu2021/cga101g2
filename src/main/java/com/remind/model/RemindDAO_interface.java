package com.remind.model;

import java.util.List;

import com.common.model.JDBCDAO_Interface;

public interface RemindDAO_interface extends JDBCDAO_Interface<RemindVO>{
	
	public RemindVO insert(RemindVO remindVO);
	public boolean delete(RemindVO remindVO);
    public RemindVO update(RemindVO remindVO);
    public RemindVO getOneById(Integer id);
    public List<RemindVO> getAll();
    
    
}
