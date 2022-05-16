package com.likelist.controller;

import static com.util.GSONUtil.writePojo2Json;
import static com.util.GSONUtil.json2Pojo;

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

import com.google.gson.Gson;
import com.likelist.model.LikelistService;
import com.likelist.model.LikelistVO;
import com.members.model.MembersVO;
import com.wishlist.model.WishlistService;
import com.wishlist.model.WishlistVO;

@WebServlet("/post/LikelistDeleteServlet")
public class LikelistDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		doPost(req, res);
//	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("LikelistDeleteServlet 執行成功");
		
		// 訊息用 Map 存放
		Map<String, String> msg = new LinkedHashMap<String, String>();
		req.setAttribute("msgs", msg);

		final Integer postId = json2Pojo(req, LikelistVO.class).getPostId();
		System.out.println("postId : " + postId);

		// 收集參數

		Integer memberId = null;
		HttpSession session = req.getSession();
		if ((MembersVO) session.getAttribute("membersVO") != null) {
			MembersVO memberVO = (MembersVO) session.getAttribute("membersVO");
			memberId = memberVO.getMemberId();
		} else {
			msg.put("msg", "-2");
			String json = new Gson().toJson(msg);
			res.getWriter().write(json);
			System.out.println("無會員資訊");
			return;
		}

		// 1.呼叫LikelistService
		LikelistService likelistSvc = new LikelistService();

		// 2.新增讚
		LikelistVO likelistVO = new LikelistVO();
		likelistVO.setMemberId(memberId);
		likelistVO.setPostId(postId);

		boolean boo = likelistSvc.delete(likelistVO);
		if (boo == true) {
			msg.put("msg", "1");
			String json = new Gson().toJson(msg);
			res.getWriter().write(json);
			System.out.println("收回貼文按讚成功");
			return;
		} else {
			msg.put("msg", "-1");
			String json = new Gson().toJson(msg);
			res.getWriter().write(json);
			System.out.println("收回貼文按讚失敗");
			return;
		}
	}

}