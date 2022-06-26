package com.members.hibernate.util;

import com.members.hibernate.service.MemberService;
import com.members.hibernate.service.impl.MemberServiceImpl;

public class MemberConstants {
	public static final MemberService SERVICE = new MemberServiceImpl();
}
