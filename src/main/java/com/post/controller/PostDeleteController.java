package com.post.controller;

import static com.util.GSONUtil.json2Pojo;

import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.post.model.PostService;
import com.post.model.PostVO;



 
@WebServlet("/deletePost")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 10 * 1024 * 1024, maxRequestSize = 10 * 10 * 1024 * 1024)
public class PostDeleteController extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("PostDeleteController 執行成功");
		
		/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************************/ 
//		Integer postId = Integer.valueOf(req.getParameter("postId").trim());	
		final Integer postId = json2Pojo(req, PostVO.class).getPostId();
		System.out.println(postId);
		
		
		
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
         *修改貼文內容
         * 
         */
        	Map<String,String> msg = new LinkedHashMap<String,String>();
			req.setAttribute("msg", msg);
			

			/***************************2.開始查詢資料***************************************/
			// 1.呼叫postService
			PostService postService = new PostService();
 
			// 2.刪除貼文
			
			boolean boo;
			boo = postService.updatedelete(postId);
			
						
			if ( boo == true) {
				msg.put("msg", "1");
				String json = new Gson().toJson(msg);
				res.getWriter().write(json);
				System.out.println("貼文刪除成功");
				return;
			} else {
				msg.put("msg", "-1");
				String json = new Gson().toJson(msg);
				res.getWriter().write(json);
				System.out.println("貼文刪除失敗");
				return;
			}
						    
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
		
			
        }

}
