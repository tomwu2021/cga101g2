package com.chatroom.model;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.chatroom.mapper.ChatroomMapper;
import connection.MyBatisUtil;

public class ChatRoomMybatisDAO {
	
	public ChatroomVO insertChatroom(ChatroomVO chatroomVO) {
		SqlSession session = MyBatisUtil.getSessionTest();
		ChatroomMapper cm = session.getMapper(ChatroomMapper.class);
		if (cm.insert(chatroomVO) == 1) {
			session.commit();
			return chatroomVO;
		} else {
			return null;
		}
	}

	public Integer checkChatroomType(Integer chatroomId) {
		SqlSession session = MyBatisUtil.getSessionTest();
		ChatroomMapper cm = session.getMapper(ChatroomMapper.class);
		return cm.checkChatroomType(chatroomId);
	}
	public List<ChatroomVO> getPersonalChatroom(Integer memberId){
		SqlSession session = MyBatisUtil.getSessionTest();
		ChatroomMapper cm = session.getMapper(ChatroomMapper.class);
		List<ChatroomVO> crvs = cm.getPersonalChatroom(memberId);
		return crvs;
	}
	public boolean deleteById(Integer chatroomId) {
		SqlSession session = MyBatisUtil.getSessionTest();
		ChatroomMapper cm = session.getMapper(ChatroomMapper.class);
		if(cm.delete(chatroomId)==1) {
			session.commit();
			return true;
		}else {
			return false;
		}	
	}
	public ChatroomVO updateChatroomName(ChatroomVO chatroomVO) {
		SqlSession session = MyBatisUtil.getSessionTest();
		ChatroomMapper cm = session.getMapper(ChatroomMapper.class);
		if(cm.updateName(chatroomVO)==1) {
			session.commit();
			return chatroomVO;
		}else {
			return null;
		}	
	}
	public List<ChatroomVO> searchChatrooms(Integer memberId,String keyword){
		SqlSession session = MyBatisUtil.getSessionTest();
		ChatroomMapper cm = session.getMapper(ChatroomMapper.class);
		return cm.searchChatrooms(memberId,keyword);
	}
}
