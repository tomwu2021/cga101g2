package com.members.hibernate.service.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.members.hibernate.dao.MemberDao;
import com.members.hibernate.dao.impl.MemberDaoImpl;
import com.members.hibernate.service.MemberService;

import core.util.HibernateUtil;

public class MemberServiceImpl implements MemberService {

	private MemberDao dao;

	public MemberServiceImpl() {
		dao = new MemberDaoImpl();
	}

	public void someMethod() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Transaction transaction = session.beginTransaction();

			// dao.insert(member); // 新增
			// dao.deleteById(1); // 刪除
			// dao.update(member); // 修改
			// Member member = dao.selectById(1); // 查單筆
			// List<Member> list = dao.selectAll(); // 查多筆

			transaction.commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}
}
