package com.pet.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.common.controller.CommonController;
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
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1 * 10 * 1024 * 1024, maxRequestSize = 1 * 10 * 1024
* 1024)
public class PetController extends CommonController {
	private static final long serialVersionUID = 1L;
    private PetService pSvc = new PetService();
    private PetActivityService paSvc = new PetActivityService();
    private PetWeightService pwSvc = new PetWeightService();
    private RemindService rSvc = new RemindService();
    
    public PetController() {
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
		Integer memberId = Integer.parseInt(req.getParameter("memberId"));
		Integer petId = pSvc.getByMemberId(memberId).get(0).getPetId();
		Date _birthday = null;
		Integer _gender = null;
		/*************************** ?????????????????????????????? ****************************************/
		if ("profile".equals(action)) {
			List<RemindVO> rList = rSvc.getThreeByMemberId(memberId);
			List<PetVO> pList = pSvc.getByMemberId(memberId);
			List<PetActivityVO> paList = paSvc.getFourByPetId(petId);
			List<PetWeightVO> pwList = pwSvc.getByPetId(petId);
			PetWeightVO recentWgt = pwSvc.getRecentWeight(petId);
			BigDecimal averageWgt = pwSvc.getAverageWeight(petId);
			final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			req.setAttribute("rList", rList);
			req.setAttribute("pVO", pList.get(0));
			req.setAttribute("paList", paList);
			req.setAttribute("pwList", GSON.toJson(pwList));//
			req.setAttribute("recentWgt", recentWgt);
			req.setAttribute("averageWgt", averageWgt);
			req.setAttribute("title", "PCLUB-"+pList.get(0).getPetName()+"???????????????");
			RequestDispatcher view =req.getRequestDispatcher("/front/pet/profile.jsp");
			view.forward(req, res);
		}
		/*************************** ???????????????????????? ****************************************/
		if ("insert".equals(action)) {
			String birthday = req.getParameter("birthday");
			String gender = req.getParameter("gender");
			String introduction = req.getParameter("introduction");
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		// ????????????
			String petName = req.getParameter("petName");
			if (petName == null || petName.trim().isEmpty()) {
				errorMsgs.put("petName"," (????????????)");
            }
			String sort1Id = req.getParameter("sort1Id");
			if (sort1Id == null) {
				errorMsgs.put("sort1Id","(?????????)");
			}
			if (birthday.length()>0) {
				try {
					java.util.Calendar cal = java.util.Calendar.getInstance();
					cal.set(Calendar.HOUR, 0);
					cal.set(Calendar.MINUTE, 0);
			        cal.set(Calendar.SECOND, 0);
			        cal.set(Calendar.MILLISECOND,0);
					Long currentTime = cal.getTime().getTime();
					_birthday = Date.valueOf(birthday.trim());
					if(_birthday.getTime() > currentTime) throw new IllegalArgumentException();
				} catch (IllegalArgumentException e) {
					errorMsgs.put("birthday"," (?????????????????????)");
				}
			}
			// ????????????
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("petName", petName);
				req.setAttribute("sort1Id", sort1Id);
				req.setAttribute("gender", gender);
				req.setAttribute("birthday", birthday);
				req.setAttribute("introduction", introduction);
				RequestDispatcher failView = req
						.getRequestDispatcher("/front/pet/intro/add.jsp");
				failView.forward(req, res);
				return;
			}
			// ????????????
			Integer breed = Integer.parseInt(sort1Id);
			if(gender!=null) {
				_gender = Integer.parseInt(gender);
			}
			Collection<Part> headshot = null;
			if (req.getPart("file") != null && req.getPart("file").getSize()>0) {
			headshot = req.getParts();
			}
			PetService petSvc = new PetService();
			petSvc.addPet(memberId, petName, breed, _gender, introduction, headshot, _birthday);
			String url = "/pet?memberId=" + memberId + "&action=profile";// ?????????
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
			
		}
		/*************************** ???????????????????????? ****************************************/
		if ("goToUpdate".equals(action)) {
			PetService petSvc = new PetService();
			PetVO pVO = petSvc.getByPetId(petId);
			req.setAttribute("petId", petId);
			req.setAttribute("petName", pVO.getPetName());
			req.setAttribute("sort1Id", pVO.getSort1Id());
			req.setAttribute("gender", pVO.getGender());
			req.setAttribute("introduction", pVO.getIntroduction());
			req.setAttribute("pictureUrl", pVO.getPicVO().getUrl());
			req.setAttribute("birthday", pVO.getBirthday());
			req.setAttribute("title", "PCLUB-"+"????????????????????????");
			RequestDispatcher view =req.getRequestDispatcher("/front/pet/intro/edit.jsp");
			view.forward(req, res);
		}
		/*************************** ???????????????????????? ****************************************/
		if ("update".equals(action)) {
			String birthday = req.getParameter("birthday");
			String gender = req.getParameter("gender");
			String introduction = req.getParameter("introduction");
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		// ????????????
			String petName = req.getParameter("petName");
			if (petName == null || petName.trim().isEmpty()) {
				errorMsgs.put("petName"," (????????????)");
            }
			String sort1Id = req.getParameter("sort1Id");
			if (sort1Id == null) {
				errorMsgs.put("sort1Id","(?????????)");
			}
			if (birthday.length()>0) {
				try {
					java.util.Calendar cal = java.util.Calendar.getInstance();
					cal.set(Calendar.HOUR, 0);
					cal.set(Calendar.MINUTE, 0);
			        cal.set(Calendar.SECOND, 0);
			        cal.set(Calendar.MILLISECOND,0);
					Long currentTime = cal.getTime().getTime();
					_birthday = Date.valueOf(birthday.trim());
					if(_birthday.getTime() > currentTime) throw new IllegalArgumentException();
				} catch (IllegalArgumentException e) {
					errorMsgs.put("birthday"," (?????????????????????)");
				}
			}
			// ????????????
			PetService petSvc = new PetService();
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("petName", petName);
				req.setAttribute("sort1Id", sort1Id);
				req.setAttribute("gender", gender);
				req.setAttribute("birthday", birthday);
				req.setAttribute("introduction", introduction);
				req.setAttribute("pictureUrl",petSvc.getByPetId(petId).getPicVO().getUrl());
				RequestDispatcher failView = req
						.getRequestDispatcher("/front/pet/intro/edit.jsp");
				failView.forward(req, res);
				return;
			}
			// ????????????
			Integer breed = Integer.parseInt(sort1Id);
			if(gender!=null) {
				_gender = Integer.parseInt(gender);
			}
			Collection<Part> headshot = null;
			if (req.getPart("file") != null && req.getPart("file").getSize()>0) {
			headshot = req.getParts();
			}
			PetVO petVO = petSvc.updatePet(memberId, petName, breed, _gender, introduction, headshot, _birthday, petId);
			String url = req.getContextPath()+"/pet?memberId=" + memberId + "&action=profile";// ?????????
			res.sendRedirect(url);
		}
		/*************************** ???????????????????????? ****************************************/
		if ("change".equals(action)) {
			
		}
    
    }

}
