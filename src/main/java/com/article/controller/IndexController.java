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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.article.model.ArticleVO;
import com.article.service.ArticleService;
import com.picture.model.PictureVO;
import com.picture.service.PictureService;

@WebServlet("/index")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PictureService picSvc = new PictureService();
       
    public IndexController() {
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		/*************************** 公告list，商城list，每篇第一張圖片 **************************************/
			ArticleService artiSvc = new ArticleService();
			List<ArticleVO> aList0 = artiSvc.getByArtiType(0);
			List<ArticleVO> aList1 = artiSvc.getByArtiType(1);
			req.setAttribute("aList0", aList0);
			req.setAttribute("aList1", aList1);
			String url = "/front/index.jsp";
			RequestDispatcher view =req.getRequestDispatcher(url);
			view.forward(req, res);		

	}

}
