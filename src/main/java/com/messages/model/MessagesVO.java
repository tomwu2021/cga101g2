package com.messages.model;

import java.sql.Timestamp;

import com.chatroom.model.ChatroomVO;

public class MessagesVO {

	private Integer messageId;
	private Integer chatroomId;
	private Integer memberId;
	private String message;
	private Timestamp createTime;
	
	private ChatroomVO chatroomVO;

	

	public MessagesVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MessagesVO(Integer messageId, Integer chatroomId, Integer memberId, String message, Timestamp createTime) {
		super();
		this.messageId = messageId;
		this.chatroomId = chatroomId;
		this.memberId = memberId;
		this.message = message;
		this.createTime = createTime;
	}

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public Integer getChatroomId() {
		return chatroomId;
	}

	public void setChatroomId(Integer chatroomId) {
		this.chatroomId = chatroomId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	
	
	public ChatroomVO getChatroomVO() {
		return chatroomVO;
	}

	public void setChatroomVO(ChatroomVO chatroomVO) {
		this.chatroomVO = chatroomVO;
	}

	@Override
	public String toString() {
		return "MessagesVO:{messageId:" + messageId + ", chatroomId:" + chatroomId + ", memberId:" + memberId
				+ ", message:" + message + ", createTime:" + createTime + "}";
	}

}
