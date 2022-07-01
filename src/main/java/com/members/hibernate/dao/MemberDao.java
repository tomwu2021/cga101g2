package com.members.hibernate.dao;

import com.members.hibernate.pojo.MemberPojo;

import core.dao.CoreDao;

public interface MemberDao extends CoreDao<MemberPojo, Integer> {
	
	// 判斷此帳號在資料庫使否有資料
	MemberPojo findOneByAccount(String account);

	MemberPojo login(String account, String password);
}
