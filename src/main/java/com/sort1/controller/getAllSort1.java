package com.sort1.controller;

import static com.util.GSONUtil.writePojo2Json;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sort1.model.Sort1Service;

@WebServlet("/front/shop/getAllSort1")
public class getAllSort1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.Controller(控制器)/Servlet(導演)接收到請求 <依照功能劃分>
		 *  基礎防呆控制層 EX:正規化,未輸入等等
		 * 2.叫Service準備好劇本,叫DAO表演 <依照功能劃分> 
		 * 需要與DAO接觸才能執行商業邏輯控制層 EX:帳號重複等等 
		 * 3.使用GSON工具把sort1Service.findAll()的POJO物件換成JSON格式
		 * 此段只要取得公開數據所以不需要流程控制
		 */
		Sort1Service sort1Service = new Sort1Service();
		writePojo2Json(response, sort1Service.getAll());
//		空虛的丟包給JSON輸出就結束了講義P123
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}

}
