package com.pet.model;

import java.util.List;
import java.util.Map;


public interface PetDAO_interface {

	public void insert(PetVO petVO);
    public void update(PetVO petVO);
    public void delete(Integer petID);
    public PetVO findByPrimaryKey(Integer petID);
    public List<PetVO> getAll();
    public List<PetVO> getAll(Map<String, Object[]> map); 
}
