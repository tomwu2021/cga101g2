package com.remind.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.JDBCConnection;


public class RemindJDBCDAO implements RemindDAO_interface {
	
	Connection con;

	private static final String INSERT = "INSERT INTO remind (member_id, content, time) VALUES (?, ?, ?)";
	private static final String DELETE = "DELETE FROM remind WHERE remind_id = ?";
	private static final String UPDATE = "UPDATE remind SET content = ?, time = ? WHERE remind_id = ?";
	private static final String GET_ONE = "SELECT remind_id, member_id, content, time FROM remind WHERE remind_id = ?";
	private static final String GET_ALL = "SELECT remind_id, member_id, content, time FROM remind ORDER BY time";
	
	
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
	
	public RemindVO insert(RemindVO remindVO, Connection con) {
	
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				pstmt.setInt(index++, remindVO.getMemberId());
				pstmt.setString(index++, remindVO.getContent());
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
	
	public boolean delete(RemindVO remindVO, Connection con) {
		
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
	
	public RemindVO update(RemindVO remindVO, Connection con) {
		
		if (con != null) {
			try {
				
				PreparedStatement pstmt = con.prepareStatement(UPDATE, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				pstmt.setString(index++, remindVO.getContent());
				pstmt.setTimestamp(index++, remindVO.getTime());
				pstmt.setInt(index++, remindVO.getRemindId());
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

	
	@Override
	public RemindVO getOneById(Integer id) {
		
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
					remindVO.setContent(rs.getString(index++));
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
		
		
	@Override
	public List<RemindVO> getAll() {
		
		con = JDBCConnection.getRDSConnection();
				List<RemindVO> list = new ArrayList<RemindVO>();
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(GET_ALL);
				int index = 1;
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					RemindVO remindVO =new RemindVO();
					remindVO.setRemindId(rs.getInt(index++));
					remindVO.setMemberId(rs.getInt(index++));
					remindVO.setContent(rs.getString(index++));
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

	
}
