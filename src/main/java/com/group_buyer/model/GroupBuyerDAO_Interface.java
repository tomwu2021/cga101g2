package com.group_buyer.model;

import java.util.List;

import com.common.model.JDBCDAO_Interface;

public interface GroupBuyerDAO_Interface extends JDBCDAO_Interface<GroupBuyerVO> {

	// 會員找所有參加的團購
	public List<GroupBuyerVO> getAllByMemberId(Integer id);

	// 查詢單筆團購訂單所有成員
	public List<GroupBuyerVO> getAllByGroupOrderId(Integer id);

	// 刪除訂單
	public void deleteByPK(Integer groupOrderId, Integer memberId);

	// 查單筆資料

	public GroupBuyerVO selectByPK(Integer groupOrderId, Integer memberId);

}
