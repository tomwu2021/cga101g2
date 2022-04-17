package com.remind.model;

import java.util.List;

import com.common.model.JDBCDAO_Interface;

public interface RemindDAO_interface extends JDBCDAO_Interface<RemindVO>{

/** 新增提醒事項：
 * (必)memberId, content, time */
	public RemindVO insert(RemindVO remindVO);
/** 刪除提醒事項：
 * (必)remindId */
	public boolean delete(RemindVO remindVO);
/** 修改提醒事項：
 * (必)remindId
 * (選)content, time */
    public RemindVO update(RemindVO remindVO);
/** 查詢一筆提醒：
 * ((必)remindId */
    public RemindVO getOneById(Integer id);
/** 查詢一會員所有提醒：
 * (必)memberId */
    public List<RemindVO> getOneByMemberId(Integer id);
/** 查詢一會員所有未到期提醒：
 * (必)memberId */
    public List<RemindVO> getUndueByMemberId(Integer id);
/** @return null */
    public List<RemindVO> getAll();
/** 查詢所有下一小時到期之提醒 */
    public List<RemindVO> getAllNow();
    
}
