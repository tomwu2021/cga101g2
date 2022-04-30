package com.album.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.album.model.AlbumJDBCDAO;
import com.album.model.AlbumVO;
import com.album.service.AlbumService;
import com.common.controller.CommonController;
import com.google.gson.Gson;
import com.picture.model.PictureJDBCDAO;
import com.picture.service.PictureService;

@WebServlet("/album")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 10 * 1024 * 1024, maxRequestSize = 10 * 10 * 1024
		* 1024)
public class AlbumController extends CommonController {

	AlbumService alServ = new AlbumService();
	Gson gson = new Gson();
	Integer albumId = null;

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

//		res.setContentType("text/html; charset=UTF-8");

		String action = req.getParameter("action");
		action = action == null ? "listToAlbum" : action;
		System.out.println(action);
		switch (action) {
// JSON使用out.write(必回)
		case "addAlbum":
			if (addAlbum(req, res)) {
				listToAlbum(req, res);
			}
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
		case "listToAlbum":
			listToAlbum(req, res);
			break;
		case "list":
			list(req, res);
			break;
		default:
			list(req, res);
			break;
		}

	}

	boolean addAlbum(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		Integer memberId = Integer.parseInt(req.getParameter("memberId"));
		String name = req.getParameter("name");
		System.out.println(memberId);
		System.out.println(name);
		req.setAttribute("memberId", memberId);
		Collection<Part> parts = req.getParts();
		if (memberId != null && name != null) {
			alServ.addAlbum(memberId, name, 0, parts);
			return true;
		}
		return false;
	}

	boolean deleteAblum(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PrintWriter out = res.getWriter();
		res.setContentType("application/json; charset=UTF-8");
		Integer memberId = Integer.parseInt(req.getParameter("memberId").trim());
		albumId = Integer.parseInt(req.getParameter("albumId"));
		if (memberId != null && albumId != null) {
			alServ.deleteAlbum(memberId, albumId);
			String alert = "{status:刪除成功}";
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
		if (albumId != null && name != null) {
			alServ.updateName(albumId, name);
			String alert = "{status:改名成功}";
			out.write(gson.toJson(alert));
			return true;
		}
		return false;
	}

	boolean updateAuthority(HttpServletRequest req, HttpServletResponse res) {
		Integer authority = Integer.parseInt(req.getParameter("authority").trim());
		albumId = Integer.parseInt(req.getParameter("albumId"));
		if (albumId != null && authority != null) {
			alServ.updateAuthority(albumId, authority);
			return true;
		}
		return false;
	}

	boolean changeCover(HttpServletRequest req, HttpServletResponse res) {
		Integer coverId = Integer.parseInt(req.getParameter("pictureId").trim());
		albumId = Integer.parseInt(req.getParameter("albumId"));
		if (albumId != null && coverId != null) {
			alServ.updateCover(albumId, coverId);
			return true;
		}
		return false;
	}

	List<AlbumVO> getPersonAlbum(HttpServletRequest req, HttpServletResponse res, Integer memberId) throws IOException {
		System.out.println("getPersonAlbum=" + memberId);
		List<AlbumVO> albumVOList = alServ.getPersonalAlbum(memberId);
		req.setAttribute("albumVOList", albumVOList);
		return albumVOList;
	}

	public void list(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setAttribute("albumVO", getOneAlbum(req, res));
		String memberId = req.getParameter("memberId");
		String albumId = req.getParameter("albumId");
		Integer coverId = Integer.parseInt(req.getParameter("coverId"));
		System.out.println("list memberID = " + memberId);
		req.setAttribute("memberId", memberId);
		req.setAttribute("albumId", albumId);
		req.setAttribute("coverId", coverId);
		super.routeTo(req, res, "My Photos", "gallery");
	}

	public void listToAlbum(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String memberId = req.getParameter("memberId").trim();
		req.setAttribute("memberId", memberId);
		System.out.println("listToAlbum");
		super.routeTo(req, res, "My Albums", "album");
	}

	AlbumVO getOneAlbum(HttpServletRequest req, HttpServletResponse res) {
		albumId = Integer.parseInt(req.getParameter("albumId"));
		return alServ.getAlbumInfo(albumId);
	}

}
