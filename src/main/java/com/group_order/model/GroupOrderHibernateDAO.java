package com.group_order.model;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import connection.HibernateUtil;

public class GroupOrderHibernateDAO implements GroupOrderHibernateDAO_interface{

	@Override
	public GroupOrderHibernateVO insert(GroupOrderHibernateVO groupOrderHibernateVO) {
		// TODO Auto-generated method stub
		
		//HQL寫法
		Long datetime = System.currentTimeMillis();
	    Timestamp timestamp = new Timestamp(datetime+(7*24*3600*1000));
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		NativeQuery<?> nativeQuery = session.createSQLQuery("insert into group_order(product_id,end_time,end_type,status) "
				+ "values(:productId,:endTime,:endType,:status)")
				.setParameter("productId", groupOrderHibernateVO.getProductId()).setParameter("endTime", timestamp)
				.setParameter("endType", groupOrderHibernateVO.getEndType()).setParameter("status",0);
		nativeQuery.executeUpdate();
		transaction.commit();

		//一般寫法		
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		try {
//			Transaction transaction = session.beginTransaction();
//			session.persist(groupOrderHibernateVO);
//			transaction.commit();
//			return groupOrderHibernateVO;
//		} catch (Exception e) {
//			// TODO: handle exception
//			session.getTransaction().rollback();
//			e.printStackTrace();
//		}
		return null;
	}

	@Override
	public boolean delete(GroupOrderHibernateVO t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GroupOrderHibernateVO update(GroupOrderHibernateVO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GroupOrderHibernateVO getOneById(Integer id) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			GroupOrderHibernateVO groupOrderHibernateVO=session.get(GroupOrderHibernateVO.class, id);
			transaction.commit();
			return groupOrderHibernateVO;
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<GroupOrderHibernateVO> getAll() {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("FROM GroupOrderHibernateVO");
		List<GroupOrderHibernateVO> groupOrderList = query.list();

		return groupOrderList;
	}

	@Override
	public List<GroupOrderHibernateVO> getAllByProductId(Integer id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("FROM GroupOrderHibernateVO where product_id="+String.valueOf(id));
		List<GroupOrderHibernateVO> groupOrderList = query.list();

		return groupOrderList;
	}

	@Override
	public List<GroupOrderHibernateVO> getAllInProgressByProductId(Integer id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("FROM GroupOrderHibernateVO where product_id="+String.valueOf(id)+" and status=0");
		List<GroupOrderHibernateVO> groupOrderList = query.list();

		return groupOrderList;
	}

	@Override
	public List<GroupOrderHibernateVO> getAllInProgress() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("FROM GroupOrderHibernateVO where status=0");
		List<GroupOrderHibernateVO> groupOrderList = query.list();

		return groupOrderList;
	}

	@Override
	public Integer updateEndTimeByGroupOrderId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateStatusByGroupOrderId(Integer id, Integer status) {
		// TODO Auto-generated method stub
		return null;
	}



}
