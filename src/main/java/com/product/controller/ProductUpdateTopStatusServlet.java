package com.product.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.product.model.ProductService;
import com.product.model.ProductVO;

@WebServlet("/back/shop/productUpdateTopStatusServlet")
public class ProductUpdateTopStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// client 端向 Servlet 請求的編碼
		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");

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

		Integer product_id = null;
		Integer top_status = null;
		try {
			product_id = Integer.valueOf(req.getParameter("product_id").trim());
			top_status = Integer.valueOf(req.getParameter("top_status").trim());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

// 錯誤訊息用 Map 存放
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		req.setAttribute("errorMsgs", errorMsgs);

//1.呼叫ProductService	
		ProductService pdSvc = new ProductService();

//推薦到未推薦的判斷	
		if (top_status == 0) {
			System.out.println("進入推薦到未推薦的判斷");
			boolean boo;
			boo = pdSvc.deleteByTopStatus(top_status,product_id);
			if (boo == true) {
				errorMsgs.put("msg", "1");
				String json = new Gson().toJson(errorMsgs);
				res.getWriter().write(json);
				System.out.println("推薦到未推薦成功");
				return;
			} else {
				errorMsgs.put("msg", "-1");
				String json = new Gson().toJson(errorMsgs);
				res.getWriter().write(json);
				return;
			}
		}
		
//未推薦到推薦的判斷		
		if (top_status == 1) {
			System.out.println("進入未推薦到推薦的判斷");
			// 2-1.確認此商品是否有上架 呼叫用來給前台的JNDI 如果沒有的話會回傳空productVO

			Map<String, String[]> map = new TreeMap<String, String[]>();
//			map.put("top_status", new String[] { String.valueOf(top_status) }); //錯誤參數
			map.put("product_id", new String[] { String.valueOf(product_id) });
			List<ProductVO> thisPdlist = pdSvc.getForShopFront(map,9999,0);

			// 2-2.計算現在已上架且已推薦的商品數量
			Integer productHaveTopStatus = null;
			Map<String, String[]> map1 = new TreeMap<String, String[]>();
			map1.put("top_status", new String[] { "1" });
			List<ProductVO> list2 = pdSvc.getForShopFront(map1,9999,0);
			productHaveTopStatus = list2.size();

			if (productHaveTopStatus >= 6) {
				errorMsgs.put("msg", "-2");
				String json = new Gson().toJson(errorMsgs);
				res.getWriter().write(json);
				System.out.println("已有"+productHaveTopStatus+"筆推薦商品");
				return;
			}
			//判斷該陣列是否為空值
			boolean empty = thisPdlist.isEmpty();
			if (empty) {
				errorMsgs.put("msg", "-3");
				String json = new Gson().toJson(errorMsgs);
				res.getWriter().write(json);
				System.out.println("該商品未上市");
				return;
			} else {
				boolean boo;
				boo = pdSvc.deleteByTopStatus(top_status,product_id);
				if (boo == true) {
					errorMsgs.put("msg", "1");
					String json = new Gson().toJson(errorMsgs);
					res.getWriter().write(json);
					System.out.println("未推薦到推薦成功");
				} else {
					errorMsgs.put("msg", "-1");
					String json = new Gson().toJson(errorMsgs);
					res.getWriter().write(json);
				}
			}
		}
	}

}
