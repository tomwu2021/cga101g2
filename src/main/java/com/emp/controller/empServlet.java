package com.emp.controller;

import java.io.*;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.album.model.AlbumJDBCDAO;
import com.chargeRecord.model.ChargeRecordService;
import com.chargeRecord.model.ChargeRecordVO;
import com.google.gson.Gson;
import com.members.model.*;
import com.util.JavaMail;
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
		case "forLogin":
			forLogin(req, res);
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
	public void forLogin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json; charset=UTF-8");
		// 訊息存在 Map
		Map<String, String> messages = new LinkedHashMap<String, String>();
		req.setAttribute("messages", messages);

//		// 接收參數
//		String loginAccount = req.getParameter("loginAccount");
//		String loginPassword = req.getParameter("loginPassword");
//		// 檢查輸入是否有效
//		if (loginAccount == null || (loginAccount.trim()).length() == 0) {
//			messages.put("messagesAccount", "請輸入會員帳號");
//		}
//		if (loginPassword == null || (loginPassword.trim()).length() == 0) {
//			messages.put("messagesPassword", "請輸入會員密碼");
//		}
//
//		if (!messages.isEmpty()) {
//			messages.put("originalAccount", loginAccount);
//			RequestDispatcher failureView = req.getRequestDispatcher("/front/login.jsp");
//			failureView.forward(req, res);
//			return;// 程式中斷
//		}
//		MembersService memberSvc = new MembersService();
//		// 資料庫是否有此筆資料
//		Boolean boo = memberSvc.getOneByAccount(loginAccount);
//
//		if (boo == true) {
//			MembersVO membersVO = memberSvc.selectForLogin(loginAccount, loginPassword);
//			if (membersVO != null) {
//				HttpSession session = req.getSession();
//				session.setAttribute("membersVO", membersVO);
//				req.setAttribute("membersVO", membersVO); // 資料庫取出的 membersVO 物件，存入 req
//				String url = "/front/member/member.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//				return;
//			} else if (membersVO == null) {
//				messages.put("messagesPassword", "請確認會員密碼");
//				messages.put("originalAccount", loginAccount);
//				RequestDispatcher failureView = req.getRequestDispatcher("/front/login.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//
//		} else {
//			messages.put("originalAccount", loginAccount);
//			messages.put("messagesAccount", "請確認會員帳號");
//			messages.put("messagesPassword", "請確認會員密碼");
//			RequestDispatcher failureView = req.getRequestDispatcher("/front/login.jsp");
//			failureView.forward(req, res);
//			return;
//		}

	}

}


