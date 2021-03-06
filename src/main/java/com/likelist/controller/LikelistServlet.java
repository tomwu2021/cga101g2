package com.likelist.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.likelist.model.LikelistService;
import com.likelist.model.LikelistVO;
import com.members.model.MembersVO;
import com.post.model.PostService;

@WebServlet("/post/likelistServlet")
public class LikelistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		// 訊息用 Map 存放
		Map<String, String> msg = new LinkedHashMap<String, String>();
		req.setAttribute("msgs", msg);
		
		Integer memberId = null;
		HttpSession session = req.getSession();
		if ((MembersVO) session.getAttribute("membersVO") != null) {
			MembersVO memberVO = (MembersVO) session.getAttribute("membersVO");
			memberId = memberVO.getMemberId();
		} else {
			msg.put("msg", "-2");
			String json = new Gson().toJson(msg);
			res.getWriter().write(json);
//			System.out.println("無會員資訊");
			return;
		}
		
		final Integer postId = Integer.parseInt((String) req.getParameter("postId"));
		final Integer val = Integer.parseInt((String) req.getParameter("val"));
		 //收集參數
//		System.out.println("==============");
//		System.out.println("postId:" + postId);
//		System.out.println("val:" + val); // 0: insert, 1: delete
//		System.out.println("==============");
		
		
		if(val == 0) {
			// 1.查詢該篇貼文的讚數
			PostService postSvc = new PostService();
			int oldlikeCount = postSvc.selectOnePostLikeCount(postId);
			// 2.呼叫LikelistService
			LikelistService likelistSvc = new LikelistService();
			// 3.新增讚
			LikelistVO likelistVO = new LikelistVO();
			likelistVO.setMemberId(memberId);
			likelistVO.setPostId(postId);

			boolean boo = likelistSvc.insertAndBoo(likelistVO, (oldlikeCount+1), postId);
			if (boo == true) {
				//交易邏輯:likelistVO新增成功且postupdate like_count成功
				//功能都正常才可以搜尋該篇貼文的讚數
				int newlikeCount = postSvc.selectOnePostLikeCount(postId);
				msg.put("msg", "1");
				msg.put("newlikeCount", String.valueOf(newlikeCount));
//				System.out.println("postId : 原本的讚數: "+oldlikeCount+"新增後讚數: "+ newlikeCount);
				String json = new Gson().toJson(msg);
				res.getWriter().write(json);
//				System.out.println("新增貼文按讚成功");
				return;
			} else {
				msg.put("msg", "-1");
				String json = new Gson().toJson(msg);
				res.getWriter().write(json);
//				System.out.println("新增貼文按讚失敗");
				return;
			}
		} else {
			// 1.查詢該篇貼文的讚數
			PostService postSvc = new PostService();
			int oldlikeCount = postSvc.selectOnePostLikeCount(postId);
			// 2.呼叫LikelistService
			LikelistService likelistSvc = new LikelistService();
			// 3.收回讚
			LikelistVO likelistVO = new LikelistVO();
			likelistVO.setMemberId(memberId);
			likelistVO.setPostId(postId);
			
			boolean boo = likelistSvc.delete(likelistVO, (oldlikeCount-1), postId);
			if (boo == true) {
				//交易邏輯:likelistVO刪除成功且postupdate like_count成功
				//功能都正常才可以搜尋該篇貼文的讚數
				int newlikeCount = postSvc.selectOnePostLikeCount(postId);
				msg.put("msg", "2");
				msg.put("newlikeCount", String.valueOf(newlikeCount));
//				System.out.println("postId : 原本的讚數: "+oldlikeCount+"收回後讚數: "+ newlikeCount);
				String json = new Gson().toJson(msg);
				res.getWriter().write(json);
//				System.out.println("收回貼文按讚成功");
				return;
			} else {
				msg.put("msg", "-1");
				String json = new Gson().toJson(msg);
				res.getWriter().write(json);
//				System.out.println("收回貼文按讚失敗");
				return;
			}
		}
		
	}

}