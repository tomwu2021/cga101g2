package com.allOrders.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.group_buyer.model.GroupBuyerService;
import com.group_buyer.model.GroupBuyerVO;
import com.group_order.model.GroupOrderService;
import com.group_order.model.GroupOrderVO;
import com.members.model.MembersService;
import com.members.model.MembersVO;
import com.orders.model.OrdersService;
import com.orders.model.OrdersVO;
import com.picture.model.PictureVO;
import com.product.model.ProductVO;
import com.ranks.model.RanksVO;

@WebServlet("/member/groupOrder.do")
public class GroupOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		MembersVO membersVO = (MembersVO) session.getAttribute("membersVO");
		String action = req.getParameter("action");
		System.out.println(action);
		
		//進入參團頁面
		if ("joinGroupOrder".equals(action)) {

			System.out.println("有進入");
			//查詢現在有開團的
			GroupOrderService groupOrderService=new GroupOrderService();			
			List<GroupOrderVO> groupList=groupOrderService.getAllInProgress();
			req.setAttribute("groupList", groupList);
			String url = "/front/shop/joinGroupsShop.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		//到結帳
		if ("toGroupOrder".equals(action)) {

			// 把商品資訊打包傳到結帳頁面
			ProductVO productVO = new ProductVO();
			GroupOrderVO groupOrderVO = new GroupOrderVO();
			String productName = req.getParameter("productName");
			Integer productId = Integer.valueOf(req.getParameter("productId").trim());
			Integer endType = Integer.valueOf(req.getParameter("endType").trim());
			Integer groupPrice1 = Integer.valueOf(req.getParameter("groupPrice1").trim());
			Integer minAmount = Integer.valueOf(req.getParameter("minAmount").trim());

			groupOrderVO.setEndType(endType);
			groupOrderVO.setMinAmount(minAmount);
			productVO.setProductName(productName);
			productVO.setProductId(productId);
			productVO.setGroupPrice1(groupPrice1);
			req.setAttribute("productVO", productVO);
			req.setAttribute("groupOrderVO", groupOrderVO);
			String url = "/front/order/GroupCheckout.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		// 團主產生訂單
		if ("checkout".equals(action)) {
			System.out.println("進入產生團購單");
			MembersService membersService = new MembersService();

			Integer memberId = membersVO.getMemberId();
			Integer productId = Integer.valueOf(req.getParameter("productId").trim());
			Integer endType = Integer.valueOf(req.getParameter("endType").trim());
			Integer amount = Integer.valueOf(req.getParameter("amount").trim());
			Integer minAmount = Integer.valueOf(req.getParameter("minAmount").trim());
			Integer price = Integer.valueOf(req.getParameter("price").trim());
			String memberName, memberPhone, address;

			if (req.getParameter("same") == null) {
				memberName = req.getParameter("memberName");
				memberPhone = req.getParameter("memberPhone");
				address = req.getParameter("county") + req.getParameter("district") + " " + req.getParameter("address");
			} else {
				memberName = membersVO.getName();
				memberPhone = membersVO.getPhone();
				address = membersVO.getAddress();
			}
			// 錢包&紅利扣款
			membersService.walletPaymentAddMoney(memberId, -(price * amount));
			// 生成一筆團購訂單
			GroupOrderService groupOrderService = new GroupOrderService();
		
			int groupOrderId = groupOrderService.addGroupOrder(productId, endType, price, minAmount).getGroupOrderId();
			
			System.out.println(groupOrderId + "訂單編號");
			
			
			GroupBuyerService groupBuyerService = new GroupBuyerService();
			// 將成員加到團購訂單中
			groupBuyerService.addGroupBuyer(groupOrderId, memberId, amount, memberPhone, address, memberName);
			
			// 如果以數量達標成團了,改團購狀態
			int established = 0;
			for (GroupBuyerVO groupBuyerVO : groupBuyerService.getAllByGroupOrderId(groupOrderId)) {
				established += groupBuyerVO.getProductAmount();
			}
			System.out.println("總數"+established);
			if (established > minAmount && endType == 1) {
				groupOrderService.updateStatus(groupOrderId,2);
				groupOrderService.updateEndTime(groupOrderId);
			}
			
			
			// 導向查看頁面
			GroupBuyerVO groupBuyerVO = groupBuyerService.selectOrderDetail(groupOrderId, memberId);
			System.out.println(groupBuyerVO.getPictureVO().getPreviewUrl());
			req.setAttribute("groupBuyerVO", groupBuyerVO);

			String url = "/front/order/showChangeOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
