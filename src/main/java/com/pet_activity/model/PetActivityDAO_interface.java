package com.pet_activity.model;

import java.util.List;

import com.common.model.JDBCDAO_Interface;
import com.remind.model.RemindVO;

public interface PetActivityDAO_interface extends JDBCDAO_Interface<PetActivityVO>{

/** 新增寵物活動紀錄：
 * (必)petId, activity, recordTime */
	public PetActivityVO insert(PetActivityVO petActivityVO);
/** 將提醒轉為寵物活動紀錄：~To Do~ */
	public PetActivityVO convert(RemindVO remindVO);
/** 刪除寵物活動紀錄：
 * (必)recordId */
	public boolean delete(PetActivityVO petActivityVO);
/** 修改寵物活動紀錄：
 * (必)recordId
 * (選)activity, recordTime */
    public PetActivityVO update(PetActivityVO petActivityVO);
/** 查詢單筆活動紀錄：
 * (必)recordId */
    public PetActivityVO getOneById(Integer id);
/** 查詢一寵物所有活動紀錄
 * (必)petId */
    public List<PetActivityVO> getOneByPetId(Integer id);
/** 查詢一寵物最近4筆活動紀錄
 * (必)petId */  
    public List<PetActivityVO> getFourByPetId(Integer id);
/** @return null */
    public List<PetActivityVO> getAll();

}
