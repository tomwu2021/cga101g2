package com.members.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;
import java.util.regex.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.Gson;
import com.members.model.*;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

@WebServlet("/front/member.do")
public class MembersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// client 端向 Servlet 請求的編碼
		req.setCharacterEncoding("UTF-8");

		// getParameter 取得值
		String action = req.getParameter("action");

		// 錯誤訊息用 Map 存放
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		req.setAttribute("errorMsgs", errorMsgs);

		// 呼叫 service
		MembersService memberSvc = new MembersService();

		// 宣告一個布林值
		Boolean boo;

		// response，設定回應的格式及編碼
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");

		/*************************** 註冊帳號 account password **********************/

		
		
		/*************************** 判斷帳號是否存在 **********************/
		String accountRegister = req.getParameter("accountRegister");
		// 判斷 accountRegister 是否有值，有值再做判斷
		if (accountRegister == null || accountRegister.trim().length() == 0) {
			errorMsgs.put("exist", "請輸入正確帳號");
			String json = new Gson().toJson(errorMsgs);
			res.getWriter().write(json);
		} else {

			boo = memberSvc.getOneByAccount(accountRegister);

			if (boo == true) {
				errorMsgs.put("exist", "此帳號已註冊");
				String json = new Gson().toJson(errorMsgs);
				res.getWriter().write(json);
			} else {
				String checkEmail = "([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}";
				if (accountRegister.matches(checkEmail)) {

					// 用 session 存一組 驗證碼
//					String verificationCode = memberSvc.genAuthCode();
//					HttpSession sessionVC = req.getSession();
//					String svc = (String) sessionVC.getAttribute("checkCodeSession");

					// 將 驗證碼 傳送至 Email
//					javaMail(verificationCode);

					errorMsgs.put("exist", "格式正確，寄送 Email 至此帳號");
					String json = new Gson().toJson(errorMsgs);
					res.getWriter().write(json);

				} else {
					errorMsgs.put("exist", "此帳號格式錯誤");
					String json = new Gson().toJson(errorMsgs);
					res.getWriter().write(json);
				}
			}
		}

		/*************************** 登入判斷 account password **********************/
		if ("forLogin".equals(action)) {

			try {
				/*************************** 接收參數 **********************/
				String strAccount = req.getParameter("account");
				String strPassword = req.getParameter("passowrd");

				errorMsgs.clear();

				/*************************** 檢查輸入是否有效 **********************/
				if (strAccount == null || (strAccount.trim()).length() == 0) {
					errorMsgs.put("account", "請輸入會員帳號");
				}
				if (strPassword == null || (strPassword.trim()).length() == 0) {
					errorMsgs.put("passowrd", "請輸入會員密碼");
				}
				if (!errorMsgs.isEmpty()) {
					errorMsgs.put("errorAccount", strAccount);
					RequestDispatcher failureView = req.getRequestDispatcher("/front/login.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 資料庫是否有此筆資料 **********************/

				if (strAccount != null && strPassword != null) {

					boo = memberSvc.getOneByAccount(strAccount);
					if (boo == true) {
						MembersVO membersVO = memberSvc.selectForLogin(strAccount, strPassword);
						if(membersVO != null) {
							HttpSession session = req.getSession();
							session.setAttribute("membersVO", membersVO);
							req.setAttribute("membersVO", membersVO); // 資料庫取出的 membersVO 物件，存入 req
							String url = "/front/member/member.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url);
							successView.forward(req, res);
						}else if ( membersVO == null ){
							errorMsgs.put("errorAccount", strAccount);
							errorMsgs.put("passowrd", "請確認會員密碼");
							RequestDispatcher failureView = req.getRequestDispatcher("/front/login.jsp");
							failureView.forward(req, res);
						}

					} else {
						errorMsgs.put("errorAccount", strAccount);
						errorMsgs.put("account", "請確認會員帳號");
						errorMsgs.put("passowrd", "請確認會員密碼");
						RequestDispatcher failureView = req.getRequestDispatcher("/front/login.jsp");
						failureView.forward(req, res);
					}

				}

			} catch (Exception e) {
				errorMsgs.put("無法取得資料", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front/login.jsp");
				failureView.forward(req, res);
			}

		}

//		/*************************** 取得一筆會員資料 **********************/
//		if ("getOne_For_Display".equals(action)) {
//
//			try {
//				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//				String str = req.getParameter("memberId");
//				if (str == null || (str.trim()).length() < 0) {
//					errorMsgs.put("memberId", "請輸入會員編號");
//				}
//
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/front/member_select.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}
//				Integer memberId = null;
//				try {
//					memberId = Integer.valueOf(str);
//				} catch (Exception e) {
//					errorMsgs.put("memberId", "會員編號格式不正確");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/front/member_select.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}
//				/***************************
//				 * 2.開始查詢資料 呼叫 DAO
//				 *****************************************/
//				MembersVO membersVO = memberSvc.getOneById(memberId);// memberSvc.getOneByName(name)
//				if (membersVO == null) {
//					errorMsgs.put("memberId", "查無資料");
//				}
//
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/front/member_select.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}
//				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//				req.setAttribute("membersVO", membersVO); // 資料庫取出的membersVO物件,存入req
//				String url = "/front/listOneMember.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.put("無法取得資料", e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}

//		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
//
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//				String account = req.getParameter("account");
////				String password = "^[(a-zA-Z0-9_)]{2,10}$";
//				String password = req.getParameter("password");
//				if (account == null || account.trim().length() == 0) {
//					errorMsgs.put("account", "會員信箱: 請勿空白");
//				}
////				else if (!account.trim().matches(password)) { // 以下練習正則(規)表示式(regular-expression)
////					errorMsgs.put("password", "會員密碼: 只能是中、英文字母, 且長度必需在2到10之間");
////				}
//
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/front/addMember.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//
//				/*************************** 2.開始新增資料 ***************************************/
//				MembersService membersSvc = new MembersService();
//				MembersVO membersVO = new MembersVO();
//				membersVO.setAccount(account);
//				membersVO.setPassword(password);
//				membersSvc.insert(membersVO);
//
//				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//				String url = "/front/listAllMember.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.put("Exception", e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front/addMember.jsp");
//				failureView.forward(req, res);
//			}
//		}

//		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
//
//			try {
//				/*************************** 1.接收請求參數 ****************************************/
//				Integer memberId = Integer.valueOf(req.getParameter("memberId"));
//				Integer status = Integer.valueOf(req.getParameter("status"));
//				/*************************** 2.開始查詢資料 ****************************************/
//
//				MembersVO memberVO = memberSvc.getOneById(memberId);
//
//				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//				String param = "?memberId=" + memberVO.getMemberId() + "&account=" + memberVO.getAccount() + "&name="
//						+ memberVO.getName() + "&address=" + memberVO.getAddress() + "&phone=" + memberVO.getPhone()
//						+ "&rankId=" + memberVO.getRankId() + "&eWalletAmount=" + memberVO.geteWalletAmount()
//						+ "&bonusAmount=" + memberVO.getBonusAmount() + "&status=" + memberVO.getStatus()
//						+ "&createTime=" + memberVO.getCreateTime();
//
//				String url = "/front/update_member_input.jsp" + param;
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_member_input.jsp
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.put("無法取得要修改的資料", e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front/listAllMember.jsp");
//				failureView.forward(req, res);
//			}
//		}

//		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//
//			try {
//				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//				Integer memberId = Integer.valueOf(req.getParameter("memberId").trim());
//				String account = req.getParameter("account").trim();
//				String name = req.getParameter("name");
//				String address = req.getParameter("address");
//				String phone = req.getParameter("phone");
//				Integer rankId = Integer.valueOf(req.getParameter("rankId").trim());
//				Integer eWalletAmount = Integer.valueOf(req.getParameter("eWalletAmount").trim());
//				Integer bonusAmount = Integer.valueOf(req.getParameter("bonusAmount").trim());
//				Integer status = Integer.valueOf(req.getParameter("status").trim());
//				Timestamp createTime = Timestamp.valueOf(req.getParameter("createTime").trim());
//
//				if (status == null) {
//					errorMsgs.put("status", "狀態請勿空白");
//				}
//
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/front/update_member_input.jsp");
//					failureView.forward(req, res);
//					return; // 程式中斷
//				}
//
//				/*************************** 2.開始修改資料 *****************************************/
////				MembersService memberSvc = new MembersService();
//				MembersVO membersVO = new MembersVO();
//				membersVO.setMemberId(memberId);
//				membersVO.setStatus(status);
//				memberSvc.changeStatus(membersVO);
//
//				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//				membersVO.setAccount(account);
//				membersVO.setName(name);
//				membersVO.setAddress(address);
//				membersVO.setPhone(phone);
//				membersVO.setRankId(rankId);
//				membersVO.seteWalletAmount(eWalletAmount);
//				membersVO.setBonusAmount(bonusAmount);
//				membersVO.setCreateTime(createTime);
//				req.setAttribute("membersVO", membersVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/front/listOneMember.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.put("修改資料失敗", e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front/update_member_input.jsp");
//				failureView.forward(req, res);
//			}
//		}


	}

}
