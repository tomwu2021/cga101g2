package com.prodouct.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.picture.service.PictureService;
import com.product.model.ProductService;
import com.product.model.ProductVO;

@WebServlet("/back/shop/productUpdateStatusServlet")
public class ProductUpdateStatusServlet extends HttpServlet {
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

		Integer status = null;
		Integer productId = null;
		try {
			productId = Integer.valueOf(req.getParameter("productId").trim());
			status = Integer.valueOf(req.getParameter("status").trim());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

// 錯誤訊息用 Map 存放
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		req.setAttribute("errorMsgs", errorMsgs);

//1.呼叫ProductService	
//判斷資料庫資料是否等同現在的狀態,如果是回傳0,並且中斷程式,如果不是繼續執行
		ProductService productService = new ProductService();
		ProductVO oldProductVO = new ProductVO();
		oldProductVO = productService.getOneProductByid(productId);
		
		if (status == oldProductVO.getStatus()) {
			errorMsgs.put("msg", "0");
			String json = new Gson().toJson(errorMsgs);
			res.getWriter().write(json);
			return;
		} else {
			ProductVO newproductVO = new ProductVO();
			newproductVO.setProductId(productId);
			newproductVO.setStatus(status);
			boolean boo;
			boo = productService.productUpdateStatus(newproductVO);
			if (boo == true) {
				errorMsgs.put("msg", "1");
				String json = new Gson().toJson(errorMsgs);
				res.getWriter().write(json);
			} else {
				errorMsgs.put("msg", "-1");
				String json = new Gson().toJson(errorMsgs);
				res.getWriter().write(json);
			}
		}
	}

}
