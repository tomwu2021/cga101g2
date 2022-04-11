package com.pet_activity.model;

import java.util.List;
import java.util.Map;

public interface PetActivityDAO_interface {
	
	public void insert(PetActivityVO petActivityVO);
    public void update(PetActivityVO petActivityVO);
    public void delete(Integer recordID);
    public PetActivityVO findByPrimaryKey(Integer recordID);
    public List<PetActivityVO> getAll();
    public List<PetActivityVO> getAll(Map<String, Object[]> map); 

}
