package com.comment.controller;

import com.chatroom.model.ChatroomService;
import com.comment.model.CommentResult;
import com.comment.model.CommentService;
import com.common.controller.CommonController;
import com.common.model.ResponseMessageDto;
import com.google.gson.Gson;
import com.members.model.MembersVO;
import com.picture.service.PictureService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/comment")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 10 * 1024 * 1024, maxRequestSize = 10 * 10 * 1024
        * 1024)
public class CommentController extends CommonController {
    CommentService cms = new CommentService();
    Gson gson = new Gson();
    int isOwner = 0;
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {
            doPost(req, res);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");
        res.setContentType("application/json; charset=UTF-8");
        PrintWriter out = res.getWriter();
        String action = req.getParameter("action");
        isOwner = super.getIsOwner(req, res);
        req.setAttribute("isOwner", isOwner);
        action = action == null ? "" : action;
        switch (action) {
            case "getReplyByCommentId":
                getReplyByCommentId(req, res);
                break;
            default:
                getCommentByPostId(req, res);
        }
    }
    public void getCommentByPostId(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Integer postId = Integer.parseInt(req.getParameter("postId"));
        PrintWriter out = res.getWriter();
        out.print(gson.toJson(cms.getCommentByPostId(postId)));
    }

    public void getReplyByCommentId(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Integer commentId = Integer.parseInt(req.getParameter("commentId"));
        PrintWriter out = res.getWriter();
        out.print(gson.toJson(cms.getReplyByCommentId(commentId)));
    }
}
