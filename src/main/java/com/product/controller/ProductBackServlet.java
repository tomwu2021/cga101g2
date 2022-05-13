package com.product.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

import com.p_sort1.model.PSort1Service;
import com.picture.model.PictureVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.product_img.model.ProductImgService;
import com.sort1.model.Sort1Service;
import com.sort1.model.Sort1VO;
import com.sort2.model.Sort2Service;
import com.sort2.model.Sort2VO;

@WebServlet("/back/shop")
public class ProductBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String sort1_name[];

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("ProductBackServlet 執行成功");
		req.setCharacterEncoding("UTF-8");// client 端向 Servlet 請求的編碼
		res.setCharacterEncoding("UTF-8");// response，設定回應的格式及編碼

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

		String action = req.getParameter("action");
		if ("listProducts_Byfind".equals(action)) {
			String whichPage = req.getParameter("whichPage"); // 1
			int pageSize = 9;
			int pageNo = 0;
			if (whichPage != null) {
				pageNo = (Integer.parseInt(whichPage) - 1) * 9;
			}
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
			// ***儲存clent端有輸入的參數 START***//
			Map<String, String> userMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("userMsgs", userMsgs);
			
			Map<String, String[]> userMsgsSort1 = new LinkedHashMap<String, String[]>();
			req.setAttribute("userMsgsSort1", userMsgsSort1);
			
			String product_id = null;
			if(req.getParameter("product_id") !=null) {
				product_id = req.getParameter("product_id");
				userMsgs.put("product_id", product_id);
			}
			String product_name =null;  
			if ( req.getParameter("product_name") != null) {
				product_name = req.getParameter("product_name") ;
				userMsgs.put("product_name", product_name);
			} 			
			
			Sort1Service sort1Svc = new Sort1Service();
			String sort1_id[] = null;
			if ( req.getParameterValues("sort1_id") == null) {
//				System.out.println("本次userMsgsSort1未選擇任何主分類checkbox");
			} else{
				sort1_id  = req.getParameterValues("sort1_id");
				userMsgsSort1.put("sort1_id", sort1_id);
				
//				for (int i =0; i <= sort1_id.length ;i++) {
//					String sort1_name1 = sort1Svc.getOneById(Integer.valueOf(sort1_id[i])).getSort1Name();
//					String sort1_name[] = new String[Integer.valueOf(sort1_id.length)];
//					sort1_name[i] = sort1_name1 ;
//					System.out.println(sort1_name[i]);
//				}
//				userMsgsSort1.put("sort1_name", sort1_name);
//				System.out.println(sort1_name);
			}
			
			Sort2Service sort2Svc = new Sort2Service();
			String sort2_id = null;
			String sort2_name = null;
			if(req.getParameter("sort2_id") !=null && !req.getParameter("sort2_id").isBlank()) {
					sort2_id = req.getParameter("sort2_id");
					userMsgs.put("sort2_id", sort2_id);
					
					sort2_name = sort2Svc.getOneById(Integer.valueOf(req.getParameter("sort2_id").trim())).getSort2Name();
					userMsgs.put("sort2_name", sort2_name);
			}
			
			String top_status = null;
			if( req.getParameter("top_status")!=null && !req.getParameter("top_status").isBlank()) {
					top_status = req.getParameter("top_status");
					userMsgs.put("top_status", top_status);
			}
			
			String status = null;
			if(req.getParameter("status") !=null && !req.getParameter("status").isBlank()) {
				status = req.getParameter("status");
				userMsgs.put("status", status);
			}
			
			String startTime =null;  
			if ( req.getParameter("startTime") != null) {
				startTime = req.getParameter("startTime") ;
				userMsgs.put("startTime", startTime);
			} 
			String endTime =null;  
			if ( req.getParameter("endTime") != null) {
				endTime = req.getParameter("endTime") ;
				userMsgs.put("endTime", endTime);
			} 
			// ***儲存clent端有輸入的參數 END***//

			/*************************** 2.開始複合查詢 ***************************************/
			long totalSeconds1 = (System.currentTimeMillis());

			Map<String, String[]> map2 = new TreeMap<String, String[]>(); //複製一份給getAllTotalCount用
			map2.putAll(map);
			
			ProductService pdSvc = new ProductService();
			List<ProductVO> list = pdSvc.getAll(map, pageSize, pageNo);
		
			int total = pdSvc.getAllTotalCount(map2); // total count
			
			ProductImgService piSvc = new ProductImgService();
			PSort1Service pSort1Svc = new PSort1Service();
			for (ProductVO vo : list) {
				List<PictureVO> pictureVOList = piSvc.getPicVOsByProductId(vo.getProductId());
				vo.setPictureVOList(pictureVOList);
				List<Sort1VO> PSort1VOList = pSort1Svc.findSort1VOByproductId(vo.getProductId());
				vo.setPSort1VOList(PSort1VOList);
			}

			// *********分界點*********//
			List<Sort1VO> sort1VOList = sort1Svc.getAll();

			List<Sort2VO> sort2VOList = sort2Svc.getAll();

			List<ProductVO> forSelectList = pdSvc.getAll();
			// *********分界點*********//

			long totalSeconds2 = (System.currentTimeMillis());
			System.out.println(totalSeconds2 - totalSeconds1);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("listProducts_Byfind", list); // 資料庫取出的list物件,存入request
			// *********分界點*********//
			System.out.println(total);
			System.out.println(whichPage);
			req.setAttribute("total", total);
			req.setAttribute("currPage", whichPage);

			req.setAttribute("sort1VOList", sort1VOList);
			req.setAttribute("sort2VOList", sort2VOList);
			req.setAttribute("forSelectList", forSelectList);
			req.setAttribute("map", map);
			// *********分界點*********//
			
			// ***輸出clent端有輸入的參數 START***//
			req.setAttribute("userMsgs", userMsgs);
			req.setAttribute("userMsgsSort1", userMsgsSort1);
			// ***輸出clent端有輸入的參數 START***//
			// **********************轉交給後台的jsp***********************//

			RequestDispatcher successView = req.getRequestDispatcher("/back/shop/listAllProduct.jsp"); // 成功轉交shop.jsp
			successView.forward(req, res);
		}
	}
}
