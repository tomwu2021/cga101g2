package com.post.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.members.model.MembersVO;
import com.post.model.PostService;
import com.report.model.ReportService;

@WebServlet("/postReport.do")
public class PostReport extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		MembersVO membersVO = (MembersVO) session.getAttribute("membersVO");
		Integer reporterId = membersVO.getMemberId();
		String sPostId = req.getParameter("postId");
		Integer postId=Integer.valueOf(sPostId);
		String reportReason = req.getParameter("reportReason");
		//創建一則檢舉
		ReportService reportService=new ReportService();
		reportService.insert(reporterId, postId, reportReason);
		//將貼文狀態改為隱藏
		PostService postService=new PostService();
		postService.updateReport(postId);
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doGet(req, res);
	}
}

