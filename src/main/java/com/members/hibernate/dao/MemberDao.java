package com.members.hibernate.dao;

import com.members.hibernate.pojo.MemberPojo;

import core.dao.CoreDao;

public interface MemberDao extends CoreDao<MemberPojo, Integer> {
	
	MemberPojo selectForLogin(String username, String password);
}
