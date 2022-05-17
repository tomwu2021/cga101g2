package com.relationshipcontroller;

import com.common.controller.CommonController;
import com.google.gson.Gson;
import com.members.model.MembersVO;
import com.relationship.model.RelationResult;
import com.relationship.model.RelationshipService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@WebServlet("/relationship")
public class RelationshipController extends CommonController {
    RelationshipService relaServ = new RelationshipService();
    MembersVO membervo;
    Gson gson = new Gson();
    Integer loginId;
    Integer memberId;
    Integer isOwner;

    Integer isFriend;
    Integer isBlock;
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        doPost(req, res);
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException  {
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");
        res.setContentType("application/json; charset=UTF-8");
        membervo = super.getLoginInfo(req, res);
        String action = req.getParameter("action");
        if(membervo!=null) {
            loginId = membervo.getMemberId();
        }
        memberId = Integer.parseInt(req.getParameter("memberId"));
        isOwner = super.getIsOwner(req,res);
        req.setAttribute("isOwner", isOwner);
        action = action == null ? "" : action;
        System.out.println("action="+action);
        switch (action) {

            case "addFriend":
                addFriend(req, res);
                break;
            case "inviteFreind":
                inviteFreind(req, res);
                break;
            case "addBlock":
                addBlock(req, res);
                break;
            case "cancelInvite":
                cancelInvite(req, res);
                break;
            case "deleteFriend":
                deleteFriend(req, res);
                break;
            case "deleteBlock":
                deleteBlock(req, res);
                break;
            case "getFriends":
                getFriends(req, res);
                break;
            case "getInviting":
                getInviting(req, res);
                break;
            case "getBlocks":
                getBlocks(req, res);
                break;
            case "getInvited":
                getInvited(req, res);
                break;
            case "searchFriend":
                searchFriend(req, res);
                break;
            case "searchInviting":
                searchInviting(req, res);
                break;
            case "searchBlock":
                searchBlock(req, res);
                break;
            case "searchInvited":
                searchInvited(req, res);
                break;
            case "findRecentFriend":
                findRecentFriend(req,res);
                break;
            default:
                list(req, res);
        }
    }
    public void addFriend(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        if(isOwner==1) {
            Integer targetId = Integer.parseInt(req.getParameter("targetId"));
            if(relaServ.addFriend(memberId,targetId)) {
                out.write("{\"status\":\"成功新增好友！\"}");
                return;
            }else{
                out.write("{\"status\":\"0\"}");
                return;
            }
        }
        out.write("{\"status\":\"0\"}");
    }

    public void inviteFreind(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        if(membervo!=null) {
            if(loginId == memberId) {
                Integer targetId = Integer.parseInt(req.getParameter("targetId"));
                if(relaServ.inviteFreind(memberId,targetId)) {
                    out.write("{\"status\":\"成功邀請好友！\"}");
                }
            }else{
                out.write("{\"status\":\"無權限進行此操作\"}");
            }
        }else{
            out.write("{\"status\":\"請先登入\"}");
        }
    }

    public void addBlock(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        if(membervo!=null) {
            if(loginId == memberId) {
                Integer targetId = Integer.parseInt(req.getParameter("targetId"));
                if(relaServ.addBlock(memberId,targetId)) {
                    out.write("{\"status\":\"成功刪除好友並加入黑名單！\"}");
                }
            }else{
                out.write("{\"status\":\"無權限進行此操作\"}");
            }
        }else{
            out.write("{\"status\":\"請先登入\"}");
        }
    }

    public void cancelInvite(HttpServletRequest req, HttpServletResponse res) throws IOException {
        if(isOwner==1){
            PrintWriter out = res.getWriter();
            Integer targetId = Integer.parseInt(req.getParameter("targetId"));
            relaServ.cancelInvite(memberId, targetId);
            out.write("{\"status\":\"成功取消邀請！\"}");
        }
    }


    public void deleteFriend(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        if(isOwner == 1) {
            Integer targetId = Integer.parseInt(req.getParameter("targetId"));
            relaServ.deleteFriend(memberId,targetId);
            out.write("{\"status\":\"成功刪除好友！\"}");
        }
    }
    public void deleteBlock(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        if(isOwner == 1) {
            Integer targetId = Integer.parseInt(req.getParameter("targetId"));
            if(relaServ.deleteBlock(memberId,targetId)) {
                out.write("{\"status\":\"成功移除黑名單！\"}");
            }
        }else{
            out.write("{\"status\":\"無權限進行此操作\"}");
        }
    }


    public void getFriends(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        List<RelationResult> mvos=relaServ.getFriends(memberId);
        System.out.println(mvos);
        if(mvos!=null) {
            out.print(gson.toJson(mvos));
        }
    }
    public void getInviting(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        List<RelationResult> mvos = new ArrayList<>();
        if(membervo!=null) {
            mvos=relaServ.getInviting(memberId);
            if(mvos!=null) {
                out.write(gson.toJson(mvos));
                return;
            }
        }
        out.write(gson.toJson(mvos));
    }
    public void getBlocks(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        if(membervo!=null && isOwner==1) {
            List<RelationResult> mvos=relaServ.getBlocks(memberId);
            if(mvos!=null) {
                Gson gson = new Gson();
                out.write(gson.toJson(mvos));
            }
        }else{
            out.write("{\"status\":\"查無此資料\"}");
        }
    }
    public void getInvited(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        if(membervo!=null) {
            List<RelationResult> mvos=relaServ.getInvited(memberId);
            if(mvos!=null) {
                Gson gson = new Gson();
                out.write(gson.toJson(mvos));
            }
        }else{
            out.write("{\"status\":\"查無此資料\"}");
        }
    }
    public void searchFriend(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        String keyword = req.getParameter("keyword");
        if(keyword!=null) {
            List<RelationResult> membersVOS =  relaServ.searchFriend(memberId, keyword);
            out.write(gson.toJson(membersVOS));
        }
        out.write("{\"status\":\"查無此資料\"}");
    }
    public void searchInviting(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        String keyword = req.getParameter("keyword");
        if(keyword!=null) {
            List<RelationResult> membersVOS =  relaServ.searchInviting(memberId, keyword);
            out.write(gson.toJson(membersVOS));
        }
        out.write("{\"status\":\"查無此資料\"}");
    }

    public void searchBlock(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        String keyword = req.getParameter("keyword");
        if(keyword!=null) {
            List<RelationResult> membersVOS =  relaServ.searchBlock(memberId, keyword);
            out.write(gson.toJson(membersVOS));
        }
        out.write("{\"status\":\"查無此資料\"}");
    }


    public void searchInvited(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        String keyword = req.getParameter("keyword");
        if(keyword!=null) {
            List<RelationResult> membersVOS =  relaServ.searchInvited(memberId, keyword);
            out.write(gson.toJson(membersVOS));
        }
        out.write("{\"status\":\"查無此資料\"}");
    }

    public void list(HttpServletRequest req, HttpServletResponse res) throws IOException {
        req.setAttribute("memberId",memberId);
        super.routeTo(req,res,"community","/relationship/relationship");
    }

    public void findRecentFriend(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        List<RelationResult> membersVOS =  relaServ.findRecentFriend(memberId);
        out.write(gson.toJson(membersVOS));
    }

}
