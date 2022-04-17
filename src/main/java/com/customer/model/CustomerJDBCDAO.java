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
	
//	① create 客戶提出問顕(前)------------------------	
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
	/** @see #insert*/
	public CustomerVO insert(CustomerVO customerVO, Connection con) {
		final String INSERT = "INSERT INTO customer (mail_address, nickname, content, reply_status) VALUES (?, ?, ?, 0)";
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				pstmt.setString(index++, customerVO.getMailAddress().trim());
				pstmt.setString(index++, customerVO.getNickname().trim());
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

//	② delete 不提供此功能------------------------	
	@Override
	public boolean delete(CustomerVO customerVO) {
		return false;	
	}
	
//	③ update 回覆客戶問題(後)------------------------	
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
	/** @see #update*/
	public CustomerVO update(CustomerVO customerVO, Connection con) {
		final String UPDATE = "UPDATE customer SET reply_status = 1, emp_no = ? WHERE case_id = ?";
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(UPDATE, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				pstmt.setInt(index++,customerVO.getEmpNo());
				pstmt.setInt(index++,customerVO.getCaseId());
				pstmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		return customerVO;
	}

//	④ read 查詢單筆客戶問題(後)------------------------	
	@Override
	public CustomerVO getOneById(Integer id) {
		final String GET_ONE = "SELECT case_id, mail_address, nickname, content, send_time, reply_status, emp_no "
							 + "FROM customer WHERE case_id = ?";
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

//	⑤ read 依email查詢客戶問題(後)------------------------	
	@Override
	public List<CustomerVO> getOneByEmail(String mailAddress) {
		final String GET_ONE_EMAIL = "SELECT case_id, mail_address, nickname, content, send_time, reply_status, emp_no "
								   + "FROM customer WHERE mail_address = ?";
		con = JDBCConnection.getRDSConnection();
		List<CustomerVO> list = new ArrayList<CustomerVO>();
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(GET_ONE_EMAIL);
				int index = 1;
				pstmt.setString(index, mailAddress);
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
	
//	⑥ read 查詢所有客戶問題(後)------------------------		
	@Override
	public List<CustomerVO> getAll() {
		final String GET_ALL = "SELECT case_id, mail_address, nickname, content, send_time, reply_status, emp_no "
							 + "FROM customer ORDER BY case_id DESC";
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


//	⑦ read 依回覆狀態查詢客戶問題(後)------------------------
	@Override
	public List<CustomerVO> getAllByStatus(Integer replyStatus) {
		final String GET_REPLY_STATUS = "SELECT case_id, mail_address, nickname, content, send_time, reply_status, emp_no "
				   + "FROM customer WHERE reply_status = ?";
		con = JDBCConnection.getRDSConnection();
		List<CustomerVO> list = new ArrayList<CustomerVO>();
		
		if (con != null) {
		try {
			PreparedStatement pstmt = con.prepareStatement(GET_REPLY_STATUS);
			int index = 1;
			pstmt.setInt(index, replyStatus);
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

//	⑧ read 依關鍵字查詢客戶問題(後)------------------------	
	@Override
	public List<CustomerVO> getAllByKeyword(String keyword) {
		final String GET_KEYWORD = "SELECT case_id, mail_address, nickname, content, send_time, reply_status, emp_no "
				   + "FROM customer WHERE content LIKE ?";
		con = JDBCConnection.getRDSConnection();
		List<CustomerVO> list = new ArrayList<CustomerVO>();
		
		if (con != null) {
		try {
			PreparedStatement pstmt = con.prepareStatement(GET_KEYWORD);
			int index = 1;
			pstmt.setString(index, "%"+keyword+"%");
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
