package com.wishlist.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.members.model.MembersVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.google.gson.Gson;

/**
 * Servlet implementation class WishlistInsertServlet
 */
@WebServlet("/shop/wishlist")
public class WishlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("WishlistServlet 執行成功");
		req.setCharacterEncoding("UTF-8");// client 端向 Servlet 請求的編碼
		res.setCharacterEncoding("UTF-8");// response，設定回應的格式及編碼
		
		
//		列舉client送來的所有請求參數名稱
		try{
			String name; 
			Enumeration<?>  pNames=req.getParameterNames(); 
			 while(pNames.hasMoreElements()){ 
			  name=(String)pNames.nextElement();
			  System.out.println(name+"="+req.getParameter(name));
			  }
			}catch(Exception e){
			System.out.println(e.toString());
			}
		
		
		String action = req.getParameter("action");
		if ("getWishlist".equals(action)) { 

		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		req.setAttribute("errorMsgs", errorMsgs);

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************************/
		// 獲取req的路徑
		StringBuffer wishlistServlet = req.getRequestURL();
		System.out.println("req.getPathTranslated(); :"+ wishlistServlet.toString());
		String wishlistServletQuery = req.getQueryString();
		System.out.println("req.getQueryString(); :"+ wishlistServletQuery);
		
		String userUrl = req.getHeader("Referer");
		System.out.println("req.getHeader(\"Referer\"); :"+ userUrl);
		// 判斷有沒有登入 Session
		HttpSession session = req.getSession();
		Integer memberId = null;
		if ((MembersVO)session.getAttribute("membersVO")!=null) {
			MembersVO memberVO = (MembersVO)session.getAttribute("membersVO");
			memberId = memberVO.getMemberId();
		}else {
			errorMsgs.put("MemberId", "請先登入");
		}

		
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			res.sendRedirect(userUrl);  
			return;
		}
		/*************************** 2.開始複合查詢 ***************************************/
		ProductService pdSvc = new ProductService();
		
		
		/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//		req.setAttribute("listGroupProducts_Byfind", list); // 資料庫取出的list物件,存入request

		// **********************0506要轉交給shop2改成轉交給shop***********************//

		RequestDispatcher successView = req.getRequestDispatcher("/front/shop/groupsShop.jsp"); // 成功轉交shop.jsp
		successView.forward(req, res);
	}
}
}
