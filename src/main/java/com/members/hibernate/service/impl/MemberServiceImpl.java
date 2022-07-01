package com.members.hibernate.service.impl;

import static core.util.HibernateUtil.getSessionFactory;
import static core.util.SHA2.getSHA256;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.members.hibernate.dao.MemberDao;
import com.members.hibernate.dao.impl.MemberDaoImpl;
import com.members.hibernate.pojo.MemberPojo;
import com.members.hibernate.service.MemberService;
import com.mysql.cj.protocol.a.authentication.Sha256PasswordPlugin;

import core.util.HibernateUtil;
import oracle.jdbc.OracleConnection.CommitOption;

public class MemberServiceImpl implements MemberService {
	private MemberDao dao;

	public MemberServiceImpl() {
		dao = new MemberDaoImpl();
	}

	@Override
	public Integer getMemberIdByAccount(String account) {

		Session session = getSessionFactory().openSession();
		try {

			Transaction transaction = session.beginTransaction();
			MemberPojo member = dao.findOneByAccount(account, session);
			if (member == null) {
				return -1;
			}
			transaction.commit();
			return member.getMemberId();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public MemberPojo login(String account, String password, Integer memberId) {
		Session session = getSessionFactory().openSession();
		try {
			Transaction transaction = session.beginTransaction();
			String passwordToSHA256 = getSHA256(password, memberId);
			MemberPojo member = dao.login(account, passwordToSHA256, session);
			if (member == null) {
				return null;
			}
			transaction.commit();
			return member;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
	}

}
