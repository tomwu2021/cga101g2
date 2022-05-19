package com.post.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.post.model.PostService;
import com.report.model.ReportService;
import com.report.model.ReportVO;

@WebServlet("/backReport.do")
public class BackReport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("selectAll".equals(action)) {
			ReportService reportService = new ReportService();
			List<ReportVO> list = reportService.getAll();
			PrintWriter out = res.getWriter();
			Gson gson = new Gson();
			out.write(gson.toJson(list));
		}
		if ("updateStatus".equals(action)) {
			ReportService reportService = new ReportService();
			PostService postService=new PostService();
			Integer postId = Integer.parseInt(req.getParameter("postId"));
			Integer status = Integer.parseInt(req.getParameter("status"));
			Integer reportId = Integer.parseInt(req.getParameter("reportId"));			
			//審核中
			if(status==1) {
				reportService.updateStatus(reportId,0);
				postService.updateReport(postId);	
			}else if(status==2){
				reportService.updateStatus(reportId,1);
				postService.updatedelete(postId);			
			}else if(status==0){
				reportService.updateStatus(reportId,1);
				postService.updateNormal(postId);				
			}
			List<ReportVO> list = reportService.getAll();
			PrintWriter out = res.getWriter();
			Gson gson = new Gson();
			out.write(gson.toJson(list));
		}

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
