package com.messages.model;


import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.messages.mapper.MessagesMapper;

import connection.MyBatisUtil;

public class MessagesMybatisDAO {

	public MessagesVO insertMessage(MessagesVO messagesVO) {
		SqlSession session = MyBatisUtil.getSessionTest();
		MessagesMapper mm = session.getMapper(MessagesMapper.class);
		if (mm.insert(messagesVO) == 1) {
			session.commit();
			session.close();
			return messagesVO;
		} else {
			return null;
		}
	}

	public boolean deleteMessage(Integer messageId) {
		SqlSession session = MyBatisUtil.getSessionTest();
		MessagesMapper mm = session.getMapper(MessagesMapper.class);
		if (mm.deleteById(messageId) == 1) {
			session.commit();
			session.close();
			return true;
		} else {
			return false;
		}
	}

	public List<MessagesVO> getMessageByChatroomId(Integer chatroomId,SqlSession session) {
		MessagesMapper mm = session.getMapper(MessagesMapper.class);
		List<MessagesVO> msgs=  mm.getMessageByChatroomId(chatroomId);
		session.commit();
		session.close();
		return msgs;

	}
	
	public List<MessagesVO> getMessageByChatroomId(Integer chatroomId) {
		SqlSession session = MyBatisUtil.getSessionTest();
		List<MessagesVO> msgs=  getMessageByChatroomId(chatroomId,session);
		return msgs;
	}
	

	public boolean isMessage(Integer messageId) {
		SqlSession session = MyBatisUtil.getSessionTest();
		MessagesMapper mm = session.getMapper(MessagesMapper.class);
		if (mm.selectById(messageId) != null) {
			return true;
		} else {
			return false;
		}
	}
	public  MessagesVO pictureExpired(MessagesVO messagesVO) {
		SqlSession session = MyBatisUtil.getSessionTest();
		MessagesMapper mm = session.getMapper(MessagesMapper.class);
		messagesVO.setMessage("此圖片已失效");
		mm.pictureExpired(messagesVO);
		return messagesVO;
	}

}
