package com.sort2.controller;

import static com.util.GSONUtil.writePojo2Json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sort2.model.Sort2Service;

/**
 * Servlet implementation class getOneSort2VOandSort1VOs
 */
@WebServlet("/front/shop/getSort2VOListAndSort1VOs")
public class getSort2VOListAndSort1VOs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Sort2Service sort2Service = new Sort2Service();
		writePojo2Json(response,sort2Service.findAll());
	}

}
