package com.customer.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.customer.model.*;
import com.customer.service.CustomerService;

@WebServlet("/contact")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CustomerController() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String searchBox = req.getParameter("searchBox");
		String radio = req.getParameter("radio");
		String caseId = req.getParameter("caseId");
		String empNo = req.getParameter("empNo");
		/*************************** 1.新增回報資料 ****************************************/
		if ("insert".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String mailAddress = req.getParameter("mailAddress");
			String emailReg ="^[_a-z0-9-]+([.][_a-z0-9-]+)*@[_a-z0-9-]+([.][_a-z0-9-]+)*$";
			
			if (mailAddress == null || mailAddress.trim().isEmpty()) {
				errorMsgs.put("mailAddress"," (請勿空白)");
			} else if(!mailAddress.trim().matches(emailReg)) { 
				errorMsgs.put("mailAddress","(請輸入正確資訊)");
            }
			
			String nickname = req.getParameter("nickname");
			if (nickname == null || nickname.trim().isEmpty()) {
				errorMsgs.put("nickname","(請勿空白)");
			}
			
			String content = req.getParameter("content");
			if (content == null || content.trim().isEmpty()) {
				errorMsgs.put("content","(請勿空白)");
			}
			String captcha = req.getParameter("captcha");
			if (!captcha.trim().isEmpty()) {
				errorMsgs.put("captcha","(輸入錯誤)");
			}
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("mailAddress", mailAddress);
				req.setAttribute("nickname", nickname);
				req.setAttribute("content", content);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/contact.jsp");
				failureView.forward(req, res);
				return;
			}
			CustomerService custSvc = new CustomerService();
			custSvc.addCustomer(mailAddress, nickname, content);
			req.setAttribute("success", nickname);
			String url = "/front/contact.jsp";
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
		/*************************** 2.依email查詢 ****************************************/
		if("email_Display".equals(action)){
			CustomerService custSvc = new CustomerService();
			List<CustomerVO> list = custSvc.getByCustEmail(searchBox);
			//TODO 查無資料
			req.setAttribute("list", list);
			req.setAttribute("action", action);
			req.setAttribute("searchBox", searchBox);
			String url = "/front/PetTest/filtCust.jsp";
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
		/*************************** 3.依關鍵字查詢 ****************************************/
		if("keyword_Display".equals(action)){
			CustomerService custSvc = new CustomerService();
			List<CustomerVO> list = custSvc.getByKeyWord(searchBox);
			//TODO 查無資料
			req.setAttribute("list", list);
			req.setAttribute("action", action);
			req.setAttribute("searchBox", searchBox);
			String url = "/front/PetTest/filtCust.jsp";
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
		/*************************** 4.依回覆狀態查詢 ****************************************/
		if("status_Display".equals(action)){
			CustomerService custSvc = new CustomerService();
			List<CustomerVO> list = custSvc.getByReplyStatus(Integer.parseInt(radio));
			//TODO 查無資料
			req.setAttribute("list", list);
			req.setAttribute("action", action);
			String url = "/front/PetTest/filtCust.jsp";
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
		/*************************** 5.單筆資料查看 ****************************************/
		if("one_Display".equals(action)){
			CustomerService custSvc = new CustomerService();
			CustomerVO custVO = custSvc.getByCustId(Integer.parseInt(caseId));
			req.setAttribute("custVO", custVO);
			String url = "/front/PetTest/listOneCust.jsp";
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
		/*************************** 6.員工回覆已處理 **************************************/
		if ("update".equals(action)) {
			CustomerService custSvc = new CustomerService();
			CustomerVO custVO = custSvc.updateCustomer(Integer.parseInt(empNo),Integer.parseInt(caseId));
					   custVO = custSvc.getByCustId(Integer.parseInt(caseId));
			req.setAttribute("custVO", custVO);
			String url = "/front/PetTest/listOneCust.jsp";
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
		

	}

}
