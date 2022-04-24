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

import com.common.controller.CommonController;
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

public class PictureController extends CommonController {
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
			out.println("<img src='" + pic.getPreviewUrl() + "' alt='" + pic.getFileName() + "' >");
			out.println("<br>");
		});

		super.routeTo(req, res, "My Photos", "gallery");

	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json; charset=UTF-8");
//		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();

		Integer thisPage = Integer.parseInt(req.getParameter("thisPage")); //設置當前頁數
		Integer pageSize = Integer.parseInt(req.getParameter("pageSize")); //設置每頁顯示筆數
		String sort = req.getParameter("sort"); //設置排序方式 (升降冪)
		String order = req.getParameter("order"); //設置排序欄位


		Integer albumId = Integer.parseInt(req.getParameter("albumId")); //取得查詢album_id 條件值
//		String[] pictureIds = (req.getParameter("pictureId")).split(","); //取得picture_id
		String[] keywords = req.getParameter("fileName").split(" "); //使用空格切割關鍵字

		long days = Long.parseLong(req.getParameter("uploadTime")); //取得天數
		//long 避免int overflow
		Timestamp uploadTime = new Timestamp(System.currentTimeMillis()-days*24*3600*1000); //天數轉換

		Map<String, Object> map = new HashMap<>(); //創建多筆指定欄位條件 Map
		map.put("album_id", albumId); //添加比對 album_id欄位值比較條件

		PageQuery pq = new PageQuery(thisPage, pageSize, sort, order, map); //創建分頁查訊物件
		pq.setFindByLikeMultiValues("file_name", keywords); //設置關鍵字條件
//		pq.setFindByEqualMultiValues("p.picture_id", pictureIds); //設值多筆pictureId 條件
		pq.setFindByAfter("upload_time", uploadTime); //設置時間條件

		PageResult<PictureVO> rpq = pictureService.getPageResult(pq);
		System.out.println("rpq:"+rpq + "\n pq:"+pq);
		Gson gson = new Gson();
		out.print(gson.toJson(rpq));
		//out.print()->位元流->html(跳頁)
		//ajax->JSON物件-不跳頁

//		List<PictureVO> pics = rpq.getItems();
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
