package com.remind.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.controller.CommonController;
import com.remind.model.RemindVO;
import com.remind.service.RemindService;

@WebServlet("/remind")
public class RemindController extends CommonController {
	private static final long serialVersionUID = 1L;
       
    public RemindController() {
        super();
    }


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
				errorMsgs.put("content"," (內容請勿空白)");
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
				RequestDispatcher failureView = req.getRequestDispatcher("/front/pet/remind/add.jsp");
				failureView.forward(req, res);
				return;
			}
			// 正常送出
			RemindService rSvc = new RemindService();
			rSvc.addRemind(Integer.parseInt(memberId), content, _time);
			String url = "/remind?action=all_Display&memberId="+memberId;
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
		/*************************** 2.刪除一筆資料 ****************************************/
		if("delete".equals(action)){
			RemindService rSvc = new RemindService();
			rSvc.deleteRemind(Integer.parseInt(remindId));
			String url = "/remind?action=all_Display&memberId="+ memberId;
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
		/*************************** 3.更改一筆資料 **************************************/
		if ("update".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// 檢查輸入
			if(content == null || content.trim().isEmpty()) {
				errorMsgs.put("content"," (請輸入內容)");
			}
			try {
				java.util.Calendar cal = java.util.Calendar.getInstance();
				Long currentTime = cal.getTime().getTime();
				_time = Timestamp.valueOf(time.trim()+":00");
				if(_time.getTime() < currentTime) throw new IllegalArgumentException();
			} catch (IllegalArgumentException e) {
				errorMsgs.put("time"," (請輸入正確時間)");
				_time = null;
			}
			// 正常送出
			RemindService rSvc = new RemindService();
			RemindVO rVO = rSvc.updateRemind(content, _time, Integer.parseInt(remindId));
			rVO = rSvc.getByRemindId(rVO.getRemindId());
			List<RemindVO> rAll = rSvc.getByMemberId(Integer.parseInt(memberId));
			req.setAttribute("rAll", rAll);
			req.setAttribute("rVO", rVO);
			String url = "/front/pet/remind/detail.jsp";
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
		/*************************** 4.單筆資料查看 ****************************************/
		if("one_Display".equals(action)){
			RemindService rSvc = new RemindService();
			RemindVO rVO = rSvc.getByRemindId(Integer.parseInt(remindId));
			req.setAttribute("rVO", rVO);
			String url = "/front/pet/remind/edit.jsp";
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
		/*************************** 5.導向新增頁面 ****************************************/
		if("goToInsert".equals(action)){
			String url =  req.getContextPath()+"/front/pet/remind/add.jsp";
			res.sendRedirect(url);
		}
		/*************************** 6.查看所有紀錄 ****************************************/
		if("all_Display".equals(action)){
			RemindService rSvc = new RemindService();
			List<RemindVO> rAll = rSvc.getByMemberId(Integer.parseInt(memberId));
			req.setAttribute("rAll", rAll);
			String url = "/front/pet/remind/detail.jsp";
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
	}

}
