package com.allOrders.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

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
import com.members.model.MembersVO;

@WebServlet("/AllOrders")
public class SelectAllOrder extends HttpServlet{

	private static final long serialVersionUID = 1L;

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
		//客製條件
		Map<String, Object> map = new HashMap<>(); // 創建多筆指定欄位條件 Map
		map.put("member_id", memberId);
		if(req.getParameter("status") != null) {
			Integer status = Integer.parseInt(req.getParameter("status"));						
			map.put("o.status", status);			
		}

		PageQuery pq = new PageQuery(thisPage, pageSize, sort, order, map); // 創建分頁查訊物件
		
		GroupBuyerService groupBuyerService=new GroupBuyerService();
		PageResult<GroupBuyerVO> rpq = groupBuyerService.getPageResult(pq);
		Gson gson = new Gson();
		out.write(gson.toJson(rpq));


	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doGet(req, res);
	}
}