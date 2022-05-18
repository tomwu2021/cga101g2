package com.wishlist.controller;

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
import com.members.model.MembersVO;
import com.wishlist.model.WishlistService;
import com.wishlist.model.WishlistVO;

@WebServlet("/shop/wishlistServlet")
public class WishlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("wishlistServlet 執行成功");

		// 訊息用 Map 存放
		Map<String, String> msg = new LinkedHashMap<String, String>();
		req.setAttribute("msgs", msg);

		// 收集參數
		final Integer productId = Integer.parseInt((String) req.getParameter("productId"));
		final Integer val = Integer.parseInt((String) req.getParameter("val"));
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
		// 收集參數
		System.out.println("==============");
		System.out.println("productId:" + productId);
		System.out.println("val:" + val); // 0: insert, 1: delete
		System.out.println("==============");
		
		// 1.呼叫wishlistService
		WishlistService wishSvc = new WishlistService();


		//0代表未加入收藏清單,點擊後新增
		//1代表已加入收藏清單,點擊後刪除 
		if(val == 0) {
			// 2.製作收藏商品VO
			WishlistVO wishlistVO = new WishlistVO();
			wishlistVO.setMemberId(memberId);
			wishlistVO.setProductId(productId);
			
			boolean boo;
			boo = wishSvc.insertWishlistVO(wishlistVO);
			if (boo == true) {
				msg.put("msg", "1");
				String json = new Gson().toJson(msg);
				res.getWriter().write(json);
				System.out.println("新增收藏商品成功");
				return;
			} else {
				msg.put("msg", "-1");
				String json = new Gson().toJson(msg);
				res.getWriter().write(json);
				System.out.println("新增收藏商品失敗");
				return;
			}
		}else {
			// 2.製作收藏商品VO
			WishlistVO wishlistVO = new WishlistVO();
			wishlistVO.setMemberId(memberId);
			wishlistVO.setProductId(productId);
			
			boolean boo;
			boo = wishSvc.delete(wishlistVO);
			if (boo == true) {
				msg.put("msg", "2");
				String json = new Gson().toJson(msg);
				res.getWriter().write(json);
				System.out.println("刪除收藏商品成功");
				return;
			} else {
				msg.put("msg", "-1");
				String json = new Gson().toJson(msg);
				res.getWriter().write(json);
				System.out.println("刪除收藏商品失敗");
				return;
			}
		}
		
	}

}