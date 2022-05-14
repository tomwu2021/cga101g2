package com.group_order.model;

import java.util.List;

public class GroupOrderService {
	private GroupOrderDAO_Interface dao;
	
	public GroupOrderService () {
		dao=new GroupOrderJDBCDAO();
	}
	
	public GroupOrderVO addGroupOrder(Integer productId,Integer endType,Integer finalPrice,Integer minAmount) {		
		GroupOrderVO groupOrderVO=new GroupOrderVO();
		groupOrderVO.setProductId(productId);
		groupOrderVO.setEndType(endType);
		groupOrderVO.setFinalPrice(finalPrice);
		groupOrderVO.setMinAmount(minAmount);
		dao.insert(groupOrderVO);
		return groupOrderVO;
	}
	
	public GroupOrderVO getOneOrder(Integer id) {

		return dao.getOneById(id);
	}
	
	public List<GroupOrderVO> getAll() {
		
		return dao.getAll();
	}
	
	public List<GroupOrderVO> getAllByProductId(Integer id) {
		
		return dao.getAllByProductId(id);
	}
	
	public void updateEndTime(Integer id) {
		
		dao.updateEndTimeByGroupOrderId(id);
	}
	
	public void updateStatus(Integer id, Integer status) {
		dao.updateStatusByGroupOrderId(id, status);
	}
	public List<GroupOrderVO> getAllInProgressByProductId(Integer id) {
		return getAllInProgressByProductId(id);
	}
	
	public List<GroupOrderVO> getAllInProgress() {
		return dao.getAllInProgress();
	}
	
	
}
