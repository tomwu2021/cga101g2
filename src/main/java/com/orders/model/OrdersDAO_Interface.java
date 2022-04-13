package com.orders.model;

import java.util.List;

import com.common.model.JDBCDAO_Interface;

public interface OrdersDAO_Interface extends JDBCDAO_Interface<OrdersVO>{
	
	//用會員id找訂單
	public List<OrdersVO> getOneByMemberId(Integer id);
	
	//修改訂單狀態
	public Integer updateStatusByOrderId(Integer status,Integer id);
}
