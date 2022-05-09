package com.pet.service;

import java.sql.Date;
import java.util.List;

import com.pet.model.PetDAO_interface;
import com.pet.model.PetJDBCDAO;
import com.pet.model.PetVO;

public class PetService {

	private PetDAO_interface dao;
	
	public PetService() {
		dao = new PetJDBCDAO();// TODO換連線池版本
	}
		
	/**
	 * 新增一隻寵物
	 * @return PetVO(petId, memberId, petName, breed, [gender], [introduction], [headShot], [birthday])
	 */
	public PetVO addPet(Integer memberId, String petName, Integer breed, Integer gender, String introduction, Integer headShot, Date birthday) {
		PetVO pVO = new PetVO();
		
		pVO.setMemberId(memberId);
		pVO.setPetName(petName);
		pVO.setSort1Id(breed);
		pVO.setGender(gender);
		pVO.setIntroduction(introduction);
		pVO.setPictureId(headShot);
		pVO.setBirthday(birthday);
		dao.insert(pVO);
		return pVO;
	}
			
	/**
	 * 修改寵物資料
	 * @return PetVO(petId, petName, breed, [gender], [introduction], [headShot], [birthday])
	 */
	public PetVO updatePet(String petName, Integer breed, Integer gender, String introduction, Integer headShot, Date birthday, Integer petId) {
		PetVO pVO = new PetVO();
		
		pVO.setPetName(petName);
		pVO.setSort1Id(breed);
		pVO.setGender(gender);
		pVO.setIntroduction(introduction);
		pVO.setPictureId(headShot);
		pVO.setBirthday(birthday);
		pVO.setPetId(petId);
		dao.update(pVO);
		return pVO;
	}
		
	/**
	 * 修改一隻寵物公開/隱藏狀態
	 */
	public Boolean changeStatus(Integer petId, Integer status) {
		Boolean bool = dao.changeStatus(petId, status);
		return bool;
	}

	/**
	 * 以寵物ID查詢
	 * @return PetVO(petId, memberId, petName, breed, [gender], [introduction], [headShot], [birthday])
	 */
	public PetVO getByPetId(Integer id) {
		PetVO pVO = dao.getOneById(id);
		return pVO;
	}

	/**
	 * 以會員ID查詢
	 * @return PetVO的集合
	 */
	public List<PetVO> getByMemberId(Integer id) {
		List<PetVO> petList = dao.getOneByMemberId(id);
		return petList;
	}
		
	/**
	 * 查公開且該月生日的寵物
	 * @return PetVO的集合
	 */
	public List<PetVO> getByBirthday(Integer month) {
		List<PetVO> petList = dao.getAllByBirth(month);
		return petList;
	}
	
	/**
	 * 會員註冊預設一隻寵物(我是🔘貓派:1 ⚪狗派:2，name='sort1Id')
	 * @return PetVO(petId, memberId, breed)
	 */
	public PetVO defaultPet(Integer memberId, Integer breed) {
		PetVO pVO = new PetVO();
		
		pVO.setMemberId(memberId);
		pVO.setSort1Id(breed);
		dao.defaultInsert(pVO);
		return pVO;
	}
}
