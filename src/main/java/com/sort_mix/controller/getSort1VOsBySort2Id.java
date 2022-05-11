package com.sort_mix.controller;

import static com.util.GSONUtil.writePojo2Json;
import static com.util.GSONUtil.json2Pojo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sort2.model.Sort2VO;
import com.sort_mix.model.SortMixService;
@WebServlet("/shop/getSort1VOsBySort2Id")
public class getSort1VOsBySort2Id extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doGet(req, res);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Integer sort2Id = Integer.valueOf(req.getParameter("sort2Id"));
		SortMixService sortMixService = new SortMixService();
		writePojo2Json(res,sortMixService.getSort1VOsBySort2Id(sort2Id));
		
//		req.setCharacterEncoding("UTF-8");
//		Sort2VO sort2VO = json2Pojo(req, Sort2VO.class);
//		SortMixService sortMixService = new SortMixService();
//		writePojo2Json(res,sortMixService.getSort1VOsBySort2Id(sort2VO.getSort2Id()));
	}
}
