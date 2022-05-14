package com.pet.model;

import java.util.List;

import com.common.model.JDBCDAO_Interface;


public interface PetDAO_interface extends JDBCDAO_Interface<PetVO>{
	
/**	新增寵物資料：
 * (必)memberId, petName, sort1Id[1-貓,2-狗]
 * (選)gender[0-公,1-母], introduction, pictureId, birthday */
	public PetVO insert(PetVO petVO);
/**	刪除寵物資料(會員新建用)：
 * (必)memberId */
	public boolean delete(PetVO petVO);
/** 更新寵物資料：
 * (必)petId, introduction, birthday
 * (選)petName, sort1Id[1-貓,2-狗], gender[0-公,1-母], pictureId */
    public PetVO update(PetVO petVO);
/** 公開/隱藏寵物資料：
 * (必)petId, status[0-公開,1-隱藏] */
    public boolean changeStatus(Integer id, Integer status);
/** 查詢單筆寵物資料：
 *  (必)petId*/
    public PetVO getOneById(Integer id);
/** 查詢一名會員的寵物資料：
 * (必)memberId */
    public List<PetVO> getOneByMemberId(Integer id);
/** 查詢所有寵物資料 */
    public List<PetVO> getAll();
/** 查詢所有指定月份生日且狀態為公開的寵物資料
 * (必)[1~12] */
    public List<PetVO> getAllByBirth(Integer birthMonth);
/**	預設寵物資料：
 * (必)memberId */
	public PetVO defaultInsert(PetVO petVO);

}
