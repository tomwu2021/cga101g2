package com.members.controller;

import java.io.*;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.album.model.AlbumJDBCDAO;
import com.google.gson.Gson;
import com.members.model.*;
import com.util.JavaMail;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

@WebServlet("/front/member.do")
public class MembersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");// client 端向 Servlet 請求的編碼
		res.setCharacterEncoding("UTF-8");// response，設定回應的格式及編碼

		// 判斷呼叫哪個方法
		String action = req.getParameter("action");
//		System.out.println(action);

		action = action == null ? "" : action;

		switch (action) {
		case "forLogin":
			forLogin(req, res);
			break;
		case "checkAccount":
			checkAccount(req, res);
			break;
		case "registerVerification":
			registerVerification(req, res);
			break;
		case "sendforgotMail":
			sendforgotMail(req, res);
			break;
		case "getRankName":
			getRankName(req, res);
			break;
		case "updateMemberInfo":
			updateMemberInfo(req, res);
			break;
		case "updateMemberPassword":
			updateMemberPassword(req, res);
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

		// 接收參數
		String loginAccount = req.getParameter("loginAccount");
		String loginPassword = req.getParameter("loginPassword");
		// 檢查輸入是否有效
		if (loginAccount == null || (loginAccount.trim()).length() == 0) {
			messages.put("messagesAccount", "請輸入會員帳號");
		}
		if (loginPassword == null || (loginPassword.trim()).length() == 0) {
			messages.put("messagesPassword", "請輸入會員密碼");
		}

		if (!messages.isEmpty()) {
			messages.put("originalAccount", loginAccount);
			RequestDispatcher failureView = req.getRequestDispatcher("/front/login.jsp");
			failureView.forward(req, res);
			return;// 程式中斷
		}
		MembersService memberSvc = new MembersService();
		// 資料庫是否有此筆資料
		Boolean boo = memberSvc.getOneByAccount(loginAccount);

		if (boo == true) {
			MembersVO membersVO = memberSvc.selectForLogin(loginAccount, loginPassword);
			if (membersVO != null) {
				HttpSession session = req.getSession();
				session.setAttribute("membersVO", membersVO);
				req.setAttribute("membersVO", membersVO); // 資料庫取出的 membersVO 物件，存入 req
				String url = "/front/member/member.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			} else if (membersVO == null) {
				messages.put("messagesPassword", "請確認會員密碼");
				messages.put("originalAccount", loginAccount);
				RequestDispatcher failureView = req.getRequestDispatcher("/front/login.jsp");
				failureView.forward(req, res);
				return;
			}

		} else {
			messages.put("originalAccount", loginAccount);
			messages.put("messagesAccount", "請確認會員帳號");
			messages.put("messagesPassword", "請確認會員密碼");
			RequestDispatcher failureView = req.getRequestDispatcher("/front/login.jsp");
			failureView.forward(req, res);
			return;
		}

	}

	/*************************** 判斷帳號是否存在 **********************/
	public void checkAccount(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String registerAccount = req.getParameter("registerAccount");

		Map<String, String> messages = new LinkedHashMap<String, String>();
		req.setAttribute("messages", messages);

		if (registerAccount == null || registerAccount.trim().length() == 0) {

			messages.put("exist", "請輸入電子郵件");
			String json = new Gson().toJson(messages);
			res.getWriter().write(json);
			return;
		}
		MembersService memberSvc = new MembersService();
		Boolean boo = memberSvc.getOneByAccount(registerAccount);

		// True 此帳號已存在
		if (boo == true) {
			messages.put("exist", "此帳號已註冊");
			String json = new Gson().toJson(messages);
			res.getWriter().write(json);
			return;
		} else { // False，帳號不存在，寄送 JavaMail
//			String checkEmail = "([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}";
			String checkEmail = "([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@gmail.com";
			if (registerAccount.matches(checkEmail)) {

				// 產生驗證碼存在 session
				String verificationCode = memberSvc.genAuthCode();
				HttpSession sessionVC = req.getSession();
				sessionVC.setAttribute("authCode", verificationCode);
				sessionVC.setAttribute("registerAccount", registerAccount);

				// 寄送 JavaMail
				JavaMail javaMail = new JavaMail();
				javaMail.setRecipient(registerAccount); // 收件人信箱
				javaMail.setTxt("歡迎您加入 Pclub 會員，請於收到此信件半小時內，完成會員驗證，感謝您的註冊！<br>驗證碼：" + verificationCode); // 內文
				javaMail.SendMail(); // 送出

				messages.put("exist", "已寄送驗證碼至此信箱");
				String json = new Gson().toJson(messages);
				res.getWriter().write(json);
				return;
			} else {
				messages.put("exist", "此帳號格式錯誤！");
				String json = new Gson().toJson(messages);
				res.getWriter().write(json);
				return;
			}
		}
	}

	/*************************** 帳號註冊 **********************/
	public void registerVerification(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		HttpSession sessionVC = req.getSession();
		String sessionAccount = (String) sessionVC.getAttribute("registerAccount"); // 取得寄送的信箱
		String sessionAuthCode = (String) sessionVC.getAttribute("authCode"); // 取得寄送的驗證碼

		String userAccount = req.getParameter("registerAccount");
		String userCheckPassword = req.getParameter("registercheckpasswordr");
		String userVerificationCode = req.getParameter("verificationCode");

		Map<String, String> messages = new LinkedHashMap<String, String>();
		req.setAttribute("messages", messages);

		if (!userAccount.equals(sessionAccount)) {
			messages.put("msgError", "與前次輸入不相符！");
			String json = new Gson().toJson(messages);
			res.getWriter().write(json);
			return;
		}
		if (!userVerificationCode.equals(sessionAuthCode)) {
			messages.put("msgError", "");
			messages.put("msgErrorVerificationCode", "驗證碼輸入錯誤！");
			String json = new Gson().toJson(messages);
			res.getWriter().write(json);
			return;
		}

		MembersService memberSvc = new MembersService();
		MembersVO membersVO = new MembersVO();
		membersVO.setAccount(userAccount);
		membersVO.setPassword(userCheckPassword);
		MembersVO newMember = memberSvc.insert(membersVO);
		messages.put("msgError", "");
		messages.put("msgErrorVerificationCode", "");
		messages.put("registerSuccessful", "註冊成功");

		// 相簿建立
		AlbumJDBCDAO aldao = new AlbumJDBCDAO();
		try {
			aldao.makeDefaultAlbum(newMember.getMemberId());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String json = new Gson().toJson(messages);
		res.getWriter().write(json);
		return;
	}

	/*************************** 忘記密碼 **********************/
	public void sendforgotMail(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String forgotPassword = req.getParameter("forgotPassword");

		Map<String, String> messages = new LinkedHashMap<String, String>();
		req.setAttribute("messages", messages);

//		System.out.println(forgotPassword);
		MembersService memberSvc = new MembersService();
		Boolean boo = memberSvc.getOneByAccount(forgotPassword);

		if (boo == true) {

			// 用 account 取得 會員 Info
			MembersVO newMemberVO = memberSvc.selectMemberIdByAccount(forgotPassword);
			// 呼叫 DAO 修改資料庫密碼
			String verificationCode = memberSvc.forgotPassword(newMemberVO);

			// 寄送 JavaMail
			JavaMail javaMail = new JavaMail();
			javaMail.setRecipient(forgotPassword); // 收件人信箱
			javaMail.setTxt("親愛的會員您好，您的新密碼為：" + verificationCode
					+ "<br>請以此密碼重新登入 Pclub，登入後請至會員中心修改密碼。<br>本郵件由 Pclub 系統自動發出，請勿回覆！"); // 內文
			javaMail.SendMail(); // 送出

			messages.put("msgError", "已寄送新密碼至此信箱");
			String json = new Gson().toJson(messages);
			res.getWriter().write(json);
			return;
		} else {
			messages.put("msgError", "查無此會員");
			String json = new Gson().toJson(messages);
			res.getWriter().write(json);
			return;
		}
	}

	/*************************** 顯示會員等級 **********************/
	public void getRankName(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String rankId = req.getParameter("rankId");
//		System.out.println(rankId);

		Map<String, String> messages = new LinkedHashMap<String, String>();
		req.setAttribute("messages", messages);

		if ("1".equals(rankId)) {
			messages.put("msgRankName", "一般會員");
			messages.put("msgDiscount", "");
			String json = new Gson().toJson(messages);
			res.getWriter().write(json);
			return;
		}
		if ("2".equals(rankId)) {
			messages.put("msgRankName", "黃金會員");
			messages.put("msgDiscount", " ( 購物折扣 95 折 )");
			String json = new Gson().toJson(messages);
			res.getWriter().write(json);
			return;
		}
		if ("3".equals(rankId)) {
			messages.put("msgRankName", "白金會員");
			messages.put("msgDiscount", " ( 購物折扣 9 折 )");
			String json = new Gson().toJson(messages);
			res.getWriter().write(json);
			return;
		}
		if ("4".equals(rankId)) {
			messages.put("msgRankName", "鑽石會員");
			messages.put("msgDiscount", " ( 購物折扣 85 折 )");
			String json = new Gson().toJson(messages);
			res.getWriter().write(json);
			return;
		}
	}

	/*************************** 會員修改基本資料 **********************/
	public void updateMemberInfo(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		Map<String, String> messages = new LinkedHashMap<String, String>();
		req.setAttribute("messages", messages);

		String name = req.getParameter("name");
		if (name == null || name.trim().length() == 0) {
			messages.put("errorName", "*名字不可為空");
		}

		// 手機驗證
		String phone = req.getParameter("phone");
		String regex = "^09[0-9]{8}$";

		if (phone == null || phone.trim().length() == 0) {
		} else {
			if (!phone.trim().matches(regex)) {
				messages.put("errorPhone", "*手機格式錯誤");
			}
		}

		String address = req.getParameter("address");

//		System.out.println(messages);

		if (!messages.isEmpty()) {
			RequestDispatcher failureView = req.getRequestDispatcher("/front/member/memberUpdate.jsp");
			failureView.forward(req, res);
			return;// 程式中斷
		} else {
			HttpSession currentSession = req.getSession();
			MembersVO sessionMembersVO = (MembersVO) currentSession.getAttribute("membersVO");
			MembersService memberSvc = new MembersService();
			sessionMembersVO.setName(name); // Name
			sessionMembersVO.setPhone(phone); // Phone
			sessionMembersVO.setAddress(address); // Address
			memberSvc.update(sessionMembersVO);

			RequestDispatcher successView = req.getRequestDispatcher("/front/member/member.jsp");
			successView.forward(req, res);
			return;// 程式中斷
		}

	}

	/*************************** 會員修改密碼 **********************/
	public void updateMemberPassword(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		Map<String, String> messages = new LinkedHashMap<String, String>();
		req.setAttribute("messages", messages);

		HttpSession currentSession = req.getSession();
		MembersVO sessionMembersVO = (MembersVO) currentSession.getAttribute("membersVO");
		String currentPassword = sessionMembersVO.getPassword();

		String oldPhone = req.getParameter("oldPhone");
		if (oldPhone == null || oldPhone.trim().length() == 0) {
			messages.put("errorOldPhone", "*密碼不可為空");
		} else {
			if (!currentPassword.equals(oldPhone)) {
				messages.put("errorOldPhone", "*密碼不符合");
			}
		}

		String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
		String nwePhone = req.getParameter("newPhone");

		if (nwePhone == null || nwePhone.trim().length() == 0) {
			messages.put("errorNewPhone", "*密碼不可為空");
		} else {
			if (!nwePhone.trim().matches(regex)) {
				messages.put("errorNewPhone", "*手機格式錯誤");
			}
		}

		String checkNewPhone = req.getParameter("checkNewPhone");
		if (checkNewPhone == null || checkNewPhone.trim().length() == 0) {
			messages.put("errorCheckNewPhone", "*密碼不可為空");
		} else {
			if (!checkNewPhone.equals(nwePhone)) {
				messages.put("errorCheckNewPhone", "*密碼與前次輸入不相符");
			}
		}
		if (!messages.isEmpty()) {
			messages.put("userInput1", oldPhone);
			messages.put("userInput2", nwePhone);

			RequestDispatcher failureView = req.getRequestDispatcher("/front/member/memberPassword.jsp");
			failureView.forward(req, res);
			return;// 程式中斷
		} else {

			// 輸入正確後，呼叫 DAO 修改資料庫密碼 nwePhone
			MembersService memberSvc = new MembersService();
			MembersVO newMemberVO = new MembersVO();
			newMemberVO.setMemberId(sessionMembersVO.getMemberId());
			newMemberVO.setPassword(nwePhone);
			memberSvc.update(newMemberVO);

			messages.put("updatePasswordSuccess", "修改密碼成功！");
			// successful 的頁面
			RequestDispatcher successView = req.getRequestDispatcher("/front/member/memberPassword.jsp");
			successView.forward(req, res);
			return;// 程式中斷
		}

	}

}

