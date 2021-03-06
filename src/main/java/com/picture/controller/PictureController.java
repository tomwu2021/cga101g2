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
	int isOwner = 0;
	/**
	 *
	 */
	private static final long serialVersionUID = -6376892214189069235L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		membervo = super.getLoginInfo(req, res);
		String action = req.getParameter("action");
		if (membervo != null) {
			isOwner = membervo.getMemberId() == Integer.parseInt(req.getParameter("memberId")==null? "-999":req.getParameter("memberId")) ? 1 : 0;
		}
		req.setAttribute("isOwner", isOwner);
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
		albumId = Integer.parseInt(req.getParameter("albumId")); // ????????????album_id ?????????
		Integer thisPage = Integer.parseInt(req.getParameter("thisPage")); // ??????????????????
		Integer pageSize = Integer.parseInt(req.getParameter("pageSize")); // ????????????????????????
		String sort = req.getParameter("sort"); // ?????????????????? (?????????)
		String order = req.getParameter("order"); // ??????????????????

//		String[] pictureIds = (req.getParameter("pictureId")).split(","); //??????picture_id
		String[] keywords = req.getParameter("fileName").split(" "); // ???????????????????????????
		req.setAttribute("albumId", albumId);
		long days = Long.parseLong(req.getParameter("uploadTime")); // ????????????
		// long ??????int overflow
		Timestamp uploadTime = new Timestamp(System.currentTimeMillis() - days * 24 * 3600 * 1000); // ????????????

		Map<String, Object> map = new HashMap<>(); // ?????????????????????????????? Map
		map.put("album_id", albumId); // ???????????? album_id?????????????????????

		PageQuery pq = new PageQuery(thisPage, pageSize, sort, order, map); // ????????????????????????
		pq.setFindByLikeMultiValues("file_name", keywords); // ?????????????????????
//		pq.setFindByEqualMultiValues("p.picture_id", pictureIds); //????????????pictureId ??????
		pq.setFindByAfter("upload_time", uploadTime); // ??????????????????

		// out.print()->?????????->html(??????)
		// ajax->JSON??????-?????????

		PageResult<PictureResult> rpq = pictureService.getPageResult(pq);
		Gson gson = new Gson();
		out.write(gson.toJson(rpq));
	}

	public void addShow(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		if (membervo != null) {
			albumId = Integer.parseInt(req.getParameter("albumId")); // ????????????album_id ?????????
			Integer memberId = Integer.parseInt(req.getParameter("memberId"));
			req.setAttribute("albumId", albumId);
			req.setAttribute("memberId", memberId);
			if (super.getIsOwner(req, res)==1) {
				System.out.println("addShow:" + albumId);
				super.routeTo(req, res, "My Photos", "addPicture");
			}
		}else {
			super.goToHome(req, res);
		}
	}

	public void upload(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		Collection<Part> parts = req.getParts();
		albumId = Integer.parseInt(req.getParameter("albumId"));
		if (membervo != null) {
			pictureService.uploadImage(parts, albumId);
			req.setAttribute("msg", "????????????");
		} else {
			req.setAttribute("errorMsg", "?????????????????????");
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
			out.write("{\"status\":\"????????????\"}");
		} else {
			out.write("{\"status\":\"?????????\"}");
		}
	}
}
