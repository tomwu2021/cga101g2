package com.group_order.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import connection.HibernateUtil;

public class TestGroupOrder {
	public static void main(String[] args) {
		// 產生團購訂單
//		GroupOrderJDBCDAO dao=new GroupOrderJDBCDAO();
//		GroupOrderVO groupOrderVO=new GroupOrderVO();
//		groupOrderVO.setProductId(2);
//		groupOrderVO.setEndType(0);
//		dao.insert(groupOrderVO);

		// 取得所有團購訂單
//		GroupOrderJDBCDAO dao=new GroupOrderJDBCDAO();
//		for(GroupOrderVO a:dao.getAll()) {
//			System.out.println(a);
//		}

		// 用團購訂單編號取得單一團購訂單
//		GroupOrderJDBCDAO dao=new GroupOrderJDBCDAO();
//		System.out.println(dao.getOneById(2));

		// 用商品編號取得所有團購訂單
//		GroupOrderJDBCDAO dao=new GroupOrderJDBCDAO();
//		for(GroupOrderVO a:dao.getAllByProductId(2)) {
//			System.out.println(a);
//		}

		// 用商品編號取得所有進行中團購訂單
//		GroupOrderJDBCDAO dao=new GroupOrderJDBCDAO();
//		for(GroupOrderVO a:dao.getAllInProgressByProductId(1)) {
//			System.out.println(a);
//		}

		// 取得所有進行中團購訂單
//		GroupOrderJDBCDAO dao=new GroupOrderJDBCDAO();
//		for(GroupOrderVO a:dao.getAllInProgress()) {
//			System.out.println(a);
//		}

		// 更該團購訂單截止時間
//		GroupOrderJDBCDAO dao=new GroupOrderJDBCDAO();
//		dao.updateEndTimeByGroupOrderId(2);

		// 更該團購訂單狀態
//		GroupOrderJDBCDAO dao=new GroupOrderJDBCDAO();
//		dao.updateStatusByGroupOrderId(3,1);

		
//----------------------------------------------------hibernate---------------------------------------------------------//
		
		// 測試hibernate連線
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		GroupOrderHibernateVO vo = session.get(GroupOrderHibernateVO.class, 1);
//		System.out.println(vo);
//		HibernateUtil.shutdown();

		// hibernate 查詢一筆團購訂單
//		GroupOrderHibernateDAO dao = new GroupOrderHibernateDAO();
//		System.out.println(dao.getOneById(2));
		
		//hibernate 新增一筆團購訂單
//		GroupOrderHibernateDAO dao = new GroupOrderHibernateDAO();
//		GroupOrderHibernateVO groupOrderHibernateVO=new GroupOrderHibernateVO();
//		Long datetime = System.currentTimeMillis();
//	    Timestamp timestamp = new Timestamp(datetime+(7*24*3600*1000));
//		groupOrderHibernateVO.setProductId(2);
//		groupOrderHibernateVO.setEndType(0);
//		groupOrderHibernateVO.setEndTime(timestamp);
//		dao.insert(groupOrderHibernateVO);
		
		//查詢所有資料
//		GroupOrderHibernateDAO dao = new GroupOrderHibernateDAO();
//		for(GroupOrderHibernateVO a:dao.getAll()) {
//			System.out.println(a);
//		}
		
		//用商品編號取得所有團購訂單
//		GroupOrderHibernateDAO dao = new GroupOrderHibernateDAO();
//		for(GroupOrderHibernateVO a:dao.getAllByProductId(2)) {
//			System.out.println(a);
//		}
		
		// 用商品編號取得所有進行中團購訂單
//		GroupOrderHibernateDAO dao = new GroupOrderHibernateDAO();
//		for(GroupOrderHibernateVO a:dao.getAllInProgressByProductId(7)) {
//			System.out.println(a);
//		}
		
		// 取得所有進行中團購訂單
//		GroupOrderHibernateDAO dao = new GroupOrderHibernateDAO();
//		for(GroupOrderHibernateVO a:dao.getAllInProgress()) {
//			System.out.println(a);
//		}
		
		// 更該團購訂單截止時間
		GroupOrderHibernateDAO dao = new GroupOrderHibernateDAO();
		dao.updateEndTimeByGroupOrderId(1);
		
		// 更該團購訂單狀態
//		GroupOrderHibernateDAO dao = new GroupOrderHibernateDAO();
//		dao.updateStatusByGroupOrderId(6,0);
	}
}
