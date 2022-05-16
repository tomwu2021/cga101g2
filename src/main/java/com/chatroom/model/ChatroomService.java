package com.chatroom.model;

import com.common.model.MappingJDBCDAO;
import com.common.model.MappingTableDto;
import connection.JDBCConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChatroomService {

    ChatRoomMybatisDAO crDao = new ChatRoomMybatisDAO();
    MappingJDBCDAO mdao = new MappingJDBCDAO();

    public ChatroomResult makePrivateChatroom(Integer memberId, Integer targetId, String name) {
        System.out.println("start connect=====================...........");
        try (Connection con = JDBCConnection.getRDSConnection()) {
            System.out.println("start checking2222=====================...........");
            int i = crDao.checkIsOpen(memberId, targetId, con);
            if (i == 0) {
                ChatroomVO chatroom = new ChatroomVO();
                chatroom.setChatroomName(name);
                chatroom.setChatroomType(0);
                ChatroomVO chatroom2 = crDao.insertChatroom(chatroom);
                if (chatroom2 != null) {
                    mdao.insertOneMapping(makeChatroomDto(chatroom2.getChatroomId(), memberId), con);
                    mdao.insertOneMapping(makeChatroomDto(chatroom2.getChatroomId(), targetId), con);
                }
                ChatroomResult crr = crDao.getChatroomResultById(memberId, chatroom2.getChatroomId(), con);
                return crr;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ChatroomVO makeGroupChatroom(List<Integer> memberIds, String name) {
        ChatroomVO chatroom = new ChatroomVO();
        chatroom.setChatroomName(name);
        chatroom.setChatroomType(1);

        ChatroomVO chatroom2 = crDao.insertChatroom(chatroom);
        List<MappingTableDto> mtds = new ArrayList<>();

        System.out.println("**************members:" + memberIds.size());
        Integer chatroomId = chatroom2.getChatroomId();
        System.out.println("==========chatroom2id:" + chatroomId);
        try {
            if (chatroomId != null) {
                memberIds.forEach(id -> {
                    System.out.println("id:" + id);
                });
                Connection con = JDBCConnection.getRDSConnection();
                memberIds.forEach(id -> {
                    System.out.println("id:" + id);
                    MappingTableDto mkct = makeChatroomDto(chatroomId, id);
                    mtds.add(mkct);
                });
                mdao.insertMultiMapping(mtds, con);
                con.close();
            }
            return chatroom2;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<ChatroomVO> getPersonalChatroom(Integer memberId) {
        return crDao.getPersonalChatroom(memberId);
    }

    public List<ChatroomVO> searchChatrooms(Integer memberId, String keyword) {
        return crDao.searchChatrooms(memberId, keyword);
    }

    public MappingTableDto makeChatroomDto(Integer chatroomId, Integer memberId) {
        MappingTableDto mtd = new MappingTableDto();
        mtd.setTableName1("chatroom_member");
        mtd.setColumn1("chatroom_id");
        mtd.setColumn2("member_id");
        mtd.setId1(chatroomId);
        mtd.setId2(memberId);
        System.out.println(mtd);
        return mtd;
    }

    public boolean updateChatroomName(Integer chatroomId, String chatroomName) {
        ChatroomVO chatroom = new ChatroomVO();
        chatroom.setChatroomId(chatroomId);
        chatroom.setChatroomName(chatroomName);
        return crDao.updateChatroomName(chatroom);
    }

    public List<ChatroomResult> getPrivateChatroom(Integer memberId) {
        try {
            Connection con = JDBCConnection.getRDSConnection();
            List<ChatroomResult> crrs = crDao.getPrivateChatroom(memberId, con);
            for (ChatroomResult crr : crrs) {
                List<ChatroomMemberResult> crms = crDao.getChatroomMember(crr.getChatroomId(), con);
                crr.setCrms(crms);
            }
            return crrs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean joinChatroom(Integer chatroomId, Integer targetId) {
        try {
            if (mdao.insertOneMapping(makeChatroomDto(chatroomId, targetId))) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean exitChatroom(Integer chatroomId, Integer memberId) {
        if (mdao.deleteOneMapping(makeChatroomDto(chatroomId, memberId))) {
            return true;
        }
        return false;
    }

}
