package com.orders.model;

import com.common.exception.JDBCException;
import com.common.model.PageQuery;
import com.common.model.PageResult;

import java.util.List;

public class OrdersDAO implements OrdersDAO_Interface{

	@Override
	public OrdersVO insert(OrdersVO t) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public OrdersVO update(OrdersVO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrdersVO getOneById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrdersVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(OrdersVO t) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public List<OrdersVO> getOneByMemberId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer updateStatusByOrderId(Integer id,Integer status) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<OrdersVO> getAllProductByOrderId(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public OrdersVO getOrderDetail(Integer orderId, Integer productId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void addProduct2Order(Integer orderId, Integer productId, Integer quantity, Integer price) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public PageResult<OrdersVO> getPageResult(PageQuery pq) throws JDBCException{
		return null;
	}

}