///*************************** 取得一筆會員資料 **********************/
//if ("getOne_For_Display".equals(action)) {
//
//	try {
//		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//		String str = req.getParameter("memberId");
//		if (str == null || (str.trim()).length() < 0) {
//			errorMsgs.put("memberId", "請輸入會員編號");
//		}
//
//		if (!errorMsgs.isEmpty()) {
//			RequestDispatcher failureView = req.getRequestDispatcher("/front/member_select.jsp");
//			failureView.forward(req, res);
//			return;// 程式中斷
//		}
//		Integer memberId = null;
//		try {
//			memberId = Integer.valueOf(str);
//		} catch (Exception e) {
//			errorMsgs.put("memberId", "會員編號格式不正確");
//		}
//		if (!errorMsgs.isEmpty()) {
//			RequestDispatcher failureView = req.getRequestDispatcher("/front/member_select.jsp");
//			failureView.forward(req, res);
//			return;// 程式中斷
//		}
//		/***************************
//		 * 2.開始查詢資料 呼叫 DAO
//		 *****************************************/
//		MembersVO membersVO = memberSvc.getOneById(memberId);// memberSvc.getOneByName(name)
//		if (membersVO == null) {
//			errorMsgs.put("memberId", "查無資料");
//		}
//
//		if (!errorMsgs.isEmpty()) {
//			RequestDispatcher failureView = req.getRequestDispatcher("/front/member_select.jsp");
//			failureView.forward(req, res);
//			return;// 程式中斷
//		}
//		/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//		req.setAttribute("membersVO", membersVO); // 資料庫取出的membersVO物件,存入req
//		String url = "/front/listOneMember.jsp";
//		RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//		successView.forward(req, res);
//
//		/*************************** 其他可能的錯誤處理 *************************************/
//	} catch (Exception e) {
//		errorMsgs.put("無法取得資料", e.getMessage());
//		RequestDispatcher failureView = req.getRequestDispatcher("/front/select_page.jsp");
//		failureView.forward(req, res);
//	}
//}

