package com.orders.model;

public class TestOrders {
	public static void main(String[] args) {
		//新增一筆訂單
		OrdersJDBCDAO dao=new OrdersJDBCDAO();
		OrdersVO ordersVO=new OrdersVO();
		ordersVO.setMemberId(5);
		ordersVO.setRecipient("張偉");
		ordersVO.setPhone("0976543218");
		ordersVO.setAddress("高雄市美麗島捷運站");
		ordersVO.setSumPrice(750);
		ordersVO.setBonus(0);
		ordersVO.setDiscount(0);
		ordersVO.setPayPrice(750);
		dao.insert(ordersVO);
	}
}
