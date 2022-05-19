package com.account.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.controller.CommonController;
import com.members.model.*;

@WebServlet("/account")
public class AccountController extends CommonController {
	private static final long serialVersionUID = 1L;
	
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
    	try {
			doPost(req, res);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MembersService memberSvc = new MembersService();
		int memberId = ((MembersVO)req.getSession().getAttribute("membersVO")).getMemberId();
		MembersVO membersVO = memberSvc.getOneById(memberId);
		req.getSession().setAttribute("membersVO", membersVO);
		RequestDispatcher view = req.getRequestDispatcher("/front/account.jsp");
		view.forward(req, res);
		
	}
	

}
