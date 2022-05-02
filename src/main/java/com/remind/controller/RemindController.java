package com.remind.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.remind.model.*;
import com.remind.service.RemindService;

@WebServlet("/remind")
public class RemindController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemindController() {
        super();
    }


    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String remindId = req.getParameter("remindId");
		String memberId = req.getParameter("memberId");
		String content = req.getParameter("content");
		String time = req.getParameter("time");
		Timestamp _time = null;
		/*************************** 1.新增一筆資料 ****************************************/
		if("insert".equals(action)){
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// 檢查輸入
			if(content == null || content.trim().isEmpty()) {
				errorMsgs.put("content"," (請勿空白)");
			}
			try {
				java.util.Calendar cal = java.util.Calendar.getInstance();
				Long currentTime = cal.getTime().getTime();
				_time = Timestamp.valueOf(time.trim()+":00");
				if(_time.getTime() < currentTime) throw new IllegalArgumentException();
			} catch (IllegalArgumentException e) {
				errorMsgs.put("time"," (請輸入正確時間)");
			}
			// 錯誤處理
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("content", content);
				req.setAttribute("time", time);
				RequestDispatcher failureView = req.getRequestDispatcher("/front/pet/rimind/list.jsp");
				failureView.forward(req, res);
				return;
			}
			// 正常送出
			RemindService rSvc = new RemindService();
			rSvc.addRemind(Integer.parseInt(memberId), content, _time);
			String url = "/front/pet/remind/detail.jsp";
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
		/*************************** 2.刪除一筆資料 ****************************************/
		if("delete".equals(action)){
			RemindService rSvc = new RemindService();
			rSvc.deleteRemind(Integer.parseInt(remindId));
			rSvc.getByMemberId(Integer.parseInt(memberId));
			String param ="?memberId="+ memberId;
			String url = "/front/pet/remind/list.jsp"+ param;
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
		/*************************** 3.更改一筆資料 **************************************/
		if ("update".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// 檢查輸入
			if(content == null || content.trim().isEmpty()) {
				errorMsgs.put("content"," (輸入空白未更新)");
			}
			try {
				java.util.Calendar cal = java.util.Calendar.getInstance();
				Long currentTime = cal.getTime().getTime();
				_time = Timestamp.valueOf(time.trim()+":00");
				if(_time.getTime() < currentTime) throw new IllegalArgumentException();
			} catch (IllegalArgumentException e) {
				errorMsgs.put("time"," (輸入錯誤未更新)");
				_time = null;
			}
			// 正常送出
			RemindService rSvc = new RemindService();
			RemindVO rVO = rSvc.updateRemind(content, _time, Integer.parseInt(remindId));
			rVO = rSvc.getByRemindId(Integer.parseInt(remindId));
			req.setAttribute("rVO", rVO);
			String url = "/front/pet/remind/detail.jsp";
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
		/*************************** 5.單筆資料查看 ****************************************/
		if("one_Display".equals(action)){
			RemindService rSvc = new RemindService();
			RemindVO rVO = rSvc.getByRemindId(Integer.parseInt(remindId));
			req.setAttribute("rVO", rVO);
			String url = "/front/pet/remind/detail.jsp";
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
	}

}