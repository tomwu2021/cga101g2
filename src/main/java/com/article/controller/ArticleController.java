package com.article.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.article.model.ArticleVO;
import com.article.service.ArticleService;
import com.common.controller.CommonController;
import com.picture.model.PictureVO;
import com.picture.service.PictureService;

@WebServlet("/article")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 10 * 1024 * 1024, maxRequestSize = 10 * 10 * 1024
* 1024)
public class ArticleController extends CommonController {
	private static final long serialVersionUID = 1L;
	PictureService picSvc = new PictureService();
       
    public ArticleController() {
        super();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) {
    	try {
			doPost(req, res);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String articleId = req.getParameter("articleId");
		String type = req.getParameter("type");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String empNo = req.getParameter("empNo");
		String picIdArray = req.getParameter("picIdArray");
		/*************************** 1.上傳最新消息 ****************************************/
		if ("insert".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			content = req.getParameter("content");
			Part file = req.getPart("file");
			if (file == null || file.getSize()==0) {
				errorMsgs.put("file","(請上傳圖片)");
			}
			if (title == null || title.trim().isEmpty()) {
				errorMsgs.put("title","(請勿空白)");
				errorMsgs.put("file","(請重新上傳圖片)");
			}
			if (content == null || content.trim().isEmpty()) {
				errorMsgs.put("content","(請勿空白)");
				errorMsgs.put("file","(請重新上傳圖片)");
			}
			if (type == null) {
				errorMsgs.put("type","(請選擇類型)");
				errorMsgs.put("file","(請重新上傳圖片)");
			}
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("type", type);
				req.setAttribute("title", title);
				req.setAttribute("content", content);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/article/add.jsp");
				failureView.forward(req, res);
				return;
			}
			ArticleService artiSvc = new ArticleService();
			Collection<Part> parts = req.getParts();
			artiSvc.addArticle(Integer.parseInt(type), title, content, Integer.parseInt(empNo), parts);
			String url = "/article?action=all_Article";
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
		/*************************** 2.單筆資料查看 ****************************************/
		if("one_Display".equals(action)){
			ArticleService artiSvc = new ArticleService();
			System.out.println("one::::+"+articleId);
			ArticleVO artiVO = artiSvc.getOneArticleInfo(Integer.parseInt(articleId));
			List<PictureVO> picList = artiSvc.getOneArticlePic(Integer.parseInt(articleId));
			req.setAttribute("artiVO", artiVO);
			req.setAttribute("picList", picList);
			String url = "/back/article/edit.jsp";
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
		/*************************** 3.刪除最新消息 ****************************************/
		if ("delete".equals(action)) {
			ArticleService artiSvc = new ArticleService();
			artiSvc.deleteArticle( Integer.parseInt(articleId)); 
			String url = "/article?action=all_Article";
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
//		/*************************** 4.修改最新消息 **************************************/
		if ("update".equals(action)) {
			List<Integer> picIds = null;
			ArticleService artiSvc = new ArticleService();
			int delCount = 0;
			if(picIdArray.length()>0) {
				String[] arr = picIdArray.split(",");
				picIds = new ArrayList<Integer>();
				for(String id:arr) {
					picIds.add(Integer.parseInt(id));
					delCount++;
				}
			};
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			content = req.getParameter("content");
			Part file = req.getPart("file");
			String picCount = req.getParameter("picCount");
			if (title == null || title.trim().isEmpty()) {
				errorMsgs.put("title","(標題請勿空白)");
			}
			if (content == null || content.trim().isEmpty()) {
				errorMsgs.put("content","(內容請勿空白)");
			}
			Collection<Part> parts = req.getParts();
			if ((file == null || file.getSize()==0) && Integer.parseInt(picCount)==delCount) {
				errorMsgs.put("file","(圖片不得為空)");
				picIds = null;
				parts = null;
			}
			artiSvc.updateArticle(Integer.parseInt(type), title, content, Integer.parseInt(empNo), Integer.parseInt(articleId), picIds, parts);
			req.setAttribute("articleId", articleId);
			String url = "/article?action=one_Display";// 去找查看
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
//		/*************************** 5.只查全部文章 **************************************/
		if ("all_Article".equals(action)) {
			ArticleService artiSvc = new ArticleService();
			List<ArticleVO> aList = artiSvc.getAllArticle();
			req.setAttribute("aList", aList);
			String url = "/back/article/list.jsp";
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
//		/*************************** 6.全部公告文章+圖片 **************************************/
		if ("all_Display".equals(action)) {
			ArticleService artiSvc = new ArticleService();
			List<ArticleVO> aList = artiSvc.getByArtiType(0);
			req.setAttribute("aList", aList);
			String url = "/front/article.jsp";
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);
		}
		
		

	}
	
}
