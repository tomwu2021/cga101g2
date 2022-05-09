package com.chatroom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.messages.mapper.MessagesMapper;
import com.chatroom.model.ChatroomVO;
import com.messages.model.MessagesVO;

public interface ChatroomMapper {
	
	@Select(value= " SELECT * FROM chatroom c "
				+ " JOIN chatroom_member cm on(c.chatroom_id=cm.chatroom_id) WHERE member_id = #{memberId} AND chatroom_type = 1")
	@Options(useGeneratedKeys = true, keyProperty = "chatroomId", keyColumn = "chatroom_id")
	List<ChatroomVO> getPersonalChatroom(Integer memberId);
	
	
	@Select(value="SELECT chatroom_type FROM chatroom WHERE chatroom_id= #{chatroomId} ")
	Integer checkChatroomType(Integer chatroomId);
	
//	"com.messages.mapper.MessagesMapper.getMessageByChatroomId"
	@Select(value= " SELECT * FROM chatroom ch WHERE ch.chatroom_id = #{chatroomId} ")
    @Results(value = {
    		@Result(property="chatroomId" ,column = "chatroom_id"),
            @Result(property="messagesList", javaType=List.class,column="chatroom_id",
                    many = @Many(select="com.messages.mapper.MessagesMapper.getMessageByChatroomId"))
    })
	ChatroomVO getById(Integer chatroomId);
	
	
	@Insert(value= " INSERT INTO chatroom(chatroom_name,chatroom_type) "
				+ " VALUES(#{chatroomName},#{chatroomType}) ")
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
