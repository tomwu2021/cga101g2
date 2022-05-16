package com.likelist.model;


import static connection.JNDIConnection.getRDSConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.JDBCConnection;

public class LikelistJDBCDAO implements LikelistDAO_interface {

	Connection con;
	
	@Override
	public LikelistVO insert(LikelistVO likelistVO) {
		con = JDBCConnection.getRDSConnection();
		LikelistVO likelistVO2 = insert(likelistVO, con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return likelistVO2;
	}
	
	public LikelistVO insert(LikelistVO likelistVO, Connection con) {
		final String INSERT ="insert into likelist (post_id , member_id) values (?, ?)";
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, likelistVO.getPostId());
				pstmt.setInt(2, likelistVO.getMemberId());
				pstmt.executeUpdate();
				
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					likelistVO.setPostId(rs.getInt(1));
				}
				return likelistVO;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
		
	}

	@Override
	public boolean delete(LikelistVO likelistVO) {
		int rowCount = 0;
		final String DELETE = "DELETE FROM cga_02.post WHERE post_id = ? AND member_id =?";

		try (Connection con = getRDSConnection(); PreparedStatement pstmt = con.prepareStatement(DELETE)) {

			pstmt.setInt(2, likelistVO.getPostId());
			pstmt.setInt(1, likelistVO.getMemberId());

			rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + "row(s) delete!");
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if (rowCount == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public LikelistVO update(LikelistVO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LikelistVO getOneById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LikelistVO> getAll() {
		List<LikelistVO> list = new ArrayList<LikelistVO>();
		
		final String GETAll = "select post_id, member_id from likelist";

		
		try(Connection con =JDBCConnection.getRDSConnection();
			PreparedStatement pstmt = con.prepareStatement(GETAll);
			ResultSet rs = pstmt.executeQuery();){
			
			while(rs.next()) {
				LikelistVO likelistVO = new LikelistVO();
				
				likelistVO.setPostId(rs.getInt("post_id"));
				likelistVO.setMemberId(rs.getInt("member_id"));
				
				list.add(likelistVO);
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
		
	}

	@Override
	public boolean delete(Integer postId, Integer memberId) {
		
		final String DELETE = "delete from likelist where post_id =? and member_id=?";
		
		try(Connection con =JDBCConnection.getRDSConnection();
			PreparedStatement pstmt = con.prepareStatement(DELETE)){
				
				pstmt.setInt(1, postId);
				pstmt.setInt(2, memberId);
				pstmt.executeUpdate();
					
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}	
}

