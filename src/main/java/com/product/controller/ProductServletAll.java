package com.product.controller;

import static com.util.GSONUtil.writePojo2Json;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductService;
import com.product.model.ProductVO;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/shop/ProductAllServlet")
public class ProductServletAll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
//		req.setCharacterEncoding("UTF-8");
		ProductService productService = new ProductService();
	    List<ProductVO> list = productService.getAll();
	    writePojo2Json(res,productService.getAll());
	}

}
