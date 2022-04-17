package com.sort_mix.model;

import java.util.List;

public interface Sort_mixDAO_interface {
	
	public void insert(Sort_mixVO sort_max);
//    public void update(Sort_maxVO sort_max);
    public void delete(Sort_mixVO sort_max);
    public List<Sort_mixVO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
