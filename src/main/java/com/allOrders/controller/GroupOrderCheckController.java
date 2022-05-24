package com.allOrders.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group_buyer.model.GroupBuyerService;
import com.group_buyer.model.GroupBuyerVO;
import com.group_order.model.GroupOrderService;
import com.group_order.model.GroupOrderVO;
import com.members.model.MembersService;
import com.notification.model.NotificationService;
import com.notification.model.NotificationVO;

public class GroupOrderCheckController extends HttpServlet implements Runnable {
	private static final long serialVersionUID = 1L;
	ScheduledExecutorService service = null;
	GroupBuyerService groupBuyerService = new GroupBuyerService();
	GroupOrderService groupOrderService = new GroupOrderService();
	MembersService membersService = new MembersService();

	@Override
	public void run() {
		// TODO Auto-generated method stub
		new GroupOrderCheckController();
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		service = Executors.newScheduledThreadPool(10);
		long initialDelay = 10;
		long period = 10;
		// 從現在開始10秒鐘之後，每隔10秒鐘執行一次
		service.scheduleAtFixedRate(new GroupOrderCheckController(), initialDelay, period, TimeUnit.SECONDS);

	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		service.shutdown();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public GroupOrderCheckController() {
		super();

		List<GroupOrderVO> checkList = groupOrderService.check();
		for (GroupOrderVO groupOrderVO : checkList) {
			int established = 0;
			// 檢查份數是否達標
			for (GroupBuyerVO groupBuyerVO : groupBuyerService.getAllByGroupOrderId(groupOrderVO.getGroupOrderId())) {
				established += groupBuyerVO.getProductAmount();
			}
			// 依時間結單且達標
			if (groupOrderVO.getEndType() == 1 && established >= groupOrderVO.getProductVO().getGroupAmount1()) {
				int reimburse1 = (int) (groupOrderVO.getProductVO().getGroupPrice1()
						- (Math.round(groupOrderVO.getProductVO().getGroupPrice1() * 0.8)));
				int reimburse2 = (int) (groupOrderVO.getProductVO().getGroupPrice1()
						- (Math.round(groupOrderVO.getProductVO().getGroupPrice1() * 0.7)));

				// 檢查達標哪個級距
				if (established >= groupOrderVO.getProductVO().getGroupAmount2()
						&& established < groupOrderVO.getProductVO().getGroupAmount3()) {
					// 改團單最終價錢
					groupOrderService.updateFinalPrice(groupOrderVO.getGroupOrderId(),
							(int) Math.round(groupOrderVO.getProductVO().getGroupPrice1() * 0.8));
				} else if (established >= groupOrderVO.getProductVO().getGroupAmount3()) {
					// 改團單最終價錢
					groupOrderService.updateFinalPrice(groupOrderVO.getGroupOrderId(),
							(int) Math.round(groupOrderVO.getProductVO().getGroupPrice1() * 0.7));
				}
				// 退會員折扣的金額
				for (GroupBuyerVO groupBuyerVO : groupBuyerService
						.getAllByGroupOrderId(groupOrderVO.getGroupOrderId())) {

//					// 加入一則通知
//					if (true) {
//						System.out.println("有拉");
//						NotificationService notificationSvc = new NotificationService();
//						NotificationVO notificationVO = new NotificationVO();
//						notificationVO.setMemberId(groupBuyerVO.getMemberId());
//						notificationVO.setContext("恭喜您!團購已達標,團購編號:" + groupBuyerVO.getGroupOrderId() + "點擊查看詳情");
//						notificationVO
//								.setUrl(getServletContext().getContextPath() + "/member/groupOrder.do?groupOrderId="
//										+ groupBuyerVO.getGroupOrderId() + "&action=seeMore");
//						notificationSvc.insert(notificationVO);
//						System.out.println(notificationVO);
//					}

					if (established >= groupOrderVO.getProductVO().getGroupAmount2()
							&& established < groupOrderVO.getProductVO().getGroupAmount3()) {
						// 錢包退款
						membersService.walletPaymentAddMoney(groupBuyerVO.getMemberId(),
								reimburse1 * groupBuyerVO.getProductAmount());

						System.out.println("退" + reimburse1 * groupBuyerVO.getProductAmount());
					} else if (established >= groupOrderVO.getProductVO().getGroupAmount3()) {
						// 錢包退款
						membersService.walletPaymentAddMoney(groupBuyerVO.getMemberId(),
								reimburse2 * groupBuyerVO.getProductAmount());
						System.out.println("退" + reimburse2 * groupBuyerVO.getProductAmount());
					}

				}
				// 更改訂單狀態
				groupOrderService.updateStatus(groupOrderVO.getGroupOrderId(), 2);
				System.out.println("============第" + groupOrderVO.getGroupOrderId() + "筆成立============");
			}
			// 依時間結算但未達標
			else if (groupOrderVO.getEndType() == 1 && established < groupOrderVO.getProductVO().getGroupAmount1()) {
				// 改團單最終價錢
				groupOrderService.updateFinalPrice(groupOrderVO.getGroupOrderId(), 0);
				// 錢包退款
				for (GroupBuyerVO groupBuyerVO : groupBuyerService
						.getAllByGroupOrderId(groupOrderVO.getGroupOrderId())) {
					membersService.walletPaymentAddMoney(groupBuyerVO.getMemberId(),
							groupOrderVO.getProductVO().getGroupPrice1() * groupBuyerVO.getProductAmount());

//					// 加入一則通知
//					NotificationService notificationSvc = new NotificationService();
//					NotificationVO notificationVO = new NotificationVO();
//					notificationVO.setMemberId(groupBuyerVO.getMemberId());
//					notificationVO.setContext("很可惜，團購未達標,已退款到錢包,團購編號:" + groupBuyerVO.getGroupOrderId() + "點擊查看詳情");
//					notificationVO.setUrl(getServletContext().getContextPath() + "/member/groupOrder.do?groupOrderId="
//							+ groupBuyerVO.getGroupOrderId() + "&action=seeMore");
//					notificationSvc.insert(notificationVO);
				}
				// 更改訂單狀態
				groupOrderService.updateStatus(groupOrderVO.getGroupOrderId(), 1);
			}
			// 依份數結算但未達標
			else if (groupOrderVO.getEndType() == 2 && established < groupOrderVO.getMinAmount()) {
				// 改團單最終價錢
				groupOrderService.updateFinalPrice(groupOrderVO.getGroupOrderId(), 0);
				// 錢包退款
				for (GroupBuyerVO groupBuyerVO : groupBuyerService
						.getAllByGroupOrderId(groupOrderVO.getGroupOrderId())) {
					membersService.walletPaymentAddMoney(groupBuyerVO.getMemberId(),
							groupOrderVO.getProductVO().getGroupPrice1() * groupBuyerVO.getProductAmount());
					// 加入一則通知
//					NotificationService notificationSvc = new NotificationService();
//					NotificationVO notificationVO = new NotificationVO();
//					notificationVO.setMemberId(groupBuyerVO.getMemberId());
//					notificationVO.setContext("很可惜，團購未達標,已退款到錢包,團購編號:" + groupBuyerVO.getGroupOrderId() + "點擊查看詳情");
//					notificationVO.setUrl(getServletContext().getContextPath() + "/member/groupOrder.do?groupOrderId="
//							+ groupBuyerVO.getGroupOrderId() + "&action=seeMore");
//					notificationSvc.insert(notificationVO);
				}
				// 更改訂單狀態
				groupOrderService.updateStatus(groupOrderVO.getGroupOrderId(), 1);
			}

			// 依份數結算且達標
			else if (groupOrderVO.getEndType() == 2 && established > groupOrderVO.getMinAmount()) {
				int reimburse1 = (int) (groupOrderVO.getProductVO().getGroupPrice1()
						- (Math.round(groupOrderVO.getProductVO().getGroupPrice1() * 0.8)));
				int reimburse2 = (int) (groupOrderVO.getProductVO().getGroupPrice1()
						- (Math.round(groupOrderVO.getProductVO().getGroupPrice1() * 0.7)));

				// 檢查達標哪個級距
				if (established >= groupOrderVO.getProductVO().getGroupAmount2()
						&& established < groupOrderVO.getProductVO().getGroupAmount3()) {
					// 改團單最終價錢
					groupOrderService.updateFinalPrice(groupOrderVO.getGroupOrderId(),
							(int) Math.round(groupOrderVO.getProductVO().getGroupPrice1() * 0.8));
				} else if (established >= groupOrderVO.getProductVO().getGroupAmount3()) {
					// 改團單最終價錢
					groupOrderService.updateFinalPrice(groupOrderVO.getGroupOrderId(),
							(int) Math.round(groupOrderVO.getProductVO().getGroupPrice1() * 0.7));
				}
				// 退會員折扣的金額
				for (GroupBuyerVO groupBuyerVO : groupBuyerService
						.getAllByGroupOrderId(groupOrderVO.getGroupOrderId())) {
					if (established >= groupOrderVO.getProductVO().getGroupAmount2()
							&& established < groupOrderVO.getProductVO().getGroupAmount3()) {
						// 錢包退款
						membersService.walletPaymentAddMoney(groupBuyerVO.getMemberId(),
								reimburse1 * groupBuyerVO.getProductAmount());

						System.out.println("退" + reimburse1 * groupBuyerVO.getProductAmount());
					} else if (established >= groupOrderVO.getProductVO().getGroupAmount3()) {
						// 錢包退款
						membersService.walletPaymentAddMoney(groupBuyerVO.getMemberId(),
								reimburse2 * groupBuyerVO.getProductAmount());
						System.out.println("退" + reimburse2 * groupBuyerVO.getProductAmount());
					}
					// 加入一則通知
//					NotificationService notificationSvc = new NotificationService();
//					NotificationVO notificationVO = new NotificationVO();
//					notificationVO.setMemberId(groupBuyerVO.getMemberId());
//					notificationVO.setContext("恭喜您!團購已達標,團購編號:" + groupBuyerVO.getGroupOrderId() + "點擊查看詳情");
//					notificationVO.setUrl(getServletContext().getContextPath() + "/member/groupOrder.do?groupOrderId="
//							+ groupBuyerVO.getGroupOrderId() + "&action=seeMore");
//					notificationSvc.insert(notificationVO);
				}
				// 更改訂單狀態
				groupOrderService.updateStatus(groupOrderVO.getGroupOrderId(), 2);
				System.out.println("============第" + groupOrderVO.getGroupOrderId() + "筆成立============");
			}
		}
	}

}
