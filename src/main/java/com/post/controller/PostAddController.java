package com.post.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.common.controller.CommonController;
import com.members.model.MembersVO;
import com.post.model.PostService;



 
@WebServlet("/addPost")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 10 * 1024 * 1024, maxRequestSize = 10 * 10 * 1024 * 1024)
public class PostAddController extends CommonController {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)  {
		try {
			doPost(req, res);
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
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
		 * 新增貼文圖片與內容
		 */

		
        if ("insert".equals(action)) { 
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);


			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
								
//				Integer memberId = super.getLoginInfo(req, res).getMemberId();
			
						 	
			    
			    Integer isOwner = null;   //memberIdIsMe
			    HttpSession session = req.getSession();
			    if((MembersVO)session.getAttribute("membersVO")!=null) {
			    MembersVO memberVO = (MembersVO)session.getAttribute("membersVO");
			    isOwner = memberVO.getMemberId();			    
			    }else {
			    //錯誤訊息,叫他去登入
				   errorMsgs.put("msg", "1");
			    }

			    req.setAttribute("isOwner", isOwner); 	//memberIdIsMe
			
				
				String content = req.getParameter("content");
							
								
				 if (req.getPart("parts") == null) {
					    errorMsgs.put("parts", "至少上傳一張照片");
				 }
				 
				 Collection<Part> parts=req.getParts();
				 
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("false area");
					RequestDispatcher failureView = req
							.getRequestDispatcher("front/post/addPost.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				
				/***************************2.開始新增資料***************************************/
				PostService ps = new PostService();
				ps.uploadPost(isOwner, content, parts);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				
				res.sendRedirect(req.getContextPath()+"/PersonPost?memberId="+isOwner+"&action=getOne_For_Display");
								
		}
        
        
	}

}
