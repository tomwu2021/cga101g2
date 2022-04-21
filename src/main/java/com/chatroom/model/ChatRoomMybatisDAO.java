package com.chatroom.model;

import java.util.List;
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

	public ChatroomVO updateChatroomName(ChatroomVO chatroomVO) {
		SqlSession session = MyBatisUtil.getSessionTest();
		ChatroomMapper cm = session.getMapper(ChatroomMapper.class);
		if (cm.updateName(chatroomVO) == 1) {
			session.commit();
			session.close();
			return chatroomVO;
		} else {
			return null;
		}
	}

	public List<ChatroomVO> searchChatrooms(Integer memberId, String keyword) {
		SqlSession session = MyBatisUtil.getSessionTest();
		ChatroomMapper cm = session.getMapper(ChatroomMapper.class);
		List<ChatroomVO> lcv = cm.searchChatrooms(memberId, keyword);
		;
		if (lcv != null) {
			return lcv;
		} else {
			return null;
		}
	}

}
