package com.wishlist.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.members.model.MembersVO;
import com.google.gson.Gson;

/**
 * Servlet implementation class WishlistInsertServlet
 */
@WebServlet("/shop/wishlist/getSessionMemberId")
public class GetMemberSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("GetMemberSession 執行成功");
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");

//		列舉client送來的所有請求參數名稱
		try {
			String name;
			Enumeration<?> pNames = req.getParameterNames();
			while (pNames.hasMoreElements()) {
				name = (String) pNames.nextElement();
				System.out.println(name + "=" + req.getParameter(name));
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		// 訊息用 Map 存放
		Map<String, String> Msgs = new LinkedHashMap<String, String>();
		req.setAttribute("Msgs", Msgs);
		
		
		Integer memberId = null;
		HttpSession session = req.getSession();
		
		if((MembersVO)session.getAttribute("membersVO")!=null) {
			MembersVO memberVO = (MembersVO)session.getAttribute("membersVO");
			memberId = memberVO.getMemberId();
			Msgs.put("msg", "1");
			Msgs.put("memberId", String.valueOf(memberId));
			String json = new Gson().toJson(Msgs);
			res.getWriter().write(json);
			System.out.println("收藏清單有登入");
			return;
		}else {
			Msgs.put("msg", "0");
			String json = new Gson().toJson(Msgs);
			res.getWriter().write(json);
			System.out.println("收藏清單沒登入");
			return;
		}
		
	}
}
