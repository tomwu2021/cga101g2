package com.group_buyer.model;

import java.util.List;

public class GroupBuyerService {

	private GroupBuyerDAO_Interface dao;
	
	public GroupBuyerService() {
		dao=new GroupBuyerJDBCDAO();
	}
	
	public GroupBuyerVO addGroupBuyer(Integer groupOrderId,Integer memberId,Integer productAmount,String phone,String address,String recipients) {
		GroupBuyerVO groupBuyerVO=new GroupBuyerVO();
		groupBuyerVO.setGroupOrderId(groupOrderId);
		groupBuyerVO.setRecipients(recipients);
		groupBuyerVO.setPhone(phone);
		groupBuyerVO.setAddress(address);
		groupBuyerVO.setMemberId(memberId);
		groupBuyerVO.setProductAmount(productAmount);
		dao.insert(groupBuyerVO);
		return groupBuyerVO;
	}
	public GroupBuyerVO updateGroupBuyer(Integer groupOrderId,Integer memberId,String phone,String address,String recipients) {
		GroupBuyerVO groupBuyerVO=new GroupBuyerVO();
		groupBuyerVO.setGroupOrderId(groupOrderId);
		groupBuyerVO.setRecipients(recipients);
		groupBuyerVO.setPhone(phone);
		groupBuyerVO.setAddress(address);
		groupBuyerVO.setMemberId(memberId);
		dao.insert(groupBuyerVO);
		return groupBuyerVO;
	}
	
	public List<GroupBuyerVO> getAll() {
		return dao.getAll();
	}
	
	public List<GroupBuyerVO> getAllByMemberId(Integer id) {
		return dao.getAllByMemberId(id);
	}
	
	public List<GroupBuyerVO> getAllByGroupOrderId(Integer id) {
		return dao.getAllByGroupOrderId(id);
	}
	
	public List<GroupBuyerVO> delete(Integer groupOrderId, Integer memberId) {
		return dao.deleteByPK(groupOrderId, memberId);
	}
}
