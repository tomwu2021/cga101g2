package com.group_buyer.controller;

import static com.util.GSONUtil.writePojo2Json;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group_buyer.model.GroupBuyerService;
import com.group_buyer.model.GroupBuyerVO;
import com.orders.model.OrdersService;
import com.orders.model.OrdersVO;

@WebServlet("/member/orders")
public class GetMemberOrder extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		Integer memberId = Integer.parseInt(req.getParameter("memberId"));
		OrdersService ordersService=new OrdersService();
		List<OrdersVO> getOnesOrder=ordersService.getAllByMemberId(memberId);
		writePojo2Json(res, getOnesOrder);

	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doGet(req, res);
	}
}
