package com.orders.model;

import java.sql.Timestamp;
import java.util.List;

public class OrdersService {
	private OrdersDAO_Interface dao;
	
	public OrdersService() {
		dao=new OrdersJDBCDAO();
	}
	
	public OrdersVO addOneOrder(Integer memberId,String recipient,String phone,String address,Integer sumPrice,Integer bonus,Integer discount,Integer payPrice) {
		OrdersVO ordersVO=new OrdersVO();
		ordersVO.setMemberId(memberId);
		ordersVO.setRecipient(recipient);
		ordersVO.setPhone(phone);
		ordersVO.setAddress(address);
		ordersVO.setSumPrice(sumPrice);
		ordersVO.setBonus(bonus);
		ordersVO.setDiscount(discount);
		ordersVO.setPayPrice(payPrice);
		dao.insert(ordersVO);		
		return ordersVO;
	}
	
	public OrdersVO getOneById(Integer id) {
		return dao.getOneById(id);
	}
	
	public List<OrdersVO> getAll() {
		return dao.getAll();
	}
	
	public List<OrdersVO> getAllByMemberId(Integer id) {
		return dao.getOneByMemberId(id);
	}
	
	public void updateStatusByOrderId(Integer id,Integer status) {
		dao.updateStatusByOrderId(id, status);
	}
	
	public List<OrdersVO> getAllProductInOrder(Integer orderId){
		return dao.getAllProductByOrderId(orderId);
	}
	
	public OrdersVO getAllProductPicture(Integer orderId,Integer productId){
		return dao.getOrderDetail(orderId, productId);
	}
}
