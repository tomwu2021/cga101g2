package com.product_img.controller;

import static com.util.GSONUtil.writePojo2Json;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product_img.model.ProductImgService;
import com.product_img.model.ProductImgVO;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/shop/ProductAndImg")
public class ProductImg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
		ProductImgService pImgService = new ProductImgService();
	    List<ProductImgVO> list =pImgService.getAll();
	    writePojo2Json(res,pImgService.getAll());
	}

}
