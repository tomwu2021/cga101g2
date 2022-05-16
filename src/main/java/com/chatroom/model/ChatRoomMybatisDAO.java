package com.chatroom.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.members.model.MembersVO;
import connection.JDBCConnection;
import org.apache.ibatis.session.SqlSession;
import com.chatroom.mapper.ChatroomMapper;
import com.messages.mapper.MessagesMapper;
import com.messages.model.MessagesMybatisDAO;
import com.messages.model.MessagesVO;
import com.mysql.cj.Session;

import connection.MyBatisUtil;

public class ChatRoomMybatisDAO {

	public ChatroomVO insertChatroom(ChatroomVO chatroomVO) {
		SqlSession session = MyBatisUtil.getSessionTest();
		ChatroomMapper cm = session.getMapper(ChatroomMapper.class);
		if (cm.insert(chatroomVO) == 1) {
			session.commit();
			session.close();
			return chatroomVO;
		} else {
			return null;
		}
	}

	public Integer checkChatroomType(Integer chatroomId, SqlSession session) {
		ChatroomMapper cm = session.getMapper(ChatroomMapper.class);
		return cm.checkChatroomType(chatroomId);
	}

	public ChatroomVO getChatroomMessage(Integer chatroomId, SqlSession session) {
		ChatroomMapper cm = session.getMapper(ChatroomMapper.class);
		ChatroomVO crv = cm.getById(chatroomId);
//		crv.setMessages(new MessagesMybatisDAO().getChatroomMessage(chatroomId, session));
		return crv;
	}

	public ChatroomVO getChatroomMessage(Integer chatroomId) {
		SqlSession session = MyBatisUtil.getSessionTest();
		ChatroomVO crv = getChatroomMessage(chatroomId, session);
		return crv;
	}

	public List<ChatroomVO> getPersonalChatroom(Integer memberId) {
		SqlSession session = MyBatisUtil.getSessionTest();
		ChatroomMapper cm = session.getMapper(ChatroomMapper.class);
		List<ChatroomVO> crvs = cm.getPersonalChatroom(memberId);
		session.close();
		return crvs;
	}

	public boolean deleteById(Integer chatroomId) {
		SqlSession session = MyBatisUtil.getSessionTest();
		ChatroomMapper cm = session.getMapper(ChatroomMapper.class);
		if (cm.delete(chatroomId) == 1) {
			session.commit();
			session.close();
			return true;
		} else {
			return false;
		}
	}

	public boolean updateChatroomName(ChatroomVO chatroomVO) {
		SqlSession session = MyBatisUtil.getSessionTest();
		ChatroomMapper cm = session.getMapper(ChatroomMapper.class);
		if(cm.updateName(chatroomVO)==1){
			session.commit();
			session.close();
			return true;
		}
		return false;
	}

	public List<ChatroomVO> searchChatrooms(Integer memberId, String keyword) {
		SqlSession session = MyBatisUtil.getSessionTest();
		ChatroomMapper cm = session.getMapper(ChatroomMapper.class);
		List<ChatroomVO> lcv = cm.searchChatrooms(memberId, keyword);
		if (lcv != null) {
			return lcv;
		} else {
			return null;
		}
	}

