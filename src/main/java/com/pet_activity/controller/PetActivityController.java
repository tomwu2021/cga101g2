package com.pet_activity.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.controller.CommonController;
import com.pet_activity.model.PetActivityVO;
import com.pet_activity.service.PetActivityService;


@WebServlet("/activity")
public class PetActivityController extends CommonController {
	private static final long serialVersionUID = 1L;
       
    public PetActivityController() {
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
				errorMsgs.put("activity"," (內容請勿空白)");
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
				RequestDispatcher failView = req.getRequestDispatcher("/front/pet/activity/add.jsp");
				failView.forward(req, res);
				return;
			}
			// 正常送出
			PetActivityService paSvc = new PetActivityService();
			paSvc.addActivityRecord(Integer.parseInt(petId), activity,_recordTime);
			String url = "/CGA101G2/activity?action=all_Display&petId="+petId;
			res.sendRedirect(url);
		}
		
		/*************************** 2.刪除一筆資料 ****************************************/
		if("delete".equals(action)){
			PetActivityService paSvc = new PetActivityService();
			paSvc.deleteActivityRecord(Integer.parseInt(recordId));
			String url = "/activity?action=all_Display&petId="+petId;
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
		/*************************** 3.更改一筆資料 **************************************/
		if ("update".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// 檢查輸入
			if(activity == null || activity.trim().isEmpty()) {
				errorMsgs.put("activity"," (請輸入內容)");
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
				_recordTime = null;
			}
			// 正常送出
			PetActivityService paSvc = new PetActivityService();
			PetActivityVO paVO = paSvc.updateActivityRecord(activity, _recordTime, Integer.parseInt(recordId));
			paVO = paSvc.getOneActivity(paVO.getRecordId());
			List<PetActivityVO> paAll = paSvc.getAllByPetId(Integer.parseInt(petId));
			req.setAttribute("paAll", paAll);
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
			String url = "/front/pet/activity/edit.jsp";
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
		/*************************** 5.導向新增頁面 ****************************************/
		if("goToInsert".equals(action)){
			String url = "/CGA101G2/front/pet/activity/add.jsp";
			res.sendRedirect(url);
		}
		/*************************** 6.查看所有紀錄 ****************************************/
		if("all_Display".equals(action)){
			PetActivityService paSvc = new PetActivityService();
			List<PetActivityVO> paAll = paSvc.getAllByPetId(Integer.parseInt(petId));
			req.setAttribute("paAll", paAll);
			String url = "/front/pet/activity/detail.jsp";
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
	}

}
