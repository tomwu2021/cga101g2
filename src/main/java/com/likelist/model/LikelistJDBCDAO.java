package com.likelist.model;

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

public class LikelistJDBCDAO implements LikelistDAO_interface {
	
	private static final String GET_ALL=
			"select post_id, member_id from likelist";
	private static final String INSERT=
			"insert into likelist (post_id , member_id) values (?, ?);";
	private static final String UPDATE=
			"";	
	private static final String DELETE=
			"";
	private static final String GET_ONE=
			"";	
	

	
	@Override
	public List<LikelistVO> getAll() {
		List<LikelistVO> list = new ArrayList<LikelistVO>();
		
		LikelistVO likelistVO = null;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = connection.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				likelistVO = new LikelistVO();
				
				likelistVO.setPostId(rs.getInt("post_id"));
				likelistVO.setMemberId(rs.getInt("member_id"));
				
				list.add(likelistVO);
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
	public void insert(LikelistVO likelistVo) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = connection.prepareStatement(INSERT);
			
			pstmt.setInt(1, likelistVo.getPostId());
			pstmt.setInt(2, likelistVo.getMemberId());
			
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
	public void update(LikelistVO likelistVo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer postId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findByPrimaryLKey(Integer postId) {
		// TODO Auto-generated method stub
		
	}
	
}
