package com.customer.model;

import java.util.List;
import java.util.Map;


public interface CustomerDAO_interface {

	public void insert(CustomerVO customerVO);
    public void update(CustomerVO customerVO);
    public void delete(Integer caseID);
    public CustomerVO findByPrimaryKey(Integer caseID);
    public List<CustomerVO> getAll();
    public List<CustomerVO> getAll(Map<String, Object[]> map); 
}
