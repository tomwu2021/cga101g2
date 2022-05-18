package com.pet_activity.service;

import java.sql.Date;
import java.util.List;

import com.pet_activity.model.*;

public class PetActivityService {
	private PetActivityDAO_interface dao;
	
	public PetActivityService() {
		dao = new PetActivityDAO();// TODO 換連線池版本
	}

	/**
	 * 新增一筆寵物活動紀錄
	 * @return PetActivityVO(recordId, petId, activity, recordTime)
	 */
	public PetActivityVO addActivityRecord(Integer petId, String activity, Date recordTime) {
		PetActivityVO paVO = new PetActivityVO();
		System.out.printf("%d",12);
		paVO.setPetId(petId);
		paVO.setActivity(activity);
		paVO.setRecordTime(recordTime);
		dao.insert(paVO);
		return paVO;
	}
	
	/**
	 * 刪除一筆寵物活動紀錄
	 */
	public Boolean deleteActivityRecord(Integer recordId) {
		PetActivityVO paVO = new PetActivityVO();
		paVO.setRecordId(recordId);
		Boolean bool = dao.delete(paVO);
		return bool;
	}
		
	/**
	 * 修改一筆寵物活動紀錄
	 * @return PetActivityVO(recordId, [activity], [recordTime])
	 */
	public PetActivityVO updateActivityRecord(String activity, Date recordTime, Integer recordId) {
		PetActivityVO paVO = new PetActivityVO();
		paVO.setActivity(activity);
		paVO.setRecordTime(recordTime);
		paVO.setRecordId(recordId);
		dao.update(paVO);
		return paVO;
	}

	/**
	 * 查詢一筆寵物活動紀錄
	 * @return PetActivityVO(recordId, petId, activity, recordTime)
	 */
	public PetActivityVO getOneActivity(Integer id) {
		PetActivityVO paVO = dao.getOneById(id);
		return paVO;
	}
	
	/**
	 * 查詢一隻寵物所有活動紀錄
	 * @return PetActivityVO的list集合
	 */
	public List<PetActivityVO> getAllByPetId(Integer petId) {
		List<PetActivityVO> activityList = dao.getOneByPetId(petId);
		return activityList;
	}

	/**
	 * 查詢一隻寵物最近4筆活動紀錄
	 * @return PetActivityVO的list集合
	 */
	public List<PetActivityVO> getFourByPetId(Integer petId) {
		List<PetActivityVO> activityList = dao.getFourByPetId(petId);
		return activityList;
	}
			
}
