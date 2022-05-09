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
    MappingJDBCDAO mdao  = new MappingJDBCDAO();

    public ChatroomVO makePrivateChatroom(Integer memberId,Integer targetId,String name){
        try (Connection con = JDBCConnection.getRDSConnection()){
            int i = crDao.checkIsOpen(memberId,targetId,con);
            System.out.println("open"+i);
            if( i == 0) {
                ChatroomVO chatroom = new ChatroomVO();
                chatroom.setChatroomName(name);
                chatroom.setChatroomType(0);
                ChatroomVO chatroom2 = crDao.insertChatroom(chatroom);
                if (chatroom2 != null) {
                    mdao.insertOneMapping(makeChatroomDto(chatroom2.getChatroomId(), memberId), con);
                    mdao.insertOneMapping(makeChatroomDto(chatroom2.getChatroomId(), targetId), con);
                    return chatroom2;
                }
            }
            return null;
        }catch(SQLException e){
            return null;
        }
    }

    public ChatroomVO makeGroupChatroom(List<Integer> memberIds, String name){
        ChatroomVO chatroom = new ChatroomVO();
        chatroom.setChatroomName(name);
        chatroom.setChatroomType(1);
        Connection con = JDBCConnection.getRDSConnection();
        ChatroomVO chatroom2 = crDao.insertChatroom(chatroom);
        List<MappingTableDto> mtds = new ArrayList<>();
        for(Integer memberId:memberIds) {
            if (chatroom2 != null) {
                mtds.add(makeChatroomDto(memberId, chatroom2.getChatroomId()));
            }
        }
        mdao.insertMultiMapping(mtds, con);
        return chatroom2;
    }
    public List<ChatroomVO>  getPersonalChatroom(Integer memberId) {
        return crDao.getPersonalChatroom(memberId);
    }

    public List<ChatroomVO> searchChatrooms(Integer memberId, String keyword){
        return crDao.searchChatrooms(memberId,keyword);
    }
    public MappingTableDto makeChatroomDto(Integer chatroomId,Integer memberId){
        MappingTableDto mtd = new MappingTableDto();
        mtd.setTableName1("chatroom_member");
        mtd.setColumn1("chatroom_id");
        mtd.setColumn2("member_id");
        mtd.setId1(chatroomId);
        mtd.setId2(memberId);
        return mtd;
    }

    public boolean updateChatroomName(Integer chatroomId,String chatroomName ){
        ChatroomVO chatroom = new ChatroomVO();
        chatroom.setChatroomId(chatroomId);
        chatroom.setChatroomName(chatroomName);
        return crDao.updateChatroomName(chatroom);
    }
    public List<ChatroomResult> getPrivateChatroom(Integer memberId) {
        try {
            Connection con = JDBCConnection.getRDSConnection();
            List<ChatroomResult> crrs =  crDao.getPrivateChatroom(memberId,con);
            for(ChatroomResult crr:crrs){
                List<ChatroomMemberResult> crms = crDao.getChatroomMember(crr.getChatroomId(),con);
                crr.setCrms(crms);
            }
            return crrs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean joinChatroom(Integer chatroomId,Integer targetId){
        if(mdao.insertOneMapping(makeChatroomDto(chatroomId, targetId))){
            return true;
        }
        return false;
    }

    public boolean exitChatroom(Integer chatroomId,Integer memberId){
        if(mdao.deleteOneMapping(makeChatroomDto(chatroomId, memberId))){
            return true;
        }
        return false;
    }

}
