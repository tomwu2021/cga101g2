package com.album.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.album.model.AlbumJDBCDAO;
import com.album.model.AlbumVO;
import com.album.service.AlbumService;
import com.common.controller.CommonController;
import com.common.model.PageQuery;
import com.common.model.PageResult;
import com.google.gson.Gson;
import com.members.model.MembersVO;
import com.picture.model.PictureJDBCDAO;
import com.picture.service.PictureService;

@WebServlet("/album")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 10 * 1024 * 1024, maxRequestSize = 10 * 10 * 1024
		* 1024)
public class AlbumController extends CommonController {

	AlbumService alServ = new AlbumService();
	Gson gson = new Gson();
	Integer albumId = null;
	MembersVO membervo;
	int isOwner = 0;
//	int isFriend = 0;
//	int isBlock = 0;
//	int hasRight = 1;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			doPost(req, res);
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	<a id=${album.albumId} class="primary_img" href="${getContextPath()}/PictureController?fileName=&albumId=${album.albumId}&thisPage=1&order=upload_time&pageSize=12&sort=ASC&uploadTime=7&action=search">
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		membervo = super.getLoginInfo(req, res);
//		res.setContentType("text/html; charset=UTF-8");
		System.out.println(req.getAttribute("memberId"));
		if (membervo != null) {
//			if(isBlock ==1) {
//				res.sendRedirect(getServletInfo()+"/index.html");
//			}
			isOwner = membervo.getMemberId() == Integer.parseInt(req.getParameter("memberId") == null ? "-999" : req.getParameter("memberId")) ? 1 : 0;
		}
//		if(isOwner!=1) {
//			isFriend = 1;
//		}
//		if(isFriend!=1) {
//			isBlock = 1;
//			hasRight = 0;
//		}
		req.setAttribute("isOwner", isOwner);
		String action = req.getParameter("action");
		action = action == null ? "list" : action;
		System.out.println(action);
		switch (action) {
		case "addAlbum":
			addAlbum(req, res);
			break;
		case "deleteAlbum":
			deleteAblum(req, res);
			break;

		case "updateName":
			updateName(req, res);
			break;
		case "updateAuthority":
			updateAuthority(req, res);
			break;
		case "getPersonAlbum":
			res.setContentType("application/json; charset=UTF-8");
			PrintWriter out = res.getWriter();
			Integer memberId = Integer.parseInt(req.getParameter("memberId"));
			out.write(gson.toJson(getPersonAlbum(req, res, memberId)));
			break;
		case "changeCover":
			changeCover(req, res);
			break;
		case "list":
			list(req, res);
			break;
		default:
			list(req, res);
			break;
		}

	}

	void addAlbum(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		if (membervo != null) {
			Integer memberId = membervo.getMemberId();
			String name = req.getParameter("name");
			req.setAttribute("memberId", memberId);
			Collection<Part> parts = req.getParts();
			AlbumVO albumVO = null;
			if (memberId != null && name != null) {
				albumVO = alServ.addAlbum(memberId, name, 0, parts);
			}
			if (albumVO == null) {
				req.setAttribute("errorMsg", "新增失敗");
			} else {
				req.setAttribute("msg", "新增成功");
			}
		}

		list(req, res);
	}

	boolean deleteAblum(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PrintWriter out = res.getWriter();
		res.setContentType("application/json; charset=UTF-8");
		Integer memberId = Integer.parseInt(req.getParameter("memberId").trim());
		albumId = Integer.parseInt(req.getParameter("albumId"));
		if (memberId != null && albumId != null && isOwner == 1) {
			alServ.deleteAlbum(memberId, albumId);
			String alert = "{\"status\":\"刪除成功\"}";
			out.write(gson.toJson(alert));
			return true;
		}
		return false;
	}

	boolean updateName(HttpServletRequest req, HttpServletResponse res) throws IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("application/json; charset=UTF-8");
		String name = req.getParameter("name").trim();
		albumId = Integer.parseInt(req.getParameter("albumId"));
		if (albumId != null && name != null && isOwner == 1) {
			alServ.updateName(albumId, name);
			String alert = "{\"status\":\"改名成功\"}";
			out.write(gson.toJson(alert));
			return true;
		}
		return false;
	}

	boolean updateAuthority(HttpServletRequest req, HttpServletResponse res) {
		Integer authority = Integer.parseInt(req.getParameter("authority").trim());
		albumId = Integer.parseInt(req.getParameter("albumId"));
		if (albumId != null && authority != null && isOwner == 1) {
			alServ.updateAuthority(albumId, authority);
			return true;
		}
		return false;
	}

	boolean changeCover(HttpServletRequest req, HttpServletResponse res) {
		Integer coverId = Integer.parseInt(req.getParameter("pictureId").trim());
		albumId = Integer.parseInt(req.getParameter("albumId"));
		if (albumId != null && coverId != null && isOwner == 1) {
			alServ.updateCover(albumId, coverId);
			return true;
		}
		return false;
	}

	PageResult<AlbumVO> getPersonAlbum(HttpServletRequest req, HttpServletResponse res, Integer memberId)
			throws IOException {
		Integer thisPage = Integer.parseInt(req.getParameter("thisPage")); // 設置當前頁數
		Integer pageSize = Integer.parseInt(req.getParameter("pageSize")); // 設置每頁顯示筆數
		String sort = req.getParameter("sort"); // 設置排序方式 (升降冪)
		String order = req.getParameter("order"); // 設置排序欄位
		String[] keywords = req.getParameter("fileName").split(" "); // 使用空格切割關鍵字
		long days = Long.parseLong(req.getParameter("uploadTime")); // 取得天數
		Timestamp uploadTime = new Timestamp(System.currentTimeMillis() - days * 24 * 3600 * 1000); // 天數轉換
		Integer loginId;
		if (membervo != null) {
			loginId = membervo.getMemberId();
		} else {
			loginId = -999;// 訪客登入用memberId
		}
//		Map<String, Object> map = new HashMap<>();
//		map.put("1", new Integer(1)); // 添加比對 album_id欄位值比較條件
		PageQuery pq = new PageQuery(thisPage, pageSize, sort, order, null, true);
		pq.setFindByLikeMultiValues("name", keywords); // 設置關鍵字條件
		pq.setFindByAfter("create_time", uploadTime);
		PageResult<AlbumVO> albumPgList = alServ.getPersonalAlbum(memberId, loginId, pq);
		req.setAttribute("albumPgList", albumPgList);
		return albumPgList;
	}

	public void list(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Integer memberId = Integer.parseInt(req.getParameter("memberId").trim());
		if (req.getAttribute("memberId") != null) {
			memberId = (Integer) req.getAttribute("memberId");
		}
		req.setAttribute("memberId", memberId);
		super.routeTo(req, res, "My Albums", "album");
	}

	AlbumVO getOneAlbum(HttpServletRequest req, HttpServletResponse res) {
		albumId = Integer.parseInt(req.getParameter("albumId"));
		return alServ.getAlbumInfo(albumId);
	}

}
