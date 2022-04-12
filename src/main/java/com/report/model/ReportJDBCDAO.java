package com.report.model;

import static common.Common.URL;
import static common.Common.USERNAME;
import static common.Common.PASSWORD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportJDBCDAO implements ReportDAO_interface {
	
	private static final String GET_ALL=
			"select report_id, reporter_id, post_id, report_reason, report_time, status from report";
	private static final String INSERT=
			"insert into report(reporter_id, post_id, report_reason, status) VALUES (?, ?, ?, ?)";
	private static final String UPDATE=
			"";
	private static final String DELETE=
			"";
	private static final String GET_ONE=
			"";

	@Override
	public List<ReportVO> getAll() {
		
		List<ReportVO> list = new ArrayList<ReportVO>();
		ReportVO reportVO = null;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = connection.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
					
			while(rs.next()) {
				reportVO = new ReportVO();
				
				reportVO.setReport_id(rs.getInt("report_id"));
				reportVO.setReporter_id(rs.getInt("reporter_id"));
				reportVO.setPost_id(rs.getInt("post_id"));
				reportVO.setReport_reason(rs.getString("report_reason"));
				reportVO.setReport_time(rs.getDate("report_time"));
				reportVO.setStatus(rs.getInt("status"));
				
				list.add(reportVO);
			}
		}catch(SQLException se) {
			new RuntimeException("A database error occured. " + se.getMessage());
		}finally {
			if(rs !=null) {
				try {
					rs.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
		}
		if(pstmt != null) {
			try {
				pstmt.close();
			}catch(SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if(connection != null) {
			try {
				connection.close();
			}catch(Exception e) {
				e.printStackTrace(System.err);
			}	
		}
		
		return list;
	}

	@Override
	public void insert(ReportVO reportVO) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = connection.prepareStatement(INSERT);
			
			pstmt.setInt(1, reportVO.getReporter_id());
			pstmt.setInt(2, reportVO.getPost_id());
			pstmt.setString(3, reportVO.getReport_reason());
			pstmt.setInt(4, reportVO.getStatus());
			
			int row = pstmt.executeUpdate();
			System.out.println(row + " inserted successfully !!");
			
			
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void update(ReportVO reportVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer report_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ReportVO findByPrimaryKey(Integer report_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}


