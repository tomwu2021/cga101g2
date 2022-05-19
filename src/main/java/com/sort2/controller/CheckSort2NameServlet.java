package com.sort2.controller;

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
import com.sort2.model.Sort2Service;
import com.sort2.model.Sort2VO;

/**
 * Servlet implementation class getOneSort2VOandSort1VOs
 */
@WebServlet("/back/shop/checkSort2Name")
public class CheckSort2NameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("CheckSort2NameServlet 執行成功");
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

		String sort2Name = req.getParameter("sort2Name");

		Sort2Service sort2Svc = new Sort2Service();
		Sort2VO sort2VO = sort2Svc.selectBySort2Name(sort2Name);
		// 0代表沒重複 1代表有重複
		if (sort2VO.getSort2Name() == null) {
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
