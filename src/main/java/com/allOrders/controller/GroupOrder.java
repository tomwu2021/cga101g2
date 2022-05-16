package com.allOrders.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.google.gson.Gson;
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
	Gson gson = new Gson();

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		MembersVO membersVO = (MembersVO) session.getAttribute("membersVO");
		String action = req.getParameter("action");
		System.out.println(action);

		// 進入參團頁面
		if ("joinGroupOrder".equals(action)) {
			res.setContentType("text/html;charset=UTF-8");
			System.out.println("joinGroupOrder有進入");
			// 查詢現在有開團的
			GroupOrderService groupOrderService = new GroupOrderService();
			List<GroupOrderVO> groupList = groupOrderService.getAllInProgress();
			req.setAttribute("groupList", groupList);
			String url = "/front/shop/joinGroupsShop.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		// 查詢參團詳情
		if ("toJoinDetial".equals(action)) {
			res.setContentType("text/html;charset=UTF-8");
			System.out.println("toJoinDetial有進入");
			// 查詢參團詳情
			Integer groupOrderId = Integer.valueOf(req.getParameter("groupOrderId").trim());
			System.out.println("getParameter:" + groupOrderId);
			GroupOrderService groupOrderService = new GroupOrderService();
			GroupOrderVO groupOrderVO = groupOrderService.getOneOrder(groupOrderId);
			// 查詢目前份數
			int established = 0;
			GroupBuyerService groupBuyerService = new GroupBuyerService();
			for (GroupBuyerVO groupBuyerVO : groupBuyerService.getAllByGroupOrderId(groupOrderId)) {
				established += groupBuyerVO.getProductAmount();
			}
			System.out.println("總數" + established);
			req.setAttribute("established", established);
			req.setAttribute("groupOrderVO", groupOrderVO);
			String url = "/front/shop/joinProductDetails.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		// 到開團者填資料
		if ("toGroupOrder".equals(action)) {

			// 把商品資訊打包傳到結帳頁面
			System.out.println("進填資料");
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
			String url = "/front/order/groupCheckout.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		// 到參團者填資料
		if ("toJoinGroupOrder".equals(action)) {

			// 把商品資訊打包傳到結帳頁面
			Integer groupOrderId = Integer.valueOf(req.getParameter("groupOrderId").trim());
			System.out.println("getParameter:" + groupOrderId);
			GroupOrderService groupOrderService = new GroupOrderService();
			GroupOrderVO groupOrderVO = groupOrderService.getOneOrder(groupOrderId);
			// 查詢目前份數
			int established = 0;
			GroupBuyerService groupBuyerService = new GroupBuyerService();
			for (GroupBuyerVO groupBuyerVO : groupBuyerService.getAllByGroupOrderId(groupOrderId)) {
				established += groupBuyerVO.getProductAmount();
			}
			System.out.println("總數" + established);
			req.setAttribute("established", established);
			req.setAttribute("groupOrderVO", groupOrderVO);
			String url = "/front/order/joinGroupCheckout.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		// 團主產生訂單結帳
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

			// 檢查錢包金額是否足夠
			if (membersVO.geteWalletAmount() >= (price * amount)) {
				System.out.println(membersVO.geteWalletAmount() + "," + price * amount);
				if (req.getParameter("same") == null) {
					memberName = req.getParameter("memberName");
					memberPhone = req.getParameter("memberPhone");
					address = req.getParameter("county") + req.getParameter("district") + " "
							+ req.getParameter("address");
				} else {
					memberName = membersVO.getName();
					memberPhone = membersVO.getPhone();
					address = membersVO.getAddress();
				}
				// 錢包扣款
				membersService.walletPaymentAddMoney(memberId, -(price * amount));
				// 生成一筆團購訂單
				GroupOrderService groupOrderService = new GroupOrderService();

				int groupOrderId = groupOrderService.addGroupOrder(productId, endType, price, minAmount)
						.getGroupOrderId();

				System.out.println(groupOrderId + "訂單編號");

				GroupBuyerService groupBuyerService = new GroupBuyerService();
				// 將成員加到團購訂單中
				groupBuyerService.addGroupBuyer(groupOrderId, memberId, amount, memberPhone, address, memberName);

				// 如果以數量達標成團了,改團購狀態
				int established = 0;
				for (GroupBuyerVO groupBuyerVO : groupBuyerService.getAllByGroupOrderId(groupOrderId)) {
					established += groupBuyerVO.getProductAmount();
				}
				System.out.println("總數" + established);
				if (established >= minAmount && endType == 2) {
					System.out.println("有執行");
					groupOrderService.updateStatus(groupOrderId, 2);
					groupOrderService.updateEndTime(groupOrderId);
					// 團購折扣退款
					GroupOrderVO groupOrderVO = groupOrderService.getOneOrder(groupOrderId);
					int reimburse1 = (int) (groupOrderVO.getProductVO().getGroupPrice1()
							- (Math.round(groupOrderVO.getProductVO().getGroupPrice1() * 0.8)));
					int reimburse2 = (int) (groupOrderVO.getProductVO().getGroupPrice1()
							- (Math.round(groupOrderVO.getProductVO().getGroupPrice1() * 0.7)));
					if (established >= groupOrderVO.getProductVO().getGroupAmount2()
							&& established < groupOrderVO.getProductVO().getGroupAmount3()) {
						//改團單最終價錢
						groupOrderService.updateFinalPrice(groupOrderId, reimburse1);
						// 錢包退款
						membersService.walletPaymentAddMoney(memberId, reimburse1*amount);
					} else if (established >= groupOrderVO.getProductVO().getGroupAmount3()) {
						//改團單最終價錢
						groupOrderService.updateFinalPrice(groupOrderId, reimburse2);
						// 錢包退款
						membersService.walletPaymentAddMoney(memberId, reimburse2*amount);
					}
				}
				MembersVO membersVO2 = membersService.getOneById(membersVO.getMemberId());

				// 導向查看頁面
				GroupBuyerVO groupBuyerVO = groupBuyerService.selectOrderDetail(groupOrderId, memberId);
				System.out.println(groupBuyerVO.getPictureVO().getPreviewUrl());
				req.setAttribute("groupBuyerVO", groupBuyerVO);
				String msg = "成功開團";
				req.setAttribute("msg", msg);
				String url = "/front/order/showChangeOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} else {
				String errorMsg = "錢包餘額不足，請儲值";
				req.setAttribute("errorMsg", errorMsg);
				String url = "/front/member/memberWallet.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}

		}

		// 參團者結帳
		if ("joinCheckout".equals(action)) {
			System.out.println("進入參團者結帳");
			MembersService membersService = new MembersService();

			Integer memberId = membersVO.getMemberId();
			Integer groupOrderId = Integer.valueOf(req.getParameter("groupOrderId").trim());
			Integer endType = Integer.valueOf(req.getParameter("endType").trim());
			Integer amount = Integer.valueOf(req.getParameter("amount").trim());
			Integer minAmount = Integer.valueOf(req.getParameter("minAmount").trim());
			Integer price = Integer.valueOf(req.getParameter("price").trim());
			String memberName, memberPhone, address;

			if (membersVO.geteWalletAmount() > (price * amount)) {
				if (req.getParameter("same") == null) {
					memberName = req.getParameter("memberName");
					memberPhone = req.getParameter("memberPhone");
					address = req.getParameter("county") + req.getParameter("district") + " "
							+ req.getParameter("address");
				} else {
					memberName = membersVO.getName();
					memberPhone = membersVO.getPhone();
					address = membersVO.getAddress();
				}
				// 錢包扣款
				membersService.walletPaymentAddMoney(memberId, -(price * amount));

				GroupOrderService groupOrderService = new GroupOrderService();
				GroupBuyerService groupBuyerService = new GroupBuyerService();
				// 將成員加到團購訂單中
				groupBuyerService.addGroupBuyer(groupOrderId, memberId, amount, memberPhone, address, memberName);
				// 如果以數量達標成團了,改團購狀態
				int established = 0;
				for (GroupBuyerVO groupBuyerVO : groupBuyerService.getAllByGroupOrderId(groupOrderId)) {
					established += groupBuyerVO.getProductAmount();
				}
				System.out.println("總數" + established);
				if (established > minAmount && endType == 2) {
					System.out.println("有執行");
					groupOrderService.updateStatus(groupOrderId, 2);
					groupOrderService.updateEndTime(groupOrderId);
					// 團購折扣退款
					GroupOrderVO groupOrderVO = groupOrderService.getOneOrder(groupOrderId);
					int reimburse1 = (int) (groupOrderVO.getProductVO().getGroupPrice1()
							- (Math.round(groupOrderVO.getProductVO().getGroupPrice1() * 0.8)));
					int reimburse2 = (int) (groupOrderVO.getProductVO().getGroupPrice1()
							- (Math.round(groupOrderVO.getProductVO().getGroupPrice1() * 0.7)));
					if (established >= groupOrderVO.getProductVO().getGroupAmount2()
							&& established <= groupOrderVO.getProductVO().getGroupAmount3()) {
						//改團單最終價錢
						groupOrderService.updateFinalPrice(groupOrderId, reimburse1);
						// 錢包退款
						membersService.walletPaymentAddMoney(memberId, reimburse1*amount);
					} else if (established >= groupOrderVO.getProductVO().getGroupAmount3()) {
						//改團單最終價錢
						groupOrderService.updateFinalPrice(groupOrderId, reimburse2);
						// 錢包退款
						membersService.walletPaymentAddMoney(memberId, reimburse2*amount);
					}
					
					

				}

				// 導向查看頁面
				GroupBuyerVO groupBuyerVO = groupBuyerService.selectOrderDetail(groupOrderId, memberId);
				System.out.println(groupBuyerVO.getPictureVO().getPreviewUrl());
				req.setAttribute("groupBuyerVO", groupBuyerVO);
				String msg = "成功加入";
				req.setAttribute("msg", msg);
				String url = "/front/order/showChangeOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} else {
				String errorMsg = "錢包餘額不足，請儲值";
				req.setAttribute("errorMsg", errorMsg);
				String url = "/front/member/memberWallet.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