//if ("insert".equals(action)) { // 來自addEmp.jsp的請求
//
//	req.setAttribute("errorMsgs", errorMsgs);
//
//	try {
//		/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//		String account = req.getParameter("account");
////		String password = "^[(a-zA-Z0-9_)]{2,10}$";

////		

//		String password = req.getParameter("password");
//		if (account == null || account.trim().length() == 0) {
//			errorMsgs.put("account", "會員信箱: 請勿空白");
//		}
////		else if (!account.trim().matches(password)) { // 以下練習正則(規)表示式(regular-expression)
////			errorMsgs.put("password", "會員密碼: 只能是中、英文字母, 且長度必需在2到10之間");
////		}
//
//		if (!errorMsgs.isEmpty()) {
//			RequestDispatcher failureView = req.getRequestDispatcher("/front/addMember.jsp");
//			failureView.forward(req, res);
//			return;
//		}
//
//		/*************************** 2.開始新增資料 ***************************************/
//		MembersService membersSvc = new MembersService();
//		MembersVO membersVO = new MembersVO();
//		membersVO.setAccount(account);
//		membersVO.setPassword(password);
//		membersSvc.insert(membersVO);
//
//		/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//		String url = "/front/listAllMember.jsp";
//		RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//		successView.forward(req, res);
//
//		/*************************** 其他可能的錯誤處理 **********************************/
//	} catch (Exception e) {
//		errorMsgs.put("Exception", e.getMessage());
//		RequestDispatcher failureView = req.getRequestDispatcher("/front/addMember.jsp");
//		failureView.forward(req, res);
//	}
//}

