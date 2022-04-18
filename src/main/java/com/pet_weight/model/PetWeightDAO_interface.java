package com.pet_weight.model;

import java.math.BigDecimal;
import java.util.List;

import com.common.model.JDBCDAO_Interface;


public interface PetWeightDAO_interface extends JDBCDAO_Interface<PetWeightVO>{

/** 新增寵物體重紀錄：
 * (必)petId, weightRecord, recordTime */
	public PetWeightVO insert(PetWeightVO petWeightVO);
/** 刪除寵物體重紀錄：
 * (必)recordId */
	public boolean delete(PetWeightVO petWeightVO);
/** 修改寵物體重紀錄：
 * (必)recordId
 * (選)weightRecord, recordTime */
    public PetWeightVO update(PetWeightVO petWeightVO);
/** 查詢單筆體重紀錄：
 * (必)recordId */
    public PetWeightVO getOneById(Integer id);
/** 查詢一寵物所有體重紀錄：
 * (必)petId */
    public List<PetWeightVO> getOneByPetId(Integer id);
/** 查詢一寵物最近一筆體重紀錄：
 * (必)petId */
    public PetWeightVO getRecentWeight(Integer id);
/** 查詢一寵物最後一季記錄之平均體重：
 * (必)petId */
    public BigDecimal getAverageWeight(Integer id);
/** @return null */
    public List<PetWeightVO> getAll();

}
