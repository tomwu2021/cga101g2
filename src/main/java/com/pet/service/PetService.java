package com.pet.service;

import java.io.IOException;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.Part;

import com.pet.model.PetDAO;
import com.pet.model.PetDAO_interface;
import com.pet.model.PetVO;
import com.picture.model.PictureVO;
import com.picture.service.PictureService;

public class PetService {

	private PetDAO_interface dao;
	private PictureService picSvc;

	public PetService() {
		dao = new PetDAO();// TODO換連線池版本
		picSvc= new PictureService();
	}
		
	/**
	 * 新增一隻寵物
	 * @return PetVO(petId, memberId, petName, breed, [gender], [introduction], [headShot], [birthday])
	 */
	public PetVO addPet(Integer memberId, String petName, Integer breed, Integer gender, String introduction, Collection<Part> headshot, Date birthday) {
		
		PetVO pVO = new PetVO();
		PictureVO picVO = new PictureVO();
		Integer picId = 999;
	// 步驟一 上傳大頭貼
		if (headshot != null) {
			try {
				picVO = picSvc.uploadImageByDefaultAlbum(headshot, memberId).get(0);
				picId = picVO.getPictureId();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	// 步驟二 建立寵物資料	
		pVO.setMemberId(memberId);
		pVO.setPetName(petName);
		pVO.setSort1Id(breed);
		pVO.setGender(gender);
		pVO.setIntroduction(introduction);
		pVO.setPictureId(picId);
		pVO.setBirthday(birthday);
		dao.insert(pVO);
		
		// 步驟三 刪除預設pet
		PetVO defaultPetId = dao.getOneByMemberId(memberId).get(0);
		dao.delete(defaultPetId);
		
		return pVO;
	}
			
	/**
	 * 修改寵物資料
	 * @return PetVO(petId, petName, breed, [gender], [introduction], [headShot], [birthday])
	 */
	public PetVO updatePet(Integer memberId, String petName, Integer breed, Integer gender, String introduction, Collection<Part> headshot, Date birthday, Integer petId) {
		PetVO pVO = new PetVO();
		PictureVO picVO = new PictureVO();
		Integer picId = picVO.getPictureId();
	// 步驟一 上傳新大頭貼
		if (headshot != null) {
			try {
				picVO = picSvc.uploadImageByDefaultAlbum(headshot, memberId).get(0);
				picId = picVO.getPictureId();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	// 步驟二 修改寵物資料	
		pVO.setPetName(petName);
		pVO.setSort1Id(breed);
		pVO.setGender(gender);
		pVO.setIntroduction(introduction);
		pVO.setPictureId(picId);
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
	 * 會員註冊預設一隻寵物
	 * @return PetVO(petId, memberId, breed)
	 */
	public PetVO defaultPet(Integer memberId) {
		PetVO pVO = new PetVO();
		
		pVO.setMemberId(memberId);
		pVO.setSort1Id(1);
		dao.defaultInsert(pVO);
		return pVO;
	}
}
