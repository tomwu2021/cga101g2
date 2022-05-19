package com.sort1.controller;

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
import com.sort1.model.Sort1Service;
import com.sort1.model.Sort1VO;

/**
 * Servlet implementation class getOneSort2VOandSort1VOs
 */
@WebServlet("/back/shop/checkSort1Name")
public class CheckSort1NameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("CheckSort1NameServlet 執行成功");
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

		String sort1Name = req.getParameter("sort1Name");

		Sort1Service sort1Svc = new Sort1Service();
		Sort1VO sort1VO = sort1Svc.selectBySort1Name(sort1Name);
		// 0代表沒重複 1代表有重複
		if (sort1VO.getSort1Name() == null) {
			msg.put("msg", "1");
			String json = new Gson().toJson(msg);
			res.getWriter().write(json);
		} else {
			msg.put("msg", "0");
			String json = new Gson().toJson(msg);
			res.getWriter().write(json);
		}

	}
}
