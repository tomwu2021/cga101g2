package com.group_buyer.controller;
import static com.util.GSONUtil.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.group_buyer.model.GroupBuyerService;
import com.group_buyer.model.GroupBuyerVO;

@WebServlet("/member/orders")
public class GroupBuyerController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		Integer memberId = Integer.parseInt(req.getParameter("memberId"));
		GroupBuyerService groupBuyerService=new GroupBuyerService();
		List<GroupBuyerVO> getOneGroupOrder=groupBuyerService.getAllByMemberId(memberId);
		writePojo2Json(res, getOneGroupOrder);
//		 Gson gson = new Gson();
//	     res.setContentType(JSON_MIME_TYPE + "charset=UTF-8");
//	     try (PrintWriter pw = res.getWriter()) {
//				pw.print(gson.toJson(getOneGroupOrder));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doGet(req, res);
	}
}
