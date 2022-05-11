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



 
@WebServlet("/MainPost")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 10 * 1024 * 1024, maxRequestSize = 10 * 10 * 1024 * 1024)
public class PostMainController extends HttpServlet {
	
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
		
	}

}
