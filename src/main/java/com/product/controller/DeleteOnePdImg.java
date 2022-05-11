package com.product.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.picture.service.PictureService;
import com.product_img.model.ProductImgService;
import com.product_img.model.ProductImgVO;

@WebServlet("/shop/DeletePdImg")
public class DeleteOnePdImg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

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

		Integer productId = null;
		Integer product_img_id = null;
		try {
			productId = Integer.valueOf(req.getParameter("productId").trim());
			product_img_id = Integer.valueOf(req.getParameter("pictureId").trim());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

//1.刪除ProductImg中間表格		 
		ProductImgService pdImgService = new ProductImgService();
		ProductImgVO productImgVO = new ProductImgVO();
		productImgVO.setProductId(productId);
		productImgVO.setProductImgId(product_img_id);

//2.刪除相片庫
		PictureService pictureService = new PictureService();

		// 宣告一個布林值
		Boolean deletedProductImgVO;
		Boolean deletedPictureId;
		deletedProductImgVO = pdImgService.delete(productImgVO);
		deletedPictureId = pictureService.deletePicture(product_img_id);

		if (deletedProductImgVO == true) {
			res.getWriter().write(1);
		} else {
			res.getWriter().write(-1);
		}
	}

}
