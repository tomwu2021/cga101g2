package com.pet.model;

import java.util.List;
import java.util.Map;

import com.common.model.JDBCDAO_Interface;


public interface PetDAO_interface extends JDBCDAO_Interface<PetVO>{

	public PetVO insert(PetVO petVO);
	public boolean delete(PetVO petVO);
    public PetVO update(PetVO petVO);
    public PetVO getOneById(Integer id);
    public List<PetVO> getAll();

}
