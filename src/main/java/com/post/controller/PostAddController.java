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



 
@WebServlet("/addPost")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 10 * 1024 * 1024, maxRequestSize = 10 * 10 * 1024 * 1024)
public class PostAddController extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		action = "insert";
		
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
				String url = "/front/post/postNew.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);					
		}
        
        
	}

}
