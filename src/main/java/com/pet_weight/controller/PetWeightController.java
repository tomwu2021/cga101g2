package com.pet_weight.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.controller.CommonController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pet_weight.model.PetWeightVO;
import com.pet_weight.service.PetWeightService;


@WebServlet("/weight")
public class PetWeightController  extends CommonController {
	private static final long serialVersionUID = 1L;
       
    public PetWeightController() {
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
		String weightRecord = req.getParameter("weightRecord");
		String recordTime = req.getParameter("recordTime");
		
				
		/*************************** 1.新增一筆資料 ****************************************/
		if("insert".equals(action)){
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// 檢查輸入
			BigDecimal weightDecimal =null;
			try {
				weightDecimal = new BigDecimal(weightRecord.trim());
				if(weightDecimal.doubleValue()<=0) {
					errorMsgs.put("weightRecord"," (請輸入正數)");
				}
			}catch (NumberFormatException e) {
				errorMsgs.put("weightRecord"," (請輸入數字)");
			}
			Date recordDate = null;
			try {
				java.util.Calendar cal = java.util.Calendar.getInstance();
				cal.set(Calendar.HOUR, 0);
				cal.set(Calendar.MINUTE, 0);
		        cal.set(Calendar.SECOND, 0);
		        cal.set(Calendar.MILLISECOND,0);
				Long currentTime = cal.getTime().getTime();
				recordDate = Date.valueOf(recordTime.trim());
				if(recordDate.getTime() > currentTime) throw new IllegalArgumentException();
			} catch (IllegalArgumentException e) {
				errorMsgs.put("recordTime"," (請輸入正確日期)");
			}
			// 錯誤處理
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("weightRecord", weightRecord);
				req.setAttribute("recordTime", recordTime);
				RequestDispatcher failureView = req.getRequestDispatcher("/front/pet/weight/add.jsp");
				failureView.forward(req, res);
				return;
			}
			// 正常送出
			PetWeightService pwSvc = new PetWeightService();
			pwSvc.addWeightRecord(Integer.parseInt(petId),  weightDecimal, recordDate);
			String url = "/weight?action=all_Display&petId="+petId;
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
		/*************************** 2.刪除一筆資料 ****************************************/
		if("delete".equals(action)){
			PetWeightService pwSvc = new PetWeightService();
			pwSvc.deleteWeightRecord(Integer.parseInt(recordId));
			pwSvc.getOneWeight(Integer.parseInt(recordId));
			String url = "/weight?action=all_Display&petId="+petId;
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
		/*************************** 3.更改體重紀錄 **************************************/
		if ("update".equals(action)) {
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// 檢查輸入
			BigDecimal weightDecimal =null;
			try {
				weightDecimal = new BigDecimal(weightRecord.trim());
				if(weightDecimal.doubleValue()<=0) {
					errorMsgs.put("weightRecord"," (請輸入正數)");
					weightDecimal=null;
				}
			}catch (NumberFormatException e) {
				errorMsgs.put("weightRecord"," (請輸入數字)");
			}
			Date recordDate = null;
			try {
				java.util.Calendar cal = java.util.Calendar.getInstance();
				cal.set(Calendar.HOUR, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND,0);
				Long currentTime = cal.getTime().getTime();
				recordDate = Date.valueOf(recordTime.trim());
				if(recordDate.getTime() > currentTime) throw new IllegalArgumentException();
			} catch (IllegalArgumentException e) {
				errorMsgs.put("recordTime"," (請輸入正確日期)");
				recordDate = null;
			}
			// 正常送出
			PetWeightService pwSvc = new PetWeightService();
			PetWeightVO pwVO = pwSvc.updateWeightRecord(weightDecimal, recordDate, Integer.parseInt(recordId));
			pwVO = pwSvc.getOneWeight(Integer.parseInt(recordId));
			List<PetWeightVO> pwAll = pwSvc.getByPetId(Integer.parseInt(petId));
			final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			req.setAttribute("pwVO", pwVO);
			req.setAttribute("pwAll", pwAll);
			req.setAttribute("pwChart", GSON.toJson(pwAll));//
			String url = "/front/pet/weight/detail.jsp";
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
		/*************************** 4.單筆資料查看 ****************************************/
		if("one_Display".equals(action)){
			PetWeightService pwSvc = new PetWeightService();
			PetWeightVO pwVO = pwSvc.getOneWeight(Integer.parseInt(recordId));
			req.setAttribute("pwVO", pwVO);
			String url = "/front/pet/weight/edit.jsp";
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
		/*************************** 5.導向新增頁面 ****************************************/
		if("goToInsert".equals(action)){
			PetWeightService pwSvc = new PetWeightService();
			List<PetWeightVO> pwAll = pwSvc.getByPetId(Integer.parseInt(petId));
			final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			req.setAttribute("pwChart", GSON.toJson(pwAll));//
			String url = "front/pet/weight/add.jsp";
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
		/*************************** 6.查看所有紀錄 ****************************************/
		if("all_Display".equals(action)){
			PetWeightService pwSvc = new PetWeightService();
			List<PetWeightVO> pwAll = pwSvc.getByPetId(Integer.parseInt(petId));
			final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			req.setAttribute("pwAll", pwAll);
			req.setAttribute("pwChart", GSON.toJson(pwAll));//
			String url = "/front/pet/weight/detail.jsp";
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}

	}

}
