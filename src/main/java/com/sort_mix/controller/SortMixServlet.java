package com.sort_mix.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.product.model.ProductVO;
import com.sort1.model.Sort1Service;
import com.sort1.model.Sort1VO;
import com.sort2.model.Sort2Service;
import com.sort2.model.Sort2VO;
import com.sort_mix.model.SortMixService;
import com.sort_mix.model.SortMixVO;

@WebServlet("/back/shop/sortMix")
public class SortMixServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("SortMixGetServlet 執行成功");
		req.setCharacterEncoding("UTF-8");// client 端向 Servlet 請求的編碼
		res.setCharacterEncoding("UTF-8");// response，設定回應的格式及編碼
		String action = req.getParameter("action");

//		列舉client送來的所有請求參數名稱
		try {
			String name;
			Enumeration<?> pNames = req.getParameterNames();
			while (pNames.hasMoreElements()) {
				name = (String) pNames.nextElement();
				System.out.println(name + "=" + req.getParameter(name));
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		if ("select".equals(action)) { // 來自addEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			long totalSeconds1 = (System.currentTimeMillis());
			// 搜尋所有主分類"們" 放入對應的子分類"們"
			Sort1Service sort1Svc = new Sort1Service();
//			List<Sort1VO> sort1VOListMore= sort1Svc.getAll();

			SortMixService sortMixSvc = new SortMixService();
//						
//		    for (Sort1VO sort1VO: sort1VOListMore) {
//		    	Integer sort1Id = sort1VO.getSort1Id();
//		    	List<Sort2VO> sort2VOList = sortMixSvc.getSort2VOsBySort1Id(sort1Id);
//		    	sort1VO.setSort2VOList(sort2VOList);
//		    }

			Sort2Service sort2Svc = new Sort2Service();
			List<Sort1VO> sort1VOList = sort1Svc.getAll();
			for (Sort1VO sort1VO : sort1VOList) {
				Map<String, String[]> map = new TreeMap<String, String[]>();
				map.put("sort1_id", new String[] { Integer.toString(sort1VO.getSort1Id()) });
				List<ProductVO> productVOList = sortMixSvc.getProductIdByMap(map);
				sort1VO.setProductVOList(productVOList);
			}
			List<Sort2VO> sort2VOList = sort2Svc.getAll();
			for (Sort2VO sort2VO : sort2VOList) {
				Map<String, String[]> map = new TreeMap<String, String[]>();
				map.put("sort2_id", new String[] { Integer.toString(sort2VO.getSort2Id()) });
				List<ProductVO> productVOList = sortMixSvc.getProductIdByMap(map);
				sort2VO.setProductVOList(productVOList);
			}

			List<SortMixVO> sortMixVOList = sortMixSvc.getAllSortMixVO();
			for (SortMixVO sortMixVO : sortMixVOList) {
				Map<String, String[]> map = new TreeMap<String, String[]>();
				map.put("sort1_id", new String[] { Integer.toString(sortMixVO.getSort1Id()) });
				map.put("sort2_id", new String[] { Integer.toString(sortMixVO.getSort2Id()) });
				List<ProductVO> productVOList = sortMixSvc.getProductIdByMap(map);
				sortMixVO.setProductVOList(productVOList);
			}
			long totalSeconds2 = (System.currentTimeMillis());
			System.out.println(totalSeconds2 - totalSeconds1);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//			req.setAttribute("sort1VOListMore", sort1VOListMore);
			req.setAttribute("sort1VOList", sort1VOList);
			req.setAttribute("sort2VOList", sort2VOList);
			req.setAttribute("sortMixVOList", sortMixVOList);
			// **********************0506要轉交給shop2改成轉交給shop***********************//
			RequestDispatcher successView = req.getRequestDispatcher("/back/shop/sortMix.jsp"); // 成功轉交shop.jsp
			successView.forward(req, res);
		}

		if ("deleteSort1VO".equals(action)) { // 來自addEmp.jsp的請求
			/*************************** 1.接收請求參數 ***************************************/
			Integer sort1Id = Integer.valueOf(req.getParameter("sort1Id"));
			/*************************** 2.開始刪除資料 ***************************************/
			SortMixService sMix1Svc = new SortMixService();
			sMix1Svc.deleteBySort1Id(sort1Id);
			Sort1Service sort1Svc = new Sort1Service();
			Sort1VO sort1VO = new Sort1VO();
			sort1VO.setSort1Id(sort1Id);
			sort1Svc.delete(sort1VO);
			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/back/shop/sortMix?action=select";
			System.out.println(url);
			req.setAttribute("msg", "刪除成功");
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("deleteSort2VO".equals(action)) { // 來自addEmp.jsp的請求
			/*************************** 1.接收請求參數 ***************************************/
			Integer sort2Id = Integer.valueOf(req.getParameter("sort2Id"));
			/*************************** 2.開始刪除資料 ***************************************/
			SortMixService sMix1Svc = new SortMixService();
			sMix1Svc.deleteBySort2Id(sort2Id);
			Sort2Service sort2Svc = new Sort2Service();
			Sort2VO sort2VO = new Sort2VO();
			sort2VO.setSort2Id(sort2Id);
			sort2Svc.delete(sort2VO);
			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/back/shop/sortMix?action=select";
			System.out.println(url);
			req.setAttribute("msg", "刪除成功");
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("deleteSortMixVO".equals(action)) {
			/*************************** 1.接收請求參數 ***************************************/
			Integer sort1Id = Integer.valueOf(req.getParameter("sort1Id"));
			Integer sort2Id = Integer.valueOf(req.getParameter("sort2Id"));
			/*************************** 2.開始刪除資料 ***************************************/
			SortMixVO sMixVO = new SortMixVO();
			sMixVO.setSort1Id(sort1Id);
			sMixVO.setSort2Id(sort2Id);
			SortMixService sMix1Svc = new SortMixService();
			sMix1Svc.delete(sMixVO);
			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/back/shop/sortMix?action=select";
			System.out.println(url);
			req.setAttribute("msg", "刪除成功");
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("insertSort1VO".equals(action)) {
			/*************************** 1.接收請求參數 ***************************************/
			String sort1Name = req.getParameter("sort1Name");
			/*************************** 2.開始新增資料 ***************************************/
			Map<String, String> errorMsg = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsg", errorMsg);
			Sort1Service sort1Svc = new Sort1Service();
			Sort1VO sort1VO = new Sort1VO();
			sort1VO = sort1Svc.selectBySort1Name(sort1Name);
			if (sort1VO.getSort1Name() == null) {
				sort1VO.setSort1Name(sort1Name);
				sort1Svc.insert(sort1VO);
			} else {
				errorMsg.put("sort1Name", "主分類名稱重複");
			}
			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/back/shop/sortMix?action=select";
			System.out.println(url);
			req.setAttribute("msg", "新增成功");
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("insertSort2VO".equals(action)) {
			/*************************** 1.接收請求參數 ***************************************/
			String sort2Name = req.getParameter("sort2Name");
			/*************************** 2.開始新增資料 ***************************************/
			Map<String, String> errorMsg = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsg", errorMsg);
			Sort2Service sort2Svc = new Sort2Service();
			Sort2VO sort2VO = new Sort2VO();
			sort2VO = sort2Svc.selectBySort2Name(sort2Name);
			if (sort2VO.getSort2Name() == null) {
				sort2VO.setSort2Name(sort2Name);
				sort2Svc.insert(sort2VO);
			} else {
				errorMsg.put("errorMsg", "子分類名稱重複");
			}
			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/back/shop/sortMix?action=select";
			System.out.println(url);
			req.setAttribute("msg", "新增成功");
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("insertSortMixVO".equals(action)) {
			/*************************** 1.接收請求參數 ***************************************/
			Integer sort1Id = Integer.valueOf(req.getParameter("sort1Id"));
			Integer sort2Id = Integer.valueOf(req.getParameter("sort2Id"));
			/*************************** 2.開始新增資料 ***************************************/
			SortMixService sMix2Svc = new SortMixService();
			SortMixVO sMixVO = new SortMixVO();
			sMixVO.setSort1Id(sort1Id);
			sMixVO.setSort2Id(sort2Id);
			SortMixVO checkMixVO = sMix2Svc.getOneBySortMixVO(sMixVO);
			if (checkMixVO.getSort1Id()== null) {
				sMix2Svc.insert(sMixVO);
				req.setAttribute("msg", "新增成功");
				String url = "/back/shop/sortMix?action=select";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			} else {
				req.setAttribute("errorMsg", "分類組合重複");
				String url = "/back/shop/sortMix?action=select";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			}

		}

	}

}
