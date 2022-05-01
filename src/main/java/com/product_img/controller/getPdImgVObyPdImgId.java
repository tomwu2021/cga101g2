package com.product_img.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product_img.model.ProductImgService;
import com.product_img.model.ProductImgVO;

/**
 * Servlet implementation class getPdImgVObyPdImgId
 */
@WebServlet("/shop/getPdImgVObyproduct_img_id")
public class getPdImgVObyPdImgId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/png");

		Integer product_img_id = Integer.valueOf(req.getParameter("product_img_id"));
		ProductImgService productImgService = new ProductImgService();
		ProductImgVO productImgVO = new ProductImgVO();
		productImgVO = productImgService.getOneById(product_img_id);
		byte[] image = productImgVO.getImage();

		OutputStream output = res.getOutputStream();
		InputStream in = new ByteArrayInputStream(image);
		int len;
		byte[] buf = new byte[1024];
		while ((len = in.read(buf)) != -1) {
			output.write(buf, 0, len);
		}
		output.flush();
		
//如果没有下面两行，可能出现getOutputStream() has already been called for this response的异常
		output.close();

}}
