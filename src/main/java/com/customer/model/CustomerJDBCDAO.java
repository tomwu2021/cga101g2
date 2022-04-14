package com.customer.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import connection.JDBCConnection;

public class CustomerJDBCDAO implements CustomerDAO_interface {
	
	Connection con;

	private static final String INSERT = "INSERT INTO customer (mail_address, nickname, content, reply_status) VALUES (?, ?, ?, 0)";
	private static final String DELETE = "DELETE FROM customer WHERE case_id = ?";
	private static final String UPDATE = "UPDATE customer SET reply_status = ?, emp_no = ? WHERE case_id = ?";
	private static final String GET_ONE = "SELECT case_id, mail_address, nickname, content, send_time, reply_status, emp_no FROM customer WHERE case_id = ?";
	private static final String GET_ALL = "SELECT remind_id, member_id, content, time FROM remind ORDER BY time";
	
	
	@Override
	public CustomerVO insert(CustomerVO customerVO) {
		
		con = JDBCConnection.getRDSConnection();
		CustomerVO customerVO2 = insert(customerVO, con);
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customerVO2;
	}
	
	public CustomerVO insert(CustomerVO customerVO, Connection con) {
	
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				pstmt.setString(index++, customerVO.getMailAddress());
				pstmt.setString(index++, customerVO.getNickname());
				pstmt.setString(index++, customerVO.getContent());
				pstmt.execute();
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					customerVO.setCaseId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		return customerVO;
	}

	
	@Override
	public boolean delete(CustomerVO customerVO) {
		
		con = JDBCConnection.getRDSConnection();
		boolean boo = delete(customerVO, con);
		
		try {
			con.close();
			return boo;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean delete(CustomerVO customerVO, Connection con) {
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(DELETE);
				int index = 1;
				pstmt.setInt(index, customerVO.getCaseId());
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
	public CustomerVO update(CustomerVO customerVO) {
		
		con = JDBCConnection.getRDSConnection();
		CustomerVO customerVO2 = update(customerVO, con);
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customerVO2;
	}
	
	public CustomerVO update(CustomerVO customerVO, Connection con) {
		
		if (con != null) {
			try {
				
				PreparedStatement pstmt = con.prepareStatement(UPDATE, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				pstmt.setInt(index++,customerVO.getReplyStatus());
				pstmt.setInt(index++,customerVO.getEmpNo());
				pstmt.setInt(index++,customerVO.getCaseId());
				pstmt.execute();
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					customerVO.setCaseId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		return customerVO;
		
	}

	
	@Override
	public CustomerVO getOneById(Integer id) {
		
		con = JDBCConnection.getRDSConnection();
		CustomerVO customerVO =new CustomerVO();
			
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(GET_ONE);
				int index = 1;
				pstmt.setInt(index, id);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					customerVO.setCaseId(rs.getInt(index++));
					customerVO.setMailAddress(rs.getString(index++));
					customerVO.setNickname(rs.getString(index++));
					customerVO.setContent(rs.getString(index++));
					customerVO.setSendTime(rs.getTimestamp(index++));
					customerVO.setReplyStatus(rs.getInt(index++));
					customerVO.setEmpNo(rs.getInt(index++));
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
		return customerVO;
		
	}
		
		
	@Override
	public List<CustomerVO> getAll() {
		
		con = JDBCConnection.getRDSConnection();
				List<CustomerVO> list = new ArrayList<CustomerVO>();
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(GET_ALL);
				int index = 1;
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					CustomerVO customerVO =new CustomerVO();
					customerVO.setCaseId(rs.getInt(index++));
					customerVO.setMailAddress(rs.getString(index++));
					customerVO.setNickname(rs.getString(index++));
					customerVO.setContent(rs.getString(index++));
					customerVO.setSendTime(rs.getTimestamp(index++));
					customerVO.setReplyStatus(rs.getInt(index++));
					customerVO.setEmpNo(rs.getInt(index++));
					list.add(customerVO);
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
