package com.post.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.post.model.PostService;
import com.post.model.PostVO;



 
@WebServlet("/detailPost")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 10 * 1024 * 1024, maxRequestSize = 10 * 10 * 1024 * 1024)
public class PostDetailController extends HttpServlet {
	
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
         *查看單篇詳細貼文
         * 
         */
		
        if("selectdetail".equals(action)) {
        	Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************************/ 
			Integer postId = Integer.valueOf(req.getParameter("postId").trim());
//			Integer postId =41;
						
			/***************************2.開始查詢資料***************************************/
			PostService postService = new PostService();
			
			PostVO postVO = postService.getOneById(postId);      //getOneById(postId)會回傳整個查詢詳細貼文資料
							    
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("postVO", postVO);
				 			
			String url = "/front/post/blog_details.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);			
			
        }

	}

}
