package com.pet.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
		Integer memberId = Integer.parseInt(req.getParameter("memberId"));
		Integer petId = Integer.parseInt(req.getParameter("petId"));
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
			RequestDispatcher view =req.getRequestDispatcher("/index");
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
