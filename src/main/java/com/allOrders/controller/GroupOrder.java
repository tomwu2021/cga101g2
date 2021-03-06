package com.allOrders.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.exception.JDBCException;
import com.common.model.PageQuery;
import com.common.model.PageResult;
import com.google.gson.Gson;
import com.group_buyer.model.GroupBuyerService;
import com.group_buyer.model.GroupBuyerVO;
import com.group_order.model.GroupOrderService;
import com.group_order.model.GroupOrderVO;
import com.members.model.MembersService;
import com.members.model.MembersVO;
import com.mysql.cj.x.protobuf.MysqlxNotice.GroupReplicationStateChangedOrBuilder;
import com.notification.model.NotificationService;
import com.notification.model.NotificationVO;
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
		if (action == null || "".equals(action)) {
			action = "list";
		}
		// 進入參團頁面
		if ("joinGroupOrder".equals(action)) {
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

		// 查詢參團詳情
		if ("toJoinDetial".equals(action)) {
			res.setContentType("application/json; charset=UTF-8");
			req.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			// 分頁條件
			Integer thisPage = Integer.parseInt(req.getParameter("thisPage")); // 設置當前頁數
			Integer pageSize = Integer.parseInt(req.getParameter("pageSize")); // 設置每頁顯示筆數
			String sort = req.getParameter("sort"); // 設置排序方式 (升降冪)
			String order = req.getParameter("order"); // 設置排序欄位
			// 客製條件
			Map<String, Object> map = new HashMap<>(); // 創建多筆指定欄位條件 Map
			if (req.getParameter("status") != null && !"".equals(req.getParameter("status"))) {
				Integer status = Integer.parseInt(req.getParameter("status"));
				map.put("o.status", status);
			}
			String[] keywords = req.getParameter("productName").split(" "); // 使用空格切割關鍵字
			PageQuery pq = new PageQuery(thisPage, pageSize, sort, order, map); // 創建分頁查訊物件
			pq.setFindByLikeMultiValues("product_name", keywords);
			GroupOrderService groupOrderService = new GroupOrderService();
			PageResult<GroupOrderVO> rpq = null;
			rpq = groupOrderService.getPageResult(pq);
			Gson gson = new Gson();
			out.write(gson.toJson(rpq));

//			res.setContentType("text/html;charset=UTF-8");
//			System.out.println("toJoinDetial有進入");
//			// 查詢參團詳情
//			Integer groupOrderId = Integer.valueOf(req.getParameter("groupOrderId").trim());
//			System.out.println("getParameter:" + groupOrderId);
//			GroupOrderService groupOrderService = new GroupOrderService();
//			GroupOrderVO groupOrderVO = groupOrderService.getOneOrder(groupOrderId);
//			// 查詢目前份數
//			int established = 0;
//			GroupBuyerService groupBuyerService = new GroupBuyerService();
//			for (GroupBuyerVO groupBuyerVO : groupBuyerService.getAllByGroupOrderId(groupOrderId)) {
//				established += groupBuyerVO.getProductAmount();
//			}
//			System.out.println("總數" + established);
//			req.setAttribute("established", established);
//			req.setAttribute("groupOrderVO", groupOrderVO);
//			String url = "/front/shop/joinProductDetails.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
		}

		// 從訂單參團詳情
		if ("seeMore".equals(action)) {
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
			String url = "/front/shop/viewGroupOrder.jsp";
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
			// 檢查參團者是否已參團
			GroupBuyerService groupBuyerService = new GroupBuyerService();
			GroupBuyerVO groupBuyerVO2 = groupBuyerService.selectOrderDetail(groupOrderId, membersVO.getMemberId());
			if (groupBuyerVO2 != null) {
				GroupBuyerVO groupBuyerVO = groupBuyerService.selectOrderDetail(groupOrderId, membersVO.getMemberId());
				System.out.println(groupBuyerVO.getPictureVO().getPreviewUrl());
				String msg = "您已參加此團購";
				req.setAttribute("msg", msg);
				req.setAttribute("groupBuyerVO", groupBuyerVO);
				String url = "/front/order/showChangeOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} else {
				// 查詢目前份數
				int established = 0;
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

		}

		// 驗證
		if ("CHECK".equals(action)) {
			// 驗證錢包密碼用
			System.out.println("有進密碼驗證");
			String password = req.getParameter("password");
			MembersService membersService = new MembersService();
			String dbPassword = membersService.geteWalletPassword(membersVO.getMemberId());
			if (password.equals(dbPassword)) {
				System.out.println("正確");
				PrintWriter out = res.getWriter();
				out.write("對");
			} else {
				System.out.println("錯誤");
				PrintWriter out = res.getWriter();
				out.write("錯");
			}

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
				// 更新session
				membersVO.seteWalletAmount(membersVO.geteWalletAmount() - (price * amount));
				// 生成一筆團購訂單
				GroupOrderService groupOrderService = new GroupOrderService();

				int groupOrderId = groupOrderService.addGroupOrder(productId, endType, price, minAmount)
						.getGroupOrderId();

				System.out.println(groupOrderId + "訂單編號");

				GroupBuyerService groupBuyerService = new GroupBuyerService();
				// 將成員加到團購訂單中
				groupBuyerService.addGroupBuyer(groupOrderId, memberId, amount, memberPhone, address, memberName);
				
				// 加入一則通知
				  NotificationService notificationSvc = new NotificationService();
				  NotificationVO notificationVO = new NotificationVO();
				  notificationVO.setMemberId(membersVO.getMemberId());
				  notificationVO.setContext("已成功開團,團購編號:"+groupOrderId+"點擊查看詳情");
				  notificationVO.setUrl(getServletContext().getContextPath() +
						  "/member/groupOrder.do?groupOrderId="+groupOrderId+"&action=seeMore");
				  notificationSvc.insert(notificationVO);

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
						// 改團單最終價錢
						groupOrderService.updateFinalPrice(groupOrderId, reimburse1);
						// 錢包退款
						membersService.walletPaymentAddMoney(memberId, reimburse1 * amount);
						// 更新session
						membersVO.seteWalletAmount(membersVO.geteWalletAmount() + reimburse1 * amount);						
						
					} else if (established >= groupOrderVO.getProductVO().getGroupAmount3()) {
						// 改團單最終價錢
						groupOrderService.updateFinalPrice(groupOrderId, reimburse2);
						// 錢包退款
						membersService.walletPaymentAddMoney(memberId, reimburse2 * amount);
						// 更新session
						membersVO.seteWalletAmount(membersVO.geteWalletAmount() + reimburse2 * amount);
					}
					// 加入一則通知
					  notificationVO.setMemberId(membersVO.getMemberId());
					  notificationVO.setContext("恭喜您!團購已達標,團購編號:"+groupOrderId+"點擊查看詳情");
					  notificationVO.setUrl(getServletContext().getContextPath() +
							  "/member/groupOrder.do?groupOrderId="+groupOrderId+"&action=seeMore");
					  notificationSvc.insert(notificationVO);
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
				// 更新session
				membersVO.seteWalletAmount(membersVO.geteWalletAmount() - (price * amount));

				GroupOrderService groupOrderService = new GroupOrderService();
				GroupBuyerService groupBuyerService = new GroupBuyerService();
				// 將成員加到團購訂單中
				groupBuyerService.addGroupBuyer(groupOrderId, memberId, amount, memberPhone, address, memberName);
				// 加入一則通知
				  NotificationService notificationSvc = new NotificationService();
				  NotificationVO notificationVO = new NotificationVO();
				  notificationVO.setMemberId(membersVO.getMemberId());
				  notificationVO.setContext("已成功參團,團購編號:"+groupOrderId+"點擊查看詳情");
				  notificationVO.setUrl(getServletContext().getContextPath() +
						  "/member/groupOrder.do?groupOrderId="+groupOrderId+"&action=seeMore");
				  notificationSvc.insert(notificationVO);
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
						// 改團單最終價錢
						groupOrderService.updateFinalPrice(groupOrderId, reimburse1);
						// 錢包退款
						membersService.walletPaymentAddMoney(memberId, reimburse1 * amount);
						// 更新session
						membersVO.seteWalletAmount(membersVO.geteWalletAmount() + reimburse1 * amount);
					} else if (established >= groupOrderVO.getProductVO().getGroupAmount3()) {
						// 改團單最終價錢
						groupOrderService.updateFinalPrice(groupOrderId, reimburse2);
						// 錢包退款
						membersService.walletPaymentAddMoney(memberId, reimburse2 * amount);
						membersVO.seteWalletAmount(membersVO.geteWalletAmount() + reimburse2 * amount);
					}
					// 加入一則通知
					  notificationVO.setMemberId(membersVO.getMemberId());
					  notificationVO.setContext("恭喜您!團購已達標,團購編號:"+groupOrderId+"點擊查看詳情");
					  notificationVO.setUrl(getServletContext().getContextPath() +
								"/member/groupOrder.do?groupOrderId=" + groupOrderId + "&action=seeMore");
					  notificationSvc.insert(notificationVO);

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
		if ("list".equals(action)) {
			String url = "/front/shop/joinGroupsShop.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
