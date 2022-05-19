package com.post.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.likelist.model.LikelistService;
import com.likelist.model.LikelistVO;
import com.members.model.MembersVO;
import com.post.model.PostService;
import com.post.model.PostVO;
import com.product.model.ProductVO;
import com.wishlist.model.WishlistService;
import com.wishlist.model.WishlistVO;

@WebServlet("/MainPost")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 10 * 1024 * 1024, maxRequestSize = 10 * 10 * 1024
		* 1024)
public class PostMainController extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
	

		// 列舉client送來的所有請求參數名稱
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

		/**
		 * 查詢status狀態為0的貼文
		 * 
		 */

		if ("selectChangePost".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 **********************************/
//				Integer memberId = Integer.valueOf(req.getParameter("memberId").trim());
			// 查詢該Session的memberId
			Integer memberId = null;
			HttpSession session = req.getSession();
			if ((MembersVO) session.getAttribute("membersVO") != null) {
				MembersVO memberVO = (MembersVO) session.getAttribute("membersVO");
				memberId = memberVO.getMemberId();
			}
			/*************************** 2.開始查詢資料 ***************************************/
			PostService ps = new PostService();
//				java.util.Date date1 = new java.util.Date();

			
			List<PostVO> postlist = ps.selectChangePost();
			// **點讚清單分界 開始**//
			if (memberId != null) {
				LikelistService likeListSvc = new LikelistService();
				for (PostVO vo : postlist) {
					LikelistVO likeListVO = likeListSvc.getOneLikelistVOForCheck(memberId,vo.getPostId());
					vo.setLikelistVO(likeListVO);
				}
			}
			// **點讚清單分界 結束**//
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("postlist", postlist);
			req.setAttribute("sessionMemberId", memberId);

//				java.util.Date date2 = new java.util.Date();
//				System.out.println("elapsed time: " + (date2.getTime() - date1.getTime()));

			String url = "/front/post/blog.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

	}

}
