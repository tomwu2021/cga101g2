package com.pet_weight.model;

import java.util.List;
import java.util.Map;


public interface PetWeightDAO_interface {
	
	public void insert(PetWeightVO petWeightVO);
    public void update(PetWeightVO petWeightVO);
    public void delete(Integer recordID);
    public PetWeightVO findByPrimaryKey(Integer recordID);
    public List<PetWeightVO> getAll();
    public List<PetWeightVO> getAll(Map<String, Object[]> map); 

}
