package com.remind.model;

import java.util.List;
import java.util.Map;

public interface RemindDAO_interface {
	
	public void insert(RemindVO remindVO);
    public void update(RemindVO remindVO);
    public void delete(Integer recordID);
    public RemindVO findByPrimaryKey(Integer recordID);
    public List<RemindVO> getAll(Integer memberID);
    public List<RemindVO> getAll(Map<String, Object[]> map); 

}
