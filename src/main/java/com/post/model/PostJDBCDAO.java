package com.post.model;

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

public class PostJDBCDAO implements PostDAO_interface {
	
	@Override
	public List<PostVO> getAll() {
		List<PostVO> list = new ArrayList<PostVO>();
		PostVO postVO =null;
		
		String sql= "select post_id, member_id, content, like_count, status, authority, create_time, update_time from post";
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				postVO = new PostVO();
				postVO.setPostId(rs.getInt("post_id"));
				postVO.setMemberId(rs.getInt("member_id"));
				postVO.setContent(rs.getString("content"));
				postVO.setLikeCount(rs.getInt("like_count"));
				postVO.setStatus(rs.getInt("status"));
				postVO.setAuthority(rs.getInt("authority"));
				postVO.setCreateTime(rs.getDate("create_time"));
				postVO.setUpdateTime(rs.getDate("update_time"));
				
				list.add(postVO);	
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
	public void insert(PostVO postVO) {
		String sql ="insert into post (member_id, content, like_count, status, authority) "
				+ "values (?, ?, ?, ?, ? )";
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setInt(1, postVO.getMemberId());
			pstmt.setString(2, postVO.getContent());
			pstmt.setInt(3, postVO.getLikeCount());
			pstmt.setInt(4, postVO.getStatus());
			pstmt.setInt(5, postVO.getAuthority());
			
			//pstmt.executeUpdate();
			
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
	public void update(PostVO postVO) {
		
		String sql = "update post set member_id = ?, content = ?, like_count = ?, authority = ? "
				+ "WHERE (post_id = ?)";
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, postVO.getMemberId());
			pstmt.setString(2, postVO.getContent());
			pstmt.setInt(3, postVO.getLikeCount());
			pstmt.setInt(4, postVO.getAuthority());
			pstmt.setInt(5, postVO.getPostId());
			
			pstmt.executeUpdate();
			
			int row = pstmt.executeUpdate();
			
			System.out.println(row + " updated successfully !!");	

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(Integer postId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PostVO findByPrimaryKey(Integer postId) {
		
		String sql= "select post_id, member_id, content, like_count, status, authority, create_time, update_time from post where post_id = ? ";
		
		PostVO postVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, postId);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				postVO = new PostVO();
				postVO.setPostId(rs.getInt("post_id"));
				postVO.setMemberId(rs.getInt("member_id"));
				postVO.setContent(rs.getString("content"));
				postVO.setLikeCount(rs.getInt("like_count"));
				postVO.setStatus(rs.getInt("status"));
				postVO.setAuthority(rs.getInt("authority"));
				postVO.setCreateTime(rs.getDate("create_time"));
				postVO.setUpdateTime(rs.getDate("update_time"));
			}
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return postVO;	
	}

}
