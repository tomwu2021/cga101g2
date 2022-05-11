package com.emp.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

@WebServlet("/front/emp.do")
public class empServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");// client 端向 Servlet 請求的編碼
		res.setCharacterEncoding("UTF-8");// response，設定回應的格式及編碼

		// 判斷呼叫哪個方法
		String action = req.getParameter("action");
		System.out.println(action);

		action = action == null ? "" : action;

		switch (action) {
		case "empForLogin":
			empForLogin(req, res);
			break;
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");

		try {
			doPost(req, res);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*************************** 登入判斷 account password **********************/
	public void empForLogin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json; charset=UTF-8");
		// 訊息存在 Map
		Map<String, String> messages = new LinkedHashMap<String, String>();
		req.setAttribute("messages", messages);

		// 接收參數
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		EmpService empSvc = new EmpService();
		// 資料庫是否有此筆資料
		EmpVO empVO = empSvc.getOneByAccount(username);
		
		if(empVO == null) {
			System.out.println("帳號錯誤");
			messages.put("errorAccount", "請確認帳號");
			messages.put("originalAccount", username);
			RequestDispatcher failureView = req.getRequestDispatcher("/back/emp/empLogin.jsp");
			failureView.forward(req, res);
			return;
		}else {
			System.out.println("判斷密碼是否正確");
			if(!password.equals(empVO.getPassword())) {//"密碼錯誤"
				System.out.println(empVO.getPassword());
				System.out.println("密碼錯誤");
				messages.put("errorPassword", "請確認密碼");
				messages.put("originalAccount", username);
				RequestDispatcher failureView = req.getRequestDispatcher("/back/emp/empLogin.jsp");
				failureView.forward(req, res);
				return;
			}else {
				HttpSession session = req.getSession();
				session.setAttribute("empVO", empVO);
				req.setAttribute("empVO", empVO); // 資料庫取出的 empVO 物件，存入 req
				System.out.println("跳轉到後台畫面");
				RequestDispatcher successView = req.getRequestDispatcher("/article?action=all_Article");
				successView.forward(req, res);
				return;
			}
		}

	}

}


