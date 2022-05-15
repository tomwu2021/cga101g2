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

@WebServlet("/shop/deleteWishlist")
public class WishlistDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("WishlistDeleteServlet 執行成功");

		final Integer productId = json2Pojo(req, WishlistVO.class).getProductId();
		System.out.println("productId" + productId);
		// 訊息用 Map 存放
		Map<String, String> msg = new LinkedHashMap<String, String>();
		req.setAttribute("msgs", msg);

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
//			System.out.println("無會員資訊");
			return;
		}

		// 1.呼叫wishlistService
		WishlistService wishSvc = new WishlistService();

		// 2.刪除收藏商品
		WishlistVO wishlistVO = new WishlistVO();
		wishlistVO.setMemberId(memberId);
		wishlistVO.setProductId(productId);

		boolean boo;
		boo = wishSvc.delete(wishlistVO);
		if (boo == true) {
			msg.put("msg", "1");
			String json = new Gson().toJson(msg);
			res.getWriter().write(json);
//			System.out.println("刪除收藏商品成功");
			return;
		} else {
			msg.put("msg", "-1");
			String json = new Gson().toJson(msg);
			res.getWriter().write(json);
//			System.out.println("刪除收藏商品失敗");
			return;
		}
	}

}