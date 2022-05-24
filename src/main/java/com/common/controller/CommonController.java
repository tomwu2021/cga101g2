package com.common.controller;

import javax.persistence.metamodel.SetAttribute;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.members.model.MembersService;
import com.members.model.MembersVO;

import java.io.IOException;
import java.util.List;

@WebServlet("/common")
public class CommonController extends HttpServlet {
	// 呼叫 service
	MembersService memberSvc = new MembersService();
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

		String action = req.getParameter("action");
		
		switch (action) {
		case "aboutUs":
			routeTo(req, res, "關於我們", "aboutUs");
			break;
		case "get1":
			req.setAttribute("url","https://cga101-02.s3.ap-northeast-1.amazonaws.com/thumbs/introduction/%E8%AC%9D%E9%96%94%E7%9A%93.mp4");
			routeTo(req, res, "謝閔皓-專題成果影片", "introduction");
			break;
		case "get2":
			req.setAttribute("url","https://cga101-02.s3.ap-northeast-1.amazonaws.com/thumbs/introduction/%E6%9D%8E%E9%9F%8B%E8%93%81.mp4");
			routeTo(req, res, "李葦蓁-專題成果影片", "introduction");
			break;
		case "get3":
			req.setAttribute("url","https://cga101-02.s3.ap-northeast-1.amazonaws.com/thumbs/introduction/3.%E9%9B%A8%E6%AC%A3(%E5%8A%A0%E9%80%9F2)_.mp4");
			routeTo(req, res, "郭雨欣-專題成果影片", "introduction");
			break;
		case "get4":
			req.setAttribute("url","https://cga101-02.s3.ap-northeast-1.amazonaws.com/thumbs/introduction/%E5%BC%B5%E7%BF%94%E9%9B%B2.mp4");
			routeTo(req, res, "張翔雲-專題成果影片", "introduction");
			break;
		case "get5":
			req.setAttribute("url","https://cga101-02.s3.ap-northeast-1.amazonaws.com/thumbs/introduction/%E5%8A%89%E9%9D%9C%E5%AE%9C.mp4");
			routeTo(req, res, "劉靜宜-專題成果影片", "introduction");
			break;
		case "get6":
			req.setAttribute("url","https://cga101-02.s3.ap-northeast-1.amazonaws.com/thumbs/introduction/cga101g2-%E5%90%B3%E8%87%B4%E9%99%9E.mp4");
			routeTo(req, res, "吳致陞-專題成果影片", "introduction");
			break;
		default:

			MembersVO membersVO = memberSvc.selectForLogin("servlet@pet.com","!QAZ2wsx");
			HttpSession session = req.getSession();
			session.setAttribute("membersVO", membersVO);
			req.setAttribute("membersVO", membersVO);
		}
		if("aboutUs".equals(action)) {
			
		}else {
			
		}
		
		
	}
    /**
     * forward到指定之動態生成JSP頁面
     *
     * @param title 頁面名稱
     * @param page  JSP File Name
     */
    protected void routeTo(HttpServletRequest req, HttpServletResponse res, String title, String page) throws IOException {
        try {
            req.setAttribute("title", title);
            req.setAttribute("pagePath", "/front/" + page + ".jsp"); //指定動態載入JSP
            RequestDispatcher rd = req.getRequestDispatcher("/front/layout/layout.jsp"); //轉導到 layout,jsp
            rd.forward(req, res);
        } catch (ServletException | IOException e) {
            System.err.println(e.getMessage());
            res.sendRedirect(req.getContextPath() + "/index.html");
        }
    }

    /**
     * forward到指定之動態生成JSP頁面
     *
     * @param title 頁面名稱
     * @param page  JSP File Name
     */
    protected void routeToWithoutBreadcrumb(HttpServletRequest req, HttpServletResponse res, String title, String page) throws IOException {
        try {
            req.setAttribute("title", title);
            req.setAttribute("pagePath", "/front/" + page + ".jsp"); //指定動態載入JSP
            RequestDispatcher rd = req.getRequestDispatcher("/front/layout/noneBreadcrumbsLayout.jsp"); //轉導到 layout,jsp
            rd.forward(req, res);
        } catch (ServletException | IOException e) {
            System.err.println(e.getMessage());
            res.sendRedirect(req.getContextPath() + "/index.html");
        }
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String title = req.getParameter("title") == null ? "Default" : req.getParameter("title");
//        if ("1".equals(req.getParameter("breadcrumb"))) {
//            routeTo(req, resp, title, req.getParameter("page"));
//        } else {
//            routeToWithoutBreadcrumb(req, resp, title, req.getParameter("page"));
//        }
//    }
    
    protected MembersVO getLoginInfo(HttpServletRequest req, HttpServletResponse resp) {
    	HttpSession session = req.getSession();
		MembersVO membervo = (MembersVO)session.getAttribute("membersVO");
		return membervo;
    }
    
    protected void goToHome(HttpServletRequest req, HttpServletResponse res) throws IOException {
    	res.sendRedirect(req.getContextPath() + "/index.html");
    }

    protected  Integer getIsOwner(HttpServletRequest req, HttpServletResponse res){
        MembersVO membervo = getLoginInfo(req,res);
        if (membervo != null) {
            Integer isOwner = membervo.getMemberId() == Integer.parseInt(req.getParameter("memberId")==null? "-999":req.getParameter("memberId")) ? 1 : 0;
            return isOwner;
        }
        return 0;
    }
}
