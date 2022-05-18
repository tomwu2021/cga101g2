package com.notification.controller;

import java.io.*;
import java.util.List;

import javax.management.Notification;
import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;
import com.notification.model.NotificationService;
import com.notification.model.NotificationVO;

import javax.servlet.annotation.WebServlet;

@WebServlet("/front/notification.do")
public class notificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");// client 端向 Servlet 請求的編碼
		res.setCharacterEncoding("UTF-8");// response，設定回應的格式及編碼

		// 判斷呼叫哪個方法
		String action = req.getParameter("action");
		System.out.println(action);

		action = action == null ? "" : action;

		switch (action) {
		case "selectNotification":
			selectNotification(req, res);
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

	/*************************** 取得某會員通知 **********************/
	public void selectNotification(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		Integer memberId = Integer.parseInt(req.getParameter("memberId"));
		NotificationService notificationSvc = new NotificationService();
		List<NotificationVO> listNotification = notificationSvc.getAllById(memberId);
		String json = new Gson().toJson(listNotification);
		res.getWriter().write(json);
		return;
	}

}
