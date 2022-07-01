package com.members.hibernate.service;

import java.util.List;

import org.hibernate.Session;

import com.members.hibernate.pojo.MemberPojo;

import core.service.CoreService;

public interface MemberService extends CoreService {

	MemberPojo login(String account, String password, Integer memberId);
	
	Integer getMemberIdByAccount(String account);
}
