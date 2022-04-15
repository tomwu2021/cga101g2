package com.orders.model;

import java.util.List;

public class TestOrders {
	public static void main(String[] args) {
		
		//新增一筆訂單
//		OrdersJDBCDAO dao=new OrdersJDBCDAO();
//		OrdersVO ordersVO=new OrdersVO();
//		ordersVO.setMemberId(5);
//		ordersVO.setRecipient("張偉");
//		ordersVO.setPhone("0976543218");
//		ordersVO.setAddress("高雄市美麗島捷運站");
//		ordersVO.setSumPrice(750);
//		ordersVO.setBonus(0);
//		ordersVO.setDiscount(0);
//		ordersVO.setPayPrice(750);
//		dao.insert(ordersVO);
		
		//查所有訂單
//		OrdersJDBCDAO dao=new OrdersJDBCDAO();
//		OrdersVO ordersVO=new OrdersVO();
//		for(OrdersVO o:dao.getAll()){
//			System.out.println(o);
//		}
		
		//訂單編號查訂單
//		OrdersJDBCDAO dao=new OrdersJDBCDAO();
//		System.out.println(dao.getOneById(1));
		
		//會員編號查訂單
//		OrdersJDBCDAO dao=new OrdersJDBCDAO();
//		for(OrdersVO o:dao.getOneByMemberId(5)){
//			System.out.println(o);
//		}
		
		//訂單編號改出貨狀態
		OrdersJDBCDAO dao=new OrdersJDBCDAO();
		dao.updateStatusByOrderId(2, 1);
	}
}
