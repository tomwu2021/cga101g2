package com.picture.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.picture.model.PictureVO;
import com.picture.service.PictureService;

public class PictureDeleteController extends HttpServlet {
	PictureService pictureService = new PictureService();
	/**
	 * 
	 */
	private static final long serialVersionUID = -6376892214189069235L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		Integer pictureId =Integer.parseInt(req.getParameter("pictureId"));
		System.out.println(pictureId);
		pictureService.deletePicture(pictureId);
		out.print("<p>成功刪除圖片</p>");
	}
}
