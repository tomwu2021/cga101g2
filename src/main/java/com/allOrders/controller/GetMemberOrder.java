package com.allOrders.controller;

import static com.util.GSONUtil.writePojo2Json;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.members.model.MembersVO;
import com.orders.model.OrdersService;
import com.orders.model.OrdersVO;

@WebServlet("/member/orders")
public class GetMemberOrder extends HttpServlet{

	private static final long serialVersionUID = 1L;

	//		OrdersService ordersService=new OrdersService();
//		List<OrdersVO> getOnesOrder=ordersService.getAllByMemberId(memberId);
//		writePojo2Json(res, getOnesOrder);


	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("application/json; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession();
		MembersVO membersVO=(MembersVO) session.getAttribute("membersVO");
		Integer memberId = membersVO.getMemberId();

		//分頁條件
		Integer thisPage = Integer.parseInt(req.getParameter("thisPage")); // 設置當前頁數
		Integer pageSize = Integer.parseInt(req.getParameter("pageSize")); // 設置每頁顯示筆數
		String sort = req.getParameter("sort"); // 設置排序方式 (升降冪)
		String order = req.getParameter("order"); // 設置排序欄位
		long days = Long.parseLong(req.getParameter("create_time")); // 取得天數
		// long 避免int overflow
		Timestamp createTime = new Timestamp(System.currentTimeMillis() - days * 24 * 3600 * 1000); // 天數轉換
		//客製條件
		Map<String, Object> map = new HashMap<>(); // 創建多筆指定欄位條件 Map
		map.put("member_id", memberId);
		if(req.getParameter("status") != null) {
			Integer status = Integer.parseInt(req.getParameter("status"));
			map.put("status", status);
		}

		PageQuery pq = new PageQuery(thisPage, pageSize, sort, order, map); // 創建分頁查訊物件
		pq.setFindByAfter("create_time",createTime ); // 設置時間條件
		OrdersService ordersService=new OrdersService();
		PageResult<OrdersVO> rpq = null;
		try {
			rpq = ordersService.getPageResult(pq);
		} catch (JDBCException e) {
			throw new RuntimeException(e);
		}
		Gson gson = new Gson();
		out.write(gson.toJson(rpq));

	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doGet(req, res);
	}
}
