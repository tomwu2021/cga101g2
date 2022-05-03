package com.post.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
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

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
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
		
		
		/**
		 * 查詢個人頁面
		 * 
		 */
		if ("getOne_For_Display".equals(action)) { // 來自post.jsp的請求

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
					return;//程式中斷
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
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				PostService ps = new PostService();
				List<PostVO> list = ps.selectPost(memberId);
				
				if (list == null) {
					errorMsgs.put("memberId","查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("front/post/post.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list); 
				String url = "front/post/listPost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

		}

		
		/**
		 * 新增貼文圖片跟內容
		 */
		
        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);


				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				Integer memberId = Integer.valueOf(req.getParameter("memberId").trim());
				Integer memberId =9;
				String content = req.getParameter("content");
				
				Collection<Part> parts=req.getParts();
				System.out.println(parts);
				if(parts ==null) {
					errorMsgs.put("part","照片: 至少選一張圖");	
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/post/addPost.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/
				PostService ps = new PostService();
				ps.uploadPost(memberId, content, parts);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front/post/listPost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);					
		}
		
        //更改貼文內容
        
//		if ("updatecontent".equals(action)) { // 來自listPostEmp.jsp的請求
//
//			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//				/***************************1.接收請求參數****************************************/
//				String content = req.getParameter("content").trim();
//				if (content == null || content.trim().length() == 0) {
//					errorMsgs.put("content","修改內容請勿空白");
//				}
//				
//				/***************************2.開始查詢資料****************************************/
//				PostService postSvc = new PostService();
//				PostVO postVO = postSvc.update(postVO, con);
//								
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				String param = "?empno="  +empVO.getEmpno()+
//						       "&ename="  +empVO.getEname()+
//						       "&job="    +empVO.getJob()+
//						       "&hiredate="+empVO.getHiredate()+
//						       "&sal="    +empVO.getSal()+
//						       "&comm="   +empVO.getComm()+
//						       "&deptno=" +empVO.getDeptno();
//				String url = "/emp/update_emp_input.jsp"+param;
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, res);
//				
//		}	
	}

}
