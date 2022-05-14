package com.group_buyer.model;

import java.util.List;

import com.common.model.PageQuery;
import com.common.model.PageResult;
import com.picture.model.PictureResult;

public class GroupBuyerService {

	private GroupBuyerDAO_Interface dao;

	public GroupBuyerService() {
		dao = new GroupBuyerJDBCDAO();
	}

	public GroupBuyerVO addGroupBuyer(Integer groupOrderId, Integer memberId, Integer productAmount, String phone,
			String address, String recipients) {
		GroupBuyerVO groupBuyerVO = new GroupBuyerVO();
		groupBuyerVO.setGroupOrderId(groupOrderId);
		groupBuyerVO.setRecipients(recipients);
		groupBuyerVO.setPhone(phone);
		groupBuyerVO.setAddress(address);
		groupBuyerVO.setMemberId(memberId);
		groupBuyerVO.setProductAmount(productAmount);
		dao.insert(groupBuyerVO);
		return groupBuyerVO;
	}

	public GroupBuyerVO updateGroupBuyer(Integer groupOrderId, Integer memberId, String phone, String address,
			String recipients) {
		GroupBuyerVO groupBuyerVO = new GroupBuyerVO();
		groupBuyerVO.setGroupOrderId(groupOrderId);
		groupBuyerVO.setRecipients(recipients);
		groupBuyerVO.setPhone(phone);
		groupBuyerVO.setAddress(address);
		groupBuyerVO.setMemberId(memberId);
		return dao.update(groupBuyerVO);
//		return groupBuyerVO;
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

	public void delete(Integer groupOrderId, Integer memberId) {
		dao.deleteByPK(groupOrderId, memberId);
	}

	public GroupBuyerVO selectOrderDetail(Integer groupOrderId, Integer memberId) {
		return dao.selectByPK(groupOrderId, memberId);
	}
	public PageResult<GroupBuyerVO> getPageResult(PageQuery pageQuery) {
		return dao.getPageResult(pageQuery);
	}
}
