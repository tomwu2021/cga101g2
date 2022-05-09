package com.pet_weight.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.pet_weight.model.PetWeightDAO;
import com.pet_weight.model.PetWeightDAO_interface;
import com.pet_weight.model.PetWeightJDBCDAO;
import com.pet_weight.model.PetWeightVO;

public class PetWeightService {
	
	private PetWeightDAO_interface dao;
	
	public  PetWeightService() {
		dao = new PetWeightJDBCDAO();// TODO換連線池版本
	}
	
	// 新增
	public PetWeightVO addWeightRecord(Integer petId, BigDecimal weight, Date recordTime) {
		PetWeightVO pwVO = new PetWeightVO();
		pwVO.setPetId(petId);
		pwVO.setWeightRecord(weight);
		pwVO.setRecordTime(recordTime);
		dao.insert(pwVO);
		return pwVO;
	}
	
	// 刪除
	public Boolean deleteWeightRecord(Integer recordId) {
		PetWeightVO pwVO = new PetWeightVO();
		pwVO.setRecordId(recordId);
	    Boolean bool = dao.delete(pwVO);
	    return bool;
	}
	
	// 修改
	public PetWeightVO updateWeightRecord(BigDecimal weight, Date recordTime, Integer id) {
		PetWeightVO pwVO = new PetWeightVO();
		pwVO.setWeightRecord(weight);
		pwVO.setRecordTime(recordTime);
		pwVO.setRecordId(id);
		dao.update(pwVO);
		return pwVO;
	}

	// 查一筆
	public PetWeightVO getOneWeight(Integer id) {
		PetWeightVO pwVO = dao.getOneById(id);
		return pwVO;
	}
	
	// 查一寵物
	public List<PetWeightVO> getByPetId(Integer petId){
		List<PetWeightVO> weightList = dao.getOneByPetId(petId);
		return weightList;
	}
	
	// 查一寵物最近一筆
	public PetWeightVO getRecentWeight(Integer petId){
		PetWeightVO pwVO = dao.getRecentWeight(petId);
		return pwVO;
	}
	
	// 查一寵物體重紀錄(季平均)
	public BigDecimal getAverageWeight(Integer petId){
		BigDecimal averge = dao.getAverageWeight(petId);
		return averge;
	}

}
