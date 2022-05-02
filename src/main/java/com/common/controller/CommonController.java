package com.common.controller;

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

@WebServlet("/common")
public class CommonController extends HttpServlet {
	// 呼叫 service
	MembersService memberSvc = new MembersService();
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

		MembersVO membersVO = memberSvc.selectForLogin("servlet@pet.com","!QAZ2wsx");
		HttpSession session = req.getSession();
		session.setAttribute("membersVO", membersVO);
		req.setAttribute("membersVO", membersVO);
		
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
    
    protected MembersVO getMemberInfo(HttpServletRequest req, HttpServletResponse resp) {
    	HttpSession session = req.getSession();
		MembersVO membervo = (MembersVO)session.getAttribute("membersVO");
		return membervo;
    }
    
    protected void goToHome(HttpServletRequest req, HttpServletResponse res) throws IOException {
    	res.sendRedirect(req.getContextPath() + "/index.html");
    }
}
