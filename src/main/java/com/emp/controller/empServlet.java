package com.emp.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.chargeRecord.model.ChargeRecordService;
import com.chargeRecord.model.ChargeRecordVO;
import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.google.gson.Gson;
import com.members.model.MembersService;
import com.members.model.MembersVO;

import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

@WebServlet("/back/emp.do")
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
		case "empSelect":
			empSelect(req, res);
			break;
		case "empChangeStatus":
			empChangeStatus(req, res);
			break;
		case "accountSelect":
			accountSelect(req, res);
			break;
		case "nameSelect":
			nameSelect(req, res);
			break;
		case "empSelectMemberRecord":
			empSelectMemberRecord(req, res);
			break;
		case "getNameByMemberId":
			getNameByMemberId(req, res);
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

		if (empVO == null) {
			messages.put("errorAccount", "請確認帳號");
			messages.put("originalAccount", username);
			RequestDispatcher failureView = req.getRequestDispatcher("/back/empLogin.jsp");
			failureView.forward(req, res);
			return;
		} else {
			if (!password.equals(empVO.getPassword())) {// "密碼錯誤"
				messages.put("errorPassword", "請確認密碼");
				messages.put("originalAccount", username);
				RequestDispatcher failureView = req.getRequestDispatcher("/back/empLogin.jsp");
				failureView.forward(req, res);
				return;
			} else {
				HttpSession session = req.getSession();
				session.setAttribute("empVO", empVO);
				req.setAttribute("empVO", empVO); // 資料庫取出的 empVO 物件，存入 req
				if (session.getAttribute("locationEmp") != null) {
					String url = session.getAttribute("locationEmp").toString(); //http://localhost:8081/CGA101G2/back/customer/detail.jsp
					res.sendRedirect(url);
					return;
				} else {
					RequestDispatcher successView = req.getRequestDispatcher("/back/empSelect.jsp");
					successView.forward(req, res);
					return;
				}
			}
		}

	}

	public void empSelect(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// 查詢 會員 資料
		MembersService memberSVC = new MembersService();
		List<MembersVO> listMemberVO = memberSVC.getAll();
		String json = new Gson().toJson(listMemberVO);
		res.getWriter().write(json);
		return;
	}

	// 修改會員狀態
	public void empChangeStatus(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String newStatus = req.getParameter("newStatus");
		String memberId = req.getParameter("memberId");

		// 呼叫 修改 會員狀態 service
		MembersService memberSvc = new MembersService();
		MembersVO membersVO = new MembersVO();
		membersVO.setMemberId(Integer.parseInt(memberId));
		membersVO.setStatus(Integer.parseInt(newStatus));
		memberSvc.changeStatus(membersVO);

		// 查詢 會員 資料
		MembersService memberSVC = new MembersService();
		List<MembersVO> listMemberVO = memberSVC.getAll();
		String json = new Gson().toJson(listMemberVO);
		res.getWriter().write(json);
		return;

	}

	public void accountSelect(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String memberAccount = req.getParameter("memberAccount");

		// 用帳號模糊查詢
		MembersService memberSvc = new MembersService();
		List<MembersVO> listMemberVO = memberSvc.SelectAllByAccount(memberAccount);
		String json = new Gson().toJson(listMemberVO);
		res.getWriter().write(json);
		return;
	}

	public void nameSelect(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String memberName = req.getParameter("memberName");

		// 用帳號模糊查詢
		MembersService memberSvc = new MembersService();
		List<MembersVO> listMemberVO = memberSvc.SelectAllByName(memberName);
		String json = new Gson().toJson(listMemberVO);
		res.getWriter().write(json);
		return;
	}

	// 員工查詢會員錢包紀錄 
	public void empSelectMemberRecord(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String memberAccount = req.getParameter("memberAccount");
		MembersService memberSvc = new MembersService();
		MembersVO membersVO = memberSvc.selectMemberByAccount(memberAccount);

		// 用 信箱查詢 memberId
		ChargeRecordService chargeRecordSvc = new ChargeRecordService();
		List<ChargeRecordVO> listAll = chargeRecordSvc.getAll(membersVO.getMemberId());
		String json = new Gson().toJson(listAll);
		res.getWriter().write(json);
		return;

	}

	public void getNameByMemberId(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String memberId = req.getParameter("memberId");
		MembersService memberSvc = new MembersService();
		String memberName = memberSvc.getOneById(Integer.parseInt(memberId)).getName();
		Map<String, String> messages = new LinkedHashMap<String, String>();
		req.setAttribute("messages", messages);

		messages.put("memberName", memberName);
		String json = new Gson().toJson(messages);
		res.getWriter().write(json);
		return;
	}
}