//if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
//
//	try {
//		/*************************** 1.接收請求參數 ****************************************/
//		Integer memberId = Integer.valueOf(req.getParameter("memberId"));
//		Integer status = Integer.valueOf(req.getParameter("status"));
//		/*************************** 2.開始查詢資料 ****************************************/
//
//		MembersVO memberVO = memberSvc.getOneById(memberId);
//
//		/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//		String param = "?memberId=" + memberVO.getMemberId() + "&account=" + memberVO.getAccount() + "&name="
//				+ memberVO.getName() + "&address=" + memberVO.getAddress() + "&phone=" + memberVO.getPhone()
//				+ "&rankId=" + memberVO.getRankId() + "&eWalletAmount=" + memberVO.geteWalletAmount()
//				+ "&bonusAmount=" + memberVO.getBonusAmount() + "&status=" + memberVO.getStatus()
//				+ "&createTime=" + memberVO.getCreateTime();
//
//		String url = "/front/update_member_input.jsp" + param;
//		RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_member_input.jsp
//		successView.forward(req, res);
//
//		/*************************** 其他可能的錯誤處理 **********************************/
//	} catch (Exception e) {
//		errorMsgs.put("無法取得要修改的資料", e.getMessage());
//		RequestDispatcher failureView = req.getRequestDispatcher("/front/listAllMember.jsp");
//		failureView.forward(req, res);
//	}
//}

