package com.product.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

@WebServlet("/shop")
public class ShopFrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("ShopFrontServlet 執行成功");
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
		if ("listProducts_Byfind".equals(action)) {
			
			// 一般購物公開頁面 轉交給shop.jsp 只接受參數 statu=1 or status =2
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
			  ///********推薦商品資料 開始*********///
		    Map<String, String[]> topMap = new TreeMap<String, String[]>();
		    topMap.put("top_status", new String[] { "1" });
		    List<ProductVO> topProdcutList = pdSvc.getForShopFront(topMap);
		    ///********推薦商品資料 結束*********///
			
			
			List<ProductVO> list = pdSvc.getForShopFront(map);
			
			//搜尋所有主分類"們" 放入對應的子分類"們"
			Sort1Service sort1Service = new Sort1Service();
			List<Sort1VO> sort1VOList= sort1Service.getAll();
			
			SortMixService sortMixService = new SortMixService();
			
		    for (Sort1VO sort1VO: sort1VOList) {
		    	Integer sort1Id = sort1VO.getSort1Id();
		    	List<Sort2VO> sort2VOList = sortMixService.getSort2VOsBySort1Id(sort1Id);
		    	sort1VO.setSort2VOList(sort2VOList);
		    }
		
		    ///********跳轉到該分類頁面的分類資料分界點 開始*********///
		    Sort1VO sort1VO = new Sort1VO();
			// 哪一個主分類做的判斷
			if (req.getParameter("sort1_id") != null) {
				Integer sort1Id = null;
				sort1Id = Integer.valueOf(req.getParameter("sort1_id").trim());
				sort1VO = sort1Service.getOneById(sort1Id);
			}
			Sort2Service sort2Service = new Sort2Service();
			Sort2VO sort2VO = new Sort2VO();
				// 哪一個子分類做的判斷
			if (req.getParameter("sort2_id") != null) {
				Integer sort2Id = null;
				sort2Id = Integer.valueOf(req.getParameter("sort2_id").trim());
				sort2VO = sort2Service.getOneById(sort2Id);
			}
			 ///********分類的分界點 結束*********///
			
		    
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			 ///********推薦商品資料 開始*********///
			req.setAttribute("topProdcutList", topProdcutList);
		    ///********推薦商品資料 結束*********///
			req.setAttribute("listProducts_Byfind", list); // 資料庫取出的list物件,存入request
			req.setAttribute("sort1VOListIncludesort2VOList", sort1VOList);
			///********跳轉到該分類頁面的分類資料分界點 開始*********///
			req.setAttribute("thisSort1VO", sort1VO);
			req.setAttribute("thisSort2VO", sort2VO);
			///********分類的分界點 結束*********///
			
			// **********************0506要轉交給shop2改成轉交給shop***********************//

			RequestDispatcher successView = req.getRequestDispatcher("/front/shop/shop2.jsp"); // 成功轉交shop.jsp
			successView.forward(req, res);
		}
	}
}
