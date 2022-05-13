package com.product.controller;

import java.io.IOException;
import java.sql.Date;
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

import com.picture.model.PictureVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.product_img.model.ProductImgService;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/groupShop")
public class GroupShopFrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");// client 端向 Servlet 請求的編碼
		res.setCharacterEncoding("UTF-8");// response，設定回應的格式及編碼
		String action = req.getParameter("action");
		
		
		//給團購商品groupsShop.jsp
		if ("listGroupProducts_Byfind".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				
				// 以下的 if 區塊只對第一次執行時有效
				if (req.getParameter("whichPage") == null){
					Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					session.setAttribute("map",map1);
					map = map1;
				} 
				
				/***************************2.開始複合查詢***************************************/
				long totalSeconds1 = (System.currentTimeMillis());
				
				ProductService pdSvc = new ProductService();
				List<ProductVO> list  = pdSvc.getForGroupShopFront(map);
				
				ProductImgService piSvc = new ProductImgService();
				for(ProductVO vo: list) {
					List<PictureVO> pictureVOList = piSvc.getPicVOsByProductId(vo.getProductId());
					vo.setPictureVOList(pictureVOList);
				}
				
				long totalSeconds2 = (System.currentTimeMillis()) ;
				System.out.println(totalSeconds2 - totalSeconds1);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listGroupProducts_Byfind", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/front/shop/groupsShop.jsp"); // 成功轉交groupsShop.jsp
				successView.forward(req, res);
		}
	}
}
