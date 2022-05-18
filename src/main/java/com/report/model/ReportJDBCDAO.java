package com.report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.post.model.PostVO;

import connection.JDBCConnection;

public class ReportJDBCDAO implements ReportDAO_interface {

	Connection con;

	@Override
	public ReportVO insert(ReportVO reportVO) {
		con = JDBCConnection.getRDSConnection();
		ReportVO reportVO2 = insert(reportVO, con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reportVO2;
	}

	public ReportVO insert(ReportVO reportVO, Connection con) {
		final String INSERT = "insert into report(reporter_id, post_id, report_reason, status,url) VALUES (?, ?, ?, 0,?)";

		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, reportVO.getReporterId());
				pstmt.setInt(2, reportVO.getPostId());
				pstmt.setString(3, reportVO.getReportReason());
				pstmt.setString(4, reportVO.getUrl());
				pstmt.executeUpdate();

				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					reportVO.setReportId(rs.getInt(1));
				}
				return reportVO;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean delete(ReportVO t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ReportVO update(ReportVO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReportVO getOneById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReportVO> getAll() {
		List<ReportVO> list = new ArrayList<ReportVO>();
		final String GETAll = "select report_id, reporter_id, post_id, report_reason, report_time, status,url from report";

		try (Connection con = JDBCConnection.getRDSConnection();
				PreparedStatement pstmt = con.prepareStatement(GETAll);
				ResultSet rs = pstmt.executeQuery();) {

			while (rs.next()) {
				ReportVO reportVO = new ReportVO();
				reportVO.setReportId(rs.getInt("report_id"));
				reportVO.setReporterId(rs.getInt("reporter_id"));
				reportVO.setPostId(rs.getInt("post_id"));
				reportVO.setReportReason(rs.getString("report_reason"));
				reportVO.setReportTime(rs.getDate("report_time"));
				reportVO.setUrl(rs.getString("url"));
				reportVO.setStatus(rs.getInt("status"));

				list.add(reportVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	@Override
	public boolean updateStatus(Integer reportId,Integer status) {
		int rowCount = 0;
		final String UPDATEDELETE = "update report set status = ? report_id = ?";

		try (Connection con = JDBCConnection.getRDSConnection();
				PreparedStatement pstmt = con.prepareStatement(UPDATEDELETE);) {

			pstmt.setInt(1, status);
			pstmt.setInt(2, reportId);
			rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + "row(s) delete!");

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}

		return true;
	}
}
