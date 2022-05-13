package com.remind.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import connection.JDBCConnection;

public class RemindJDBCDAO implements RemindDAO_interface {
	Connection con;
	
//	① create 新增提醒事項(前)------------------------	
	@Override
	public RemindVO insert(RemindVO remindVO) {
		con = JDBCConnection.getRDSConnection();
		RemindVO remindVO2 = insert(remindVO, con);
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return remindVO2;
	}
	/** @see #insert*/	
	public RemindVO insert(RemindVO remindVO, Connection con) {
		final String INSERT = "INSERT INTO remind (member_id, content, time) VALUES (?, ?, ?)";
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				pstmt.setInt(index++, remindVO.getMemberId());
				pstmt.setString(index++, remindVO.getContent().trim());
				pstmt.setTimestamp(index++, remindVO.getTime());
				pstmt.execute();
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					remindVO.setRemindId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		return remindVO;
	}

//	② delete 刪除提醒事項(前)------------------------	
	@Override
	public boolean delete(RemindVO remindVO) {
		con = JDBCConnection.getRDSConnection();
		boolean boo = delete(remindVO, con);
		
		try {
			con.close();
			return boo;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	/** @see #delete*/
	public boolean delete(RemindVO remindVO, Connection con) {
		final String DELETE = "DELETE FROM remind WHERE remind_id = ?";
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(DELETE);
				int index = 1;
				pstmt.setInt(index, remindVO.getRemindId());
				pstmt.execute();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}	
		} else {
			return false;
		}
	}
	
//	③ update 修改提醒事項(前)------------------------	
	@Override
	public RemindVO update(RemindVO remindVO) {
		con = JDBCConnection.getRDSConnection();
		RemindVO remindVO2 = update(remindVO, con);
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return remindVO2;
	}
	/** @see #update*/
	public RemindVO update(RemindVO remindVO, Connection con) {
		final StringBuffer UPDATE = new StringBuffer("UPDATE remind SET ");
		final String content = remindVO.getContent();
			if (content != null && !content.trim().isEmpty()) UPDATE.append("content = ?,");
		final Timestamp time = remindVO.getTime();
			if (time != null) UPDATE.append("time = ?,");
		UPDATE.deleteCharAt(UPDATE.length()-1).append("WHERE remind_id = ?");
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(UPDATE.toString());
				int offset = 1;
				if (content != null && !content.trim().isEmpty()) pstmt.setString(offset++, content);
				if (time != null) pstmt.setTimestamp(offset++, time);
				pstmt.setInt(offset++, remindVO.getRemindId());
				pstmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		return remindVO;		
	}

//	④ read 查詢單則提醒(前)------------------------	
	@Override
	public RemindVO getOneById(Integer id) {
		final String GET_ONE = "SELECT remind_id, member_id, content, time FROM remind "
				         	 + "WHERE remind_id = ?";
		con = JDBCConnection.getRDSConnection();
		RemindVO remindVO =new RemindVO();
			
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(GET_ONE);
				int index = 1;
				pstmt.setInt(index, id);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					remindVO.setRemindId(rs.getInt(index++));
					remindVO.setMemberId(rs.getInt(index++));
					remindVO.setContent(transOuter(rs.getString(index++)));
					remindVO.setTime(rs.getTimestamp(index++));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return remindVO;
	}

//	⑤ read 查詢一會員之所有提醒(前)------------------------
	public List<RemindVO> getOneByMemberId(Integer id){
		final String GET_ONE_MEMBER = "SELECT remind_id, member_id, content, time "
							 + "FROM remind WHERE member_id = ? ORDER BY time DESC";
		con = JDBCConnection.getRDSConnection();
		List<RemindVO> list = new ArrayList<RemindVO>();

		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(GET_ONE_MEMBER);
				int index = 1;
				pstmt.setInt(index,id);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					RemindVO remindVO =new RemindVO();
					remindVO.setRemindId(rs.getInt(index++));
					remindVO.setMemberId(rs.getInt(index++));
					remindVO.setContent(transOuter(rs.getString(index++)));
					remindVO.setTime(rs.getTimestamp(index++));
					list.add(remindVO);
					index = 1;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
//	⑥ read 查詢一會員最近3則提醒(前)------------------------
	public List<RemindVO> getThreeByMemberId(Integer id){
		final String GET_THREE_MEMBER = "SELECT remind_id, member_id, content, time "
							 + "FROM remind WHERE member_id = ? AND time >= CURDATE() ORDER BY time ASC LIMIT 3";
		con = JDBCConnection.getRDSConnection();
		List<RemindVO> list = new ArrayList<RemindVO>();

		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(GET_THREE_MEMBER);
				int index = 1;
				pstmt.setInt(index,id);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					RemindVO remindVO =new RemindVO();
					remindVO.setRemindId(rs.getInt(index++));
					remindVO.setMemberId(rs.getInt(index++));
					remindVO.setContent(transOuter(rs.getString(index++)));
					remindVO.setTime(rs.getTimestamp(index++));
					list.add(remindVO);
					index = 1;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
//	⑦ read 查詢一會員之未到期提醒(前)------------------------
	public List<RemindVO> getUndueByMemberId(Integer id){
		final String GET_UNDUE_MEMBER = "SELECT remind_id, member_id, content, time "
				 + "FROM remind WHERE member_id = ? AND time > ? ORDER BY time";
		con = JDBCConnection.getRDSConnection();
		List<RemindVO> list = new ArrayList<RemindVO>();
		Calendar cal = Calendar.getInstance();

		if (con != null) {
		try {
			PreparedStatement pstmt = con.prepareStatement(GET_UNDUE_MEMBER);
			pstmt.setInt(1,id);
			pstmt.setTimestamp(2, new Timestamp(cal.getTime().getTime()));
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int index = 1;
				RemindVO remindVO =new RemindVO();
				remindVO.setRemindId(rs.getInt(index++));
				remindVO.setMemberId(rs.getInt(index++));
				remindVO.setContent(rs.getString(index++));
				remindVO.setTime(rs.getTimestamp(index++));
				list.add(remindVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		} else {
			return null;
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

//	⑧ read 不提供此功能------------------------	
	@Override
	public List<RemindVO> getAll() {
		return null;
	}

//	⑨ read 查詢所有即將到期提醒(後)------------------------
    public List<RemindVO> getAllNow(){
		final String GET_ALL_NOW = "SELECT remind_id, member_id, content, time "
				 + "FROM remind WHERE time = ?";
		con = JDBCConnection.getRDSConnection();
		List<RemindVO> list = new ArrayList<RemindVO>();
		Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND,0);
        cal.add(Calendar.HOUR,1);
		if (con != null) {
		try {
			PreparedStatement pstmt = con.prepareStatement(GET_ALL_NOW);
			pstmt.setTimestamp(1, new Timestamp(cal.getTime().getTime()));
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int index = 1;
				RemindVO remindVO =new RemindVO();
				remindVO.setRemindId(rs.getInt(index++));
				remindVO.setMemberId(rs.getInt(index++));
				remindVO.setContent(rs.getString(index++));
				remindVO.setTime(rs.getTimestamp(index++));
				list.add(remindVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		} else {
			return null;
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**文字換行顯示用*/
	public String transOuter(String str) { 
		while (str.indexOf("<") != -1) { 
			  str = str.substring(0, str.indexOf("<")) + "&lt;" 
			    + str.substring(str.indexOf("<") + 1); 
			 }
		while (str.indexOf(">") != -1) { 
			  str = str.substring(0, str.indexOf(">")) + "&gt;" 
			    + str.substring(str.indexOf(">") + 1); 
			 }
		 return str; 
	}
	public String transInner(String str) { 
		while (str.indexOf("<") != -1) { 
			  str = str.substring(0, str.indexOf("<")) + "&lt;" 
			    + str.substring(str.indexOf("<") + 1); 
			 }
		while (str.indexOf(">") != -1) { 
			  str = str.substring(0, str.indexOf(">")) + "&gt;" 
			    + str.substring(str.indexOf(">") + 1); 
			 }
		while (str.indexOf("\r") != -1) { 
		  str = str.substring(0, str.indexOf("\r")) 
		    + str.substring(str.indexOf("\r") + 1); 
		 } 
		 while (str.indexOf("\n") != -1) { 
			  str = str.substring(0, str.indexOf("\n")) + "<br/>" 
			    + str.substring(str.indexOf("\n") + 1); 
			 }
		 while (str.indexOf(" ") != -1) { 
		  str = str.substring(0, str.indexOf(" ")) + "&nbsp;" 
		    + str.substring(str.indexOf(" ") + 1); 
		 } 
		 return str; 
	}
    
}
