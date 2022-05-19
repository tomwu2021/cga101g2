package com.allOrders.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.model.PageQuery;
import com.common.model.PageResult;
import com.google.gson.Gson;
import com.group_buyer.model.GroupBuyerService;
import com.group_buyer.model.GroupBuyerVO;
import com.group_order.model.GroupOrderService;
import com.group_order.model.GroupOrderVO;
import com.members.model.MembersVO;
import com.orders.model.OrdersService;
import com.orders.model.OrdersVO;

@WebServlet("/AllOrders")
public class SelectAllOrder extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("orderList".equals(action)) {
			PrintWriter out = res.getWriter();
			OrdersService ordersService = new OrdersService();
			List<OrdersVO> list = ordersService.getAll();
			Gson gson = new Gson();
			out.write(gson.toJson(list));
		}
		if ("groupOrderList".equals(action)) {
			PrintWriter out = res.getWriter();
			GroupOrderService groupOrderService = new GroupOrderService();
			List<GroupOrderVO> list = groupOrderService.getAll();
			Gson gson = new Gson();
			out.write(gson.toJson(list));
		}
		if ("updateOrder".equals(action)) {
			Integer orderId = Integer.parseInt(req.getParameter("orderId"));
			Integer status = Integer.parseInt(req.getParameter("status"));
			OrdersService ordersService = new OrdersService();
			ordersService.updateStatusByOrderId(orderId, status);
			PrintWriter out = res.getWriter();
			List<OrdersVO> list = ordersService.getAll();
			System.out.println(list);
			Gson gson = new Gson();
			out.write(gson.toJson(list));
			
		}
		if ("GroupOredrDetail".equals(action)) {
			Integer orderId = Integer.parseInt(req.getParameter("orderId"));
		    GroupBuyerService groupBuyerService=new GroupBuyerService();	
			PrintWriter out = res.getWriter();
			List<GroupBuyerVO> list = groupBuyerService.getAllByGroupOrderId(orderId);
			System.out.println(list);
			Gson gson = new Gson();
			out.write(gson.toJson(list));
			
		}

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}