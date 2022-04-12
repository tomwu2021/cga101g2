package com.post_pic.model;

import static common.Common.PASSWORD;
import static common.Common.URL;
import static common.Common.USERNAME;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Post_PicJDBCDAO implements Post_PicDAO_interface {
	
	private static final String INSERT=
			"insert into post_pic (post_id) VALUES (?)";
	private static final String GET_ALL=
			"select picture_id, post_id from post_pic";
	private static final String UPDATE=
			"";
	private static final String DELETE=
			"";
	private static final String GET_ONE=
			"";

	@Override
	public void insert(Post_PicVO post_picVO) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = connection.prepareStatement(INSERT);
			
			pstmt.setInt(1, post_picVO.getPostId());
			
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
	public void update(Post_PicVO post_picVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer pictureId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Post_PicVO findByPrimaryKey(Integer pictureId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post_PicVO> getAll() {
		List<Post_PicVO> list = new ArrayList<Post_PicVO>();
		Post_PicVO post_picVO = null;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = connection.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
					
			while(rs.next()) {
				post_picVO = new Post_PicVO();
				
				post_picVO.setPictureId(rs.getInt("picture_id"));
				post_picVO.setPostId(rs.getInt("post_id"));
				
				list.add(post_picVO);
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
}
