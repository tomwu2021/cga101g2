package com.product.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
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

import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.sort1.model.Sort1Service;
import com.sort1.model.Sort1VO;
import com.sort2.model.Sort2Service;
import com.sort2.model.Sort2VO;
import com.sort_mix.model.SortMixService;

@WebServlet("/back/shop")
public class ProductBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		System.out.println("ProductBackServlet 執行成功");
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
		
		
		if ("listProducts_Byfind".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.將輸入資料轉為Map **********************************/
			// 採用Map<String,String[]> getParameterMap()的方法
			// 注意:an immutable java.util.Map
			// Map<String, String[]> map = req.getParameterMap();
			HttpSession session = req.getSession();
			Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");

			// 以下的 if 區塊只對第一次執行時有效
			if (req.getParameter("whichPage") == null) {
				Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
				session.setAttribute("map", map1);
				map = map1;
			}

			/*************************** 2.開始複合查詢 ***************************************/
			ProductService pdSvc = new ProductService();
			List<ProductVO> list = pdSvc.getAll(map);
			//*********分界點*********//
			Sort1Service sort1Svc = new Sort1Service();
			List<Sort1VO> sort1VOList = sort1Svc.getAll();
			
			Sort2Service sort2Svc = new Sort2Service();
			List<Sort2VO> sort2VOList = sort2Svc.getAll();
			
			List<ProductVO> forSelectList = pdSvc.getAll();
			//*********分界點*********//
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("listProducts_Byfind", list); // 資料庫取出的list物件,存入request
			//*********分界點*********//
			req.setAttribute("sort1VOList", sort1VOList); 
			req.setAttribute("sort2VOList", sort2VOList); 
			req.setAttribute("forSelectList", forSelectList); 
			req.setAttribute("map", map); 
			//*********分界點*********//
			// **********************0506要轉交給shop2改成轉交給shop***********************//

			RequestDispatcher successView = req.getRequestDispatcher("/back/shop/listAllProduct.jsp"); // 成功轉交shop.jsp
			successView.forward(req, res);
		}
	}
}
