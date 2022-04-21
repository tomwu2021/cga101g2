package com.chatroom.model;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.messages.model.MessagesVO;

public class ChatroomVO {
	
	private Integer chatroomId;
	private String chatroomName;
	private Integer chatroomType;
	private Timestamp createTime;

	private List<MessagesVO> messageList;

	public ChatroomVO() {

	}

	public ChatroomVO(Integer chatroomId, String chatroomName, Integer chatroomType, Timestamp createTime) {
		super();
		this.chatroomId = chatroomId;
		this.chatroomName = chatroomName;
		this.chatroomType = chatroomType;
		this.createTime = createTime;
	}

	public Integer getChatroomId() {
		return chatroomId;
	}

	public void setChatroomId(Integer chatroomId) {
		this.chatroomId = chatroomId;
	}

	public String getChatroomName() {
		return chatroomName;
	}

	public void setChatroomName(String chatroomName) {
		this.chatroomName = chatroomName;
	}

	public Integer getChatroomType() {
		return chatroomType;
	}

	public void setChatroomType(Integer chatroomType) {
		this.chatroomType = chatroomType;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public List<MessagesVO> getMessageList() {
		return messageList;
	}

	public void setMessagesList(List<MessagesVO> messageList) {
		this.messageList = messageList;
	}

	@Override
	public String toString() {
		return "ChatroomVO:{chatroomId:" + chatroomId + ", chatroomName:" + chatroomName + ", chatroomType:"
				+ chatroomType + ", createTime:" + createTime + ", messageList:" + messageList + "}";
	}

}
