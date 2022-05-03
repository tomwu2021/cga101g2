package com.group_buyer.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import connection.HibernateUtil;

public class TestGroupBuyer {
	public static void main(String[] args) {
		// 新增參團者
//		GroupBuyerJDBCDAO dao=new GroupBuyerJDBCDAO();
//		GroupBuyerVO groupBuyerVO=new GroupBuyerVO();
//		groupBuyerVO.setGroupOrderId(5);
//		groupBuyerVO.setRecipients("佩佩豬把拔");
//		groupBuyerVO.setPhone("0999887766");
//		groupBuyerVO.setAddress("台中屠宰場");
//		groupBuyerVO.setMemberId(1);
//		groupBuyerVO.setProductAmount(3);
//		System.out.println(dao.insert(groupBuyerVO));

		// 查詢所有團購成員
//		GroupBuyerJDBCDAO dao=new GroupBuyerJDBCDAO();
//		for(GroupBuyerVO a:dao.getAll()) {
//			System.out.println(a);
//		}

		// 會員找所有參加的團購
//		GroupBuyerJDBCDAO dao=new GroupBuyerJDBCDAO();
//		for(GroupBuyerVO a:dao.getAllByMemberId(1)) {
//			System.out.println(a);
//		}

		// 查詢單筆團購訂單所有成員
//		GroupBuyerJDBCDAO dao=new GroupBuyerJDBCDAO();
//		for(GroupBuyerVO a:dao.getAllByGroupOrderId(1)) {
//			System.out.println(a);
//		}

		// 刪除訂單
//		GroupBuyerJDBCDAO dao=new GroupBuyerJDBCDAO();
//		dao.deleteByPK(2,1);

		// 更改資料
//		GroupBuyerJDBCDAO dao=new GroupBuyerJDBCDAO();
//		GroupBuyerVO groupBuyerVO=new GroupBuyerVO();
//		groupBuyerVO.setGroupOrderId(1);
//		groupBuyerVO.setRecipients("維尼熊雄把拔");
//		groupBuyerVO.setPhone("0988765684");
//		groupBuyerVO.setAddress("台南屠宰場");
//		groupBuyerVO.setMemberId(1);
//		dao.update(groupBuyerVO);

		// 測試hibernate複合主鍵
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		GroupBuyerPK groupBuyerPK=new GroupBuyerPK(1,1);
//		GroupBuyerHibernateVO vo = session.get(GroupBuyerHibernateVO.class, groupBuyerPK);
//		System.out.println(vo);
//		HibernateUtil.shutdown();

		// 查單筆記鍵資訊
//		GroupBuyerJDBCDAO dao=new GroupBuyerJDBCDAO();
//		System.out.println(dao.selectByPK(1, 1));
		
		GroupBuyerJDBCDAO dao=new GroupBuyerJDBCDAO();
		System.out.println(dao.selectByPK(1, 1));

	}
}
