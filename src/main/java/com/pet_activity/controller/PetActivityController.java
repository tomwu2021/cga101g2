package com.pet_activity.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pet_activity.model.PetActivityVO;
import com.pet_activity.service.PetActivityService;


@WebServlet("/activity")
public class PetActivityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PetActivityController() {
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String recordId = req.getParameter("recordId");
		String petId = req.getParameter("petId");
		String activity = req.getParameter("activity");
		String recordTime = req.getParameter("recordTime");
		Date _recordTime = null;
		/*************************** 1.新增一筆資料 ****************************************/
		if("insert".equals(action)){
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// 檢查輸入
			if(activity == null || activity.trim().isEmpty()) {
				errorMsgs.put("activity"," (請勿空白)");
			}
			try {
				java.util.Calendar cal = java.util.Calendar.getInstance();
				cal.set(Calendar.HOUR, 0);
				cal.set(Calendar.MINUTE, 0);
		        cal.set(Calendar.SECOND, 0);
		        cal.set(Calendar.MILLISECOND,0);
				Long currentTime = cal.getTime().getTime();
				_recordTime = Date.valueOf(recordTime.trim());
				if(_recordTime.getTime() > currentTime) throw new IllegalArgumentException();
			} catch (IllegalArgumentException e) {
				errorMsgs.put("recordTime"," (請輸入正確日期)");
			}
			// 錯誤處理
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("activity", activity);
				req.setAttribute("recordTime", recordTime);
				RequestDispatcher failView = req.getRequestDispatcher("/front/pet/activity/list.jsp");
				failView.forward(req, res);
				return;
			}
			// 正常送出
			PetActivityService paSvc = new PetActivityService();
			PetActivityVO paVO = paSvc.addActivityRecord(Integer.parseInt(petId), activity,_recordTime);
			paVO = paSvc.getOneActivity(Integer.parseInt(recordId));
			req.setAttribute("paVO", paVO);
			String url = "/front/pet/activity/detail.jsp";
			RequestDispatcher successView =req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		/*************************** 2.刪除一筆資料 ****************************************/
		if("delete".equals(action)){
			PetActivityService paSvc = new PetActivityService();
			paSvc.deleteActivityRecord(Integer.parseInt(recordId));
			paSvc.getOneActivity(Integer.parseInt(recordId));
			String param ="?petId="+ petId;
			String url = "/front/pet/activity/list.jsp"+ param;
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
		/*************************** 3.更改一筆資料 **************************************/
		if ("update".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// 檢查輸入
			if(activity == null || activity.trim().isEmpty()) {
				errorMsgs.put("activity"," (未更新！請輸入內容)");
			}
			try {
				java.util.Calendar cal = java.util.Calendar.getInstance();
				cal.set(Calendar.HOUR, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND,0);
				Long currentTime = cal.getTime().getTime();
				_recordTime = Date.valueOf(recordTime.trim());
				if(_recordTime.getTime() > currentTime) throw new IllegalArgumentException();
			} catch (IllegalArgumentException e) {
				errorMsgs.put("recordTime"," (未更新！請輸入正確日期)");
				_recordTime = null;
			}
			// 正常送出
			PetActivityService paSvc = new PetActivityService();
			PetActivityVO paVO = paSvc.updateActivityRecord(activity, _recordTime, Integer.parseInt(recordId));
			paVO = paSvc.getOneActivity(Integer.parseInt(recordId));
			req.setAttribute("paVO", paVO);
			String url = "/front/pet/activity/detail.jsp";
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
		/*************************** 4.單筆資料查看 ****************************************/
		if("one_Display".equals(action)){
			PetActivityService paSvc = new PetActivityService();
			PetActivityVO paVO = paSvc.getOneActivity(Integer.parseInt(recordId));
			req.setAttribute("paVO", paVO);
			String url = "/front/pet/activity/detail.jsp";
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
	}

}
