package com.common.controller;

import com.menu.MenuService;
import com.menu.model.MenuDAO;
import com.menu.model.MenuVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/front")
public class CommonController extends HttpServlet {
    MenuService menuService = new MenuService();

    /**
     * forward到指定之動態生成JSP頁面
     *
     * @param title 頁面名稱
     * @param page  JSP File Name
     */
    protected void routeTo(HttpServletRequest req, HttpServletResponse res, String title, String page) throws IOException {
        try {
            List<Map<String, String>> breadcrumbList = menuService.getBreadcrumbList(req.getContextPath(), page);
            req.setAttribute("title", title);
            req.setAttribute("breadcrumbList", breadcrumbList);
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String title = req.getParameter("title") == null ? "Default" : req.getParameter("title");
        if ("1".equals(req.getParameter("breadcrumb"))) {
            routeTo(req, resp, title, req.getParameter("page"));
        } else {
            routeToWithoutBreadcrumb(req, resp, title, req.getParameter("page"));
        }
    }
}
