package com.pet.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.customer.service.CustomerService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.members.model.MembersVO;
import com.pet.model.PetVO;
import com.pet.service.PetService;
import com.pet_activity.model.PetActivityVO;
import com.pet_activity.service.PetActivityService;
import com.pet_weight.model.PetWeightVO;
import com.pet_weight.service.PetWeightService;
import com.remind.model.RemindVO;
import com.remind.service.RemindService;

@WebServlet("/pet")
public class PetController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PetService pSvc = new PetService();
    private PetActivityService paSvc = new PetActivityService();
    private PetWeightService pwSvc = new PetWeightService();
    private RemindService rSvc = new RemindService();
    
    public PetController() {
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
    
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//		Integer memberId = Integer.parseInt(req.getParameter("memberId"));
//		Integer petId = Integer.parseInt(req.getParameter("petId"));
		Integer memberId = Integer.parseInt(req.getParameter("memberId"));
		Integer petId = pSvc.getByMemberId(memberId).get(0).getPetId();
		/*************************** 列出寵物所有相關資訊 ****************************************/
		if ("profile".equals(action)) {
			List<RemindVO> rList = rSvc.getThreeByMemberId(memberId);
			List<PetVO> pList = pSvc.getByMemberId(memberId);
			List<PetActivityVO> paList = paSvc.getFourByPetId(petId);
			List<PetWeightVO> pwList = pwSvc.getByPetId(petId);
			PetWeightVO recentWgt = pwSvc.getRecentWeight(petId);
			BigDecimal averageWgt = pwSvc.getAverageWeight(petId);
			final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			req.setAttribute("rList", rList);
			req.setAttribute("pList", pList);
			req.setAttribute("paList", paList);
			req.setAttribute("pwList", GSON.toJson(pwList));//
			req.setAttribute("recentWgt", recentWgt);
			req.setAttribute("averageWgt", averageWgt);
			RequestDispatcher view =req.getRequestDispatcher("/front/pet/profile.jsp");
			view.forward(req, res);
		}
		/*************************** 新增寵物基本資訊 ****************************************/
		//新增畫面
		if ("insert".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String petName = req.getParameter("petName");
			if (petName == null || petName.trim().isEmpty()) {
				errorMsgs.put("petName"," (請勿空白)");
            }
			
			String sort1Id = req.getParameter("sort1Id");
			if (sort1Id == null) {
				errorMsgs.put("sort1Id","(請勿空白)");
			}
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("petName", petName);
				req.setAttribute("sort1Id", sort1Id);
				RequestDispatcher failView = req
						.getRequestDispatcher("/front/pet/intro/add.jsp");
				failView.forward(req, res);
				return;
			}
			Integer breed = Integer.parseInt(sort1Id);
			String gender = req.getParameter("gender");
			String headshot = req.getParameter("headshot");
			String introduction = req.getParameter("introduction");
			String birthday = req.getParameter("birthday");
			PetService petSvc = new PetService();
			petSvc.addPet(memberId, petName, breed, Integer.parseInt(gender), introduction, headshot, Date.valueOf(birthday));
			String url = "/front/contact.jsp";
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
			
		}
		/*************************** 修改寵物基本資訊 ****************************************/
		if ("update".equals(action)) {
			// 修改相片
		}
		/*************************** 隱藏寵物基本資訊 ****************************************/
		if ("change".equals(action)) {
			
		}
    
    }

}
