package com.pet_weight.model;

import java.util.List;
import java.util.Map;

import com.common.model.JDBCDAO_Interface;


public interface PetWeightDAO_interface extends JDBCDAO_Interface<PetWeightVO>{
	
	public PetWeightVO insert(PetWeightVO petWeightVO);
	public boolean delete(PetWeightVO petWeightVO);
    public PetWeightVO update(PetWeightVO petWeightVO);
    public PetWeightVO getOneById(Integer id);
    public List<PetWeightVO> getAll();

}
