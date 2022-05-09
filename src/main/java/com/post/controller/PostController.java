package com.post.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
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

import org.apache.naming.java.javaURLContextFactory;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.members.model.MembersService;
import com.members.model.MembersVO;
import com.pet.model.PetJDBCDAO;
import com.pet.model.PetVO;
import com.pet.service.PetService;
import com.pet_activity.model.PetActivityJDBCDAO;
import com.picture.model.PictureVO;
import com.picture.service.PictureService;
import com.post.model.PostService;
import com.post.model.PostVO;



 
@WebServlet("/uploadPost")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 10 * 1024 * 1024, maxRequestSize = 10 * 10 * 1024 * 1024)
public class PostController extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		action = "selectChangePost";
		
		/**
		 * 查詢全部個人貼文
		 * 
		 */
		if ("getOne_For_Display".equals(action)) { 

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("memberId");
				
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.put("memberId","請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/post/post.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer memberId = null;
				try {
					memberId = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.put("memberId","會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("front/post/post.jsp");
					failureView.forward(req, res);
					return;
				}
				
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
				
			
				
//				if (personList == null) {
//					errorMsgs.put("memberId","查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("front/post/post.jsp");
//					failureView.forward(req, res);
//					return;
//				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("personList", personList); 
				String url = "/front/post/postNew.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

		}
		
		/**
		 * 查詢status狀態為0的貼文
		 * 
		 */

		if ("selectChangePost".equals(action)) {
			
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************************/ 
//				Integer memberId = Integer.valueOf(req.getParameter("memberId").trim());
				Integer memberId =9;
				
				/***************************2.開始查詢資料***************************************/
				PostService ps = new PostService();
				java.util.Date date1 = new java.util.Date();
				
				List<PostVO> postlist = ps.selectChangePost(memberId);
				
				MembersService mSvcMembersService = new MembersService();
			    MembersVO membersVO = mSvcMembersService.getOneById(memberId);
			    
			    PictureService pictureService = new PictureService();
			    PetService petService = new PetService();
			    
//			    PetJDBCDAO dao = new PetJDBCDAO(); // remember to change!!!!
			    
			    List<PetVO> petVOlist = petService.getByMemberId(memberId);
			    
			    List<String> urlList = new ArrayList<>(); // new empty list
			    
			    for (PetVO petVO: petVOlist) {
			    	Integer pid = petVO.getPictureId();
			    	PictureVO pictureVO = pictureService.getOne(pid);
			    	urlList.add(pictureVO.getPreviewUrl()); // add one url
			    	
			    }
			    for (PostVO postVO: postlist) {
			    	postVO.setMembersVO(membersVO);
			    	postVO.setUrlList(urlList);
			    }
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("postlist", postlist);
					       
				java.util.Date date2 = new java.util.Date();
				System.out.println("elapsed time: " + (date2.getTime() - date1.getTime()));
				
			String url = "/front/post/blog.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}	
		
		/**
		 * 新增貼文圖片與內容
		 */

		
        if ("insert".equals(action)) { 
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);


			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				Integer memberId = Integer.valueOf(req.getParameter("memberId").trim());
				Integer memberId =9;
				String content = req.getParameter("content");
				
				Collection<Part> parts=req.getParts();
				System.out.println(parts);
				if(parts ==null) {
					errorMsgs.put("part","����: �撠銝�撘萄��");	
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/post/postNew.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/
				PostService ps = new PostService();
				ps.uploadPost(memberId, content, parts);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front/post/listPost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);					
		}
		


	}

}
