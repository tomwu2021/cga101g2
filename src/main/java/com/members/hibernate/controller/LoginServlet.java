package com.members.hibernate.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;

import java.io.IOException;

import static com.members.hibernate.util.MemberConstants.SERVICE;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.members.hibernate.pojo.MemberPojo;

@WebServlet("/member/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		request.setCharacterEncoding("UTF-8");// client 端向 Servlet 請求的編碼
		response.setCharacterEncoding("UTF-8");// response，設定回應的格式及編碼

		MemberPojo member = new MemberPojo();
		// 接收參數
		String account = request.getParameter("account");
		String password = request.getParameter("password");

		System.out.println(account);
		System.out.println(password);

		if (account == null || (account.trim()).length() == 0 || password == null || (password.trim()).length() == 0) {
			member.setMessage("請確認帳號密碼");
			member.setSuccessful(false);
			String json = new Gson().toJson(member);
			response.getWriter().write(json);
			return;
		}

		// 用 account 找 memberId
		Integer memberId = SERVICE.getMemberIdByAccount(account);

		if (memberId == -1) {
			member.setMessage("請確認帳號密碼");
			member.setSuccessful(false);
			String json = new Gson().toJson(member);
			response.getWriter().write(json);
			return;
		}

		MemberPojo newMember = SERVICE.login(account, password, memberId);

		if (newMember == null) {
			member.setMessage("請確認帳號密碼");
			member.setSuccessful(false);
			String json = new Gson().toJson(member);
			response.getWriter().write(json);
			return;
		}
		
		member.setMessage("登入成功");
		member.setSuccessful(true);
		String json = new Gson().toJson(member);
		response.getWriter().write(json);
		return;
	}
}
