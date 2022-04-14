package com.pet_activity.model;

import java.util.List;
import java.util.Map;

import com.common.model.JDBCDAO_Interface;

public interface PetActivityDAO_interface extends JDBCDAO_Interface<PetActivityVO>{
	
	public PetActivityVO insert(PetActivityVO petActivityVO);
	public boolean delete(PetActivityVO petActivityVO);
    public PetActivityVO update(PetActivityVO petActivityVO);
    public PetActivityVO getOneById(Integer id);
    public List<PetActivityVO> getAll();

}
