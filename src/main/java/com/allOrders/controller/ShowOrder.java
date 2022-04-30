package com.allOrders.controller;

import static com.util.GSONUtil.writePojo2Json;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group_buyer.model.GroupBuyerService;
import com.group_buyer.model.GroupBuyerVO;
import com.orders.model.OrdersService;
import com.orders.model.OrdersVO;

@WebServlet("/member/showOrder.do")
public class ShowOrder extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		//要再改
		if (true) {
		Integer memberId = Integer.parseInt(req.getParameter("memberId"));
		GroupBuyerService groupBuyerService=new GroupBuyerService();
		List<GroupBuyerVO> getOneGroupOrder=groupBuyerService.getAllByMemberId(memberId);
		req.setAttribute("getOneGroupOrder", getOneGroupOrder);
		
		OrdersService ordersService=new OrdersService();
		List<OrdersVO> getOnesOrder=ordersService.getAllByMemberId(memberId);
		req.setAttribute("getOnesOrder", getOnesOrder);
		String url = "/front/order/myAccount.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);
		}
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doGet(req, res);
	}
}
