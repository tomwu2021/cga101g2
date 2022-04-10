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
import com.util.TransferTool;

@WebServlet("/uploadFromAlbum")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 10 * 1024 * 1024, maxRequestSize = 10 * 10 * 1024
		* 1024)

public class PictureController extends HttpServlet {
	PictureService pictureService = new PictureService();

	/**
	 * 
	 */
	private static final long serialVersionUID = -6376892214189069235L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		Integer albumId = null;
		if (!"".equals(req.getParameter("albumId"))) {
			albumId = Integer.parseInt(req.getParameter("albumId"));
		}
		Collection<Part> parts = req.getParts();

		List<PictureVO> pics = pictureService.uploadImage(parts, albumId);
		pics.forEach(pic -> {
			out.println("<p>" + pic.getFileName() + "(" + TransferTool.transferSize(pic.getSize()) + ")</p>");
			out.println("<img src='" + pic.getpUrl() + "' alt='" + pic.getFileName() + "' >");
			out.println("<br>");
		});
	}

}
