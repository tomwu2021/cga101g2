package com.pet_weight.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pet_weight.model.*;
import com.pet_weight.service.PetWeightService;


@WebServlet("/weight")
public class PetWeightController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PetWeightController() {
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
		String weightRecord = req.getParameter("weightRecord");
		String recordTime = req.getParameter("recordTime");
		
				
		/*************************** 1.新增一筆資料 ****************************************/
		if("create".equals(action)){
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			BigDecimal weightDecimal =null;
			try {
				weightDecimal = new BigDecimal(weightRecord.trim());
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
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("weightRecord", weightRecord);
				req.setAttribute("recordTime", recordTime);
				RequestDispatcher failureView = req.getRequestDispatcher("/front/PetTest/addPetWgt.jsp");
				failureView.forward(req, res);
				return;
			}
			PetWeightService pwSvc = new PetWeightService();
			pwSvc.addWeightRecord(Integer.parseInt(petId),  weightDecimal, recordDate);
			String param ="?petId="+ petId;
			String url = "/front/PetTest/listAllPetWgt.jsp"+ param;
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
		/*************************** 2.刪除一筆資料 ****************************************/
		if("delete".equals(action)){
			PetWeightService pwSvc = new PetWeightService();
			pwSvc.deleteWeightRecord(Integer.parseInt(recordId));
			pwSvc.getOneWeight(Integer.parseInt(recordId));
			String param ="?petId="+ petId;
			String url = "/front/PetTest/listAllPetWgt.jsp"+ param;
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
		/*************************** 5.單筆資料查看 ****************************************/
		if("one_Display".equals(action)){
			PetWeightService pwSvc = new PetWeightService();
			PetWeightVO pwVO = pwSvc.getOneWeight(Integer.parseInt(recordId));
			req.setAttribute("pwVO", pwVO);
			String url = "/front/PetTest/listOnePetWgt.jsp";
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
		/*************************** 6.更改活動紀錄 **************************************/
		if ("update".equals(action)) {
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			BigDecimal weightDecimal =null;
			try {
				weightDecimal = new BigDecimal(weightRecord.trim());
			}catch (NumberFormatException e) {
				errorMsgs.put("weightRecord"," (輸入錯誤未更新)");
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
				errorMsgs.put("recordTime"," (輸入錯誤未更新)");
				recordDate = null;
			}
						
			PetWeightService pwSvc = new PetWeightService();
			PetWeightVO pwVO = pwSvc.updateWeightRecord(weightDecimal, recordDate, Integer.parseInt(recordId));
					   pwVO = pwSvc.getOneWeight(Integer.parseInt(recordId));
			req.setAttribute("pwVO", pwVO);
			String url = "/front/PetTest/listOnePetWgt.jsp";
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
		

	}

}
