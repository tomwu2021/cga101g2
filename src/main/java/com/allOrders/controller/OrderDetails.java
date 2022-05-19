package com.allOrders.controller;



import java.io.IOException;
import java.net.MulticastSocket;
import java.util.ArrayList;
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
@WebServlet("/member/order.do")
public class OrderDetails extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		//要再改
		if (true && !"update".equals(action)&& !"groupOrderDeatil".equals(action)&& !"orderDeatil".equals(action)) {
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
			           "&addressDetial=" +groupBuyerVO.getAddress().substring(groupBuyerVO.getAddress().indexOf(" ")+1)+
			           "&address=" +groupBuyerVO.getAddress();
			           

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
			String msg="修改成功";
			req.setAttribute("msg", msg);
			String url = "/front/order/showChangeOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);

			}
		
		if ("groupOrderDeatil".equals(action)) {
			System.out.println("有執行");
			Integer memberId = Integer.valueOf(req.getParameter("memberId").trim());	
			Integer groupOrderId = Integer.valueOf(req.getParameter("groupOrderId").trim());
			GroupBuyerService groupBuyerService=new GroupBuyerService();
			//查詢回傳資料
			GroupBuyerVO groupBuyerVO=groupBuyerService.selectOrderDetail(groupOrderId, memberId);
			System.out.println(groupBuyerVO.getPictureVO().getPreviewUrl());
			req.setAttribute("groupBuyerVO", groupBuyerVO);
			
			String url = "/front/order/showChangeOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);

			}
		if ("orderDeatil".equals(action)) {
			System.out.println("有執行");	
			Integer orderId = Integer.valueOf(req.getParameter("orderId").trim());
			OrdersService ordersService=new OrdersService();
			//先查商品ID，拿到單筆訂單所有商品id
			List<OrdersVO> productList=ordersService.getAllProductInOrder(orderId);
			//再用商品ID找圖片
			List<OrdersVO> productDetail=new ArrayList<OrdersVO>();
			
			for(OrdersVO o:productList) {
				productDetail.add(ordersService.getAllProductPicture(orderId, o.getProductVO().getProductId()));
			}
			System.out.println(productList.get(0).getSumPrice());
			req.setAttribute("orderDetail", productDetail.get(0));
			req.setAttribute("productDetail", productDetail);
			
			String url = "/front/order/orderDetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);

			}
		
		
		if ("BackOrderDeatil".equals(action)) {
			System.out.println("有執行");	
			Integer orderId = Integer.valueOf(req.getParameter("orderId").trim());
			OrdersService ordersService=new OrdersService();
			//先查商品ID，拿到單筆訂單所有商品id
			List<OrdersVO> productList=ordersService.getAllProductInOrder(orderId);
			//再用商品ID找圖片
			List<OrdersVO> productDetail=new ArrayList<OrdersVO>();
			
			for(OrdersVO o:productList) {
				productDetail.add(ordersService.getAllProductPicture(orderId, o.getProductVO().getProductId()));
			}
			System.out.println(productList.get(0).getSumPrice());
			req.setAttribute("orderDetail", productDetail.get(0));
			req.setAttribute("productDetail", productDetail);
			
			String url = "/front/order/backOrderDetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);

			}
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doGet(req, res);
	}
}
