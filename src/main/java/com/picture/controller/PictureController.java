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

@WebServlet("/uploadFromAlbum")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 10 * 1024 * 1024, maxRequestSize = 10 * 10 * 1024
		* 1024)

public class PictureController extends HttpServlet {
	PictureService pictureService = new PictureService();

	/**
	 * 
	 */
	private static final long serialVersionUID = -6376892214189069235L;

	public PrintWriter getPrintWriter(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("UTF-8"); // 處理中文檔名
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		return out;
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = getPrintWriter(req, res);
		Integer albumId = Integer.parseInt(req.getParameter("albumId"));
		Collection<Part> parts = req.getParts();

		// Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理
		List<PictureVO> pics = pictureService.uploadImage(parts, albumId);
		out.print(pics);
	}

}
