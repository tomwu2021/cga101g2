package com.allOrders.controller;



import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.group_buyer.model.GroupBuyerService;
import com.group_buyer.model.GroupBuyerVO;
@WebServlet("/member/order.do")
public class OrderDetails extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if (true && !"update".equals(action)) {
		Integer memberId = Integer.parseInt(req.getParameter("memberId"));
		Integer groupOederId = Integer.parseInt(req.getParameter("groupOrderId"));
		GroupBuyerVO groupBuyerVO=new GroupBuyerVO();
		GroupBuyerService groupBuyerService=new GroupBuyerService();
//		groupBuyerVO=groupBuyerService.selectOrderDetail(1, 1);
		groupBuyerVO=groupBuyerService.selectOrderDetail(groupOederId,memberId);
		String param = "?recipient="  +groupBuyerVO.getRecipients()+
					   "&groupOrderId=" +groupBuyerVO.getGroupOrderId()+
					   "&memberId=" +groupBuyerVO.getMemberId()+
			           "&phone=" +groupBuyerVO.getPhone()+
			           //從空格後輸輸
			           "&address=" +groupBuyerVO.getAddress().substring(groupBuyerVO.getAddress().indexOf(" ")+1);

		String url = "/front/order/changeOrder.jsp"+param;
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);
		}
		
		if ("update".equals(action)) {
			System.out.println("有執行");
			Integer memberId = Integer.valueOf(req.getParameter("memberId").trim());	
			Integer groupOrderId = Integer.valueOf(req.getParameter("groupOrderId").trim());
			String recipient = req.getParameter("recipient");
			String phone = req.getParameter("phone");
			String address = req.getParameter("county")+req.getParameter("district")+" "+req.getParameter("address");
			System.out.println("memberId:"+memberId+"groupOrderId:"+groupOrderId+"recipient"+recipient+"phone"+phone+"address"+address);
			GroupBuyerService groupBuyerService=new GroupBuyerService();
			//更改資料
			GroupBuyerVO groupBuyerVO=groupBuyerService.updateGroupBuyer(groupOrderId, memberId,phone, address, recipient);
			//查詢回傳資料
			GroupBuyerVO groupBuyerVO2=groupBuyerService.selectOrderDetail(groupOrderId, memberId);	
			req.setAttribute("groupBuyerVO", groupBuyerVO2);
			
			String url = "/front/order/showChangeOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);

			}
		
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doGet(req, res);
	}
}
