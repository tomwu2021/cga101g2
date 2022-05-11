package com.post.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.members.model.MembersService;
import com.members.model.MembersVO;
import com.pet.model.PetVO;
import com.pet.service.PetService;
import com.picture.model.PictureVO;
import com.picture.service.PictureService;
import com.post.model.PostService;
import com.post.model.PostVO;



 
@WebServlet("/PersonPost")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 10 * 1024 * 1024, maxRequestSize = 10 * 10 * 1024 * 1024)
public class PostPersonController extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
	//  列舉client送來的所有請求參數名稱
		  try {
		   String name;
		   Enumeration<?> pNames = req.getParameterNames();
		   while (pNames.hasMoreElements()) {
		    name = (String) pNames.nextElement();
		    System.out.println(name + "=" + req.getParameter(name));
		   }
		  } catch (Exception e) {
		   System.out.println(e.toString());
		  }
		
		/**
		 * 查詢全部個人貼文
		 * 
		 */
		if ("getOne_For_Display".equals(action)) { 

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//			Integer memberId = Integer.valueOf(req.getParameter("memberId").trim());
			Integer memberId =9;	
			
				
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.put("memberId","請輸入會員編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front/post/post.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				Integer memberId = null;
//				try {
//					memberId = Integer.valueOf(str);
//				} catch (Exception e) {
//					errorMsgs.put("memberId","會員編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("front/post/post.jsp");
//					failureView.forward(req, res);
//					return;
//				}
				
				/***************************2.開始查詢資料*****************************************/
				PostService ps = new PostService();
				List<PostVO> personList = ps.selectPost(memberId);
				
				MembersService mService = new MembersService();
				MembersVO membersVO = mService.getOneById(memberId);  
				
				PictureService pictureService = new PictureService();
				PetService petService = new PetService();
				
				List<PetVO> petVOlist = petService.getByMemberId(memberId); 
				List<String> urList = new ArrayList<String>();     // new empty list
				
				for (PetVO petVO : petVOlist) {
					Integer petid = petVO.getPictureId(); 
					PictureVO pictureVO = pictureService.getOne(petid);
					urList.add(pictureVO.getPreviewUrl());
				}
				for (PostVO postVO : personList) {
					postVO.setMembersVO(membersVO);
					postVO.setUrlList(urList);
				}
				
			
				
				if (personList == null) {
					errorMsgs.put("memberId","查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("front/post/post.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("personList", personList); 
				String url = "/front/post/personPost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

		}
		

	}

}
