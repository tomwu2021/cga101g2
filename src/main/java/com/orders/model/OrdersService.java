package com.orders.model;

import com.common.exception.JDBCException;
import com.common.model.PageQuery;
import com.common.model.PageResult;

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
	
	public void addProduct2Order(Integer orderId, Integer productId, Integer quantity, Integer price) {
		dao.addProduct2Order(orderId, productId, quantity, price);
	}

	public PageResult<OrdersVO> getPageResult(PageQuery pq) throws JDBCException {
		return dao.getPageResult(pq);
	}
}
