package com.members.controller;

import java.io.*;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.album.model.AlbumJDBCDAO;
import com.chargeRecord.model.ChargeRecordDAO;
import com.chargeRecord.model.ChargeRecordService;
import com.chargeRecord.model.ChargeRecordVO;
import com.google.gson.Gson;
import com.members.model.*;
import com.pet.service.PetService;
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
		System.out.println(action);

		action = action == null ? "" : action;

		switch (action) {
		case "forLogin": // 登入
			forLogin(req, res);
			break;
		case "checkAccount": // 帳號確認
			checkAccount(req, res);
			break;
		case "registerVerification": // 註冊帳號驗證
			registerVerification(req, res);
			break;
		case "sendforgotMail": // 忘記密碼發送Email
			sendforgotMail(req, res);
			break;
		case "getRankName": // 取得會員等級
			getRankName(req, res);
			break;
		case "updateMemberInfo": // 會員修改基本資料
			updateMemberInfo(req, res);
			break;
		case "updateMemberPassword": // 會員修改密碼
			updateMemberPassword(req, res);
			break;
		case "walletAddMoney": // 會員儲值
			walletAddMoney(req, res);
			break;
		case "updateMemberWalletPassword": // 會員修改錢包密碼
			updateMemberWalletPassword(req, res);
			break;
		case "checkWalletPassword": // 確認會員錢包密碼
			checkWalletPassword(req, res);
			break;
		case "updateSetWalletPassword": // 設定錢包密碼
			updateSetWalletPassword(req, res);
			break;
		case "memberWalletUsedRecord": // 會員儲值/消費紀錄
			memberWalletUsedRecord(req, res);
			break;
		case "firstLogin":
			firstLogin(req, res); // 首次登入判斷
			break;
		case "logout":
			logout(req, res); // 登出
			break;
		case "totalMoney": // 取得總儲值金額
			totalMoney(req, res);
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

		// service
		MembersService memberSvc = new MembersService();

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

		// 資料庫是否有此筆資料
		Boolean boo = memberSvc.getOneByAccount(loginAccount);

		if (boo == true) {
			MembersVO membersVO = memberSvc.selectForLogin(loginAccount, loginPassword);
			// 判斷停權會員無法登入

			if (membersVO != null) {

				if (membersVO.getStatus().equals(0)) {
					messages.put("messagesAccount", "此帳號已被停權無法登入");
					RequestDispatcher failureView = req.getRequestDispatcher("/front/login.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				} else {

					HttpSession session = req.getSession();
					session.setAttribute("membersVO", membersVO);
					req.setAttribute("membersVO", membersVO); // 資料庫取出的 membersVO 物件，存入 req

					if (session.getAttribute("location") != null) {
						String url = session.getAttribute("location").toString();
//						System.out.println(url);
						res.sendRedirect(url);
						return;
					} else { // http://localhost:8081/CGA101G2
						RequestDispatcher successView = req.getRequestDispatcher("/index.html");
						successView.forward(req, res);
						return;
					}
				}

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
			String checkEmail = "([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@gmail.com";
			if (registerAccount.matches(checkEmail)) {

				// 產生驗證碼存在 session
				String verificationCode = memberSvc.genAuthCode();
				HttpSession sessionVC = req.getSession();
				sessionVC.setAttribute("authCode", verificationCode);
				sessionVC.setAttribute("registerAccount", registerAccount);

				System.out.println(verificationCode);

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
			messages.put("msgErrorVerificationCode", "驗證碼錯誤！");
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

		// 預設一隻寵物，給 memberId
		PetService pSvc = new PetService();
		pSvc.defaultPet(newMember.getMemberId());

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

		if (!messages.isEmpty()) {//
			RequestDispatcher failureView = req.getRequestDispatcher("/front/member/memberUpdate.jsp");
			failureView.forward(req, res);
			return;// 程式中斷
		}
		HttpSession currentSession = req.getSession();
		MembersVO sessionMembersVO = (MembersVO) currentSession.getAttribute("membersVO");
		MembersService memberSvc = new MembersService();
		sessionMembersVO.setName(name); // Name
		sessionMembersVO.setPhone(phone); // Phone
		sessionMembersVO.setAddress(address); // Address

		System.out.println(sessionMembersVO);
		memberSvc.update(sessionMembersVO);

		RequestDispatcher successView = req.getRequestDispatcher("/front/member/member.jsp");
		successView.forward(req, res);
		return;// 程式中斷

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
				messages.put("errorNewPhone", "*密碼格式錯誤");
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
			RequestDispatcher successView = req.getRequestDispatcher("/front/member/memberSuccess.jsp");
			successView.forward(req, res);
			return;// 程式中斷
		}

	}

	/*************************** 會員錢包儲值 **********************/
	public void walletAddMoney(HttpServletRequest req, HttpServletResponse res) {

		// session MemberVO
		HttpSession currentSession = req.getSession();
		MembersVO sessionMembersVO = (MembersVO) currentSession.getAttribute("membersVO");
		Integer currentMemberId = sessionMembersVO.getMemberId();

		if (currentSession.getAttribute("submit") != null) {

			res.setContentType("application/json; charset=UTF-8");

			// Service
			MembersService memberSvc = new MembersService();
			ChargeRecordDAO chargeRecordDAO = new ChargeRecordDAO();

			// 訊息存在 Map
			Map<String, String> messages = new LinkedHashMap<String, String>();
			req.setAttribute("messages", messages);

			// 接收參數
			String storedValueAmount = req.getParameter("storedValueAmount");
			String cardNumber = req.getParameter("cardNumber");
			String passwordWallet = req.getParameter("passwordWallet");
			String regex = "^[0-9]*$";

			// 判斷是否輸入數字
			if (!storedValueAmount.trim().matches(regex)) {
				messages.put("inputError", "*請輸入正整數");
			}

			// 判斷錢包密碼是否正確
			String dbPassword = memberSvc.geteWalletPassword(currentMemberId);
			if (!passwordWallet.equals(dbPassword)) {
				messages.put("inputErrorpasswordWallet", "*錢包密碼輸入錯誤");
			}

			if (!messages.isEmpty()) {
				messages.put("resStoredValueAmount", storedValueAmount);
				messages.put("resCardNumber", cardNumber);
				messages.put("resPasswordWallet", passwordWallet);
				RequestDispatcher failureView = req.getRequestDispatcher("/front/member/memberWallet.jsp");
				try {
					failureView.forward(req, res);
					return;
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}

			// 儲值成功 DAO
			memberSvc.walletPaymentAddMoney(currentMemberId, Integer.valueOf(storedValueAmount));
			sessionMembersVO.seteWalletAmount(memberSvc.getOneById(currentMemberId).geteWalletAmount());

			// 儲值成功後判斷累積儲值金額修改會員等級
			Integer sumChargeAmount = chargeRecordDAO.SumChargeAmount(currentMemberId);
			System.out.println(sumChargeAmount);

			if (sumChargeAmount >= 10001) {
				memberSvc.updateRank(currentMemberId, 4);
				sessionMembersVO.setRankId(4);
				RequestDispatcher successView = req.getRequestDispatcher("/front/member/memberWalletUsedRecord.jsp");
				try {
					successView.forward(req, res);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
				currentSession.removeAttribute("submit");
				return;
			} else if (sumChargeAmount >= 5001) {
				memberSvc.updateRank(currentMemberId, 3);
				sessionMembersVO.setRankId(3);
				RequestDispatcher successView = req.getRequestDispatcher("/front/member/memberWalletUsedRecord.jsp");
				try {
					successView.forward(req, res);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
				currentSession.removeAttribute("submit");
				return;
			} else {
				memberSvc.updateRank(currentMemberId, 2);
				sessionMembersVO.setRankId(2);
				RequestDispatcher successView = req.getRequestDispatcher("/front/member/memberWalletUsedRecord.jsp");
				try {
					successView.forward(req, res);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
				currentSession.removeAttribute("submit");
				return;
			}

		}

		RequestDispatcher successView = req.getRequestDispatcher("/front/member/memberWalletUsedRecord.jsp");
		try {
			successView.forward(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		return;// 程式中斷

	}

	public void updateMemberWalletPassword(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		Map<String, String> messages = new LinkedHashMap<String, String>();
		req.setAttribute("messages", messages);

		HttpSession currentSession = req.getSession();
		MembersVO sessionMembersVO = (MembersVO) currentSession.getAttribute("membersVO");
		String currentWalletPassword = sessionMembersVO.geteWalletPassword();

		String oldWalletPassword = req.getParameter("oldWalletPassword");

		System.out.println(currentWalletPassword);
		System.out.println(oldWalletPassword);

		if (oldWalletPassword == null || oldWalletPassword.trim().length() == 0) {
			messages.put("errorOldoldWalletPassword", "*密碼不可為空");
		} else {
			if (!oldWalletPassword.equals(currentWalletPassword)) {
				messages.put("errorOldoldWalletPassword", "*請確認舊密碼是否輸入正確");
			}
		}

		String regex = "^[0-9]{6}$";
		String newWalletPassword = req.getParameter("newWalletPassword");
		System.out.println(newWalletPassword);
		if (newWalletPassword == null || newWalletPassword.trim().length() == 0) {
			messages.put("errornewWalletPassword", "*密碼不可為空");
		} else {
			if (!newWalletPassword.trim().matches(regex)) {
				messages.put("errornewWalletPassword", "*密碼格式不正確");
			}
		}

		String checkNewWalletPassword = req.getParameter("checkNewWalletPassword");
		if (checkNewWalletPassword == null || checkNewWalletPassword.trim().length() == 0) {
			messages.put("checkNewWalletPassword", "*密碼不可為空");
		} else {
			if (!checkNewWalletPassword.equals(newWalletPassword)) {
				messages.put("checkNewWalletPassword", "*密碼與前次輸入不相符");
			}
		}
		if (!messages.isEmpty()) {
			messages.put("userInput1", oldWalletPassword);
			messages.put("userInput2", newWalletPassword);

			RequestDispatcher failureView = req.getRequestDispatcher("/front/member/memberWalletPassword.jsp");
			failureView.forward(req, res);
			return;// 程式中斷
		} else {
			// 輸入正確後，呼叫 DAO 修改資料庫錢包密碼
			MembersService memberSvc = new MembersService();
			MembersVO newMemberVO = new MembersVO();
			newMemberVO.setMemberId(sessionMembersVO.getMemberId());
			newMemberVO.seteWalletPassword(newWalletPassword);
			memberSvc.update(newMemberVO);
			sessionMembersVO
					.seteWalletPassword(memberSvc.getOneById(sessionMembersVO.getMemberId()).geteWalletPassword());
			messages.put("updatePasswordSuccess", "修改密碼成功！");
			// successful 的頁面
			RequestDispatcher successView = req.getRequestDispatcher("/front/member/memberSuccess.jsp"); // 製作一個 success
																											// 畫面
			successView.forward(req, res);
			return;// 程式中斷
		}
	}

	public void checkWalletPassword(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		Map<String, String> messages = new LinkedHashMap<String, String>();
		req.setAttribute("messages", messages);

		HttpSession currentSession = req.getSession();
		MembersVO sessionMembersVO = (MembersVO) currentSession.getAttribute("membersVO");
		String currentWalletPassword = sessionMembersVO.geteWalletPassword();
//		System.out.println(currentWalletPassword);

		MembersService memberSvc = new MembersService();
		String walletPassword = memberSvc.selectForLogin(sessionMembersVO.getAccount(), sessionMembersVO.getPassword())
				.geteWalletPassword();
		System.out.println(walletPassword);

		sessionMembersVO.seteWalletPassword(walletPassword);

//		System.out.println("執行111");

		if (currentWalletPassword == null || currentWalletPassword.trim().length() == 0) {
			// 跳轉到新建密碼畫面
			messages.put("exist", "false");
			String json = new Gson().toJson(messages);
			res.getWriter().write(json);
			return;
		} else {
			// 跳轉到原本畫面
			messages.put("exist", "true");
			String json = new Gson().toJson(messages);
			res.getWriter().write(json);
			return;
		}
	}

	public void updateSetWalletPassword(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		HttpSession currentSession = req.getSession();
		MembersVO sessionMembersVO = (MembersVO) currentSession.getAttribute("membersVO");

		Map<String, String> messages = new LinkedHashMap<String, String>();
		req.setAttribute("messages", messages);

		String regex = "^[0-9]{6}$";
		String setWalletPassword = req.getParameter("setWalletPassword");
		System.out.println(setWalletPassword);
		if (setWalletPassword == null || setWalletPassword.trim().length() == 0) {
			messages.put("errorSetWalletPassword", "*密碼不可為空");
		} else {
			if (!setWalletPassword.trim().matches(regex)) {
				messages.put("errorSetWalletPassword", "*密碼格式不正確");
			}
		}

		String checkWalletPassword = req.getParameter("checkWalletPassword");
		if (checkWalletPassword == null || checkWalletPassword.trim().length() == 0) {
			messages.put("errorCheckWalletPassword", "*密碼不可為空");
		} else {
			if (!checkWalletPassword.equals(setWalletPassword)) {
				messages.put("errorCheckWalletPassword", "*密碼與前次輸入不相符");
			}
		}
		if (!messages.isEmpty()) {
			messages.put("userInput1", setWalletPassword);
			RequestDispatcher failureView = req.getRequestDispatcher("/front/member/memberSetWalletPassword.jsp");
			failureView.forward(req, res);
			return;// 程式中斷
		} else {
			// 輸入正確後，呼叫 DAO 修改資料庫錢包密碼
			MembersService memberSvc = new MembersService();
			MembersVO newMemberVO = new MembersVO();
			newMemberVO.setMemberId(sessionMembersVO.getMemberId());
			newMemberVO.seteWalletPassword(setWalletPassword);
			memberSvc.update(newMemberVO);
			System.out.println("執行");
			System.out.println(memberSvc.getOneById(sessionMembersVO.getMemberId()).geteWalletPassword());
			sessionMembersVO
					.seteWalletPassword(memberSvc.getOneById(sessionMembersVO.getMemberId()).geteWalletPassword());
			messages.put("updatePasswordSuccess", "成功設定錢包密碼！");
			// successful 的頁面
			RequestDispatcher successView = req.getRequestDispatcher("/front/member/memberSuccess.jsp");
			successView.forward(req, res);
			return;// 程式中斷
		}
	}

	public void memberWalletUsedRecord(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		HttpSession currentSession = req.getSession();
		MembersVO sessionMembersVO = (MembersVO) currentSession.getAttribute("membersVO");

		Map<String, String> messages = new LinkedHashMap<String, String>();
		req.setAttribute("messages", messages);

		ChargeRecordService chargeRecordSvc = new ChargeRecordService();

		List<ChargeRecordVO> listAll = chargeRecordSvc.getAll(sessionMembersVO.getMemberId());

		System.out.println(listAll);

		String json = new Gson().toJson(listAll);
		res.getWriter().write(json);
		return;
	}

	public void firstLogin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession();
		MembersVO sessionMembersVO = (MembersVO) session.getAttribute("membersVO"); // 取得 MembersVO

		Map<String, String> messages = new LinkedHashMap<String, String>();
		req.setAttribute("messages", messages);

		// 首次登入
		if (sessionMembersVO.getBonusAmount() == 0 && sessionMembersVO.geteWalletPassword() == null) {

			System.out.println("這是第一次登入要執行的事情");
			// 首次登入發送紅利
			// ewallet_password == null，代表此會員未消費，所以也不曾使用過紅利點數，因為要使用紅利點數就需要設定錢包密碼進行儲值消費
			// 再判斷 bonus == 0，代表會員從未領過紅利點數
			// 兩者條件成立即 => 首次登入發送紅利
			MembersService memberSvc = new MembersService();
			MembersVO bonusMembersVO = new MembersVO();

			bonusMembersVO.setMemberId(sessionMembersVO.getMemberId());
			bonusMembersVO.setBonusAmount(100);
			memberSvc.changeBonus(bonusMembersVO);

			sessionMembersVO.setBonusAmount(100);
			messages.put("firstLogin", "firstLogin");
			String json = new Gson().toJson(messages);
			res.getWriter().write(json);
			return;
		}
		return;

	}

	public void logout(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("把拔別把我登出!!!!");
		HttpSession session = req.getSession();
		session.removeAttribute("membersVO");
		RequestDispatcher failureView = req.getRequestDispatcher("/front/login.jsp");
		failureView.forward(req, res);
		return;
	}

	public void totalMoney(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 取得總儲值金額後回傳
		HttpSession currentSession = req.getSession();
		MembersVO sessionMembersVO = (MembersVO) currentSession.getAttribute("membersVO");
		Integer currentMemberId = sessionMembersVO.getMemberId();
		ChargeRecordDAO chargeRecordDAO = new ChargeRecordDAO();
		Integer sumChargeAmount = chargeRecordDAO.SumChargeAmount(currentMemberId);
		
		Map<String, String> messages = new LinkedHashMap<String, String>();
		req.setAttribute("messages", messages);
		
		messages.put("totalMoney", String.valueOf(sumChargeAmount));
		String json = new Gson().toJson(messages);
		res.getWriter().write(json);
		return;
	}
}