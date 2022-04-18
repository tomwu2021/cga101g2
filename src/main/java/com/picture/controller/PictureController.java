package com.picture.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.ibatis.javassist.expr.NewArray;
import org.apache.ibatis.session.SqlSession;

import com.common.model.PageQuery;
import com.common.model.PageResult;
import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.google.gson.Gson;
import com.picture.mapper.PictureMapper;
import com.picture.model.PictureJDBCDAO;
import com.picture.model.PictureVO;
import com.picture.service.PictureService;
import com.util.TransferTool;

import connection.MyBatisUtil;

@WebServlet("/uploadFromAlbum")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 10 * 1024 * 1024, maxRequestSize = 10 * 10 * 1024
		* 1024)

public class PictureController extends HttpServlet {
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
		Integer albumId = null;
		if (!"".equals(req.getParameter("albumId"))) {
			albumId = Integer.parseInt(req.getParameter("albumId"));
		}
		Collection<Part> parts = req.getParts();

		List<PictureVO> pics = pictureService.uploadImage(parts, albumId);

		pics.forEach(pic -> {
			out.println("<p>" + pic.getFileName() + "(" + TransferTool.transferSize(pic.getSize()) + ")</p>");
			out.println("<img src='" + pic.getUrl() + "' alt='" + pic.getFileName() + "' >");
			out.println("<br>");
		});
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json; charset=UTF-8");
//		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		Integer thisPage = Integer.parseInt(req.getParameter("thisPage"));
		Integer pageSize = Integer.parseInt(req.getParameter("pageSize"));
		String sort = req.getParameter("sort");
		String order = req.getParameter("order");
		Integer albumId = Integer.parseInt(req.getParameter("albumId"));
		String fileName = req.getParameter("fileName");
		Long days = Long.parseLong(req.getParameter("uploadTime"));
		//long 避免int overflow
		Timestamp uploadTime = new Timestamp(System.currentTimeMillis()-days*24*3600*1000);
		
		Map<String, Object> map = new HashMap<>();
		map.put("album_id", albumId);
		map.put("file_name", fileName);
		PageQuery pq = new PageQuery(thisPage, pageSize, sort, order, map);
		pq.setFindByAfterTime("upload_time", uploadTime);
		PageResult<PictureVO> rpq = pictureService.getPageResult(pq);
		
		Gson gson = new Gson();
		out.print(gson.toJson(rpq));
		//out.print()->位元流->html(跳頁)
		//ajax->JSON物件-不跳頁
		
		List<PictureVO> pics = rpq.getItems();
//		pics.forEach(pic -> {
//			out.println("<p>" + pic.getFileName() + "(" + TransferTool.transferSize(pic.getSize()) + ")</p>");
//			out.print("<img src="+ pic.getUrl() +" style='height:250px', 'width=400px';>");
//			out.print("<p> uploadTime:"+ pic.getCreateTime() +"</p>");
//			out.println("<br>");
//		});
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		Integer pictureId = Integer.parseInt(req.getParameter("pictureId"));
		System.out.println(pictureId);
		pictureService.deletePicture(pictureId);
		out.print("<p>成功刪除圖片</p>");
	}

}
