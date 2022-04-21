package com.chatroom.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.chatroom.mapper.ChatroomMapper;
import com.common.model.MappingJDBCDAO;
import com.common.model.MappingTableDto;
import com.picture.mapper.PictureMapper;
import com.picture.model.PictureVO;

import connection.JDBCConnection;
import connection.MyBatisUtil;

public class TestChatroom {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChatRoomMybatisDAO crbd = new ChatRoomMybatisDAO();
		MappingJDBCDAO mjd = new MappingJDBCDAO();
		ChatroomVO crv = new ChatroomVO();
		MappingTableDto mtd = new MappingTableDto();
//		mtd.setTableName1("chatroom_member");
//		mtd.setColumn1("chatroom_id");
//		mtd.setColumn2("member_id");
//		crv.setChatroomType(1);
//		crv.setChatroomName("菊草葉");
//		System.out.println(crbd.insertChatroom(crv));
//		mtd.setId1(crv.getChatroomId());
//		for (int i = 1; i < 10; i++) {
//			mtd.setId2(i);
//			mjd.insertOneMapping(mtd);
//		}
//		crv.setChatroomId(10);
//		crv.setChatroomName("hello");
//		System.out.println(crbd.getPersonalChatroom(709));
//		System.out.println(crbd.deleteById(7));
//		System.out.println(crbd.updateChatroomName(crv));
//		List<ChatroomVO> ccv=crbd.searchChatrooms(1,"火");
//		int i = 0;
		System.out.println(crbd.getChatroomMessage(3));
//		(3)getMessages().forEach(m->{
//			System.out.println(m);
//		});
	}
}
