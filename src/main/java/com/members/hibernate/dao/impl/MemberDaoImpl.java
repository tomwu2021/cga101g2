package com.members.hibernate.dao.impl;

import static core.util.HibernateUtil.*;

import java.util.List;
import org.hibernate.Session;

import com.members.hibernate.dao.MemberDao;
import com.members.hibernate.pojo.MemberPojo;

public class MemberDaoImpl implements MemberDao {

	private Session getSession() {
		return getSessionFactory().getCurrentSession();
	}

	@Override
	public Integer insert(MemberPojo member) {
		getSession().persist(member);
		return 1;
	}

	@Override
	public Integer deleteById(Integer id) {
		Session session = getSession();
		MemberPojo member = session.load(MemberPojo.class, id);
		session.delete(member);
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
	public MemberPojo selectForLogin(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
