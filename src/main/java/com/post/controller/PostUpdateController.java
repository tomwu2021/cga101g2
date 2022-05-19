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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.members.model.MembersVO;
import com.picture.model.PictureVO;
import com.post.model.PostService;
import com.post.model.PostVO;
import com.product_img.model.ProductImgService;



 
@WebServlet("/updatePost")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 10 * 1024 * 1024, maxRequestSize = 10 * 10 * 1024 * 1024)
public class PostUpdateController extends HttpServlet {
	
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
         *修改貼文內容
         * 
         */
		
        if("updatepostcontent".equals(action)) {
        	Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************************/ 			
			Integer postId = Integer.valueOf(req.getParameter("postId").trim());
			
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
		    
		    
		    //***收集照片參數開始***//
		    //要被刪除的照片
			String deleteImg[] = req.getParameterValues("deleteImg");
			if (deleteImg != null) {
				for (int i = 0; i < deleteImg.length; i++) {
					System.out.println("deleteImg陣列" + "[" + i + "] :" + "picture_id : " + deleteImg[i]);
				}
			}
			//要新增的照片			

			Collection<Part> parts = req.getParts(); // Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理

			ArrayList<Part> partsList = new ArrayList<Part>();
			for (Part part : parts) {
				if (part.getName().equals("img")) {
					partsList.add(part);
				}
			}
			
			//至少要有一張照片
			//所以要被刪除的照片總數不可以等於現有的照片總數 >> 刪除照片總數-上傳照片總數不可以等於現有的照片總數
			// 1.如果沒有照片要刪除,就不用進入此判斷(Cannot read the array length because "deleteImg" is null)
			//判斷該陣列是否為空值
//			計算該productId的pictureVOList數量
			PostService postService = new PostService();         
			PostVO postVO = postService.getOneById(postId, isOwner);   
			List<PictureVO> pictureVOList = postVO.getPictureList();	 //讀取原本照片數量						
			if(deleteImg !=null) {
				Integer nowPdimgCounts = pictureVOList.size();
				System.out.println("貼文編號 :"+postVO.getPostId()+"的照片總數 : "+nowPdimgCounts);
				if ((deleteImg.length - partsList.size()) == nowPdimgCounts) {					//deleteImg 要刪除的 partsList要新增的
					errorMsgs.put("img", "不可全部刪除，商品至少要有一張照片");						//nowPdimgCountsg尚未修改前照片總數量
				}
			}
			//***收集照片參數結束***//
		    
						
		    if (!errorMsgs.isEmpty()) {
				req.setAttribute("postVO", postVO); //照片會消失,所以要丟回去
				String url = "/front/post/updatePost.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
			/***************************2.開始查詢資料***************************************/
			
			PostVO postupdateVO = new PostVO();
			
			postupdateVO.setContent(content);
			postupdateVO.setPostId(postId);
			
			postupdateVO = postService.update(postupdateVO, partsList, deleteImg);
		
									    
			/***************************3.更新完成,準備轉交(Send the Success view)************/
				 			
			res.sendRedirect(req.getContextPath()+"/detailPost?memberId="+isOwner+"&postId="+postId+"&action=selectdetail");			
			
        }

	}

}
