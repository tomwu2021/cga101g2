package com.chatroom.controller;

import com.album.service.AlbumService;
import com.chatroom.model.ChatroomResult;
import com.chatroom.model.ChatroomService;
import com.chatroom.model.ChatroomVO;
import com.common.controller.CommonController;
import com.common.model.ResponseMessageDto;
import com.google.gson.Gson;
import com.members.model.MembersVO;
import com.picture.service.PictureService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/chatroom")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 10 * 1024 * 1024, maxRequestSize = 10 * 10 * 1024
        * 1024)
public class ChatroomController extends CommonController {
    PictureService pictureService = new PictureService();
    ChatroomService chserv = new ChatroomService();
    Integer albumId = null;
    Gson gson = new Gson();
    MembersVO loginInfo;
    Integer memberId;
    int isOwner = 0;
    Integer loginId;

    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {
            doPost(req, res);
        } catch (ServletException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");
        res.setContentType("application/json; charset=UTF-8");
        PrintWriter out = res.getWriter();
        loginInfo = super.getLoginInfo(req, res);
        String action = req.getParameter("action");
        System.out.println("chatroom=" + action);
        System.out.println(loginInfo);
        if (loginInfo == null) {
            out.write(gson.toJson(new ResponseMessageDto(500, "請先登入")));
            return;
        }
        loginId = loginInfo.getMemberId();
        memberId = req.getParameter("memberId") == null ? 0 : Integer.parseInt(req.getParameter("memberId"));
        isOwner = super.getIsOwner(req, res);
        req.setAttribute("isOwner", isOwner);
        action = action == null ? "" : action;
        switch (action) {
            case "makePrivateChatroom":
                makePrivateChatroom(req, res);
                break;
            case "makeGroupChatroom":
                makeGroupChatroom(req, res);
                break;
            case "getPrivateChatroom":
                getPrivateChatroom(req, res);
                break;
            case "searchChatrooms":
                searchChatrooms(req, res);
                break;
            case "updateChatroomName":
                updateChatroomName(req, res);
                break;
            case "joinChatroom":
                joinChatroom(req, res);
                break;
            case "exitChatroom":
                exitChatroom(req, res);
                break;
            default:
                getPersonalChatroom(req, res);
        }
    }

    public void makePrivateChatroom(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        Integer targetId = Integer.parseInt(req.getParameter("targetId"));
        if (isOwner == 1 && targetId != null) {
            String name = req.getParameter("name");
            System.out.println("makechatroom:" + targetId + "!!!!name:" + name);
            ChatroomResult crr = chserv.makePrivateChatroom(memberId, targetId, name);
            System.out.println(crr);
            out.write(gson.toJson(crr));
        }
    }

    public void makeGroupChatroom(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String jsonStr = req.getParameter("inviteIds");
        String name = req.getParameter("name");
        PrintWriter out = res.getWriter();
        String[] targets = jsonStr.replace("[","").replace("]","").split(",");
        List<Integer> targetIdList = new ArrayList<>();
        for(String s: targets){
            targetIdList.add(Integer.parseInt(s));
        }
        if (isOwner == 1) {
            if (targetIdList != null && !"".equals(targetIdList) && name != null && !"".equals(name)) {
                chserv.makeGroupChatroom(targetIdList, name);
                out.write("聊天室建立成功");
            }
        }
    }

    public void getPersonalChatroom(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        if (loginInfo != null) {
            List<ChatroomVO> crvos = chserv.getPersonalChatroom(loginInfo.getMemberId());
            out.write(gson.toJson(crvos));
        } else {
            out.print(gson.toJson(new ResponseMessageDto(200, "無聊天室")));
        }
    }

    public void getPrivateChatroom(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        if (loginInfo != null) {
            List<ChatroomResult> crvos = chserv.getPrivateChatroom(loginInfo.getMemberId());
            out.write(gson.toJson(crvos));
        } else {
            out.print(gson.toJson(new ResponseMessageDto(200, "無聊天室")));
        }
//        out.print(gson.toJson(new ResponseMessageDto(200,"查詢完成")));
    }

    public void searchChatrooms(HttpServletRequest req, HttpServletResponse res) throws IOException {
        if (isOwner == 1) {
            PrintWriter out = res.getWriter();
            String keyword = req.getParameter("keyword");
            List<ChatroomVO> crvos = chserv.searchChatrooms(memberId, keyword);
            out.write(gson.toJson(crvos));
        } else {
            req.setAttribute("errorMsg", "請先登入後再查看");
        }
    }

    public void updateChatroomName(HttpServletRequest req, HttpServletResponse res) throws IOException {
//            List<Integer> targetIdList = new ArrayList<>();
        PrintWriter out = res.getWriter();
        String newName = req.getParameter("newName");
        Integer chatroomId = Integer.parseInt(req.getParameter("chatroomId"));
        System.out.println("newName:" + newName + "|||chatroomId:" + chatroomId);
        chserv.updateChatroomName(chatroomId, newName);
        out.print(gson.toJson(new ResponseMessageDto(200, "更改成功")));

    }

    public void joinChatroom(HttpServletRequest req, HttpServletResponse res) {
        if (isOwner == 1) {
            List<Integer> targetIdList = new ArrayList<>();
            String[] targetIds = req.getParameterValues("inviteIds");
            Integer chatroomId = Integer.parseInt(req.getParameter("chatroomId"));
            for (String tid : targetIds) {
                Integer targetId = Integer.parseInt(tid);
                chserv.joinChatroom(chatroomId, targetId);
            }
        } else {
            req.setAttribute("errorMsg", "請先登入後再加入成員");
        }
    }

    public void exitChatroom(HttpServletRequest req, HttpServletResponse res) {
        if (isOwner == 1) {
            String newName = req.getParameter("chatroom_name");
            Integer chatroomId = Integer.parseInt(req.getParameter("chatroomId"));
            chserv.exitChatroom(chatroomId, memberId);
        } else {
            req.setAttribute("errorMsg", "請先登入後再退出");
        }
    }
}