//if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//
//	try {
//		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//		Integer memberId = Integer.valueOf(req.getParameter("memberId").trim());
//		String account = req.getParameter("account").trim();
//		String name = req.getParameter("name");
//		String address = req.getParameter("address");
//		String phone = req.getParameter("phone");
//		Integer rankId = Integer.valueOf(req.getParameter("rankId").trim());
//		Integer eWalletAmount = Integer.valueOf(req.getParameter("eWalletAmount").trim());
//		Integer bonusAmount = Integer.valueOf(req.getParameter("bonusAmount").trim());
//		Integer status = Integer.valueOf(req.getParameter("status").trim());
//		Timestamp createTime = Timestamp.valueOf(req.getParameter("createTime").trim());
//
//		if (status == null) {
//			errorMsgs.put("status", "狀態請勿空白");
//		}
//
//		if (!errorMsgs.isEmpty()) {
//			RequestDispatcher failureView = req.getRequestDispatcher("/front/update_member_input.jsp");
//			failureView.forward(req, res);
//			return; // 程式中斷
//		}
//
//		/*************************** 2.開始修改資料 *****************************************/
////		MembersService memberSvc = new MembersService();
//		MembersVO membersVO = new MembersVO();
//		membersVO.setMemberId(memberId);
//		membersVO.setStatus(status);
//		memberSvc.changeStatus(membersVO);
//
//		/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//		membersVO.setAccount(account);
//		membersVO.setName(name);
//		membersVO.setAddress(address);
//		membersVO.setPhone(phone);
//		membersVO.setRankId(rankId);
//		membersVO.seteWalletAmount(eWalletAmount);
//		membersVO.setBonusAmount(bonusAmount);
//		membersVO.setCreateTime(createTime);
//		req.setAttribute("membersVO", membersVO); // 資料庫update成功後,正確的的empVO物件,存入req
//		String url = "/front/listOneMember.jsp";
//		RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//		successView.forward(req, res);
//
//		/*************************** 其他可能的錯誤處理 *************************************/
//	} catch (Exception e) {
//		errorMsgs.put("修改資料失敗", e.getMessage());
//		RequestDispatcher failureView = req.getRequestDispatcher("/front/update_member_input.jsp");
//		failureView.forward(req, res);
//	}
//}
