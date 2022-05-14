package com.orders.model;

import java.util.List;

import com.common.exception.JDBCException;
import com.common.model.JDBCDAO_Interface;
import com.common.model.PageQuery;
import com.common.model.PageResult;

public interface OrdersDAO_Interface extends JDBCDAO_Interface<OrdersVO> {

	// 用會員id找訂單
	public List<OrdersVO> getOneByMemberId(Integer id);

	// 修改訂單狀態
	public Integer updateStatusByOrderId(Integer id, Integer status);

	// 用訂單id查訂單所有商品
	public List<OrdersVO> getAllProductByOrderId(Integer orderId);

	// 查單個商品詳情
	public OrdersVO getOrderDetail(Integer orderId,Integer productId);
	
	//
	public void addProduct2Order(Integer orderId, Integer productId,Integer quantity,Integer price);

	//分頁搜尋
	public PageResult<OrdersVO> getPageResult(PageQuery pq) throws JDBCException;
}
