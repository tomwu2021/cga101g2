package com.post.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.members.model.MembersVO;
import com.post.model.PostService;
import com.post.model.PostVO;



 
@WebServlet("/detailPost")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 10 * 1024 * 1024, maxRequestSize = 10 * 10 * 1024 * 1024)
public class PostDetailController extends HttpServlet {
	
	Gson gson = new Gson();
	
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
			
			Integer memberId = Integer.valueOf(req.getParameter("memberId").trim());   //memberIdNotMe
			//這一行是對方的個人頁面的id
			Integer isOwner = null;   //memberIdIsMe
			HttpSession session = req.getSession();
			if((MembersVO)session.getAttribute("membersVO")!=null) {
			MembersVO memberVO = (MembersVO)session.getAttribute("membersVO");
			isOwner = memberVO.getMemberId();			    
			}
			
			req.setAttribute("memberId",memberId);   //memberIdNotMe
			req.setAttribute("isOwner", isOwner); 	//memberIdIsMe
						
			/***************************2.開始查詢資料***************************************/
			PostService postService = new PostService();         
			
			PostVO postVO = postService.getOneById(postId, memberId);      //getOneById(postId)會回傳整個查詢詳細貼文資料
			
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("postVO", postVO);
			
			String url = "/front/post/blog_details.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);			
			
        }
        
        if("selectReport".equals(action)) {
        	Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************************/ 
			Integer postId = Integer.valueOf(req.getParameter("postId").trim());
			
			Integer memberId = Integer.valueOf(req.getParameter("memberId").trim());   //memberIdNotMe
			//這一行是對方的個人頁面的id
			Integer isOwner = null;   //memberIdIsMe
			HttpSession session = req.getSession();
			if((MembersVO)session.getAttribute("membersVO")!=null) {
			MembersVO memberVO = (MembersVO)session.getAttribute("membersVO");
			isOwner = memberVO.getMemberId();			    
			}
			
			req.setAttribute("memberId",memberId);   //memberIdNotMe
			req.setAttribute("isOwner", isOwner); 	//memberIdIsMe
						
			/***************************2.開始查詢資料***************************************/
			PostService postService = new PostService();         
			
			PostVO postVO = postService.getOneByReport(postId, memberId);      //getOneById(postId)會回傳整個查詢詳細貼文資料
			
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("postVO", postVO);
			
			String url = "/front/post/blog_details.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);			
			
        }
        
          
        
        if("changecontent".equals(action)) {
        	Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************************/ 
			Integer postId = Integer.valueOf(req.getParameter("postId").trim());
			
			Integer isOwner = null;   //memberIdIsMe
			HttpSession session = req.getSession();
			if((MembersVO)session.getAttribute("membersVO")!=null) {
			MembersVO memberVO = (MembersVO)session.getAttribute("membersVO");
			isOwner = memberVO.getMemberId();			    
			}
			
			req.setAttribute("isOwner", isOwner); 	//memberIdIsMe
						
			/***************************2.開始查詢資料***************************************/
			PostService postService = new PostService();         
			
			PostVO postVO = postService.getOneById(postId, isOwner);      //getOneById(postId)會回傳整個查詢詳細貼文資料
										    
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("postVO", postVO);
			
		
			String url = "/front/post/updatePost.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);			
			
        }
        
        if("getHotPost".equals(action)){
        	   PrintWriter out = res.getWriter();
        	   res.setContentType("application/json; charset=UTF-8");
        	   PostService postService = new PostService();
        	   List<PostVO> postVOList = postService.selectHotPost();
        	   out.print(gson.toJson(postVOList));
        }

	}

}