	public int checkIsOpen(Integer memberId, Integer targetId, Connection con) throws SQLException {
		System.out.println("start checking=====================...........");
		String sql =" SELECT (? IN (SELECT member_id FROM chatroom_member WHERE chatroom_id " +
				" IN (SELECT crm2.chatroom_id FROM chatroom_member crm2 " +
				"		JOIN chatroom cr2 ON cr2.chatroom_id = crm2.chatroom_id " +
				"       WHERE cr2.chatroom_type = 0 AND  member_id = ?))) as is_open " +
				" FROM chatroom_member crm " +
				" JOIN chatroom cr ON (crm.chatroom_id = cr.chatroom_id) " +
				"  LIMIT 1 ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1,targetId);
		stmt.setInt(2,memberId);
		ResultSet rs = stmt.executeQuery();
		rs.next();

		int isOpen = rs.getInt(1);
		System.out.println("isopen=========================="+isOpen);
		if(isOpen != 1){
			return 0;
		}
		rs.close();
		stmt.close();
		return 1;
	}
	public int checkIsOpen(Integer memberId, Integer targetId) throws SQLException{
		Connection con = JDBCConnection.getRDSConnection();
		int isOpen = checkIsOpen(memberId,targetId,con);
		con.close();
		return isOpen;
	}
	public List<ChatroomResult> getPrivateChatroom(Integer memberId, Connection con) throws SQLException {
		String sql = "SELECT cr.*,m.name,m.member_id,pic.picture_id,pic.preview_url " +
				" FROM chatroom_member crm " +
					" JOIN members m ON (m.member_id=crm.member_id) " +
					" JOIN chatroom cr ON (crm.chatroom_id = cr.chatroom_id) " +
					" JOIN pet p ON(m.member_id = p.member_id) " +
					" JOIN picture pic ON(p.picture_id = pic.picture_id) " +
				" WHERE ((m.member_id != ? " +
				" AND cr.chatroom_id IN (SELECT cr2.chatroom_id FROM chatroom_member crm2 " +
				" JOIN chatroom cr2 ON (crm2.chatroom_id = cr2.chatroom_id) " +
				"                        WHERE cr2.chatroom_type=0 AND crm2.member_id = ? ))) " +
				" OR (crm.member_id = ? AND (m.member_id != ? OR cr.chatroom_type =1)) " +
				" ORDER BY cr.create_time DESC ";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1,memberId);
		stmt.setInt(2,memberId);
		stmt.setInt(3,memberId);
		stmt.setInt(4,memberId);
		List<ChatroomResult> crre = new ArrayList<>();
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			crre.add(buildChatroomResult(rs));
		}
		rs.close();
		stmt.close();
		System.out.println("CRRE:"+crre);
		return crre;
	}
	public List<ChatroomResult> getPrivateChatroom(Integer memberId) throws SQLException {
		Connection con =JDBCConnection.getRDSConnection();
		List<ChatroomResult> crre = getPrivateChatroom(memberId,con);
		con.close();
		return crre;
	}

	public ChatroomResult buildChatroomResult(ResultSet rs) throws SQLException {
		ChatroomResult chatroom = new ChatroomResult();
		chatroom.setChatroomId(rs.getInt("chatroom_id"));
		chatroom.setChatroomType(rs.getInt("chatroom_type"));
		chatroom.setChatroomName(rs.getString("chatroom_name"));
		chatroom.setCreateTime(rs.getTimestamp("create_time"));
		chatroom.setName(rs.getString("m.name"));
		chatroom.setMemberId(rs.getInt("m.member_id"));
		chatroom.setPictureId(rs.getInt("pic.picture_id"));
		chatroom.setPreviewUrl(rs.getString("pic.preview_url"));
		return chatroom;
	}

	public List<ChatroomMemberResult> getChatroomMember(Integer chatroomId,Connection con) throws SQLException {
		String sql = " SELECT m.member_id,m.name,pic.preview_url " +
				" FROM members m " +
				" JOIN chatroom_member crm ON(m.member_id = crm.member_id) " +
				" JOIN pet p ON(p.member_id=m.member_id) " +
				" JOIN picture pic ON(pic.picture_id = p.picture_id) " +
				" WHERE crm.chatroom_id = ? ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1,chatroomId);
		List<ChatroomMemberResult> crms = new ArrayList<>();
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			crms.add(buildChatroomMemberResult(rs));
		}
		rs.close();
		stmt.close();
		return crms;
	}
	public ChatroomMemberResult buildChatroomMemberResult(ResultSet rs) throws SQLException {
		ChatroomMemberResult crm = new ChatroomMemberResult();
		crm.setMemberId(rs.getInt("m.member_id"));
		crm.setName(rs.getString("m.name"));
		crm.setPreviewUrl(rs.getString("pic.preview_url"));
		return crm;
	}

	public ChatroomResult getChatroomResultById(Integer memberId,Integer chatroomId,Connection con) throws SQLException {
		System.out.println("start checking=====================...........");
		String sql ="SELECT cr.*,m.name,m.member_id,pic.picture_id,pic.preview_url " +
				" FROM chatroom_member crm " +
				" JOIN members m ON (m.member_id=crm.member_id) " +
				" JOIN chatroom cr ON (crm.chatroom_id = cr.chatroom_id) " +
				" JOIN pet p ON(m.member_id = p.member_id) " +
				" JOIN picture pic ON(p.picture_id = pic.picture_id) " +
				" WHERE cr.chatroom_id = ? " +
				" AND crm.member_id != ? ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1,chatroomId);
		stmt.setInt(2,memberId);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		ChatroomResult crr = buildChatroomResult(rs);
		crr.setChatroomType(rs.getInt("cr.chatroom_type"));
		rs.close();
		stmt.close();
		return crr;
	}
	public ChatroomResult getChatroomResultById(Integer memberId,Integer chatroomId) throws SQLException {
		Connection con = JDBCConnection.getRDSConnection();
		ChatroomResult crr = getChatroomResultById(memberId,chatroomId,con);
		con.close();
		return crr;
	}

}