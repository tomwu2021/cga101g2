package com.members.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.members.model.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/front/member.do")
public class MembersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		/*************************** 登入判斷 account password **********************/
		if ("forLogin".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 接收參數 **********************/
				String strAccount = req.getParameter("account");
				String strPassword = req.getParameter("passowrd");


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

				// 先判斷資料庫是否有此帳號
				MembersService memberSvc = new MembersService();
				MembersVO membersVO = memberSvc.selectForLogin(strAccount, strPassword);
				
				if (membersVO != null) {
					req.setAttribute("membersVO", membersVO); // 資料庫取出的 membersVO 物件，存入 req
					String url = "/front/listOneMember.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
					successView.forward(req, res);
				}else {
					Boolean boo = memberSvc.getOneByAccount(strAccount);
					if(boo == true) {
						errorMsgs.put("passowrd", "密碼輸入錯誤");
						errorMsgs.put("errorAccount", strAccount);
						RequestDispatcher failureView = req.getRequestDispatcher("/front/login.jsp");
						failureView.forward(req, res);
						return;// 程式中斷
					}else{
						errorMsgs.put("account", "帳號輸入錯誤");
						errorMsgs.put("errorAccount", strAccount);
						RequestDispatcher failureView = req.getRequestDispatcher("/front/login.jsp");
						failureView.forward(req, res);
						return;// 程式中斷
					}
				}


			} catch (Exception e)  {
				errorMsgs.put("無法取得資料", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front/login.jsp");
				failureView.forward(req, res);
			}

		}
		
		/*************************** 判斷此帳號是否存在 **********************/
		if ("checkAccount".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 接收參數 **********************/
				String strAccount = req.getParameter("account");

				if (strAccount == null || (strAccount.trim()).length() == 0) {
					errorMsgs.put("registerAccount", "請輸入會員帳號");
					errorMsgs.put("errorAccount", strAccount);
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front/login.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		
		
		/*************************** 註冊帳號 account password **********************/
//		至少八個字符，至少一個字母和一個數字：
//		"^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$"
		
//		if ("memberRegister".equals(action)) {
//			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				/*************************** 接收參數 **********************/
//				String strAccount = req.getParameter("account");
//
//		}
		
		

		/*************************** 取得一筆會員資料 **********************/
		if ("getOne_For_Display".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("memberId");
				if (str == null || (str.trim()).length() < 0) {
					errorMsgs.put("memberId", "請輸入會員編號");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front/member_select.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				Integer memberId = null;
				try {
					memberId = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.put("memberId", "會員編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front/member_select.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/***************************
				 * 2.開始查詢資料 呼叫 DAO
				 *****************************************/
				MembersService memberSvc = new MembersService();
				MembersVO membersVO = memberSvc.getOneById(memberId);// memberSvc.getOneByName(name)
				if (membersVO == null) {
					errorMsgs.put("memberId", "查無資料");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front/member_select.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("membersVO", membersVO); // 資料庫取出的membersVO物件,存入req
				String url = "/front/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.put("無法取得資料", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String account = req.getParameter("account");
//				String password = "^[(a-zA-Z0-9_)]{2,10}$";
				String password = req.getParameter("password");
				if (account == null || account.trim().length() == 0) {
					errorMsgs.put("account", "會員信箱: 請勿空白");
				}
//				else if (!account.trim().matches(password)) { // 以下練習正則(規)表示式(regular-expression)
//					errorMsgs.put("password", "會員密碼: 只能是中、英文字母, 且長度必需在2到10之間");
//				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front/addMember.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				MembersService membersSvc = new MembersService();
				MembersVO membersVO = new MembersVO();
				membersVO.setAccount(account);
				membersVO.setPassword(password);
				membersSvc.insert(membersVO);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front/addMember.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer memberId = Integer.valueOf(req.getParameter("memberId"));
				Integer status = Integer.valueOf(req.getParameter("status"));
				/*************************** 2.開始查詢資料 ****************************************/
				MembersService memberSvc = new MembersService();

				MembersVO memberVO = memberSvc.getOneById(memberId);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String param = "?memberId=" + memberVO.getMemberId() + "&account=" + memberVO.getAccount() + "&name="
						+ memberVO.getName() + "&address=" + memberVO.getAddress() + "&phone=" + memberVO.getPhone()
						+ "&rankId=" + memberVO.getRankId() + "&eWalletAmount=" + memberVO.geteWalletAmount()
						+ "&bonusAmount=" + memberVO.getBonusAmount() + "&status=" + memberVO.getStatus()
						+ "&createTime=" + memberVO.getCreateTime();

				String url = "/front/update_member_input.jsp" + param;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_member_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.put("無法取得要修改的資料", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer memberId = Integer.valueOf(req.getParameter("memberId").trim());
				String account = req.getParameter("account").trim();
				String name = req.getParameter("name");
				String address = req.getParameter("address");
				String phone = req.getParameter("phone");
				Integer rankId = Integer.valueOf(req.getParameter("rankId").trim());
				Integer eWalletAmount = Integer.valueOf(req.getParameter("eWalletAmount").trim());
				Integer bonusAmount = Integer.valueOf(req.getParameter("bonusAmount").trim());
				Integer status = Integer.valueOf(req.getParameter("status").trim());
				Timestamp createTime = Timestamp.valueOf(req.getParameter("createTime").trim());

				if (status == null) {
					errorMsgs.put("status", "狀態請勿空白");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front/update_member_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				MembersService memberSvc = new MembersService();
				MembersVO membersVO = new MembersVO();
				membersVO.setMemberId(memberId);
				membersVO.setStatus(status);
				memberSvc.changeStatus(membersVO);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				membersVO.setAccount(account);
				membersVO.setName(name);
				membersVO.setAddress(address);
				membersVO.setPhone(phone);
				membersVO.setRankId(rankId);
				membersVO.seteWalletAmount(eWalletAmount);
				membersVO.setBonusAmount(bonusAmount);
				membersVO.setCreateTime(createTime);
				req.setAttribute("membersVO", membersVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.put("修改資料失敗", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front/update_member_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer memberId = Integer.valueOf(req.getParameter("memberId"));

				/*************************** 2.開始刪除資料 ***************************************/
				MembersService memberSvc = new MembersService();
				memberSvc.deleteOneById(memberId);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front/listAllMember.jsp");

				failureView.forward(req, res);
			}
		}

	}

}
