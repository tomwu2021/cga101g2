package com.messages.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.chatroom.model.ChatroomVO;
import com.messages.model.MessagesVO;

public interface MessagesMapper {

	@Insert(value= " INSERT INTO messages(chatroom_id, member_id, message) VALUES(#{chatroomId},#{memberId}), #(message) ")
	@Options(useGeneratedKeys = true, keyProperty = "chatroomId", keyColumn = "chatroom_id")
	int insert(MessagesVO messagesVO);

	@Delete(value="DELETE FROM messages WHERE message_id=#{messageId}")
	int deleteById(Integer messageId);

	@Select(value="SELECT msg.message_id, msg.chatroom_id as chaa"
			+ ", msg.member_id, msg.message, msg.create_time FROM messages msg WHERE msg.chatroom_id=#{chatroomId} ")
	@Results(value = {
    		@Result(property="chatroomId" ,column = "chaa"),
    })
	List<MessagesVO> getMessageByChatroomId(Integer chatroomId);
	
	@Select(value="SELECT message_id FROM messages WHERE message_id=#{messageId}")
	Integer selectById(Integer messageId);
	
	@Update(value= " UPDATE messages SET message = #{message} WHERE message_id = #{messageId} ")
	int pictureExpired(MessagesVO messagesVO);
	
	
}
