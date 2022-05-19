package com.product.controller;

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
import com.product_img.model.ProductImgService;
import com.product_img.model.ProductImgVO;

@WebServlet("/shop/CheckProductName")
public class CheckProductNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("CheckProductNameServlet 執行成功");
		req.setCharacterEncoding("UTF-8");// client 端向 Servlet 請求的編碼
		// 訊息用 Map 存放
		Map<String, String> msg = new LinkedHashMap<String, String>();
		req.setAttribute("msgs", msg);

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

		String productName = req.getParameter("productName");
		if (productName == null || productName.trim().length() == 0) {
			msg.put("msg", "-1");
			String json = new Gson().toJson(msg);
			res.getWriter().write(json);
			return;
		} else {
			ProductService pdSvc = new ProductService();
			ProductVO productVO = pdSvc.checkProdcutName(productName);
			//0代表有重複,不可以送出
			//1代表沒重複,可以送出
			if (productVO.getProductName() == null) {
				msg.put("msg", "1");
				String json = new Gson().toJson(msg);
				res.getWriter().write(json);
			}else {
				msg.put("msg", "0");
				String json = new Gson().toJson(msg);
				res.getWriter().write(json);
			}
		}

	}

}
