package com.members.hibernate.dao.impl;

import static core.util.HibernateUtil.*;
import static core.util.HibernateUtil.getSessionFactory;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import com.members.hibernate.dao.MemberDao;
import com.members.hibernate.pojo.MemberPojo;

public class MemberDaoImpl implements MemberDao {

	@Override
	public Integer insert(MemberPojo member) {
		getSession().persist(member);
		return 1;
	}

	@Override
	public Integer deleteById(Integer id) {
		MemberPojo member = new MemberPojo();
		member.setMemberId(id);
		getSession().remove(member);
		return 1;
	}

	@Override
	public Integer update(MemberPojo newMember) {
		MemberPojo member = getSession().load(MemberPojo.class, newMember.getMemberId());
		member.setPassword(newMember.getAccount());
		return 1;
	}

	@Override
	public MemberPojo selectById(Integer id) {
		return getSession().get(MemberPojo.class, id);
	}

	@Override
	public List<MemberPojo> selectAll() {
		return getSession().createQuery("from Member", MemberPojo.class).list();
	}

	@Override
	public MemberPojo findOneByAccount(String account, Session session) {
		
		Query<MemberPojo> query = session.createQuery("FROM MemberPojo WHERE account = :account", MemberPojo.class)
				.setParameter("account", account);
		MemberPojo member = query.uniqueResult();
		return member;
	}

	@Override
	public MemberPojo login(String account, String password, Session session) {
		Query<MemberPojo> query = session.createQuery("FROM MemberPojo WHERE account = :account AND password = :password", MemberPojo.class)
				.setParameter("account", account)
				.setParameter("password", password);
		MemberPojo member = query.uniqueResult();
		return member;
	}


}
