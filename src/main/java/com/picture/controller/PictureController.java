package com.picture.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.album.model.AlbumVO;
import com.album.service.AlbumService;
import com.common.controller.CommonController;
import org.apache.ibatis.javassist.expr.NewArray;
import org.apache.ibatis.session.SqlSession;

import com.common.model.PageQuery;
import com.common.model.PageResult;
import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.google.gson.Gson;
import com.members.model.MembersVO;
import com.picture.mapper.PictureMapper;
import com.picture.model.PictureJDBCDAO;
import com.picture.model.PictureResult;
import com.picture.model.PictureVO;
import com.picture.service.PictureService;
import com.util.TransferTool;

import antlr.CharQueue;
import connection.MyBatisUtil;

@WebServlet("/photos")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 10 * 1024 * 1024, maxRequestSize = 10 * 10 * 1024
		* 1024)

public class PictureController extends CommonController {
	PictureService pictureService = new PictureService();
	AlbumService alServ = new AlbumService();
	Integer albumId = null;
	MembersVO membervo;
	/**
	 *
	 */
	private static final long serialVersionUID = -6376892214189069235L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		membervo = super.getMemberInfo(req, res);
		String action = req.getParameter("action");

		action = action == null ? "" : action;
		switch (action) {
		case "search":
			search(req, res);
			break;
		case "addShow":
			addShow(req, res);
			break;
		case "upload":
			upload(req, res);
			break;
		default:
			list(req, res);
		}
//		pics.forEach(pic -> {
//			out.println("<p>" + pic.getFileName() + "(" + TransferTool.transferSize(pic.getSize()) + ")</p>");
//			out.println("<img src='" + pic.getPreviewUrl() + "' alt='" + pic.getFileName() + "' >");
//			out.println("<br>");
//		});
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		try {
			doPost(req, res);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		List<PictureVO> pics = rpq.getItems();
//		pics.forEach(pic -> {
//			out.println("<p>" + pic.getFileName() + "(" + TransferTool.transferSize(pic.getSize()) + ")</p>");
//			out.print("<img src="+ pic.getUrl() +" style='height:250px', 'width=400px';>");
//			out.print("<p> uploadTime:"+ pic.getCreateTime() +"</p>");
//			out.println("<br>");
//		});
	}

	public void search(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json; charset=UTF-8");
//		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		albumId = Integer.parseInt(req.getParameter("albumId")); // 取得查詢album_id 條件值
		Integer thisPage = Integer.parseInt(req.getParameter("thisPage")); // 設置當前頁數
		Integer pageSize = Integer.parseInt(req.getParameter("pageSize")); // 設置每頁顯示筆數
		String sort = req.getParameter("sort"); // 設置排序方式 (升降冪)
		String order = req.getParameter("order"); // 設置排序欄位

//		String[] pictureIds = (req.getParameter("pictureId")).split(","); //取得picture_id
		String[] keywords = req.getParameter("fileName").split(" "); // 使用空格切割關鍵字
		req.setAttribute("albumId", albumId);
		long days = Long.parseLong(req.getParameter("uploadTime")); // 取得天數
		// long 避免int overflow
		Timestamp uploadTime = new Timestamp(System.currentTimeMillis() - days * 24 * 3600 * 1000); // 天數轉換

		Map<String, Object> map = new HashMap<>(); // 創建多筆指定欄位條件 Map
		map.put("album_id", albumId); // 添加比對 album_id欄位值比較條件

		PageQuery pq = new PageQuery(thisPage, pageSize, sort, order, map); // 創建分頁查訊物件
		pq.setFindByLikeMultiValues("file_name", keywords); // 設置關鍵字條件
//		pq.setFindByEqualMultiValues("p.picture_id", pictureIds); //設值多筆pictureId 條件
		pq.setFindByAfter("upload_time", uploadTime); // 設置時間條件

		// out.print()->位元流->html(跳頁)
		// ajax->JSON物件-不跳頁

		PageResult<PictureResult> rpq = pictureService.getPageResult(pq);
		Gson gson = new Gson();
		out.write(gson.toJson(rpq));
	}

	public void addShow(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		if (membervo != null) {
			albumId = Integer.parseInt(req.getParameter("albumId")); // 取得查詢album_id 條件值
			Integer memberId = Integer.parseInt(req.getParameter("memberId"));
			req.setAttribute("albumId", albumId);
			req.setAttribute("memberId", memberId);
			if (membervo.getMemberId() == memberId) {
				System.out.println("addShow:" + albumId);
				super.routeTo(req, res, "My Photos", "addPicture");
			}
		}else {
			super.goToHome(req, res);
		}
	}

	public void upload(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		Collection<Part> parts = req.getParts();
		if (membervo != null) {
			pictureService.uploadImage(parts, albumId);
			req.setAttribute("msg", "新增成功");
		} else {
			req.setAttribute("errorMsg", "未登入新增失敗");
		}
		list(req, res);
	}

	public void list(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String memberId = req.getParameter("memberId");
		Integer albumId = Integer.parseInt(req.getParameter("albumId"));
		System.out.println("list memberID = " + memberId);
		req.setAttribute("albumVO", alServ.getAlbumInfo(albumId));
		req.setAttribute("memberId", memberId);
		req.setAttribute("albumId", albumId);
		super.routeTo(req, res, "My Photos", "gallery");
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json; charset=UTF-8");
		PrintWriter out = res.getWriter();
		String jsonStr = req.getParameter("picList");
		Gson gson = new Gson();
		List list = gson.fromJson(jsonStr, List.class);
		if (membervo != null) {
			for (Object pic : list) {
				pictureService.deletePicture(((Double) pic).intValue(), membervo.getMemberId());
			}
			out.write("{\"status\":\"操作成功\"}");
		} else {
			out.write("{\"status\":\"未登入\"}");
		}
	}
}
