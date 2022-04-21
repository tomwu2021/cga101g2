package com.post.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.JDBCConnection;

public class PostJDBCDAO implements PostDAO_interface {
	
	Connection con;
	
	@Override
	public PostVO insert(PostVO postVO) {
		con = JDBCConnection.getRDSConnection();
		PostVO postVO2 = insert(postVO, con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return postVO2;
	}
	
	public PostVO insert(PostVO postVO, Connection con) {
		final String INSERT ="insert into post (member_id, content, like_count, status, authority) values (?, ?, ?, ?, ? ) ";
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, postVO.getMemberId());
				pstmt.setString(2, postVO.getContent());
				pstmt.setInt(3, postVO.getLikeCount());
				pstmt.setInt(4, postVO.getStatus());
				pstmt.setInt(5, postVO.getAuthority());
				pstmt.executeUpdate();
				
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					postVO.setPostId(rs.getInt(1));
				}
				return postVO;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean delete(PostVO t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PostVO update(PostVO postVO) {
		con = JDBCConnection.getRDSConnection();
		PostVO postVO2 = update(postVO, con);
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return postVO2;
	}
	
	public PostVO update(PostVO postVO, Connection con) {
		
		final String UPDATE= "update post set content = ? WHERE (post_id = ?)";
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(UPDATE);

				pstmt.setString(1, postVO.getContent());
				pstmt.setInt(2, postVO.getPostId());
				
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public PostVO getOneById(Integer id) {
		
		final String GETONE ="select post_id, member_id, content, like_count, status, authority, create_time, update_time from post where post_id = ? ";
		
		try(Connection con =JDBCConnection.getRDSConnection();
			PreparedStatement pstmt = con.prepareStatement(GETONE);){
			
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PostVO postVO = new PostVO();
				postVO.setPostId(rs.getInt("post_id"));
				postVO.setMemberId(rs.getInt("member_id"));
				postVO.setContent(rs.getString("content"));
				postVO.setLikeCount(rs.getInt("like_count"));
				postVO.setStatus(rs.getInt("status"));
				postVO.setAuthority(rs.getInt("authority"));
				postVO.setCreateTime(rs.getDate("create_time"));
				postVO.setUpdateTime(rs.getDate("update_time"));
				
				return postVO;
			}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
	}

	@Override
	public List<PostVO> getAll() {
		List<PostVO> list = new ArrayList<PostVO>();
		PostVO postVO =null;
		final String GETAll = "select post_id, member_id, content, like_count, status, authority, create_time, update_time from post";

		
		try(Connection con =JDBCConnection.getRDSConnection();
			PreparedStatement pstmt = con.prepareStatement(GETAll);
			ResultSet rs = pstmt.executeQuery();){
			
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
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<PostVO> selectPost(Integer id) {
		final String SELECT_POST = "select post_id from post where member_id =? order by create_time desc ";
		
		try (Connection con = JDBCConnection.getRDSConnection();
				PreparedStatement pstmt = con.prepareStatement(SELECT_POST);) {

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			List< PostVO> list = new ArrayList<PostVO>();
			
			
			while (rs.next()) {
				PostVO postVO = new PostVO();
				postVO.setPostId(rs.getInt("post_id"));
				postVO.setMemberId(rs.getInt("member_id"));

				list.add(postVO);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<PostVO> selectHotPost() {
		final String SELECT_HOTPOST = "select * from post "
				+ "where DateDiff(curdate(), create_time) <= 7 "
				+ "order by like_count desc "
				+ "limit 0,3";
		
		List< PostVO> list = new ArrayList<PostVO>();
		
		try (Connection con = JDBCConnection.getRDSConnection();
			 PreparedStatement pstmt = con.prepareStatement(SELECT_HOTPOST);
			 ResultSet rs = pstmt.executeQuery();) {

			while (rs.next()) {
				PostVO postVO = new PostVO();
				postVO.setPostId(rs.getInt("post_id"));
				postVO.setContent(rs.getString("content"));
				postVO.setLikeCount(rs.getInt("like_count"));
				postVO.setStatus(rs.getInt("status"));
				postVO.setAuthority(rs.getInt("authority"));
				postVO.setCreateTime(rs.getDate("create_time"));
				postVO.setUpdateTime(rs.getDate("update_time"));

				list.add(postVO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	
	}

	
}
