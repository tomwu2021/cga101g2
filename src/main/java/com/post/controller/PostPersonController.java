package com.post.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.hql.spi.id.cte.CteValuesListUpdateHandlerImpl;

import com.common.controller.CommonController;
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
public class PostPersonController extends CommonController {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		try {
			doPost(req, res);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		  
		  		
//		  Integer memberId = Integer.valueOf(req.getParameter("memberId").trim());
//			int isOwner= super.getIsOwner(req, res);
//			req.setAttribute("isOwner", isOwner);
//			req.setAttribute("memberId",memberId);
//			System.out.println(isOwner);
		  
			
		/**
		 * 查詢全部個人貼文
		 * 
		 */
		if ("getOne_For_Display".equals(action)) { 

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			
			
			/**
			*修改  
		    */
			  Integer memberId = Integer.valueOf(req.getParameter("memberId").trim());   //memberIdNotMe
			    //這一行是對方的個人頁面的id
			    Integer isOwner = null;   //memberIdIsMe
			    HttpSession session = req.getSession();
			    if((MembersVO)session.getAttribute("membersVO")!=null) {
			    MembersVO memberVO = (MembersVO)session.getAttribute("membersVO");
			    isOwner = memberVO.getMemberId();			    
			    }else {
			    //錯誤訊息,叫他去登入
				   errorMsgs.put("msg", "1");
			    }
			    req.setAttribute("memberId",memberId);   //memberIdNotMe
			    req.setAttribute("isOwner", isOwner); 	//memberIdIsMe
						  
				
				/***************************2.開始查詢資料*****************************************/
				PostService ps = new PostService();
				List<PostVO> personList = ps.selectPost(memberId);
				
				
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
				
				String url = "/front/post/personPost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

		}
		

	}

}
