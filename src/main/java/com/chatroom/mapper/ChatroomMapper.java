package com.chatroom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.chatroom.model.ChatroomVO;

public interface ChatroomMapper {
	
	@Select(value= " SELECT * FROM chatroom c "
				+ " JOIN chatroom_member cm on(c.chatroom_id=cm.chatroom_id) WHERE member_id = #{memberId}")
	@Options(useGeneratedKeys = true, keyProperty = "chatroomId", keyColumn = "chatroom_id")
	List<ChatroomVO> getPersonalChatroom(Integer memberId);
	
	
	@Select(value="SELECT chatroom_type FROM chatroom WHERE chatroom_id=#{chatroomId}")
	Integer checkChatroomType(Integer chatroomId);
	
	
	@Insert(value= " INSERT INTO chatroom(chatroom_name,chatroom_type) "
				+ "VALUES(#{chatroomName},#{chatroomType}) ")
	@Options(useGeneratedKeys = true, keyProperty = "chatroomId", keyColumn = "chatroom_id")
	int insert(ChatroomVO chatroomVO);
	
	@Delete(value="DELETE FROM chatroom WHERE chatroom_id = #{chatroomId}")
	@Options(useGeneratedKeys = true, keyProperty = "chatroomId", keyColumn = "chatroom_id")
	int delete(Integer chatroomId);

	@Update(value="UPDATE chatroom SET chatroom_name = #{chatroomName} "
				+ "WHERE chatroom_id = #{chatroomId}")
	@Options(useGeneratedKeys = false, keyProperty = "chatroomId", keyColumn = "chatroom_id")
	int updateName(ChatroomVO chatroomVO);
	
	@Select(value="SELECT c.* FROM chatroom c "
				+ " JOIN chatroom_member cm ON(c.chatroom_id=cm.chatroom_id) "
				+ " WHERE member_id = #{memberId} "
				+ " AND chatroom_name LIKE '${'%'+keyword+'%'}'")
	List<ChatroomVO> searchChatrooms(@Param("memberId")Integer memberId,@Param("keyword")String keyword);
}
